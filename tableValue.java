import java.util.ArrayList;
import java.util.*;
public class tableValue {
    public static double tablePotentialValue(ArrayList<Card> tableCards, boolean turn) {
        double tablePotentialValue = 0.0;
        boolean flushDrawPotential1 = false;
        boolean flushDrawPotential2 = false;
        boolean openStraightPotential1 = false;
        boolean openStraightPotential2 = false;
        boolean gutshotStraightPotential1 = false;
        boolean gutshotStraightPotential2 = false;
        boolean gutshotStraightPotential3 = false;
        boolean hasAce = false;
        ArrayList<Card> deck = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();
        for(Card i: tableCards){
            deck.add(i);
        }
        deck.sort(Comparator.comparing(Card::getNumber));
        Collections.reverse(deck);
        
        //potential flushes
        for(Card i: deck) {
            String suit = i.suit();
            suits.add(suit);
        }
        for(String i: suits){
            int suitFrequency = Collections.frequency(suits, i);
            if(suitFrequency == 3) {
                flushDrawPotential1 = true;
            }
            if(suitFrequency == 4) {
                flushDrawPotential2 = true;
            }
        }
        
        //straight boiler plate stuff
        ArrayList<Integer> cardNums = new ArrayList<>();
        for(Card i: deck){
            int cardNum = i.getNumber();
            if(cardNum == 1){
                cardNums.add(14);
            }
            cardNums.add(cardNum);
        }
        Collections.sort(cardNums, Collections.reverseOrder());
        ArrayList<Integer> deltas = new ArrayList<>();
        for(int l = 1; l<(cardNums.size()); l++){
            int m = cardNums.get(l) - cardNums.get(l-1);
            deltas.add(m);
        }
        
        //open straights
        //open straight 1
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
            if(o == 2) {
                openStraightPotential1 = true;
            }
        }
        
        //open straight 2
        int p = 0;
        for(int l = 1; l<(cardNums.size()); l++) {
            int m = cardNums.get(l) - cardNums.get(l-1);
            if(m == -1){
                p++;
            } else if(m == 0) {
                p += 0;
            }else {
                p = 0;
            }
            if(p == 3) {
                openStraightPotential2 = true;
            }
        }
        
        
        //gutshot straight 1
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 1 && deltas.get(l-1)==2){
                gutshotStraightPotential1 = true;
                break;
            }
        }
        
        //gutshot straight 2
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 2 && deltas.get(l-1)==1){
                gutshotStraightPotential1 = true;
                break;
            }
        }
        
        //gutshot straight 3
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 3 && deltas.get(l-1)==1){
                gutshotStraightPotential2 = true;
                break;
            }
        }
        
        //gutshot straight 4
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 1 && deltas.get(l-1)==3){
                gutshotStraightPotential2 = true;
                break;
            }
        }
        
        //gutshot straight 5
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
                gutshotStraightPotential3 = true;
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
                gutshotStraightPotential3 = true;
            }else {
                a = 0;
            }
        }
        
        //gutshot straight 3: 2 in a row, gap of 2, 2 in a row
        //[2, 3, 5, 6, 10]
        int c = 0;
        for(int l = 1; l<(deltas.size()); l++){
            if(deltas.get(l) == 2){
                try {
                    if(deltas.get(l+1)==1 && deltas.get(l-1)==1) {
                        gutshotStraightPotential3 = true;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        ArrayList<Card> playerCards = new ArrayList<>();
        double straightOrAboveChance = playerValue.potentialValue(playerCards, deck, turn);
        
        //percent chances based on head to head; change if add more players
        if(flushDrawPotential1){
            tablePotentialValue += 0.18;
        } 
        if(openStraightPotential2){
            tablePotentialValue += 0.16;
        } 
        if (gutshotStraightPotential3){
            tablePotentialValue += 0.08;
        } 
        if (flushDrawPotential2){
            tablePotentialValue += 0.0367;
        } 
        if (openStraightPotential1){
            tablePotentialValue += 0.0256;
        } 
        if (gutshotStraightPotential2 || gutshotStraightPotential1){
            tablePotentialValue += 0.0128;
        }
        
        return tablePotentialValue;
    }
    public static double tableRealValue(ArrayList<Card> tableCards) {
        double tableRealValue = 0.0;
        
        ArrayList<Card> deck = new ArrayList<>();
        for(Card i: tableCards){
            deck.add(i);
        }
        deck.sort(Comparator.comparing(Card::getNumber));
        Collections.reverse(deck);
        if((deck.get(deck.size()-1)).getNumber() == 1) {
            tableRealValue += 14/15.0+1;
        } else {
            tableRealValue += deck.get(0).getNumber()/15.0 + 1;
        }
        String c1 = "";
        for(Card i: tableCards){
            int numFrequency = Collections.frequency(deck, i.faceCard());
            if(numFrequency >=2){
                if(!c1.equals(i.faceCard())){
                    tableRealValue += 6;
                }
                c1 = i.faceCard();
            }
        }
        
        return tableRealValue;
    }
}