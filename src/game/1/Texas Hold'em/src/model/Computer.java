package model;
import java.io.Serializable;
import java.util.ArrayList;

/** Objects of this class represents 
 *	a AI player of the game 
 *	it is a extension of the player object
 *	It works by checking the highest bet and the cards
 *	and the points the player have and calculates what the best choice 
 *	is.
 */

public class Computer extends Player implements Serializable{

	private int highestBet;
	private ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	private HandEvaluator evaluate = new HandEvaluator();

	public Computer(String Name, int Points, int id){
		super(Name, Points, id);	
	}
	
	public void move(Table table){
		highestBet = table.findBiggestBet();
		cardsOnTable.clear();
		cardsOnTable.addAll(table.getCardsOnTable());
		
				//Only the two cards player have been dealed in the beginning of the game.
		if(cardsOnTable.size() == 0){
			checkFirstBettingRound();
		}
		
				//First three cards dealed out on the table
		if(cardsOnTable.size() == 3){
			checkSecondBettingRound();
		}

				//Four cards on the table
		if(cardsOnTable.size() == 4){
			checkThirdBettingRound();
		}

				//Last round
		if(cardsOnTable.size() == 5){
			checkFourthBettingRound();
		}	
	}
	
	private void checkFirstBettingRound(){
		if(highestBet > 40){
			
			if(super.getHand().get(0).getRank().getCode() == super.getHand().get(1).getRank().getCode()){
				computerRise();
			}
			
			else if(super.getHand().get(0).getSuit().getCode() == super.getHand().get(1).getSuit().getCode()){
				computerCheck();
			}
			
			else if(5 > super.getHand().get(0).getRank().getCode() - super.getHand().get(1).getRank().getCode() && -5 < super.getHand().get(0).getRank().getCode() - super.getHand().get(1).getRank().getCode()){
				computerCheck();
			}
			
			else	{
				computerFold();
			}
		}
		else{
			computerCheck();
		}
	}
	private void checkSecondBettingRound(){
		evaluate.Evaluate(cardsOnTable, this);
		
		if(highestBet > 40){
			
			if(super.getHandValue()> 15){
				computerCheck();
			}
			
			else if(super.getHandValue()> 15){
				computerRise();
			}
			
			else{
				computerFold();
			}
		}
		else{
			computerCheck();
		}	
	}
	
	private void checkThirdBettingRound(){
		evaluate.Evaluate(cardsOnTable, this);
		
		if(highestBet > 40){
			
			if(super.getHandValue()> 15){
				computerCheck();
			}
			else if(super.getHandValue()> 15){
				computerRise();
			}
			else{
				computerFold();
			}
		}
		else{
			computerCheck();
		}	
	}
	
	private void checkFourthBettingRound(){
		evaluate.Evaluate(cardsOnTable, this);
		
		if(highestBet > 40){
			if(super.getHandValue()> 15){
				computerCheck();
			}
			
			else if(super.getHandValue()> 15){
				computerRise();
			}
			
			else{
				computerFold();
			}
		}
		else{
			computerCheck();
		}	
	}
	
	private void computerCheck(){
		
		if(super.getPoints()<=highestBet){
			highestBet = super.getPoints();
		}
			super.setBet(highestBet);
			super.setmoveAction("Check");
	}
	
	private void computerFold(){
			super.fold();
			super.setmoveAction("Folded");
	}
	
	private void computerRise(){
		super.setBet(highestBet); //random here 
		super.setmoveAction("Rised");

	}
	

	private static final long serialVersionUID = 1L;

}
