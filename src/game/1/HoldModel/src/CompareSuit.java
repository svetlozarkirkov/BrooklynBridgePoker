


import java.util.Comparator;


public class CompareSuit implements Comparator<Card>{

	@Override
	public int compare(Card card1, Card card2) {
		if(card1.getSuit().getCode() < card2.getSuit().getCode()){
			return 1;
		} else if(card1.getSuit().getCode() > card2.getSuit().getCode()){
			return -1;
		}
		return 0;
	}
}
