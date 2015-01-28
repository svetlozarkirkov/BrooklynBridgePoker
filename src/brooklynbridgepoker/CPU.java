
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;

public class CPU {
    private String name;    // holds the cpu name

    private int cash;   // holds the cpu cash
    
    private int bet;    // holds the cpu bet
    
    private ArrayList<PlayCard> currentCards;   // holds the cpu hand
          
    public CPU(){   // default constructor
		
	}
    
    public int getCPUCash(){    //gets the cpu cash
	return cash;
	}
    public String getCPUName(){ //gets the cpu name
        return name;
    }
    public int getCPUBet(){ //gets the bet the cpu made
        return bet;
    }
    
    public ArrayList<PlayCard> getCPUCurrentCards(){    // gets current cpu hand in arraylist
        return currentCards;
    }
    public void setCPUName(String name){    // sets the cpu name
        this.name=name;
    }
    public void setCPUCash(int cash){   //sets the current cash of the cpu
        this.cash=cash;
    }
    public void setCPUBet(int bet){ //sets the current bet the cpu made
        this.bet=bet;
    }
    
    // Method when the cpu changes some of his cards with new ones
    public void changeCPUCurrentCards(ArrayList<PlayCard> currentCPUCards, ArrayList<Integer> indexes,ArrayList<PlayCard> currentDeck){
        for (int i = 0; i < indexes.size();i++){
            currentCPUCards.remove(indexes.indexOf(i));    //removes the card from the cpu hand at the specified index
            Random randomCard = new Random();   //random
            int randomIndex = randomCard.nextInt(currentDeck.size());   //getting random index from the cards left in the current deck
            currentCPUCards.add(indexes.indexOf(i), currentDeck.get(randomIndex)); //adds the new card to the cpu hand
        }
    }
}
