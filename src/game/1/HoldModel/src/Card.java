

import javax.swing.ImageIcon;

/** Objects of this class represents cards in
 *	a deck (of cards).
 *	A card is immutable, i.e. once created its
 *	rank or suit cannot be changed.
 */

public class Card {
	
	/** rank: 1 = Ace, 2 = 2, ...
	 *  suit: 1 = spades, 2 = hearts, ...
	 */		
	
    private final Rank rank;
    private final Suit suit;
    private final ImageIcon image;
	
	public Card(Rank rank, Suit suit, ImageIcon image) {
		this.rank = rank;
		this.suit = suit;
		this.image = image;
	}
	
	public ImageIcon getImage() {
		return image;
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
