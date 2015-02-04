package model;

import java.io.Serializable;



public enum Value implements Serializable{
	
	HighCard("High Card"), Pair("Pair"), TwoPairs("Two Pairs"), TreeOfAKind("Tree of a Kind"), Straight("Straight"), Flush("Flush"),
	FullHouse("Full House"), FourOfAKind("Four of a Kind"), StraightFlush("Straight Flush"), RoyalStraightFlush("Royal Straight Flush");
	
	 private final String name;
	 
	 private Value(String s) {
	   name = s;
	 }
 
	 public String toString(){	 
		return name;
	 }

};   


