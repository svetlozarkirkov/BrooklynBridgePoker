package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Card;
import model.Table;

public class TablePanel extends JPanel{

	private final ImageIcon CARD_PLACEHOLDER;// = new ImageIcon("images/card_placeholder.png");
	private final ImageIcon CARD_BACK;//= new ImageIcon("images/card_back.png");
	
	private Table table;
	
	private JLabel pot;
	private JLabel activity;
	
	private JLabel deck;
	private JLabel[] cards = new JLabel[5];;
	
	
	public TablePanel(Table table_){
		this.table = table_;
		this.setPreferredSize(new Dimension(600,260));
		this.setLayout(new BorderLayout());
		ClassLoader cl = this.getClass().getClassLoader();
		CARD_PLACEHOLDER = new ImageIcon(cl.getResource("images/card_placeholder.png"));
		CARD_BACK = new ImageIcon(cl.getResource("images/card_back.png"));

		JPanel infoArea = new JPanel();
		JPanel cardArea = new JPanel();
		cardArea.setBackground(Color.red);
		
		infoArea.setLayout(new GridLayout(2,1));
		infoArea.setBackground(Color.red);

		pot = new JLabel();
		addPadding(pot);
		pot.setText("<html><center><i>"+ "POT: "+"<br>" +  table.getPot() +" $" +"</i></center><html>");	
		infoArea.add(pot);	

		activity = new JLabel();
		addPadding(activity);
		activity.setText("<html><center><i>"+ "Last Movement: "+"<br><br>" +  table.getPot()  +"</i></center><html>");	

		infoArea.add(activity);
		
		
		deck = new JLabel(CARD_BACK);
		deck.setBorder(new EmptyBorder(0, 0, 0, 40));
		
		//Making the space for the cards that are pulled from the deck 
		JPanel cardsOnTable = new JPanel();
		cardsOnTable.setBackground(Color.red);
		for(int i=0; i < cards.length;i++){
			cards[i] = new JLabel(CARD_PLACEHOLDER);
			cards[i].setBorder(new EmptyBorder(0, 2, 0, 2));
			cardsOnTable.add(cards[i]);
		}
		
		
		cardArea.setBorder(new EmptyBorder(0, 0, 20, 0));
		cardArea.add(deck);
		cardArea.add(cardsOnTable);

		infoArea.setLayout(new BoxLayout(infoArea, BoxLayout.Y_AXIS));
		
		infoArea.add(pot);
		infoArea.add(activity);
		
		this.add(infoArea, BorderLayout.NORTH);
        this.add(cardArea, BorderLayout.SOUTH);
		

		
	}
	
	public void updatePot(){
		pot.setText("<html><center><i>"+ "POT: "+"<br>" +  table.getPot() +" $" +"</i></center><html>");	
	}
	
	public void updateActivity(){
		activity.setText("<html><center><i>"+ "Last Movement: "+"<br><br>" +  table.getActivity() +"</i></center><html>");	
	}
	
	public void clearTable(){
		for(int i=0; i < cards.length;i++){
			this.cards[i].setIcon(CARD_PLACEHOLDER);//add imageitem from card
		}
		activity.setText("<html><center><i>"+ "Last Movement: "+"<br><br>"  +"</i></center><html>");	
		updatePot();
	}
	
	
	public void updateCards(ArrayList<Card> cards){
		ClassLoader cl = this.getClass().getClassLoader();

		for(int i = 0; i < cards.size(); i++){
			//this.cards[i].setIcon(cards.get(i).getImage());//add imageitem from card
			this.cards[i].setIcon(new ImageIcon(cl.getResource("images/"+cards.get(i).getSuit().getCode() +""+cards.get(i).getRank().getCode()+".png" )));//add imageitem from card

			
		}
	}

	private void addPadding(JLabel Label){
		Label.setHorizontalAlignment(JLabel.CENTER);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Label.setBorder(paddingBorder);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
