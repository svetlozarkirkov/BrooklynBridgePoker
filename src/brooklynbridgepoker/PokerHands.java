package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.ISUB;


public class PokerHands {
	
	public static void main(String[] args) {
		
		//Declaring cards and players hands
		char[] symbols = {'\u2663', '\u2666', '\u2665', '\u2660'};
		String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		
		ArrayList<String> cards = new ArrayList<String>();
		ArrayList<String> player1Hand = new ArrayList<String>();
		ArrayList<String> player2Hand = new ArrayList<String>();
		ArrayList<String> player3Hand = new ArrayList<String>();
		ArrayList<String> player4Hand = new ArrayList<String>();

		Random random = new Random();
		
		//Construct the deck of cards
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < symbols.length; j++) {
				cards.add(names[i]+symbols[j]);
			}
		}
		
		int randomNumber = random.nextInt(cards.size() + 0);
		
		//Instance of CPU1-4 // 5 cards in the hand
		for (int j = 0; j < 5; j++) {
			player1Hand.add(j, cards.get(randomNumber));
			cards.remove(randomNumber);
			randomNumber = random.nextInt(cards.size() +0);
		}	
		for (int j = 0; j < 5; j++) {
			player2Hand.add(j, cards.get(randomNumber));
			cards.remove(randomNumber);
			randomNumber = random.nextInt(cards.size() +0);
		}	
		for (int j = 0; j < 5; j++) {
			player3Hand.add(j, cards.get(randomNumber));
			cards.remove(randomNumber);
			randomNumber = random.nextInt(cards.size() +0);
		}	
		for (int j = 0; j < 5; j++) {
			player4Hand.add(j, cards.get(randomNumber));
			cards.remove(randomNumber);
			randomNumber = random.nextInt(cards.size() +0);
		}
		
		String[] handNames = new String[5];
		String[] handSymbols = new String[5];
		boolean isFlush = false;
		boolean isThreeOfaKind = false;
		boolean isFourOfaKind = false;
		boolean isTwoOfaKind = false;
		//get names for player1
			for (int i = 0; i < 5; i++) {
				handNames[i] = player1Hand.get(i).substring(0, player1Hand.get(i).length() - 1);
			}
		//get ranks for player1
		for (int i = 0; i < 5; i++) {
			handSymbols[i] = player1Hand.get(i).substring(player1Hand.get(i).length() - 1, player1Hand.get(i).length());
		}
		
		//list player1 cards for testing
		for (int i = 0; i < 5; i++) {
			System.out.print(handNames[i]+" ");
			System.out.print(handSymbols[i]+" ");
			System.out.println();
		}
		
		//Flush checking
		if (handSymbols[0].equals(handSymbols[1]) &&
				handSymbols[1].equals(handSymbols[2]) &&
						handSymbols[2].equals(handSymbols[3]) &&
								handSymbols[3].equals(handSymbols[4])) {
			isFlush = true;
		}
		
		if (isFlush) {
			System.out.println("Flush");
		}
		
		//Counting ranks frequency
		int[] count = new int[5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (handNames[i].equals(handNames[j])) {
					count[i] +=1;
				}
			}
		}
		
		//Display ranks frequency
		for (int i = 0; i < 5; i++) {
			System.out.println(handNames[i] + " - " +count[i]);
		}
		
		//Four of a Kind
		for (int i = 0; i < 5; i++) {
			if (count[i] == 4) {
				isFourOfaKind = true;
			}
		}
		
		if (isFourOfaKind) {
			System.out.println("Four of a Kind");
		}
		
		//Three of a Kind
		for (int i = 0; i < 5; i++) {
			if (count[i] == 3) {
				isThreeOfaKind = true;
			}
		}
		
		if (isThreeOfaKind) {
			System.out.println("Three of a Kind");
		}
		
		//Two of a Kind
		for (int i = 0; i < 5; i++) {
			if (count[i] == 2) {
				isTwoOfaKind = true;
			}
		}
		
		if (isTwoOfaKind) {
			System.out.println("Two of a Kind");
		}
		
		
		//Calculate the value of a player hand

		/*
		
		byte countSpades = 0;
		byte countHearts = 0;
		byte countDiamonds = 0;
		byte countClubs = 0;
		String checkNames = null;
		int[] rankDist = null;
		 */
		
		/*
		//Four of a kind
		for (int i = 0; i < 5; i++) {
			
			System.out.println(player1Hand.get(i).trim()); 
		} */
		
	} 

}
