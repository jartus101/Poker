import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
public class Poker {
    public static void main(String[] args) {
        System.out.print('\u000C'); //used on BlueJ only
        //int icount = 0;
        Deck deck = new Deck(1); //makes new deck of size 1
        Scanner myObj = new Scanner(System.in);
        Player James = new Player("James");
        Player Computer = new Player("Computer");
        boolean turn = false;
        //double betAmount = 0;
        //double pot = 20;
        //double stack = 500;
        ArrayList<Card> tableCards = new ArrayList<Card>();
        ArrayList<Card> playerCards = new ArrayList<Card>();
        ArrayList<Card> computerCards = new ArrayList<Card>();
        computerCards = Player.yourHand(deck);
        playerCards = Player.yourHand(deck);
        for(Card i: playerCards){
            System.out.println(i.print());
        }
        System.out.println("Cards have been dealt.");
        
        String preFlopTurn = myObj.nextLine();
        System.out.println("The flop comes:");
        for(int i = 0; i<3; i++){
            Card card = deck.drawCard();
            tableCards.add(card);
            System.out.println(card.print());
        }
        System.out.println("Computer realvalue: " + playerValue.realValue(computerCards, tableCards, Computer));
        
        String flopTurn = myObj.nextLine(); 
        Card turnCard = deck.drawCard();
        tableCards.add(turnCard);
        System.out.println("The turn comes " + turnCard.print());
        turn = true;
        System.out.println(tableValue.tableRealValue(tableCards));
        System.out.println("Computer realvalue: " + playerValue.realValue(computerCards, tableCards, Computer));
        
        String turnTurn = myObj.nextLine(); 
        Card riverCard = deck.drawCard();
        System.out.println("The river comes " + riverCard.print());
        tableCards.add(riverCard);
        System.out.println("Computer realvalue: " + playerValue.realValue(computerCards, tableCards, Computer));
        
        String riverTurn = myObj.nextLine(); 
       System.out.println(playerValue.realValue(computerCards, tableCards, Computer));
       
        Player.checkHand(playerCards, tableCards, James);
        Player.checkHand(computerCards, tableCards, Computer);
        System.out.println();
        System.out.println("The computer had:");
        for(Card i: computerCards) {
            System.out.println(i.print());
        }
        
        System.out.println(playerValue.potentialValue(computerCards, tableCards, turn));
        if(Computer.getWeight()>James.getWeight()) {
            System.out.println("The computer won");
        } else if(Computer.getWeight()<James.getWeight()){
            System.out.println("You won");
        } else{
            System.out.println("You tied");
        }
        System.out.println(Computer.getStack());
    }
}  
