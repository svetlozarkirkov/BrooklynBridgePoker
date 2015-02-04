


public enum Value {
	
	HighCard(0,"High Card"), Pair(1, "Pair"), TwoPairs(2, "Two Pairs"), TreeOfAKind(3, "Tree of a Kind"), Straight(4, "Straight"), Flush(5, "Flush"),
	FullHouse(6, "Full House"), FourOfAKind(7, "Four of a Kind"), StraightFlush(8, "Straight Flush"), RoyalStraightFlush(9, "Royal Straight Flush");
	
	 private final int code;
	 private final String name;
	 
	 private Value(int c, String s) {
	   code = c;
	   name = s;
	 }
	 
	 public int getCode() {
	   return code;
	 }
	 
	 public String toString(){	 
		return name;
	 }

};   


