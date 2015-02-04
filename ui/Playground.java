package poker.ui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Playground extends JPanel implements ActionListener {

	private Image board;
	private PlayCard currentCards;
	Image firstCard;
	Image secondCard;
	Image thirdCard;
	Image fourthCard;
	Image fifthCard;
	ArrayList<Image> cardDraw = new ArrayList<>();
	String imgNamePath;
	String cardImgPath;
	Board table = new Board();
	String handName;
	Player cashValue = new Player();
	Pot potValue = new Pot();
	TextField pot = new TextField();
	Button start = new Button();
	Button exit = new Button();
	Button deal = new Button();
	Button change = new Button();
	Label credit = new Label();
	Label playerCash = new Label();
	TextField cash = new TextField();
	Checkbox checkFirst = new Checkbox();
	Checkbox checkSecond = new Checkbox();
	Checkbox checkThird = new Checkbox();
	Checkbox checkFourth = new Checkbox();
	Checkbox checkFifth = new Checkbox();

	// "1 - Jacks or Better \r\n 2 - Two Pairs \r\n 3 - Three Of A Kind \r\n 4 - Straight \r\n 6 - Flush \r\n 9 - Full House \r\n 25 - Four Of A Kind \r\n 50 - Straight Flush \r\n 250 - Royal Flush";
	private Object handRates = "Maybe image here";
	JFormattedTextField handRatesTopField = new JFormattedTextField(handRates);

	public Playground() {
		this.setLayout(null);
		initBoard();
		initActions();
	}

	public void initActions() {
		deal.setLabel("Deal");
		change.setLabel("Change");
		start.setLabel("New Game");
		exit.setLabel("Exit");
		credit.setText("Credit:");
		playerCash.setText("Cash:");

		checkFirst.setBounds(260, 330, 10, 10);
		checkSecond.setBounds(320, 330, 10, 10);
		checkThird.setBounds(380, 330, 10, 10);
		checkFourth.setBounds(440, 330, 10, 10);
		checkFifth.setBounds(500, 330, 10, 10);

		deal.setBounds(350, 400, 50, 45);
		change.setBounds(410, 400, 50, 45);
		start.setBounds(170, 400, 80, 45);
		exit.setBounds(570, 400, 80, 45);
		handRatesTopField.setBounds(220, 10, 350, 170);
		handRatesTopField.setEditable(false);
		credit.setBounds(340, 357, 50, 20);
		playerCash.setBounds(350, 450, 45, 25);
		
		this.add(start);
		this.add(exit);
		this.add(handRatesTopField);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
				initPot();
				initPokerButtons();
				Board.startNewGame(table);
				initCard();
				initCash();
				start.setEnabled(false);
				deal.setEnabled(false);
			}
		});

		deal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deal");
				deal.setEnabled(false);
				initCash();
			}
		});

		change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Change cards");
				boolean firstCrd = checkFirst.getState();
				boolean secondCrd = checkSecond.getState();
				boolean thirdCrd = checkThird.getState();
				boolean fourthCrd = checkFourth.getState();
				boolean fifthCrd = checkFifth.getState();
				
				ArrayList<Integer> indexes = new ArrayList(); // this is filled
																// by the
																// checkboxes
				if (firstCrd == true) indexes.add(0);
				if (secondCrd == true) indexes.add(1);
				if (thirdCrd == true) indexes.add(2);
				if (fourthCrd == true) indexes.add(3);
				if (fifthCrd == true) indexes.add(4);
				table.player.changePlayerCards(
						table.player.getPlayerCurrentCards(), indexes,
						table.currentDeck);
				initCard();
				deal.setEnabled(true);
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void initPot() {
		int value = 0;
		String valueString;
		value = value + potValue.getCurrentPotTotal();
		valueString =Integer.toString(value);
		pot.setText(valueString);
		pot.setEditable(false);
		pot.setBounds(380, 357, 45, 20);
		this.add(pot);
	}
	
	private void initCash() {
		int valueCash = 0;
		String valueCashString;
		valueCash = valueCash + cashValue.getPlayerCash();
		valueCashString = Integer.toString(valueCash);
		cash.setText(valueCashString);
		cash.setEditable(false);
		cash.setBounds(400, 450, 45, 25);
		this.add(cash);
	}

	private void initPokerButtons() {
		this.add(deal);
		this.add(change);
		this.add(credit);
		this.add(playerCash);
		this.add(checkFirst);
		this.add(checkSecond);
		this.add(checkThird);
		this.add(checkFourth);
		this.add(checkFifth);
	}

	private void initBoard() {
		loadImage();
	}

	private void initCard() {
		loadCard();
	}
	
	public void loadImage() {
		ImageIcon boardImg = new ImageIcon("image/table2.jpg");
		board = boardImg.getImage();
	}

	public void loadCard() {
		cardDraw.clear();
		for (int i = 0; i < 5; i++) {
			currentCards = table.player.getPlayerCurrentCards().get(i);
			imgNamePath = currentCards.getFace() + currentCards.getSuit();
			cardImgPath = "image/cards/"+ imgNamePath +".png";
			System.out.println(cardImgPath);
			ImageIcon cardsImg = new ImageIcon(cardImgPath);
			cardDraw.add(cardsImg.getImage());
		}
		firstCard = cardDraw.get(0);
		secondCard = cardDraw.get(1);
		thirdCard= cardDraw.get(2);
		fourthCard = cardDraw.get(3);
		fifthCard = cardDraw.get(4);
		repaint();
		}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
		paintCards(g);
		
	}

	public void paintCards(Graphics g) {
		g.drawImage(firstCard, 230, 230, null);
		g.drawImage(secondCard, 290, 230, null);
		g.drawImage(thirdCard, 350, 230, null);
		g.drawImage(fourthCard, 410, 230, null);
		g.drawImage(fifthCard, 470, 230, null);
	}
	
	public void startGame(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}