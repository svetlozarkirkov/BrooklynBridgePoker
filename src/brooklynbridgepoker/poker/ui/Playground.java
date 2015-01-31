package poker.ui;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Playground extends JPanel implements MouseListener{
	
	private Image board;
	Label p1 = new Label();
	Label p2 = new Label();
	Label p3 = new Label();
	Label p4 = new Label();
	Label p5 = new Label();
	
	TextField pot = new TextField();
	
	Button bet = new Button();
	Button check = new Button();
	Button fold = new Button();
	
	public Playground() {
		this.setLayout(null);
		initBoard();
		initPlayers();
		initPot();
		initActions();
	}

	public void initActions(){
		bet.setLabel("Bet");
		check.setLabel("Check");
		fold.setLabel("Fold");
		
		bet.setBounds(330, 400, 40, 35);
		fold.setBounds(380, 400, 40, 35);
		check.setBounds(430, 400, 40, 35);
		
		this.add(bet);
		this.add(check);
		this.add(fold);
	}
	
	private void initPot() {
		pot.setText("500");
		pot.setEditable(false);
		pot.setBounds(380, 270, 45, 20);
		this.add(pot);
	}
	
	private void initPlayers() {
		p1.setText("CPU1");
		p2.setText("CPU2");
		p3.setText("CPU3");
		p4.setText("CPU4");
		
		p1.setBounds(270, 72, 40, 25);
		p2.setBounds(505, 72, 40, 25);
		p3.setBounds(700, 340, 40, 25);
		p4.setBounds(70, 340, 40, 25);

		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}