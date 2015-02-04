import java.util.ArrayList;

import javax.swing.ImageIcon;


public class mainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Table game = new Table();
		//game.newGame(0, 4);
		ImageIcon CARD_BACK= new ImageIcon("images/card_back.png");

		Deck deck = new Deck();
		deck.shuffleCards();
		ArrayList<Card> cardsondeck = new ArrayList<Card>();
		Player player1 = new Player("Teddy", 600);
		HandEvaluator Evaluator = new HandEvaluator();
		
		
		ArrayList<Card> TestDeck = new ArrayList<Card>();

		TestDeck.add(new Card(Rank.Nine,Suit.Spades,CARD_BACK));
		TestDeck.add(new Card(Rank.Three,Suit.Spades,CARD_BACK));
		TestDeck.add(new Card(Rank.Four,Suit.Spades,CARD_BACK));
		TestDeck.add(new Card(Rank.Five,Suit.Spades,CARD_BACK));
		TestDeck.add(new Card(Rank.Six,Suit.Spades,CARD_BACK));

		player1.addCardToHand(new Card(Rank.Nine,Suit.Hearts,CARD_BACK));
		player1.addCardToHand(new Card(Rank.Nine,Suit.Clubs,CARD_BACK));

		System.out.print(TestDeck.toString()+"\n\n");
		System.out.print(player1.toString());

		
		Evaluator.Evaluate(TestDeck, player1);
		/*
		
		cardsondeck.add(deck.dealCard());
		cardsondeck.add(deck.dealCard());
		cardsondeck.add(deck.dealCard());
		cardsondeck.add(deck.dealCard());
		cardsondeck.add(deck.dealCard());

		player1.addCardToHand(deck.dealCard());
		player1.addCardToHand(deck.dealCard());

		
		System.out.print(cardsondeck.toString()+"\n\n");
		System.out.print(player1.toString());

		
		Evaluator.Evaluate(cardsondeck, player1);
		*/
	}

}
