
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;

public class CPU {
    private String name;    // holds the cpu name

    private int cash;   // holds the cpu cash
    
    private int bet;    // holds the cpu bet
    
    private int cpuHandsWon;    // hands won by the computer player
    
    private int cpuCashWon; // cash won by the computer player
    
    private int difficulty; // difficulty for the cpu (1 - normal, 2 - cheater)
    
    private ArrayList<PlayCard> currentCards;   // holds the cpu hand
    
    private int combRank;
    
    private double cardsXPos;
    
    private double cardsYPos;
          
    public CPU(){   // default constructor
		
	}
    
    public int getCPUCash(){    //gets the cpu cash
	return this.cash;
	}
    public String getCPUName(){ //gets the cpu name
        return this.name;
    }
    public int getCPUBet(){ //gets the bet the cpu made
        return this.bet;
    }
    
    public int getCPUCashWon(){    //gets the amount of cash won during the game
        return this.cpuCashWon;
    }
    
    public int getHandsWon(){   // gets how many times the cpu won the round
        return this.cpuHandsWon;
    }
    
    public int getCPUDifficulty(){
        return this.difficulty;
    }
    
    public ArrayList<PlayCard> getCPUCurrentCards(){    // gets current cpu hand in arraylist
        return this.currentCards;
    }
    
    public double getCardsXPos(){   // gets the X position of the cards
        return this.cardsXPos;
    }
    
    public double getCardsYPos(){   // gets the Y position of the cards
        return this.cardsYPos;
    }
    
    public void setCPUName(String name){    // sets the cpu name
        this.name=name;
    }
    public void setCPUCash(int cash){   //sets the current cash of the cpu
        this.cash+=cash;
    }
    public void setCPUBet(int bet){ //sets the current bet the cpu made
        this.bet+=bet;
        this.cash-=bet;
    }
    
    public void cpuWon(){ // adds a winning hand to the count
        this.cpuHandsWon+=1;
    }
    
    public void cpuWonCash(int winningCashCPU){
        this.cpuCashWon+=winningCashCPU;
    }
    
    public void setCPUDifficulty(int difficultyCPU){
        this.difficulty=difficultyCPU;
    }
    
    public void setCPUCurrentCards(ArrayList<PlayCard> cardsCPU){    // sets current cpu cards
        this.currentCards=cardsCPU;
        if (this.difficulty==2){    // checks if "cheater mode" is active
            for (int i = 0; i < 4; i++){    
                this.currentCards.get(i).unflipCard();  // shows four of cpu cards
            }
        }
    }
    
    public void setCardsXPos(double x){ // sets the X position of the cards
        this.cardsXPos=x;
    }
    
    public void setCardsYPos(double y){ // sets the Y position of the cards
        this.cardsYPos=y;
    }
    
    // Method when the cpu changes some of his cards with new ones
    public void changeCPUCurrentCards(ArrayList<Integer> indexes,ArrayList<PlayCard> currentDeck){
        ArrayList<PlayCard> currentCPUCards = this.currentCards;
        ArrayList<PlayCard> cardsRemoved = new ArrayList(); // list to store removed cards
        for (int i = 0; i < indexes.size();i++){
            int oldCardState = currentCPUCards.get(indexes.indexOf(i)).getCardState();
            cardsRemoved.add(currentCPUCards.get(indexes.indexOf(i))); // stores the removed card into a list
            currentCPUCards.remove(indexes.indexOf(i));    //removes the card from the cpu hand at the specified index
            Random randomCard = new Random();   //random
            int randomIndex = randomCard.nextInt(currentDeck.size());   //getting random index from the cards left in the current deck  
            PlayCard newCard = currentDeck.get(randomIndex);
            if (oldCardState == 0){
                newCard.flipCard();
            }
            else{
                newCard.unflipCard();
            }
            currentCPUCards.add(indexes.indexOf(i), newCard); //adds the new card to the cpu hand
            currentDeck.remove(randomIndex); // removes the card from the current deck
        }
        
        for(PlayCard card: cardsRemoved){   // hides the cards if they were shown
            card.flipCard();
        }
        currentDeck.addAll(cardsRemoved);   // adds removed cards back into the current deck
        this.setCombRank();
    }
    
    public void clearCards(){   // clears the cpu cards before a new round or when folding
        this.currentCards.clear();
    }
    
    public void clearBet(){     // clears the cpu bet before a new round or when folding
        this.bet=0;
    }
    
    public void cpuBetinPot(Pot pot, int bet){
        pot.insertPlayerInPot(this.name, bet);
        setCPUBet(bet);
        setCPUCash(this.cash-=bet);
    }
    
    public void cpuCheck(){
        
    }
    
    public void setCombRank(){
        this.combRank=HandCheck.checkHand(this.getCPUCurrentCards());
    }
    
    public void clearCombRank(){
        this.combRank=0;
    }
    
}
