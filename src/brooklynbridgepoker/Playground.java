package brooklynbridgepoker;

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
public class Playground extends JPanel implements ActionListener{
	
	private Image board;
	
	TextField pot = new TextField();
        
        Board table = new Board();
        
	private Object handRates = "Hand Rates Here";
	JFormattedTextField handRatesTopField = new JFormattedTextField(handRates );
	
	Button start = new Button();
	Button exit = new Button();
	Button bet = new Button();
	Button change = new Button();
	Label credit = new Label();
	
	Checkbox checkFirst = new Checkbox();
	Checkbox checkSecond = new Checkbox();
	Checkbox checkThird = new Checkbox();
	Checkbox checkFourth = new Checkbox();
	Checkbox checkFifth = new Checkbox();
	
	public Playground() {
		this.setLayout(null);
		initBoard();
		initPot();
		initActions();
	}
  
	
	public void initActions(){
		bet.setLabel("Bet");
		change.setLabel("Change");
		start.setLabel("New Game");
		exit.setLabel("Exit");
		credit.setText("Credit");
		
		checkFirst.setBounds(260, 330, 10, 10);
		checkSecond.setBounds(320, 330, 10, 10);
		checkThird.setBounds(380, 330, 10, 10);
		checkFourth.setBounds(440, 330, 10, 10);
		checkFifth.setBounds(500, 330, 10, 10);
		
		bet.setBounds(350, 400, 50, 45);
		change.setBounds(410, 400, 50, 45);
		start.setBounds(170, 400, 80, 45);
		exit.setBounds(570, 400, 80, 45);
		handRatesTopField.setBounds(220, 10, 350, 170);
		credit.setBounds(340, 357, 45, 20);
		
		this.add(start);
		this.add(exit);
		this.add(bet);
		this.add(change);
		this.add(handRatesTopField);
		this.add(credit);
		this.add(checkFirst);
		this.add(checkSecond);
		this.add(checkThird);
		this.add(checkFourth);
		this.add(checkFifth);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
                                Board.startNewGame(table);                                
			}
		});
		
		bet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				  System.out.println("Bet");
                                  table.player.setPlayerBet(100);   // sum to bet
			}
		});
		
		change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Change cards");
                            ArrayList<Integer> indexes = new ArrayList(); // this is filled by the checkboxes
                            table.player.changePlayerCards(table.player.getPlayerCurrentCards(),indexes,table.currentDeck);
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
		pot.setText("500");
		pot.setEditable(false);
		pot.setBounds(380, 357, 45, 20);
		this.add(pot);
	}
	

	private void initBoard() {  
        loadImage();
    }
	
	private void loadImage() {
		ImageIcon boardImg = new ImageIcon("image/table2.jpg");
		board = boardImg.getImage();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
	}

	public void startGame(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}