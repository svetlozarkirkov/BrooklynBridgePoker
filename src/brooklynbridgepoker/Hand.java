package brooklynbridgepoker;

import java.util.ArrayList;

public class Hand extends PlayCard{

	private ArrayList<PlayCard> cards;
	
	public Hand(){
		clearHand();
	}

	public void clearHand(){ //clear player hand
		cards = new ArrayList<PlayCard>();
	}

	public ArrayList<PlayCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<PlayCard> cards) {
		this.cards = cards;
	}
	
	public void addCard(PlayCard card){
		cards.add(card);
	}
	
	public int getValue(int value){
		return value;
		
	}
}
