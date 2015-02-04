package model;
 
 /*	a deck (of cards).
 *	you can get the amounts of cards in the deck 
 *	But you can only deal the cord on the top
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Card> Cards = new ArrayList<Card>();
	
	/** The constructor fills the deck with 52 cards
	 */
	public Deck(){
		fill();
	}
	
	/** Returns the number of cards in the deck
	 */
	public int getNoOfCars(){
		return Cards.size();
	}
	
	/** Deals out the card on top in the deck
	 */
	public Card dealCard() throws NoSuchCardException{
		if(Cards.size() == 0){
			throw new NoSuchCardException("No cards in Deck to be Dealed");
		}
		else{
			return Cards.remove(0);
		}
	}
	
	/** Fills the deck with 52 new cards
	 */
	public void fill(){
		Cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
            	Cards.add(new Card(rank, suit ));
            }
        }
	}
	
	public String toString(){
		String tempString = "";
		Card tempCard;
		for(int i = 0; i < Cards.size(); i++){
			tempCard = Cards.get(i);
			tempString += tempCard.toString() + "\n";
		}	
		return tempString;
	}
	
	/** Shuffles the deck
	 */
	public void shuffleCards(){
		Collections.shuffle(Cards);
	}
	
	public void sortByRank(){
	    Collections.sort(Cards, new CompareRank());
	}
	
	public void sortBySuit(){
	    Collections.sort(Cards, new CompareSuit());
	}
}
