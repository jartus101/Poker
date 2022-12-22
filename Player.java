import java.util.ArrayList;
import java.util.*;
public class Player {
    private double weight;
    private double stack;
    private String name;
    HashMap<String, Integer> faceCardMap = new HashMap<>();
    public Player(){
        this.weight = 0;
        this.stack = 500;
        this.name = "Player";
    }
    
    public Player(double weight, String name) {
        this.weight = weight;
        this.stack = 500;
        this.name = name;
    }
    public String getPlayerName(){
        return this.name;
    }
    public Player(String name){
        this.weight = 0;
        this.stack = 500;
        this.name = name;
    }
    
    public double getStack(){
        return this.stack;
    }
    
    public void setStack(double i){
        this.stack = i;
    }
    
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(double i) {
        this.weight = i;
    }
    public void addWeight(double i) {
        this.weight += i;
    }
    
    public static ArrayList<Card> yourHand(Deck deck) {
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        ArrayList<Card> deck2 = new ArrayList<Card>();
        deck2.add(card1);
        deck2.add(card2);
        return deck2;
    }
    
    public static double getHighCardVal(ArrayList<Card> playerCards){
        double highCardVal = 0.0;
        for(Card i: playerCards) {
            if((playerCards.get(playerCards.size()-1)).getNumber() == 1) {
                highCardVal = 14/15.0;
            } else {
                highCardVal = playerCards.get(0).getNumber()/15.0;
            }
        }
        return highCardVal;
    }
    
    public static void checkHand(ArrayList<Card> playerCards, ArrayList<Card> tableCards, Player player) {
        String threeKind = "";
        String onePair = "";
        String twoKind = "";
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
                        checker1.add(h);
                    } 
                } 
                onePair = h;
                checker1.add(h);
                singlePair = true;
            } 
            if((threeOfAKind && singlePair) || doubleThreeKind) {
                fullHouse = true;
            } 
        }
        
        Set<String> set = new HashSet<>(checker1);
        checker1.clear();
        checker1.addAll(set);
        ArrayList<Integer> twoPairHelper = new ArrayList<>();
        if(twoPair) {
            for(String i: checker1){
                int j = Card.faceCard3(i);
                twoPairHelper.add(j);
            }
            Collections.sort(twoPairHelper, Collections.reverseOrder());
            twoKind = Card.faceCard2(twoPairHelper.get(0));
            onePair = Card.faceCard2(twoPairHelper.get(1));
        }
        
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
            System.out.println(player.getPlayerName() + " had a royal flush.");
            player.setWeight(10000);
        } else if(straightFlushC) {
            System.out.println(player.getPlayerName() + " had a straight flush from " + straightFlushLC + " to " + straightFlushHC);
            player.setWeight(8 + (Card.faceCard3(straightFlushHC)/15.0));
        } else if(fourOfAKind) {
            System.out.println("You had a four of a kind of " + fourKind + "'s.");
            player.setWeight(7 + (Card.faceCard3(fourKind)/15.0));
        }else if(fullHouse || doubleThreeKind) {
            System.out.println(player.getPlayerName() + " had a pair of " + onePair + "'s, and a three of a kind of " + threeKind + "'s; you had a full house.");
            player.setWeight(6 + (Card.faceCard3(threeKind)/15.0));
        } else if(flushC) {
            System.out.println(player.getPlayerName() + " had a flush of " + flushCS + ".");
            player.setWeight(5 + (flushKickerNum/15.0));
        } else if(straightC) {
            System.out.println(player.getPlayerName() + " had a straight from " + straightCL + " to " + straightCH);
            player.setWeight(4 + (Card.faceCard3(straightCH)/15.0));
        }else if(threeOfAKind) {
            System.out.println(player.getPlayerName() + " had a three of a kind of " + threeKind + "'s.");
            player.setWeight(3 + (Card.faceCard3(threeKind)/15.0));
            player.addWeight(kickerCard);
        } else if(twoPair) {
            System.out.println(player.getPlayerName() + " had a two pair of " + onePair + "'s and " + twoKind + "'s.");
            player.setWeight(2);
            if(twoPairHelper.get(twoPairHelper.size()-1) == 1){
                player.addWeight(14/15.0);
            } else {
                player.addWeight(Card.faceCard3(twoKind)/15.0);
            }
            player.addWeight(kickerCard);
        }else if(singlePair) {
            System.out.println(player.getPlayerName() + " had a pair of " + onePair + "'s.");
            player.setWeight(1 + (Card.faceCard3(onePair)/15.0));
            player.addWeight(kickerCard);
        } else {
            System.out.println(player.getPlayerName() + " had " + Card.faceCard2(highCard3) + " high.");
            if(highCard.get(0) == 1){
                player.setWeight(15/10000.0);
            } else {
                double highCard4 = highCard3/10000.0;
                player.setWeight(highCard4);
            }
        }
    }
}