


import java.util.Comparator;

public class CompareRank implements Comparator<Card>{

	@Override
	public int compare(Card card1, Card card2) {
		if(card1.getRank().getCode() > card2.getRank().getCode()){
			return 1;
		} else if(card1.getRank().getCode() < card2.getRank().getCode()){
			return -1;
		}
		return 0;	
	}
}
