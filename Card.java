public class Card {
    private int suit;
    private int number;
    
    public Card(int suit, int number) {
        this.suit = suit;
        this.number = number;
    }
    
    public int getNumber() {
        return this.number;
    }
    public int getSuitNumber() {
        return this.suit;
    }
    
    public String faceCard() {
        if (this.number == 1) {
            return "Ace";
        } else if (this.number <= 10 ) {
            return "" + this.number;
        } else if (this.number == 11) {
            return "Jack";
        } else if (this.number == 12) {
            return "Queen";
        } else if (this.number == 13) {
            return "King";
        } else if (this.number == 14){
            return "Ace";
        } else {
            return "Invalid number";
        }
    }
    
    public static String faceCard2(int number) {
        if (number == 1) {
            return "Ace";
        } else if (number <= 10 ) {
            return "" + number;
        } else if (number == 11) {
            return "Jack";
        } else if (number == 12) {
            return "Queen";
        } else if (number == 13) {
            return "King";
        } else if (number == 14){
            return "Ace";
        } else {
            return "Invalid number";
        }
    }
    
    public static int faceCard3(String number) {
        if (number.equals("Ace")) {
            return 14;
        } else if (number.equals("Jack")) {
            return 11;
        } else if (number.equals("Queen")) {
            return 12;
        } else if (number.equals("King")) {
            return 13;
        }  else if (Integer.valueOf(number)<11) {
            return Integer.valueOf(number);
        }else {
            return 69;
        }
    }
    
    public String suit() {
        if (this.suit == 1) {
            return "Diamonds";
        } else if (this.suit == 2 ) {
            return "Hearts";
        } else if (this.suit == 3) {
            return "Clubs";
        } else if (this.suit == 0) {
            return "Spades";
        } else {
            return "Invalid Suit";
        }
    }
    
    public String suit(int suitNo) {
        if (suitNo == 1) {
            return "Diamonds";
        } else if (suitNo == 2 ) {
            return "Hearts";
        } else if (suitNo == 3) {
            return "Clubs";
        } else if (suitNo == 0) {
            return "Spades";
        } else {
            return "Invalid Suit";
        }
    }
    
    public String color() {
        if (this.suit == 1 || this.suit == 2) {
            return "Red";
        } else if (this.suit == 3 || this.suit == 0) {
            return "Black";
        } else {
            return "Invalid Color";
        }
    }
    
    public String print() {
        return this.faceCard() + " of " + this.suit();
    }
}
