

import java.util.ArrayList;
import java.util.Observable;

/** Objects of this class represents 
 *	a player of blackjack
 *	a player have a hand of cards and a set of points
 */

public class Player extends Observable{
	
	private String name;
	private int points;
	private Hand playerHand;
	//private Value HandValue;
	private boolean folded;
	private int bet;
	
	
	/** the constructor creates a new hand ans sets the points to 0
	 */
	public Player(String Name, int Points){
		newHand();
		points = Points;
		name = Name;
	}
	
	public int getBet(){
		return bet;
	}
	public String getName(){
		return name;
	}
	/** Adds a point to the player
	 */
	public void addPoints(){
		points++;
	}	
	
	public void removePoints(){
		points++;
	}	
	
	public void fold(){
		folded = true;
	    setChanged();
	    notifyObservers("Folded");
	}	
	
	public boolean returnfold(){
		return folded;
	}	
	
	/** returns the amount of points the player have
	 */
	public int getPoints(){
		return points;
	}
	
	public ArrayList<Card> getHand(){
		return playerHand.getCards();
	}
	
	/** Gives the player a new hand of cards
	 */	
	public void newHand(){
		playerHand = new Hand();
		folded = false;
	}
	
	/**Adds a card to the hand
	 */
	public void addCardToHand(Card newCard){
		playerHand.addCard(newCard);
	}
	
	/** returns the amount of cards in the hand
	 */
	public int cardsInHand(){
		return playerHand.getNoOfCards();
	}
	
	public String toString(){
		return playerHand.toString();
	}	
}
