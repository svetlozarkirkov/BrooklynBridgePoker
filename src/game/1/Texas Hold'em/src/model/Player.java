package model;
import java.io.Serializable;
import java.util.ArrayList;

/** Objects of this class represents 
 *	a player of blackjack
 *	a player have a hand of cards and a set of points
 */

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private final int id;
	private int points;
	private String moveAction;
	
	private Hand playerHand;
	
	private Value handComp;
	private int handvalue;
	
	private boolean folded;
	private int bet;
	
	private boolean moved = false;
	
	
	/** the constructor creates a new hand ans sets the points to 0
	 */
	public Player(String Name, int Points, int id){
		playerHand = new Hand();
		points = Points;
		name = Name;
		moveAction = " ";
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setmoveAction(String action){
		moveAction = action;
	}
	
	public String getmoveAction(){
		return moveAction;
	}
	
	
	public void moved(){
		moved = true;
	}
	
	public void resetmoved(){
		moved = false;
	}
	
	public boolean getMoved(){
		return moved;
	}
	
	public int getHandValue(){
		return handvalue;
	}
	
	public void setHandValue(int handvalue){
		this.handvalue =  handvalue;
	}
	
	public Value getHandComp(){
		return handComp;
	}
	
	public void setHandComp(Value HandComp){
		this.handComp =  HandComp;
	}
	
	
	public void setBet(int bet){
		this.bet = bet;
	}
	
	public void resetBet(){
		this.bet = 0;
	}
	
	public int getBet(){
		return bet;
	}
	
	public String getName(){
		return name;
	}
	/** Adds a point to the player
	 */
	public void addPoints(int amount){
		points += amount;
	}	
	
	public void removePoints(){
		points -= bet;
	}	
	
	public int getPoints(){
		return points;
	}
	
	public void fold(){
		folded = true;
	}	
	
	public boolean getfold(){
		return folded;
	}	
	
	/** returns the amount of points the player have
	 */
	
	public ArrayList<Card> getHand(){
		return playerHand.getCards();
	}
	
	/** Gives the player a new hand of cards
	 */	
	public void newHand(){
		playerHand.clearHand();
		folded = false;
		moveAction = " ";
		handComp = null;
		handvalue = 0;
		bet = 0;
		moved = false;
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
