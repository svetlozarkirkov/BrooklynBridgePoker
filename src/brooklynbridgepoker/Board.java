
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Board {
     
     public ArrayList<PlayCard> deck; // default deck with all 52 cards
     public ArrayList<PlayCard> currentDeck; // deck used each round
     public Player player;   // stores the human player
     public Pot currentPot;  // current pot for the round
     public static int roundsCount; // tracks how many rounds were played
     public String handCombination;
     
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
                                //card.flipCard(); // hides the card initially
                                deck.add(card);
			}
		}
                Collections.shuffle(deck);  // shuffling the deck
		this.currentDeck=deck;
	}
     
     
     
     public void newRound(Board table){
         if (this.player.getPlayerCash() < 100){
             playerBroke();
         }
         newGame(table);
     }
     
     public void addRound(){    // adds a round to the count
         roundsCount++;
     }
     
     public void addPlayer(String name){
         this.player=new Player();
         this.player.setPlayerName(name);
     }
          
     public void playerBroke(){
         System.out.println("GAME OVER");
         
     }
     
     public void playerWinsRound(){
         this.player.playerWon();
         this.player.playerWonCash(this.currentPot.getCurrentPotTotal());
         this.player.setPlayerCash(this.currentPot.getCurrentPotTotal());
         
     }
     
     public void giveCardsToPlayer(){
         ArrayList<PlayCard> cards = new ArrayList();
         Random rnd = new Random();
         int randomIndex = rnd.nextInt(this.currentDeck.size());
         for (int i = 0; i < 5; i++){
             cards.add(this.currentDeck.get(randomIndex));
             this.currentDeck.remove(randomIndex);
             randomIndex = rnd.nextInt(this.currentDeck.size());
         }
         this.player.setPlayerCards(cards);
     }
     
     public void getHandName(){
         this.handCombination=HandCheck.handName;
     }
     
     public static void newGame(Board table){
        table.defaultDeck();
        Collections.shuffle(table.currentDeck); // shuffling again
        if (roundsCount>0){
            table.player.clearCards();   // clearing the human player cards
        table.player.clearBet(); // clears the human player bet
        table.currentPot.clearPot(); // clears the pot
        }
        table.addRound();
        table.giveCardsToPlayer();
        table.player.setCombRank();
        table.getHandName();
        table.player.setPlayerBet(100);
        
     }
}
