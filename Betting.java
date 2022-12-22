import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;
import java.util.*;
public class Betting {
    public static double initialHand(ArrayList<Card> playerCards, double pot) {
        double betAmount = 0.0;
        
        playerCards.sort(Comparator.comparing(Card::getNumber));
        Collections.reverse(playerCards);
        
        if(Collections.frequency(playerCards, playerCards.get(0).getNumber()) > 1){
            if(playerCards.get(0).getNumber() >= 10){
                betAmount = pot*3;
            } else {
                betAmount = pot*2;
            }
        } else if(Math.abs(playerCards.get(0).getNumber() - playerCards.get(1).getNumber()) == 1 && playerCards.get(0).getNumber() >= 6){
            betAmount = pot;
        }else if((playerCards.get(0)).getNumber() >= 10) {
            betAmount = pot/2;
        } else {
            betAmount = 0.0;
        }
        
        return betAmount;
    }
    public static int initialBet(){
        //run logic based on poker theory for how to bet first
        return 25;
    }
    public static void betResponse(int runningBet, Player person, Player computer, int currentPot){
        int betResponse = 25;
        int bet = runningBet;
        Scanner sc = new Scanner(System.in);
        while(bet!=betResponse){
            //run logic based on poker theory for how to bet
            System.out.println("Computer bets " + betResponse);
            System.out.println("Type an integer for your bet. Type 0 to fold");
            bet = sc.nextInt();
            if(bet==0){
                
            }
            if(bet<betResponse){
                System.out.println("Invalid bet. Too low.");
            } else if(bet>betResponse){
                //run more internal logic to figure out the next move
            }
        }
        sc.close();
        computer.setStack(computer.getStack()-bet);
        person.setStack(person.getStack()-bet);
    }
}