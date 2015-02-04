package poker.ui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Poker extends JFrame{
	
	//Set the main window size
	static final int WIDTH = 800;
	static final int HEIGHT = 511;
	
	//Don't confuse variable board with the Board class !!!
	private Playground board;
	
	//Constructor for the game --> see Main class
	public Poker() {
		board = new Playground();
		init();
	}
	
	//Load all elements of the game
	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Brooklyn Bridge Poker");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(board);
		this.setVisible(true);
	}
}