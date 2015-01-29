package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class PokerHands {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int hands = scan.nextInt();
		char[] symbols = {'\u2663', '\u2666', '\u2665', '\u2660'};
		String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		ArrayList<String> cards = new ArrayList<String>();
		Random random = new Random();
		
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < symbols.length; j++) {
				cards.add(names[i]+symbols[j]);
			}
		}
		
		int randomNumber = random.nextInt(cards.size() + 0);
		
		for (int i = 0; i < hands; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("%s ",cards.get(randomNumber));
				cards.remove(randomNumber);
				randomNumber = random.nextInt(cards.size() +0);
			}			
			System.out.println();
		}
	}
}


