
package brooklynbridgepoker;

import static com.sun.tracing.dtrace.DependencyClass.CPU;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
     
     public ArrayList<PlayCard> deck; // default deck with all 52 cards
     public ArrayList<String> cpuNamesList;  // cpu names generated for this session
     public ArrayList<PlayCard> currentDeck; // deck used each round
     public HumanPlayer human;   // stores the human player
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
         for (int i = 0; i < this.computers.size(); i++){    // clearing the cards and the bet for each computer
             this.computers.get(i).clearBet();
             this.computers.get(i).clearCards();
         }
         this.human.clearHumanCards();   // clearing the human player cards
         this.human.clearHumanBet(); // clears the human player bet
         this.currentPot.clearPot(); // clears the pot
         addRound();
     }
     
     public void addRound(){    // adds a round to the count
         roundsCount++;
     }
     
     public void addHumanPlayer(String name){
         this.human=new HumanPlayer();
         this.human.setHumanPlayerName(name);
     }
          
     public void brokeHuman(){
         
     }
     
     public void humanWinsRound(){
         this.human.humanWon();
         this.human.humanWonCash(this.currentPot.getCurrentPotTotal());
         this.human.setHumanPlayerCash(this.currentPot.getCurrentPotTotal());
         
     }
     
}
