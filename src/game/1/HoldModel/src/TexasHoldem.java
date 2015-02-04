

public class TexasHoldem {
	
	Deck gameDeck;
	Player Player1;
	
	public TexasHoldem(){
		gameDeck = new Deck();
		gameDeck.shuffleCards();
		Player1 = new Player("Teddy", 600);
	}

	
	
	
	
}
