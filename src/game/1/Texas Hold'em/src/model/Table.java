package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Table extends Observable implements Serializable{

	private static String[] botNames = { "BOT Greg", "Bot Bob", "Bot Charlie", "Bot Thomas" };

	private Deck gameDeck;
	private ArrayList<Player> Players;
	private ArrayList<Card> board;
	private int pot;
	private HandEvaluator Evaluator;
	private String activity;
	private int dealer;
	private static final int SMALL_BLIND = 10;
	private static final int BIG_BLIND = 20;
	private ArrayList<Player> saveFile;

	
	public Table(){
		gameDeck = new Deck();
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();
		Players = new ArrayList<Player>();
		board = new ArrayList<Card>();
		Evaluator = new HandEvaluator();
		pot = 0;
		dealer = 0;
	}
	public void addUI(Object gui){
		this.addObserver((Observer) gui);
	}
	
	public void newGame(int numberOfComputerplayers, int numberofRealPlayers, int startCash){
		hardReset();
		int i;
		
		for(i = 0; i < numberofRealPlayers;i++){
			Players.add(new Human("Player "+i, startCash, i));			
		}
		
		for(; i < numberOfComputerplayers+numberofRealPlayers;i++){
			Players.add(new Computer(botNames[i], startCash,i));
		}

		setChanged();
		notifyObservers("NewGame");	 
	}
	
	public void loadGame(ArrayList<Player> saveFile){
		hardReset();

		Players.addAll(saveFile);
		setChanged();
		notifyObservers("NewGame");	 
	}
	
	public void play() {

		while(true){
			try {
				if(Players.size()<=1){
					setChanged();
					notifyObservers("GameEnded");				
					break;
				}
				giveBlinds();
				dealCards();
				takeBets();//Player input
				firstCardsOnTable();
				takeBets();//Player input
				addAditionalCardToTable();
				takeBets();//Player input
				addAditionalCardToTable();
				takeBets();//Player input
				checkForWinner();
				Thread.sleep(5000);
				removeBrokePlayers();
				newRound();
			} catch (InterruptedException e) {
				break;
			}
		}
	}		
	
	private void giveBlinds(){

		if(dealer+2<Players.size()){
			Players.get(dealer+1).setBet(SMALL_BLIND);
			Players.get(dealer+2).setBet(BIG_BLIND);
		}else{
			if(dealer+1<Players.size()){
				Players.get(dealer+1).setBet(SMALL_BLIND);
				Players.get(0).setBet(BIG_BLIND);
			}else{
				Players.get(0).setBet(SMALL_BLIND);
				Players.get(1).setBet(BIG_BLIND);
			}
		}
		
		setChanged();
		notifyObservers("Blinds");
	}
	
	private void changeDealer(){
		if(dealer+1<Players.size()){
			dealer++;
		}
		else{
			dealer=0;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void newRound(){
		gameDeck.fill();
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();
		for(int i=0; i< Players.size();i++){
			Players.get(i).newHand();
		}
		board.clear();
		changeDealer();
		
		saveFile = (ArrayList<Player>) Players.clone();

		
		setChanged();
		notifyObservers("NewRound");
	}


	private void takeBets() throws InterruptedException{
		boolean done = true;
		int rounds = 0;
		int folds=0;
		
		while(true){
			done = true;
			rounds = 0;
			folds=0;


			//Check for active players, if all folded or only one left break;
			for(int i = 0; i < Players.size(); i++){
					if(Players.get(i).getfold()){
						folds++;
					}
			}
			
			if(folds == Players.size()-1){
				break;
			}
			
			for(int i = dealer; i < Players.size(); i++){


				if(!Players.get(i).getfold()){//If the player folded he is not allowed to make a move until next round //and if only one player left he wins

					if(Players.get(i) instanceof Human){

						setChanged();
						notifyObservers("player"+i);

						synchronized(Players.get(i)) {
							while(!Players.get(i).getMoved()) {
								try {
									Players.get(i).wait();
								}
								catch(InterruptedException e){
									throw e;
								}
							}
							Players.get(i).resetmoved();
							setChanged();
							notifyObservers("player"+i+"hide");
						}

					}
					if(Players.get(i) instanceof Computer){
						//Datorn spelar
						Computer temp;
						temp = (Computer) Players.get(i);
						temp.move(this);
						temp = null;
						setChanged();
						notifyObservers("bot"+i);
						Thread.sleep(3000);

					}	
				}
				rounds++;
				if(rounds == Players.size()){
					break;
				}
				
				if(i+1==Players.size()){
					i=-1;
				}
			}

			for(int i = 0; i < Players.size()-1; i++){
				if(Players.get(i).getBet() != Players.get(i+1).getBet()){
					if(Players.get(i).getfold() != Players.get(i+1).getfold()){
					}else if(Players.get(i).getPoints()-Players.get(i).getBet() == 0 || Players.get(i+1).getPoints()-Players.get(i+1).getBet() == 0){	
					}
					else{
						done = false;
					}
				}
			}

			if(done){
				break;
			}
		}
		addBetsToPot();	
	}

	private void checkForWinner(){
		int highestHand = 0;
		int highestHandValue = 0;
		
		for(int i = 0; i < Players.size(); i++){
			if(!Players.get(i).getfold()){
				Evaluator.Evaluate(board,Players.get(i));
			}else{
				Players.get(i).setHandValue(0);
			}
		}
		
		for(int i = 0; i < Players.size(); i++){
			if(Players.get(i).getHandValue() > highestHandValue){
				highestHand = i;
				highestHandValue = Players.get(i).getHandValue();
			}
		}
		Players.get(highestHand).addPoints(pot);

		pot = 0;
		setActivity(Players.get(highestHand).getName()+" Winns with "+Players.get(highestHand).getHandComp().toString());
		
		setChanged();
		notifyObservers("winner");
	}
	
	private void addBetsToPot(){	
		for(int i= 0; i<Players.size();i++){
			if(!Players.get(i).getfold()){
				pot += Players.get(i).getBet();
				Players.get(i).removePoints();
			}
			Players.get(i).resetBet();
		}	//Update pot on screen.
		
	    setChanged();
	    notifyObservers("UpdatePot");
	}
	
	//metods to deal the cards. Funkar
	private void dealCards(){
		for(int k = 0; k <2;k++){
			for(int i = 0; i < Players.size();i++){
				Players.get(i).addCardToHand(gameDeck.dealCard());
			}
		}
		
	    setChanged();
	    notifyObservers("CardsDealed");
	}

	//funkar
	private void firstCardsOnTable(){
		for(int i = 0; i < 3;i++){
			board.add(gameDeck.dealCard());
		}
		setChanged();
		notifyObservers("UpdateCardsOnTable");
	}

	//funkar
	private void addAditionalCardToTable(){
		gameDeck.dealCard();
		board.add(gameDeck.dealCard());
		setChanged();
		notifyObservers("UpdateCardsOnTable");
	}

	//funkar
	private void removeBrokePlayers(){
		for(int i = 0; i < Players.size(); i++){
			if(Players.get(i).getPoints() < 20){
				Players.remove(i);
				setChanged();
				notifyObservers("BrokePlayer"+i);
			}
		}
	}
	
	public int findBiggestBet(){
		int max = 0;
		for(int i = 0; i < Players.size();i++){
			if(Players.get(i).getBet() > max){
				max = Players.get(i).getBet();
			}	
		}
		return max;
	}
	
	public ArrayList<Player> getSaveFile(){
		return saveFile;
	}
	
	public String getActivity(){
		return activity;
	}
	
	public void setActivity(String activity){
		this.activity = activity;
	}
	
	public ArrayList<Player> getPlayers(){
		return Players;
	}
	
	public Player getPlayer(int index){
		return Players.get(index);
	}
	
	public int getPot(){
		return pot;
	}

	public ArrayList<Card> getCardsOnTable(){
		return board;
	}
	
	private void hardReset(){
		gameDeck.fill();;
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();
		gameDeck.shuffleCards();

		Players.clear();
		board.clear();;
		pot = 0;
		activity = " ";
	}

	public String toString(){
		String outPut = "";
		
		for(int i = 0; i < Players.size(); i++){
			outPut += Players.get(i).toString() +"\n";
		}
		
		outPut+="CARDS ON TABLE\n";
		for(int i = 0; i < board.size();i++){
			outPut += board.get(i).toString() + "\n";
		}
		return outPut;
	}
	
	private static final long serialVersionUID = 1L;

}
