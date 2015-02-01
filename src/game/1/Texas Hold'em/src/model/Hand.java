package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> Cards;
	
	public Hand(){	
		Cards = new ArrayList<Card>();
	}
	
	public int getNoOfCards(){
		return Cards.size();
	}

	public void addCard(Card newCard){
		Cards.add(newCard);
	}
	
	public Card getCard(int i) throws NoSuchCardException{
		if(i <= Cards.size() && i != 0)
			return Cards.get(i-1);
		else
			throw new NoSuchCardException("Not a valid card request:" + i );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Card> getCards(){
		return (ArrayList<Card>)Cards.clone();
	}
	
	public Card removeCard(int i) throws NoSuchCardException{
		if(i <= Cards.size() && i != 0){
			return Cards.remove(i-1);
		}
		else
			throw new NoSuchCardException("Not a valid card request:" + i );
	}	
	
	public void clearHand(){
		Cards.clear();
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
	
	public void sortByRank(){
	    Collections.sort(Cards, new CompareRank());
	}
	
	public void sortBySuit(){
	    Collections.sort(Cards, new CompareSuit());
	}
	
}