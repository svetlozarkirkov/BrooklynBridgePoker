
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer {
    private String name;    // holds the player name

    private int cash;   // holds the player cash
    
    private int bet;    // holds the player bet
    
    private int playerHandsWon;
    
    private int playerCashWon;
    
    private ArrayList<PlayCard> currentCards;   // holds the player hand
    
    public HumanPlayer(){   // default constructor
		
	}
    
    public int getHumanPlayerCash(){    //gets the player cash
	return this.cash;
	}
    public String getHumanPlayerName(){ //gets the player name
        return this.name;
    }
    public int getHumanPlayerBet(){ //gets the bet the player made
        return this.bet;
    }
    
    public int getHumanPlayerHandsWon(){
        return this.playerHandsWon;
    }
    
    public int getHumanPlayerCashWon(){
        return this.playerCashWon;
    }
    
    public ArrayList<PlayCard> getHumanPlayerCurrentCards(){    // gets current player hand in arraylist
        return this.currentCards;
    }
    
    public void setHumanPlayerCards(ArrayList<PlayCard> humanCards){    // gives cards to the human player
        for(PlayCard humanCard: humanCards){
            humanCard.unflipCard();             // shows the cards
        }
        this.currentCards=humanCards;
    }
    
    public void setHumanPlayerName(String name){    // sets the player name
        this.name=name;
    }
    public void setHumanPlayerCash(int cash){   //sets the current cash of the player
        this.cash+=cash;
    }
    public void setHumanPlayerBet(int bet){ //sets the current bet the player made
        this.bet+=bet;
    }
    public void humanWon(){ // adds a winning hand for the player
        this.playerHandsWon+=1;
    }
    public void humanWonCash(int humanWinningCash){ // adds the cash won into the total
        this.playerCashWon+=humanWinningCash;
    }
    
    // Method when the player changes some of his cards with new ones
    public void changeHumanPlayerCards(ArrayList<PlayCard> humanPlayerCards, ArrayList<Integer> indexes,ArrayList<PlayCard> currentDeck){
        ArrayList<PlayCard> cardsRemoved = new ArrayList(); // list to store removed cards
        for (int i = 0; i < indexes.size();i++){
            cardsRemoved.add(humanPlayerCards.get(indexes.indexOf(i))); // stores the removed card into a list
            humanPlayerCards.remove(indexes.indexOf(i));    //removes the card from the player hand
            Random randomCard = new Random();   //random
            int randomIndex = randomCard.nextInt(currentDeck.size());   //getting random index from the cards left in the current deck
            humanPlayerCards.add(indexes.indexOf(i), currentDeck.get(randomIndex)); //adds the new card to the player hand
            currentDeck.remove(randomIndex);    // removes the card from the current deck
        }
        for (PlayCard humanCard: this.currentCards){    // unflips all player cards again (just to be sure)
            humanCard.unflipCard();
        }
        for(PlayCard card: cardsRemoved){   // hides the removed cards if they were shown
            card.flipCard();
        }
        currentDeck.addAll(cardsRemoved); // adds removed cards back into the current deck ( maybe not needed  ??? )
    }
    
    public void clearHumanCards(){  // clears the human player cards before new round or when folding
        this.currentCards.clear();
    }
    
    public void clearHumanBet(){    // clears the human player bet before new round or when folding
        this.bet=0;
    }
}
