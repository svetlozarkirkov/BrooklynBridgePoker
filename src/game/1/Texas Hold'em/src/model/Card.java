package model;


import java.io.Serializable;
		/** Objects of this class represents cards in
		 *	a deck (of cards).
		 *	A card is immutable, i.e. once created its
		 *	rank or suit cannot be changed.
		 */

public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** rank: 1 = Ace, 2 = 2, ...
	 *  suit: 1 = spades, 2 = hearts, ...
	 */		
	
    private final Rank rank;
    private final Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	

	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String toString() {
		return rank + " of " + suit;
	}


	
}
