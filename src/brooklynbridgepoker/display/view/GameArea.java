package view;

import model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameArea extends JPanel implements Observer{

		//private JLabel splash = new JLabel(CARD_BACK);
	private Table table;
	private TablePanel midTable;
	private ArrayList<PlayerPanel> player = new ArrayList<PlayerPanel>();
	private ChoicePanel choices;
	private GridBagConstraints gc;
	private TableThread thread;
	
	private JPanel gamepanel;
	private JPanel inputPanel;
	
	public GameArea(){
		
		table = new Table();
		table.addUI(this);
		gamepanel = new JPanel();
		inputPanel = new JPanel();

		this.setLayout(new BorderLayout());
		
			//Sets and configs the layout for the game
		gamepanel.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		
			//Padding
		gc.insets = new Insets(10, 10, 10, 10);
		
		
			//Sets up the tablePanel
		midTable = new TablePanel(table);	
		midTable.setBackground(Color.red);
		drawTable();

			//Sets up the playerchoice area
		choices = new ChoicePanel(table,this);
		inputPanel.add(choices);
		
			//creates the playerPanels
		for(int i = 0; i < 2; i++){
			player.add(new PlayerPanel());
		}
		
		drawPlayerAreas();
		
		this.add(gamepanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
	}
	
	public void newGame(){

			//Clears the table if any cards was out when new game was started
		midTable.clearTable();

			//Synchronize player panels with player from table 
		for(int i = 0; i < table.getPlayers().size(); i++){
			player.get(i).updatePlayer(table.getPlayer(i));
		}
	}
	
	private void drawPlayerAreas(){
			//Player areas
		for(int i = 0; i < player.size(); i++){
			if(i == 0){
				gc.gridx = 1;
				gc.gridy = 2;
				gamepanel.add(player.get(i),gc);
			}
			
			if(i == 1){
				gc.gridx = 1;
				gc.gridy = 0;
				gamepanel.add(player.get(i),gc);
			}
			
			if(i == 2){
				gc.gridx = 0;
				gc.gridy = 1;
				gamepanel.add(player.get(i),gc);
			}
			
			if(i == 3){
				gc.gridx = 2;
				gc.gridy = 1;
				gamepanel.add(player.get(i),gc);
			}
		}
	}
	
	private void drawTable(){
			//Mid table
		gc.gridx = 1;
		gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.BOTH;
        gamepanel.add(midTable, gc);
	}
	
		//Needs to be splitet up to diffrent methods
	public void update(Observable o, Object arg) {
		String msg =(String) arg;

		if(msg.compareTo("NewGame")==0){
			newGame();
		}
		
		updateBot(msg);
		updatePot(msg);
		blindsDone(msg);
		
		UpdateCardsOnTable(msg);
		cardsDealed(msg);
		newRound(msg);
		
		showWinner(msg);
		playerMakeChoice(msg);
		playerChoiceDone(msg);
		
		removeBrokePlayer(msg);
	}
	
	public void updatePlayer(int index){
		player.get(index).updatePlayer(table.getPlayer(index));
	}
	
	private void updateallPlayers(){
		for(int i =0; i < table.getPlayers().size();i++){
			player.get(i).updatePlayer(table.getPlayer(i));
		}
	}
	
	public Table getTable(){
		return table;
	}
	
	public Thread getThread(){
		return thread;
	}
	
	private void updatePot(String message){
		if(message.compareTo("UpdatePot")==0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					midTable.updatePot();
					updateallPlayers();
				}
			});
		}
	}
	
	private void blindsDone(String message){
		if(message.compareTo("Blinds")==0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					updateallPlayers();
				}
			});
		}
	}
	
	private void UpdateCardsOnTable(String message){
		if(message.compareTo("UpdateCardsOnTable")==0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					midTable.updateCards(table.getCardsOnTable());
				}
			});
		}
	}
	
	private void cardsDealed(String message){
		if(message.compareTo("CardsDealed")==0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					for(int i=0;i < table.getPlayers().size();i++){
						player.get(i).hideCards();
					}
				}
			});
		}
	}
	
	private void newRound(String message){
		if(message.compareTo("NewRound")==0){
			midTable.clearTable();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					for(int i=0;i < table.getPlayers().size();i++){
						player.get(i).notActive();
					}
				}
			});
		}
	}
	
	private void showWinner(String message){

		if(message.compareTo("winner")==0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					midTable.updatePot();
					midTable.updateActivity();
					choices.hideChoice();

					for(int k=0;k < table.getPlayers().size();k++){
						player.get(k).showCards();
					}
				}
			});
		}

	}
	private void playerChoiceDone(String message){
		for(int i=0;i < table.getPlayers().size();i++){
			if(message.compareTo("player0hide")==0){
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(0).hideCards();
						choices.hideChoice();

					}
				});
				
			}
			if(message.compareTo("player1hide")==0){
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(1).hideCards();
					}
				});
			}
		}
	}
	
	private void playerMakeChoice(String message){
		for(int i=0;i < table.getPlayers().size();i++){
			
			if(message.compareTo("player0")==0){

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						choices.useChoicePanel(table.getPlayer(0));
						player.get(0).showCards();
					}
				});
			}
			
			if(message.compareTo("player1")==0){

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						choices.useChoicePanel(table.getPlayer(1));
						player.get(1).showCards();

					}
				});
			}
		}
	}
	
	private void updateBot(String message){
		for(int i=0;i < table.getPlayers().size();i++){
			
			if(message.compareTo("bot0")==0){

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(0).updatePlayer(table.getPlayer(0));
					}
				});
			}
			
			if(message.compareTo("bot1")==0){

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(1).updatePlayer(table.getPlayer(1));

					}
				});
			}
		}
	}
	
	private void removeBrokePlayer(String message){
		for(int i=0;i < table.getPlayers().size();i++){
			
			if(message.compareTo("BrokePlayer0")==0){
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(0).notActive();
					}
				});
			}else if(message.compareTo("BrokePlayer1")==0){
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						player.get(1).notActive();
					}
				});
			}
		}
	}
	
private static final long serialVersionUID = 1L;
}
