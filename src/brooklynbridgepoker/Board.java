
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Collections;


public class Board {
     
     private static ArrayList<PlayCard> defaultDeck;
     
     public static void setCpuCardsPositions(ArrayList<CPU> cpuPlayers){    // set coordinates for the cpu cards
         
         for (int i = 0; i < cpuPlayers.size(); i++){
             if (i==0){
                 cpuPlayers.get(i).setCardsXPos(0);
                 cpuPlayers.get(i).setCardsYPos(-150);
             }
             else if (i == 1){
                 cpuPlayers.get(i).setCardsXPos(-100);
                 cpuPlayers.get(i).setCardsYPos(-100);
             }
             else if (i == 2){
                 cpuPlayers.get(i).setCardsXPos(100);
                 cpuPlayers.get(i).setCardsYPos(-100);
             }
         }
     }
     
     public static ArrayList<PlayCard> defaultDeck() {   // this is the default deck with all cards with type PlayCard
		String[] faces = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		char[] suits = {'C','D','H','S'};
		int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		ArrayList<PlayCard> deck = new ArrayList();   // the list to hold the cards
		for (int i = 0; i < faces.length;i++){
			for (int j = 0; j < suits.length; j++){
				PlayCard card = new PlayCard();
				card.setRank(ranks[i]);
				card.setSuit(suits[j]);
				card.setFace(faces[i]);
                                card.setImage(faces[i]+suits[j]);
                                deck.add(card);
			}
		}
		for (int x = 0; x < 100; x++){
			Collections.shuffle(deck);  // shuffling the deck 100 times :D
		}
		return defaultDeck;
	}
}
