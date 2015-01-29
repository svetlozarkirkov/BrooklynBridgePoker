package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;





public class PokerHands {
	public static void main(String[] args) {
				
		char[] symbols = {'\u2663', '\u2666', '\u2665', '\u2660'};
		String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		ArrayList<String> cards = new ArrayList<String>();
		ArrayList<String> player1Hand = new ArrayList<String>();
		ArrayList<String> player2Hand = new ArrayList<String>();
		ArrayList<String> player3Hand = new ArrayList<String>();
		ArrayList<String> player4Hand = new ArrayList<String>();



		Random random = new Random();
		
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < symbols.length; j++) {
				cards.add(names[i]+symbols[j]);
			}
		}
		
		int randomNumber = random.nextInt(cards.size() + 0);
		
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
		
		for (String card : player1Hand) {
			System.out.printf("%s ",card);
		}
		
		
	} 
}
