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
	ArrayList<PlayCard> holdingCards = new ArrayList<PlayCard>();
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
	TextField printHandName = new TextField();
	HandCheck getHandRank = new HandCheck();
	Checkbox checkFirst = new Checkbox();
	Checkbox checkSecond = new Checkbox();
	Checkbox checkThird = new Checkbox();
	Checkbox checkFourth = new Checkbox();
	Checkbox checkFifth = new Checkbox();
	boolean countGame = false;
	int potTotal;

	public Playground() {
		this.setLayout(null);
		initBoard();
		initActions();
	}

	public void initActions() {
		deal.setLabel("Deal");
		change.setLabel("Change");
		start.setLabel("New Hand");
		exit.setLabel("Exit");
		credit.setText("Credit:");
		playerCash.setText("Cash:");

		checkFirst.setBounds(260, 330, 13, 13);
		checkSecond.setBounds(320, 330, 13, 13);
		checkThird.setBounds(380, 330, 13, 13);
		checkFourth.setBounds(440, 330, 13, 13);
		checkFifth.setBounds(500, 330, 13, 13);

		deal.setBounds(350, 400, 50, 45);
		change.setBounds(410, 400, 70, 45);
		start.setBounds(170, 400, 80, 45);
		exit.setBounds(570, 400, 80, 45);
		credit.setBounds(330, 357, 50, 20);
		playerCash.setBounds(350, 450, 45, 25);
		
		this.add(start);
		this.add(exit);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
				initPokerButtons();
				Board.startNewGame(table);
				initCard();
				if (!countGame) {
					cashValue.setPlayerCash(1000);
				}
				initCash();
				initPot();
				//cashvalue set player cash
				start.setEnabled(false);
				deal.setEnabled(true);
				change.setEnabled(true);
				initHandName();
				countGame = true;
			}
		});

		deal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deal");
				deal.setEnabled(false);
				change.setEnabled(false);
				start.setEnabled(true);
				initCashDeal();
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
				initHandNameChange();
				change.setEnabled(false);
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
		//
		//potValue.setCurrentPotTotal(0);
		switch (HandCheck.checkHand(holdingCards)) {
		case 2:
			potValue.setCurrentPotTotal(50 * 2);
			break;

		case 4:
			potValue.setCurrentPotTotal(50 * 4);
			break;	
			
		case 6:
			potValue.setCurrentPotTotal(50 * 6);
			break;
			
		case 8:
			potValue.setCurrentPotTotal(50 * 8);
			break;
			
		case 12:
			potValue.setCurrentPotTotal(50 * 12);
			break;
			
		case 18:
			potValue.setCurrentPotTotal(50 * 18);
			break;
		
		case 50:
			potValue.setCurrentPotTotal(50 * 50);
			break;
			
		case 100:
			potValue.setCurrentPotTotal(50 * 100);
			break;
			
		case 500:
			potValue.setCurrentPotTotal(50 * 500);
			break;
			
		default:
			potValue.setCurrentPotTotal(0);
			break;
		}
		potTotal = potValue.getCurrentPotTotal();
		pot.setText(Integer.toString(potTotal));
		pot.setEditable(false);
		pot.setBounds(380, 357, 45, 20);
		this.add(pot);
		potValue.clearPot();

	} 
	
	private void initCash() {
		//cashValue.getPlayerCash();
		cashValue.setPlayerCash(potTotal);
		cashValue.setPlayerBet(50);
		int valueCash = cashValue.getPlayerCash();
		String valueCashString;
		valueCashString = Integer.toString(valueCash);
		cash.setText("$" + valueCashString);
		cash.setEditable(false);
		cash.setBounds(400, 450, 45, 25);
		this.add(cash);
	} 
	
	private void initCashDeal() {
		cashValue.setPlayerCash(potValue.getCurrentPotTotal());
		int valueCashDeal = cashValue.getPlayerCash() + potTotal; //the bug POT+50 is here
		String valueCashString = Integer.toString(valueCashDeal);
		cash.setText("$" + valueCashString);
		cash.setEditable(false);
		cash.setBounds(400, 450, 45, 25);
		this.add(cash);
	}

	private void initHandName() {
		String test = HandCheck.handName;
		printHandName.setText(test);
		printHandName.setEditable(false);
		printHandName.setBounds(340, 180, 120, 40);
		this.add(printHandName);
		this.setFocusable(true);
	}
	
	private void initHandNameChange() {
		printHandName.setText(table.getHandName());
		printHandName.setEditable(false);
		printHandName.setBounds(340, 180, 120, 40);
		this.add(printHandName);
		this.setFocusable(true);
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
	
	
	//Methods for drawing
	private void initBoard() {
		loadImage();
	}

	private void initCard() {
		loadCard();
	}
	
	public void loadImage() {
		ImageIcon boardImg = new ImageIcon("image/board2.png");
		board = boardImg.getImage();
	}

	public void loadCard() {
		holdingCards.clear();
		holdingCards.addAll(table.player.getPlayerCurrentCards());
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
	
	//Drawing
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