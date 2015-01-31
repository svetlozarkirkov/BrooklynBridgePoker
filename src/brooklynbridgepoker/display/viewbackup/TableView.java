package view;

import model.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class TableView extends JPanel{

	Table table;
	private static final ImageIcon CARD_PLACEHOLDER = new ImageIcon("");
	private static final ImageIcon CARD_BACK= new ImageIcon("resources/images/card_back.png");
	
	JLabel pot;
	JLabel activity;
	
	JLabel deck;
	JLabel cards[] = new JLabel[5];
	
	ArrayList<PlayerChoices> menu = new ArrayList<PlayerChoices>();//needs a player to work.

	public TableView(Table table){
		this.table = table;
		
		JPanel menuPanel;
		
		//Wasted for Computer needs to be improved
		menuPanel = new JPanel();//new CardLayout()
		menuPanel.setBackground(Color.black);
		menuPanel.setPreferredSize(new Dimension(60,60));
		menuPanel.setBackground(Color.red);
		
		for(int i = 0; i < table.getPlayers().size();i++){
			menu.add(new PlayerChoices(table.getPlayer(i),table));
			menuPanel.add(menu.get(i));
		}
		
		this.setBackground(Color.red);
		this.setLayout(new BorderLayout());

		JPanel cardArea = new JPanel();
		JPanel infoArea = new JPanel();

		infoArea.setLayout(new GridLayout(2,1));
		infoArea.setBackground(Color.red);

		pot = new JLabel();
		
		addPadding(pot);
		pot.setText("<html><i>"+ "POT: "+"<br>" +  table.getPot() +" $" +"</i><html>");	
		infoArea.add(pot);	

		activity = new JLabel();
		addPadding(activity);
		infoArea.add(activity);

		deck = new JLabel(CARD_BACK);
		JPanel cardsOnTable = new JPanel();
		cardsOnTable.setBackground(Color.red);

		for(int i=0; i < cards.length;i++){
			cards[i] = new JLabel(CARD_PLACEHOLDER);
			cardsOnTable.add(cards[i]);
		}

		cardArea.setLayout(new GridBagLayout());
		cardArea.setBackground(Color.red);
		GridBagConstraints gc = new GridBagConstraints();

		gc.insets = new Insets(30, 50, 30, 50);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		cardArea.add(deck, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		cardArea.add(cardsOnTable, gc);

		this.add(infoArea, BorderLayout.NORTH);
        this.add(cardArea, BorderLayout.CENTER);
		this.add(menuPanel, BorderLayout.SOUTH);
		
	}
	
	public void showPlayerChoices(int index){
		menu.get(index).makeChoise();
	}
	
	public void hidePlayerChoices(int index){
		menu.get(index).hide();
	}
	
	public void updatePot(){
		this.pot.setText("<html><i>"+ "POT: "+"<br>" + table.getPot() +" $" +"</i><html>");	
	}
	
	public void updateMessage(String message){
		this.activity.setText(message);	
	}
	
	public void updateCards(ArrayList<Card> cards){
	
		for(int i = 0; i < cards.size(); i++){
			this.cards[i].setIcon(cards.get(i).getImage());//add imageitem from card
		}
	}
	
	private void addPadding(JLabel Label){
		Label.setHorizontalAlignment(JLabel.CENTER);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Label.setBorder(paddingBorder);
	}
	
	private static final long serialVersionUID = 1L;

}
