import java.util.ArrayList;
import java.util.*;
public class HandCheckers {
    //straight checkers for if it's a straight and for what its high and low values are
    public static boolean straightChecker(ArrayList<Integer> cardNumsStraightFlush) {
        int o = 0;
        int straightHighIndex = 4;
        boolean straight = false;
        String straightHigh = "";
        String straightLow = "";
        for(int l = 1; l<(cardNumsStraightFlush.size()); l++) {
            int m = cardNumsStraightFlush.get(l) - cardNumsStraightFlush.get(l-1);
            if(m == -1){
                o++;
            } else if(m == 0) {
                o += 0;
                straightHighIndex++;
            }else {
                o = 0;
            }
            if(o >= 4) {
                straight = true;
                int straightLowI = cardNumsStraightFlush.get(l);
                int straightHighI = cardNumsStraightFlush.get(l-straightHighIndex);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(cardNumsStraightFlush.get(l));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(cardNumsStraightFlush.get(l-4));
                }
                break;
            }
        }
        return straight;
    }
    public static String straightHigh(ArrayList<Integer> cardNumsStraightFlush) {
        int o = 0;
        int p = 0;
        int r = 0;
        int t = 4;
        int straightHighIndex = 4;
        boolean straight = false;
        String straightHigh = "";
        String straightLow = "";
        for(int l = 1; l<(cardNumsStraightFlush.size()); l++) {
            int m = cardNumsStraightFlush.get(l) - cardNumsStraightFlush.get(l-1);
            if(m == -1){
                o++;
            } else if(m == 0) {
                o += 0;
                straightHighIndex++;
            }else {
                o = 0;
            }
            if(o >= 4) {
                straight = true;
                int straightLowI = cardNumsStraightFlush.get(l);
                int straightHighI = cardNumsStraightFlush.get(l-straightHighIndex);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(cardNumsStraightFlush.get(l));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(cardNumsStraightFlush.get(l-straightHighIndex));
                }
                break;
            }
        }
        return straightHigh;
    }
    public static String straightLow(ArrayList<Integer> cardNumsStraightFlush) {
        int o = 0;
        int p = 0;
        int r = 0;
        int t = 4;
        int straightHighIndex = 4;
        boolean straight = false;
        String straightHigh = "";
        String straightLow = "";
        for(int l = 1; l<(cardNumsStraightFlush.size()); l++) {
            int m = cardNumsStraightFlush.get(l) - cardNumsStraightFlush.get(l-1);
            if(m == -1){
                o++;
            } else if(m == 0) {
                o += 0;
                straightHighIndex++;
            }else {
                o = 0;
            }
            if(o >= 4) {
                straight = true;
                int straightLowI = cardNumsStraightFlush.get(l);
                int straightHighI = cardNumsStraightFlush.get(l-straightHighIndex);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(cardNumsStraightFlush.get(l));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(cardNumsStraightFlush.get(l-4));
                }
                break;
            }
        }
        return straightLow;
    }
    
    //flush checker + suit sayer
    public static boolean flushChecker(ArrayList<Integer> cardNumsStraight, ArrayList<Integer> cardNumsStraightFlush, ArrayList<Integer> straightFlushChecker) {
        boolean flushC = false;
        String flushSuit = "";
        int flushSuit2 = 4;
        for(int i : cardNumsStraight) {
            int suitFrequency = Collections.frequency(cardNumsStraight, i);
            if(suitFrequency >= 5) {
                flushC = true;
                flushSuit2 = i;
                if (flushSuit2 == 1) {
                    flushSuit = "Diamonds";
                } else if (flushSuit2 == 2 ) {
                    flushSuit = "Hearts";
                } else if (flushSuit2 == 3) {
                    flushSuit = "Clubs";
                } else if (flushSuit2 == 0) {
                    flushSuit = "Spades";
                }
                for(int j = 0; j < cardNumsStraight.size(); j++) {
                    if(cardNumsStraight.get(j) == flushSuit2) {
                        int k = cardNumsStraightFlush.get(j);
                        straightFlushChecker.add(k);
                    }
                }
            }
        }
        return flushC;
    }
    public static String flushCheckerSuit(ArrayList<Integer> cardNumsStraight, ArrayList<Integer> cardNumsStraightFlush, ArrayList<Integer> straightFlushChecker) {
        boolean flushC = false;
        String flushSuit = "";
        int flushSuit2 = 4;
        for(int i : cardNumsStraight) {
            int suitFrequency = Collections.frequency(cardNumsStraight, i);
            flushC = false;
            if(suitFrequency >= 5) {
                flushC = true;
                flushSuit2 = i;
                if (flushSuit2 == 1) {
                    flushSuit = "Diamonds";
                } else if (flushSuit2 == 2 ) {
                    flushSuit = "Hearts";
                } else if (flushSuit2 == 3) {
                    flushSuit = "Clubs";
                } else if (flushSuit2 == 0) {
                    flushSuit = "Spades";
                }
                for(int j = 0; j < cardNumsStraight.size(); j++) {
                    if(cardNumsStraight.get(j) == flushSuit2) {
                        int k = cardNumsStraightFlush.get(j);
                        straightFlushChecker.add(k);
                    }
                }
            }
        }
        return flushSuit;
    }
    
    //normal sets
    
    //straight flush checker
    public static boolean straightFlushChecker(ArrayList<Integer> straightFlushChecker){
        int r = 0; 
        boolean straightFlush = false;
        String straightLow = "";
        String straightHigh = "";
        for(int i = 1; i<(straightFlushChecker.size()); i++) {
            if((straightFlushChecker.get(i-1) - straightFlushChecker.get(i)) == 1) {
                r++;
            } else {
                r = 0;
            }
            if(r>=4){
                straightFlush = true;
                int straightLowI = straightFlushChecker.get(i);
                int straightHighI = straightFlushChecker.get(i-4);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(straightFlushChecker.get(i));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(straightFlushChecker.get(i-4));
                }
            }
        }
        return straightFlush;
    }
    public static String straightFlushLC(ArrayList<Integer> straightFlushChecker){
        int r = 0; 
        boolean straightFlush = false;
        String straightLow = "";
        String straightHigh = "";
        for(int i = 1; i<(straightFlushChecker.size()); i++) {
            if((straightFlushChecker.get(i-1) - straightFlushChecker.get(i)) == 1) {
                r++;
            } else {
                r = 0;
            }
            if(r>=4){
                straightFlush = true;
                int straightLowI = straightFlushChecker.get(i);
                int straightHighI = straightFlushChecker.get(i-4);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(straightFlushChecker.get(i));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(straightFlushChecker.get(i-4));
                }
            }
        }
        return straightLow;
    }
    public static String straightFlushHC(ArrayList<Integer> straightFlushChecker){
        int r = 0; 
        boolean straightFlush = false;
        String straightLow = "";
        String straightHigh = "";
        for(int i = 1; i<(straightFlushChecker.size()); i++) {
            if((straightFlushChecker.get(i-1) - straightFlushChecker.get(i)) == 1) {
                r++;
            } else {
                r = 0;
            }
            if(r>=4){
                straightFlush = true;
                int straightLowI = straightFlushChecker.get(i);
                int straightHighI = straightFlushChecker.get(i-4);
                if(straightLowI == 1) {
                    straightLow = "Ace";
                } else {
                    straightLow = String.valueOf(straightFlushChecker.get(i));
                }
                if(straightHighI == 14) {
                    straightHigh = "Ace";
                } else if(straightHighI == 13) {
                    straightHigh = "King";
                } else if(straightHighI == 12) {
                    straightHigh = "Queen";
                } else if(straightHighI == 11) {
                    straightHigh = "Jack";
                } else {
                    straightHigh = String.valueOf(straightFlushChecker.get(i-4));
                }
            }
        }
        return straightHigh;
    }
}
