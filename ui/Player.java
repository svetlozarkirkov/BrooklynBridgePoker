package poker.ui;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;    // holds the player name

    private int cash;   // holds the player cash
    
    private int bet;    // holds the player bet
    
    private int playerHandsWon; //Rounds won by the player ! count when handValue is something
    
    private int playerCashWon; //The amount of cash won by the player
    
    private int combRank;
    
    private ArrayList<PlayCard> currentCards;   // holds the player hand
    
    public Player(){   // default constructor
		
	}
    
    //Getters
	public String getPlayerName() { // gets the player name
		return this.name;
	}
    
	public int getPlayerCash() { // gets the player cash
		return this.cash;
	}

	public int getPlayerBet() { // gets the bet the player made
		return this.bet;
	}

	public int getPlayerHandsWon() {
		return this.playerHandsWon;
	}

	public int getPlayerCashWon() {
		return this.playerCashWon;
	}

	public ArrayList<PlayCard> getPlayerCurrentCards() { // gets current player
															// hand in arraylist
		return this.currentCards;
	}

	//Setters
    public void setPlayerName(String name){    // sets the player name
        this.name = name;
    }
    
    public void setPlayerCash(int cash){   //sets the current cash of the player
        this.cash += cash;
    }
    
    public void setPlayerBet(int bet){ //sets the current bet the player made
        this.bet += bet;
        this.cash -= bet;
    }
    
    public void playerWon(){ // adds a winning hand for the player
        this.playerHandsWon += 1;
    }
    public void playerWonCash(int humanWinningCash){ // adds the cash won into the total
        this.playerCashWon += humanWinningCash;
    }
    
    public void setPlayerCards(ArrayList<PlayCard> playerCards){    // gives cards to the human player
        /*
    	for(PlayCard playerCard: playerCards){
            playerCard.unflipCard();             // shows the cards
        } */
        this.currentCards = playerCards;
    }
    
    public void clearCards(){  // clears the human player cards before new round or when folding
        this.currentCards.clear();
    }
    
    public void clearBet(){    // clears the human player bet before new round or when folding
        this.bet = 0;
    }
    
    //Getting player bet, adding to the pot and update the player cash amount after bet
    public void playerBet(Pot pot, int bet){
        //pot.setCurrentPotTotal(bet);
        setPlayerBet(bet);
        setPlayerCash(this.cash -= bet);
    }
    
    public void setCombRank(){
        this.combRank = HandCheck.checkHand(this.getPlayerCurrentCards());
    }
    
    public void clearCombRank(){
        this.combRank = 0;
    }
    
	// Method when the player changes some of his cards with new ones
	public void changePlayerCards(ArrayList<PlayCard> playerCards,
			ArrayList<Integer> indexes, ArrayList<PlayCard> currentDeck) {
		
		ArrayList<PlayCard> cardsRemoved = new ArrayList(); // list to store
															// removed cards
		for (int i = 0; i < indexes.size(); i++) {
			cardsRemoved.add(playerCards.get(indexes.get(i))); // stores the
																	// removed
																	// card into
																	// a list
			//playerCards.remove(indexes.get(i)); // removes the card from the
													// player hand
			Random randomCard = new Random(); // random
			int randomIndex = randomCard.nextInt(currentDeck.size()); // getting
																		// random
																		// index
																		// from
																		// the
																		// cards
																		// left
																		// in
																		// the
																		// current
																		// deck
			playerCards.set(indexes.get(i), currentDeck.get(randomIndex)); // adds
																			// the
																				// new
																				// card
																				// to
																				// the
																				// player
																				// hand
			currentDeck.remove(randomIndex); // removes the card from the
												// current deck
		}
		/*
		for (PlayCard playerCard : this.currentCards) { // unflips all player
														// cards again (just to
														// be sure)
			playerCard.unflipCard();
		}
		for (PlayCard card : cardsRemoved) { // hides the removed cards if they
												// were shown
			card.flipCard();
		} */
		currentDeck.addAll(cardsRemoved); // adds removed cards back into the
											// current deck ( maybe not needed
											// ??? )
		this.setCombRank();
	}
}
