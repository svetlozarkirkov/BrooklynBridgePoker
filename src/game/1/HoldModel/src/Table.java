import java.util.ArrayList;


public class Table {
	
	private Deck gameDeck;
	private ArrayList<Player> Players;
	private ArrayList<Card> board;
	private int pot;
	
	private final int MAXPLAYERS = 4;
	
	public Table(){
		gameDeck = new Deck();
		gameDeck.shuffleCards();
		Players = new ArrayList<Player>();
		board = new ArrayList<Card>();
		pot = 0;
	}
	
	public void run(){
		
		for(int i=0; i < Players.size();i++){
		//waitForPlayerMove(Players.get(i));
		//playerMove(Players.get(i));
		}
		
		firstCardsOnTable();
		
		for(int i=0; i < Players.size();i++){
		//waitForPlayerMove(Players.get(i));
		//playerMove(Players.get(i));
		}
		
		addAditionalCardToTable();
		
		for(int i=0; i < Players.size();i++){
		//waitForPlayerMove(Players.get(i));
		//playerMove(Players.get(i));
		}
		
		addAditionalCardToTable();
		
		//Check for winner
		//reset and start over
	}	
	
	public void newGame(int numberOfComputerplayers, int numberofRealPlayers){
		
		for(int i = 0; i < numberOfComputerplayers;i++){
			Players.add(new Computer("Bob", 600));
		}
		for(int i = 0; i < numberofRealPlayers;i++){
			Players.add(new Human("Bob", 600));
		}
	}
	
	public void addPlayer(){
		if(Players.size() < MAXPLAYERS){
			Players.add(new Player("Teddy", 600));
		}
	}
	
	public void takebets(){	
		for(int i= 0; i<Players.size();i++){
			pot = Players.get(i).getBet();
		}
	}
	
	public Player getPlayer(int index){
		return Players.get(index);
	}
	
	public int getPot(){
		return pot;
	}
	
	public void dealCards(){
		for(int k = 0; k <2;k++){
			for(int i = 0; i < Players.size();i++){
				Players.get(i).addCardToHand(gameDeck.dealCard());
			}
		}
	}

	public void firstCardsOnTable(){
		for(int i = 0; i < 3;i++){
			board.add(gameDeck.dealCard());
		}
	}
	
	public void addAditionalCardToTable(){
		gameDeck.dealCard();
		board.add(gameDeck.dealCard());
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Card> getCardsOnTable(){
		return (ArrayList<Card>) board.clone();
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
}
