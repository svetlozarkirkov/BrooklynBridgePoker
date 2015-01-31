package poker.ui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Poker extends JFrame{
	
	static final int WIDTH = 800;
	static final int HEIGHT = 511;
	private Playground board;
	
	public Poker() {
		board = new Playground();
		init();
	}
	
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