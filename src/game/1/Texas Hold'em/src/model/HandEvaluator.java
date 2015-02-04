package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class HandEvaluator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> Cards = new ArrayList<Card>();
	
	public HandEvaluator(){
		
	}
	
	public void Evaluate(ArrayList<Card> CardsOnTable, Player Player){
		Cards.clear();
		Cards.addAll(CardsOnTable);
		Cards.addAll(Player.getHand());
				
		Collections.sort(Cards,new CompareRank());

		boolean straightFound = false;
		boolean trissFound = false;
		boolean pairFound = false;
		int handValue = 0;
		Value handComp = null;
		int tempValue = 0;
				
		handValue = Cards.get(Cards.size()-1).getRank().getCode();
		handComp = Value.HighCard;
		tempValue = findPair();
		if(0<tempValue){
			pairFound = true;
			if(handValue<tempValue){handValue = tempValue; handComp = Value.Pair;}
			tempValue = findTwoPairs();
			if(handValue<tempValue){handValue = tempValue; handComp = Value.TwoPairs;}
		}
		tempValue = findTriss();
		if(0<tempValue){
			if(handValue<tempValue){handValue = tempValue; handComp = Value.TreeOfAKind;}
			trissFound = true;
		}
		tempValue = findStraight();
		if(0<tempValue){
			if(handValue<tempValue){handValue = tempValue; handComp = Value.Straight;}
			straightFound = true;
		}

		tempValue = findFlush();
		if(handValue<tempValue){handValue = tempValue; handComp = Value.Flush;}
		
		//If a triss wasen't found there can't be a full house
		if(trissFound){
			tempValue = findFullHouse();
			if(handValue<tempValue){handValue = tempValue; handComp = Value.FullHouse;}
		}

		//If a pair wasen't found there can't be four of a kind
		if(pairFound){
			tempValue = findFourOfaKind();
			if(handValue<tempValue){handValue = tempValue; handComp = Value.FourOfAKind;}
		}

		//If a straight wasen't found there can't be a straight or royal straight flush
		if(straightFound){
			tempValue = findStraightFlush();
			if(handValue<tempValue){handValue = tempValue; handComp = Value.StraightFlush;}
			tempValue = findRoyalStraightFlush();
			if(handValue<tempValue){handValue = tempValue; handComp = Value.RoyalStraightFlush;}
		}

		Player.setHandComp(handComp);
		Player.setHandValue(handValue);
		
		//handValue;
	}
	
	private int findPair(){
		int highestPair =0;
		
		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				highestPair = Cards.get(i).getRank().getCode();
				i++;
			}
		}
		if(highestPair == 0)
			return 0;
		return highestPair+14*1;
	}
	
	private int findTwoPairs(){

		ArrayList<Card> Pairs = new ArrayList<Card>();
			
		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				Pairs.add(Cards.get(i));
				i++;
			}
		}
		
		if(Pairs.size() >= 2){
			return Cards.get(Pairs.size()-1).getRank().getCode()+14*2;
		}	
		
		return 0;
	}

	private int findTriss(){
		int highestTriss = 0;
		
		for(int i = 0; i < Cards.size()-2;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				if(Cards.get(i+1).getRank().getCode() == Cards.get(i+2).getRank().getCode()){
					highestTriss = Cards.get(i).getRank().getCode();
				}			
			}
		}
		
		if(highestTriss == 0)
			return 0;
		return highestTriss+14*3;
	}

	private int findStraight(){
		int highestStraight = 0;
		int ladder = 0;
		int target;
		
		for(int k = 0; k < Cards.size() -1;k++){		
			target = Cards.get(k).getRank().getCode()+1;
			for(int i = 1; i < Cards.size();i++){
				if(target == Cards.get(i).getRank().getCode()){
					ladder++;
					highestStraight = Cards.get(i).getRank().getCode();
					target = Cards.get(i).getRank().getCode()+1;
				}			
			}

			if(ladder >= 4)
				return highestStraight+14*4;
			else
				ladder = 0;
			

		}
		return 0;
	}
	
	private int findFlush(){
		int highestFlush = 0;
		
		Collections.sort(Cards,new CompareSuit());
		
		for(int i = 0; i < Cards.size()-4;i++){
			if(Cards.get(i).getSuit().getCode() == (Cards.get(i+1).getSuit().getCode())){
				if(Cards.get(i).getSuit().getCode() == Cards.get(i+2).getSuit().getCode()){
					if(Cards.get(i).getSuit().getCode() == Cards.get(i+3).getSuit().getCode()){
						if(Cards.get(i).getSuit().getCode() == Cards.get(i+4).getSuit().getCode()){
							highestFlush = Cards.get(i).getRank().getCode();
						}	
					}	
				}			
			}
		}
		Collections.sort(Cards,new CompareRank());

		if(highestFlush == 0)
			return 0;
		return highestFlush+14*5;
	}

	private int findFullHouse(){
		int triss = 0;
		int pair = 0;

		for(int i = 0; i < Cards.size()-2;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				if(Cards.get(i+1).getRank().getCode() == Cards.get(i+2).getRank().getCode()){
					triss = Cards.get(i).getRank().getCode();
				}			
			}
		}

		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				if(Cards.get(i).getRank().getCode() != triss){
					pair = Cards.get(i).getRank().getCode();
					i++;
				}
			}
		}


		if(pair > 0)
			return triss+14*6;
		return 0;
	}
	
	private int findFourOfaKind(){
		int rank = 0;
		
		for(int i = 0; i < Cards.size()-3;i++){
			if(Cards.get(i).getRank().getCode() == (Cards.get(i+1).getRank().getCode())){
				if(Cards.get(i).getRank().getCode() == Cards.get(i+2).getRank().getCode()){
					if(Cards.get(i).getRank().getCode() == Cards.get(i+3).getRank().getCode()){
						rank = Cards.get(i).getRank().getCode();
					}	
				}			
			}
		}

		if(rank == 0)
			return 0;
		return rank+14*7;
	}

	private int findStraightFlush(){
		int highestStraight = 0;
		int ladder = 0;
		int target;
		int targetSuit;
		
		for(int k = 0; k < Cards.size() -1;k++){		
			target = Cards.get(k).getRank().getCode()+1;
			targetSuit = Cards.get(k).getSuit().getCode();			
			for(int i = 1; i < Cards.size();i++){
				if(target == Cards.get(i).getRank().getCode() && targetSuit == Cards.get(i).getSuit().getCode()){
					ladder++;
					highestStraight = Cards.get(i).getRank().getCode();
					target = Cards.get(i).getRank().getCode()+1;
				}			
			}
			if(ladder >= 4)
				return highestStraight+14*8;
			else{
				ladder = 0;
			}

		}
		return 0;

	}

	private int findRoyalStraightFlush(){
		int highestStraight = 0;
		int ladder = 0;
		int target;
		int targetSuit;
		
		for(int k = 0; k < Cards.size() -1;k++){		
			target = Cards.get(k).getRank().getCode()+1;
			targetSuit = Cards.get(k).getSuit().getCode();
			if(target == Rank.Jack.getCode()){
				for(int i = 1; i < Cards.size();i++){
					if(target == Cards.get(i).getRank().getCode() && targetSuit == Cards.get(i).getSuit().getCode()){
						ladder++;
						highestStraight = Cards.get(i).getRank().getCode();
						target = Cards.get(i).getRank().getCode()+1;
					}			
				}
				if(ladder >= 4)
					return highestStraight+14*9;
				else{
					ladder = 0;
				}
			}

		}
		return 0;

	}
}
 