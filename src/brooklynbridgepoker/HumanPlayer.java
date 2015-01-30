
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer {
    private String name;    // holds the player name

    private int cash;   // holds the player cash
    
    private int bet;    // holds the player bet
    
    private ArrayList<PlayCard> currentCards;   // holds the player hand
    
    public HumanPlayer(){   // default constructor
		
	}
    
    public int getHumanPlayerCash(){    //gets the player cash
	return cash;
	}
    public String getHumanPlayerName(){ //gets the player name
        return name;
    }
    public int getHumanPlayerBet(){ //gets the bet the player made
        return bet;
    }
    public ArrayList<PlayCard> getHumanPlayerCurrentCards(){    // gets current player hand in arraylist
        return currentCards;
    }
    public void setHumanPlayerName(String name){    // sets the player name
        this.name=name;
    }
    public void setHumanPlayerCash(int cash){   //sets the current cash of the player
        this.cash=cash;
    }
    public void setHumanPlayerBet(int bet){ //sets the current bet the player made
        this.bet=bet;
    }
    
    // Method when the player changes some of his cards with new ones
    public void changeHumanPlayerCards(ArrayList<PlayCard> humanPlayerCards, ArrayList<Integer> indexes,ArrayList<PlayCard> currentDeck){
        for (int i = 0; i < indexes.size();i++){
            humanPlayerCards.remove(indexes.indexOf(i));    //removes the card from the player hand
            Random randomCard = new Random();   //random
            int randomIndex = randomCard.nextInt(currentDeck.size());   //getting random index from the cards left in the current deck
            humanPlayerCards.add(indexes.indexOf(i), currentDeck.get(randomIndex)); //adds the new card to the player hand
        }
    }
    
    public void clearHumanCards(){
        this.currentCards=null;
    }
}
