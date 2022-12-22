import java.util.ArrayList;
import java.util.*;
public class playerValue {
    public static double potentialValue(ArrayList<Card> playerCards, ArrayList<Card> tableCards, boolean turn){
        double potentialValue = 0.0;
        boolean flushDraw = false;
        boolean openStraight = false;
        boolean gutshotStraight = false;
        String flushDrawSuit = "";
        
        ArrayList<Card> deck2 = new ArrayList<Card>();
        ArrayList<String> suits = new ArrayList<>();
        for(Card i: playerCards){
            deck2.add(i);
        }
        for(Card i: tableCards) {
            deck2.add(i);
        }
        deck2.sort(Comparator.comparing(Card::getNumber));
        Collections.reverse(deck2);
        
        //flush draw odds
        for(Card i: deck2) {
            String suit = i.suit();
            suits.add(suit);
        }
        for(String i: suits){
            int suitFrequency = Collections.frequency(suits, i);
            if(suitFrequency == 4) {
                flushDraw = true;
                flushDrawSuit = i;
                break;
            }
        }
        
        //straight boiler plate stuff
        ArrayList<Integer> cardNums = new ArrayList<>();
        for(Card i: deck2){
            int cardNum = i.getNumber();
            if(cardNum == 1){
                cardNums.add(14);
            }
            cardNums.add(cardNum);
        }
        Collections.sort(cardNums, Collections.reverseOrder());
        
        //open straight
        int o = 0;
        for(int l = 1; l<(cardNums.size()); l++) {
            int m = cardNums.get(l) - cardNums.get(l-1);
            if(m == -1){
                o++;
            } else if(m == 0) {
                o += 0;
            }else {
                o = 0;
            }
            if(o >= 3) {
                openStraight = true;
            }
        }
        
        //gutshot straight 1: 3 in a row, then gap of 2
        //[8, 7, 6, 4]
        int b = 0;
        for(int l = 1; l<(cardNums.size()); l++) {
            int m = cardNums.get(l) - cardNums.get(l-1);
            if(m == -1){
                b++;
            } else if(m == 0) {
                b += 0;
            } else if(b==2 && m == -2){
                gutshotStraight = true;
            }else {
                b = 0;
            }
        }
        
        //gutshot straight 2: 1, gap of 2, 3 in a row
        ArrayList<Integer> cardNumsGSS = new ArrayList<Integer>(cardNums);
        Collections.reverse(cardNumsGSS);
        int a = 0;
        for(int l = 1; l<(cardNumsGSS.size()); l++) {
            int m = cardNumsGSS.get(l) - cardNumsGSS.get(l-1);
            if(m == 1){
                a++;
            } else if(m == 0) {
                a += 0;
            } else if(b==2 && m == -2){
                gutshotStraight = true;
            }else {
                a = 0;
            }
        }
        
        //gutshot straight 3: 2 in a row, gap of 2, 2 in a row
        //[2, 3, 5, 6, 10]
        int c = 0;
        ArrayList<Integer> deltas = new ArrayList<>();
        for(int l = 1; l<(cardNumsGSS.size()); l++){
            int m = cardNumsGSS.get(l) - cardNumsGSS.get(l-1);
            deltas.add(m);
        }
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 1){
                try {
                    if(deltas.get(l+1)==2 && deltas.get(l+2)==2) {
                        gutshotStraight = true;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        //potential value setting
        if(gutshotStraight && turn == true){
            potentialValue = 0.08;
        } else if(gutshotStraight && turn == false){
            potentialValue = 0.165;
        }
        
        if(openStraight && turn == true){
            potentialValue = 0.17;
        } else if(openStraight && turn == false){
            potentialValue = 0.32;
        }
        
        if(flushDraw && turn == true){
            potentialValue = 0.20;
        } else if(flushDraw && turn == false){
            potentialValue = 0.34;
        }
        
        if(openStraight && flushDraw && turn == false){
            potentialValue = 0.66;
        } else if (gutshotStraight && flushDraw && turn == false){
            potentialValue = 0.50;
        } else if (openStraight && flushDraw && turn == true) {
            potentialValue = 0.33;
        } else if (openStraight && flushDraw && turn == true) {
            potentialValue = 0.25;
        }
        
        return potentialValue;
    }
    public static double realValue(ArrayList<Card> playerCards, ArrayList<Card> tableCards, Player player){
        int handValue = 0;
        String threeKind = "";
        String onePair = "";
        String onePair2 = "";
        String twoKind = "";
        String flushSuit = "";
        String fourKind = "";
        boolean twoPair = false;
        boolean threeOfAKind = false;
        boolean singlePair = false;
        boolean fullHouse = false;
        boolean fourOfAKind = false;
        boolean hasAce = false;
        ArrayList<String> cardFaces = new ArrayList<String>();
        ArrayList<Integer> cardNums = new ArrayList<Integer>();
        ArrayList<Integer> cardNumsStraight = new ArrayList<Integer>();
        ArrayList<Integer> cardNumsStraightFlush = new ArrayList<Integer>();
        ArrayList<Integer> highCard = new ArrayList<Integer>();
        ArrayList<Integer> straightFlushChecker = new ArrayList<Integer>();
        ArrayList<Card> deck2 = new ArrayList<Card>();
        
        for(Card i: playerCards){
            deck2.add(i);
        }
        for(Card i: tableCards) {
            deck2.add(i);
        }
        
        for(Card i: playerCards) {
            int j = i.getNumber();
            highCard.add(j);
        }
        
        for(Card i: deck2) {
            String cardFace = i.faceCard();
            cardFaces.add(cardFace);
            int cardNum = i.getNumber();
            cardNums.add(cardNum);
            if(cardFace == "Ace") {
                hasAce = true;
            }
        }
        
        Collections.sort(cardNums);
        Set<Integer> setStraight = new HashSet<>(cardNums);
        cardNums.clear();
        cardNums.addAll(setStraight);
        Set<Integer> highCard2 = new HashSet<>(highCard);
        highCard.clear();
        highCard.addAll(highCard2);
        
        deck2.sort(Comparator.comparing(Card::getNumber));
        Collections.reverse(deck2);
        for(int q = 0; q<deck2.size(); q++) {
            Card card2 = deck2.get(q);
            int card2S = card2.getNumber();
            int card2N = card2.getSuitNumber();
            cardNumsStraight.add(card2N);
            cardNumsStraightFlush.add(card2S);
        }
        Collections.reverse(cardNums);
        
        
        //Flush checking
        ArrayList<Integer> flushKicker = new ArrayList<Integer>();
        int flushSuit2 = 0;
        int flushKickerNum = 0;
       boolean flushC = HandCheckers.flushChecker(cardNumsStraight, cardNumsStraightFlush, straightFlushChecker);
       String flushCS = HandCheckers.flushCheckerSuit(cardNumsStraight, cardNumsStraightFlush, straightFlushChecker);
       if(flushC) {
           for(Card i: playerCards){
               if(i.suit().equals(flushCS)){
                   flushKicker.add(i.getNumber());
               }
           }
           Collections.sort(flushKicker, Collections.reverseOrder());
           if(flushKicker.get(flushKicker.size()-1) == 1){
               flushKickerNum = 14;
           } else {
               flushKickerNum = flushKicker.get(0);
           }
       }
       
        //adding a second ace for straights and flushes
        if(hasAce){
            cardNums.add(0, 14);
            Card ace = new Card(flushSuit2, 14);
            deck2.add(0, ace);
        }
        //adding a second ace computer
        
        
        //Straight
        String straightLow = "";
        String straightHigh = "";
        boolean straightC = HandCheckers.straightChecker(cardNumsStraightFlush);
        String straightCH = HandCheckers.straightHigh(cardNumsStraightFlush);
        String straightCL = HandCheckers.straightLow(cardNumsStraightFlush);
       
        //straight checking computer
        
        
        //Straight Flush Checking
       boolean straightFlushC = HandCheckers.straightFlushChecker(straightFlushChecker);
       String straightFlushHC = HandCheckers.straightFlushHC(straightFlushChecker);
       String straightFlushLC = HandCheckers.straightFlushLC(straightFlushChecker);
       
       //straight flush checking computer
       
       
        //Normal sets defining
        boolean doubleThreeKind = false;
        int x = 0;
        ArrayList<String> checker1 = new ArrayList<>();
        ArrayList<String> checker2 = new ArrayList<>();
        for(String h : cardFaces) {
            int numFrequency = Collections.frequency(cardFaces, h);
            if(numFrequency == 4) {
                fourOfAKind = true;
                fourKind = h;
            } 
            if(numFrequency == 3) {
                if(threeOfAKind){
                    if(!checker2.contains(h)) {
                        doubleThreeKind = true;
                        onePair = h;
                        break;
                    }
                }
                threeOfAKind = true;
                threeKind = h;
                checker2.add(threeKind);
            } 
            if(numFrequency == 2) {
                if(singlePair) {
                    if(!checker1.contains(h)){
                        twoPair = true;
                        twoKind = h;
                    } 
                } 
                onePair = h;
                checker1.add(onePair);
                singlePair = true;
            } 
            if((threeOfAKind && singlePair) || doubleThreeKind) {
                fullHouse = true;
            } 
        }
        
        //sets defining computer
        
        
        //telling you what hand you got
        int s = highCard.size();
        int highCard3 = highCard.get(s-1);
        double kickerCard = 0.0;
        if(highCard.get(0) == 1){
            kickerCard = (15/10000.0);
        } else {
            kickerCard = highCard3/10000.0;
        }
        if(straightFlushC && straightFlushHC.equals("Ace")) {
            player.setWeight(9);
        } else if(straightFlushC) {
            player.setWeight(8 + (Card.faceCard3(straightFlushHC)/15.0));
        } else if(fourOfAKind) {
            player.setWeight(7 + (Card.faceCard3(fourKind)/15.0));
        }else if(fullHouse || doubleThreeKind) {
            player.setWeight(6 + (Card.faceCard3(threeKind)/15.0));
        } else if(flushC) {
            player.setWeight(5 + (flushKickerNum/15.0));
        } else if(straightC) {
            player.setWeight(4 + (Card.faceCard3(straightCH)/15.0));
        }else if(threeOfAKind) {
            player.setWeight(3 + (Card.faceCard3(threeKind)/15.0));
            player.addWeight(kickerCard);
        } else if(twoPair) {
            player.setWeight(2);
            if(onePair2.equals("Ace") || twoKind.equals("Ace")){
                player.addWeight(14/15.0);
            }else if(Card.faceCard3(onePair)/15.0 > Card.faceCard3(twoKind)/15.0){
                player.addWeight(Card.faceCard3(onePair)/15.0);
            } else if(Card.faceCard3(onePair)/15.0 < Card.faceCard3(twoKind)/15.0){
                player.addWeight(Card.faceCard3(twoKind)/15.0);
            }
            player.addWeight(kickerCard);
        }else if(singlePair) {
            player.setWeight(1 + (Card.faceCard3(onePair)/15.0));
            player.addWeight(kickerCard);
        } else {
            if(highCard.get(0) == 1){
                player.addWeight(15/10000.0);
            } else {
                double highCard4 = highCard3/10000.0;
                player.addWeight(highCard4);
            }
        }
        
        return player.getWeight();
    }
}
