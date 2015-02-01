
package brooklynbridgepoker;

import static com.sun.tracing.dtrace.DependencyClass.CPU;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
     
     public ArrayList<PlayCard> deck; // default deck with all 52 cards
     public ArrayList<PlayCard> currentDeck; // deck used each round
     public Player player;   // stores the human player
     public Pot currentPot;  // current pot for the round
     public int roundsCount; // tracks how many rounds were played
     
     public Board(){
         
     }
     

     public void defaultDeck() {   // this is the default deck with all cards with type PlayCard
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
                                card.flipCard(); // hides the card initially
                                deck.add(card);
			}
		}
                Collections.shuffle(deck);  // shuffling the deck
		this.deck=deck;
	}
     
     
     
     public void newRound(){
         this.currentDeck.clear();   // clearing the current deck
         defaultDeck();
         this.currentDeck=deck;   // creating new current deck from the default one
         Collections.shuffle(this.deck); // shuffling again
         this.player.clearCards();   // clearing the human player cards
         this.player.clearBet(); // clears the human player bet
         this.currentPot.clearPot(); // clears the pot
         addRound();
     }
     
     public void addRound(){    // adds a round to the count
         roundsCount++;
     }
     
     public void addPlayer(String name){
         this.player=new Player();
         this.player.setPlayerName(name);
     }
          
     public void playerBroke(){
         
     }
     
     public void playerWinsRound(){
         this.player.playerWon();
         this.player.playerWonCash(this.currentPot.getCurrentPotTotal());
         this.player.setPlayerCash(this.currentPot.getCurrentPotTotal());
         
     }
     
}
