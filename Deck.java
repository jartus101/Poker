import java.util.ArrayList;
public class Deck {
    
    private ArrayList<Card> deck;
    public Deck(int numOfDecks) {
        this.deck = deck;
        deck = new ArrayList<Card>();
        for (int i = 0; i < numOfDecks; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 13; k++) {
                    Card card = new Card(j, k);
                    deck.add(card);
                }
            }
        }
    }
    
    public int getSize() {
        return this.deck.size();
    }
    
    public void addCard(int suit, int number) {
        Card card = new Card(suit, number);
        deck.add(card);
    }
    public void addCard(Card card) {
        deck.add(card);
    }
    
    public Card drawCard() {
        int rand = (int)(Math.random() * this.deck.size());
        Card card = this.deck.get(rand);
        this.deck.remove(rand);
        return card;
    }
}
