package poker.ui;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;    // holds the player name

    private int cash;   // holds the player cash
    
    private int bet;    // holds the player bet
    
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
    
    public void setPlayerCards(ArrayList<PlayCard> playerCards){    // gives cards to the human player

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

		currentDeck.addAll(cardsRemoved); // adds removed cards back into the
											// current deck ( maybe not needed
											// ??? )
		this.setCombRank();
	}
}
