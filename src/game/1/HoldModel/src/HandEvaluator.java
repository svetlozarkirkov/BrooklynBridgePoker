

import java.util.ArrayList;
import java.util.Collections;

public class HandEvaluator {
	
	ArrayList<Card> Cards = new ArrayList<Card>();
	
	public HandEvaluator(){
		
	}
	
	public void Evaluate(ArrayList<Card> CardsOnTable, Player Player){
		Cards.addAll(CardsOnTable);
		Cards.addAll(Player.getHand());
		Collections.sort(Cards,new CompareRank());
		//Collections.sort(Cards,new CompareSuit());
		boolean straightFound = false;
		boolean trissFound = false;
		boolean pairFound = false;
		int handValue = 0;
		int tempValue;
		
		System.out.println(Cards.toString());
		
		handValue = Cards.get(Cards.size()-1).getRank().getCode();
		tempValue = findPair();
		
		
		
		System.out.println("Pair " +findPair());
		System.out.println("findTwoPairs " +findTwoPairs());
		System.out.println("findTriss " +findTriss());
		System.out.println("findFlush " +findFlush());
		System.out.println("findFourOfaKind " +findFourOfaKind());
		System.out.println("findStraight " +findStraight());

		//System.out.println("findStraightFlush " +findStraightFlush());

		

		
		
		
		/*
		if(0<tempValue){
			pairFound = true;
			if(handValue<tempValue){handValue = tempValue;}
			tempValue = findTwoPairs();
			if(handValue<tempValue){handValue = tempValue;}
		}
		tempValue = findTriss();
		if(0<tempValue){
			handValue = tempValue;
			trissFound = true;
		}
		tempValue = findStraight();
		if(0<tempValue){
			handValue = tempValue;
			straightFound = true;
		}

		tempValue = findFlush();
		if(handValue<tempValue){handValue = tempValue;}
		
		//If a triss wasen't found there can't be a full house
		if(trissFound){
			tempValue = findFullHouse();
			if(handValue<tempValue){handValue = tempValue;}
		}

		//If a pair wasen't found there can't be four of a kind
		if(pairFound){
			tempValue = findFourOfaKind();
			if(handValue<tempValue){handValue = tempValue;}
		}

		//If a straight wasen't found there can't be a straight or royal straight flush

		if(straightFound){
			tempValue = findStraightFlush();
			if(handValue<tempValue){handValue = tempValue;}
			tempValue = findRoyalStraightFlush();
			if(handValue<tempValue){handValue = tempValue;}
		}

		System.out.print("Hand Value is " + handValue);

		//handValue;
		 * 
		 */
	}
	
		
	//WORKING
	private int findPair(){
		int highestPair =0;
		
		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				highestPair = Cards.get(i).getRank().getCode();
				i++;
			}
		}		
		return highestPair;
	}
	
	//WORKING
	private int findTwoPairs(){

		ArrayList<Card> Pairs = new ArrayList<Card>();
			
		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				Pairs.add(Cards.get(i));
				i++;
			}
		}
		
		if(Pairs.size() >= 2){
			return Cards.get(Pairs.size()-1).getRank().getCode();
		}			
		return 0;
	}

	//WORKING
	private int findTriss(){
		int highestTriss = 0;
		
		for(int i = 0; i < Cards.size()-2;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
				if(Cards.get(i+1).getRank().getCode() == Cards.get(i+2).getRank().getCode()){
					highestTriss = Cards.get(i).getRank().getCode();
				}			
			}
		}
		return highestTriss;
	}

	
	//WORKING
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

		return highestFlush;
	}

	//WORKING
	private int findFullHouse(){
		int triss = 0;
		int pair = 0;
		triss = findTriss();

		if(triss > 0){
			for(int i = 0; i < Cards.size()-1;i++){
				if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()){
					if(Cards.get(i).getRank().getCode() != triss){
						pair = Cards.get(i).getRank().getCode();
						i++;
					}
				}
			}
		}
		if(pair > 0)
			return triss;
		return 0;
	}
	
	//WORKING
	private int findFourOfaKind(){
		
		for(int i = 0; i < Cards.size()-3;i++){
			if(Cards.get(i).getRank().getCode() == (Cards.get(i+1).getRank().getCode())){
				if(Cards.get(i).getRank().getCode() == Cards.get(i+2).getRank().getCode()){
					if(Cards.get(i).getRank().getCode() == Cards.get(i+3).getRank().getCode()){
						return Cards.get(i).getRank().getCode();
					}	
				}			
			}
		}

		return 0;
	}
	
	private int findStraight(){
		int highestStraight = 0;
		int ladder = 0;
		int target;
		
		for(int k = 0; k < Cards.size() -1;k++){
			
			target = Cards.get(k).getRank().getCode()+1;
			
			System.out.println("TArget is " +target);

			for(int i = 1; i < Cards.size();i++){
				if(target == Cards.get(i).getRank().getCode()){
					ladder++;
					highestStraight = Cards.get(i).getRank().getCode();
					System.out.println("Ladder is " + ladder + " "+Cards.get(i).getRank());
					target = Cards.get(i).getRank().getCode()+1;
					System.out.println("TArget is " +target);

				}
				
			}

			if(ladder >= 4)
				return highestStraight;
			else{
				System.out.println("Did not find target" );

				ladder = 0;
			}

		}
		return 0;
	}
	
	
	private int findStraightFlush(){
		
		int highestStraight = 0;
		int ladder = 0;
		int targetRank;
		int targetSuit;

		
		targetRank = Cards.get(0).getRank().getCode()+1;
		targetSuit = Cards.get(0).getSuit().getCode();
		for(int i = 1; i < Cards.size()-1;i++){
			if(targetRank == Cards.get(i).getRank().getCode() ){
				ladder++;
				highestStraight = Cards.get(i).getRank().getCode();
				System.out.println("Ladder is " + ladder + " "+Cards.get(i).getRank());
				targetRank = Cards.get(i).getRank().getCode()+1;
			}
		}
		
		if(ladder >= 4)
			return highestStraight;
		return 0;
		
/*		int highestStraight = 0;
		int ladder = 0;
	
		for(int i = 0; i < Cards.size()-1;i++){
			if(Cards.get(i).getRank().getCode() == Cards.get(i+1).getRank().getCode()-1 && Cards.get(i).getSuit().getCode() == Cards.get(i+1).getSuit().getCode()){
				ladder++;
				highestStraight = Cards.get(i+1).getRank().getCode();
				System.out.println("Ladder is " + ladder + " "+Cards.get(i+1).getRank());
			}
		}
		
		if(ladder >= 4)
			return highestStraight;
		return 0;*/
	}

	
	private int findRoyalStraightFlush(){
		int flush = 0;
		flush = findStraightIndex();
		
		if(Cards.get(flush).getRank().getCode() == Rank.Ten.getCode()){
			if(Cards.get(flush).getSuit().getCode() == (Cards.get(flush+1).getSuit().getCode())){
				if(Cards.get(flush).getSuit().getCode() == Cards.get(flush+2).getSuit().getCode()){
					if(Cards.get(flush).getSuit().getCode() == Cards.get(flush+3).getSuit().getCode()){
						if(Cards.get(flush).getSuit().getCode() == Cards.get(flush+4).getSuit().getCode()){
							if(Cards.get(flush).getSuit().getCode() == Cards.get(flush+4).getSuit().getCode()){
								return Cards.get(flush).getRank().getCode();
							}
						}	
					}	
				}			
			}
		}
		return 0;
	}
	
	private int findStraightIndex(){
		int highestStraightIndex = 0;
	
		for(int i = 0; i < Cards.size()-4;i++){
			if(Cards.get(i).getRank().getCode() == (Cards.get(i+1).getRank().getCode()-1)){
				if(Cards.get(i+1).getRank().getCode() == Cards.get(i+2).getRank().getCode()-1){
					if(Cards.get(i+2).getRank().getCode() == Cards.get(i+3).getRank().getCode()-1){
						if(Cards.get(i+3).getRank().getCode() == Cards.get(i+4).getRank().getCode()-1){
							highestStraightIndex = i;
						}	
					}	
				}			
			}
		}
		return highestStraightIndex;
	}

}
 