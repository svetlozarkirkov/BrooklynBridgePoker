
package brooklynbridgepoker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Board {
     
     public ArrayList<PlayCard> deck; // default deck with all 52 cards
     public ArrayList<String> cpuNamesList;  // cpu names generated for this session
     public ArrayList<PlayCard> currentDeck; // deck used each round
     public ArrayList<CPU> computers;    // stores the computer players
     public HumanPlayer human;   // stores the human player
     public Pot currentPot;  // current pot for the round
     public int roundsCount; // tracks how many rounds were played
     
     public Board(){
         
     }
     
//     public static void setCpuCardsPositions(ArrayList<CPU> cpuPlayers){    // set coordinates for the cpu cards
//         
//         for (int i = 0; i < cpuPlayers.size(); i++){
//             if (i==0){
//                 cpuPlayers.get(i).setCardsXPos(0);
//                 cpuPlayers.get(i).setCardsYPos(-150);
//             }
//             else if (i == 1){
//                 cpuPlayers.get(i).setCardsXPos(-100);
//                 cpuPlayers.get(i).setCardsYPos(-100);
//             }
//             else if (i == 2){
//                 cpuPlayers.get(i).setCardsXPos(100);
//                 cpuPlayers.get(i).setCardsYPos(-100);
//             }
//         }
//     }
     
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
     
     
     public void setCpuNamesList() throws FileNotFoundException, IOException{     // random generation of cpu names
        ArrayList<String> names = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader("src/brooklynbridgepoker/resources/cpunames.txt"));
        String line = reader.readLine();
        while (line != null) {
          names.add(line);
          line = reader.readLine();
        }
        this.cpuNamesList=names;
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
     
     public void addComputerPlayer(){
         CPU bot = new CPU();
         Random rnd = new Random();
         int randomNameIndex = rnd.nextInt(this.cpuNamesList.size());
         bot.setCPUName(this.cpuNamesList.get(randomNameIndex));
         this.computers.add(bot);
         this.cpuNamesList.remove(randomNameIndex);
     }
     
     public void brokeCPU(CPU cpu){
         this.computers.remove(computers.indexOf(cpu));
     }
     
     public void foldCPU(CPU cpu){
         this.currentPot.removePlayerInPot(cpu.getCPUName());
     }
     
     public void foldHuman(){
         this.currentPot.removePlayerInPot(human.getHumanPlayerName());
     }
     
     public void brokeHuman(){
         
     }
     
     public void humanWinsRound(){
         this.human.humanWon();
         this.human.humanWonCash(this.currentPot.getCurrentPotTotal());
         this.human.setHumanPlayerCash(this.currentPot.getCurrentPotTotal());
         
     }
     
     public void cpuWinsRound(CPU cpu){
         this.computers.get(this.computers.indexOf(cpu)).cpuWon();
         this.computers.get(this.computers.indexOf(cpu)).cpuWonCash(this.currentPot.getCurrentPotTotal());
         this.computers.get(this.computers.indexOf(cpu)).setCPUCash(this.currentPot.getCurrentPotTotal());
     }
}
