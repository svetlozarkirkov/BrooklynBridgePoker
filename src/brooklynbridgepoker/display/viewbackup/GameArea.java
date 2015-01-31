package view;

import model.*;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class GameArea extends JPanel implements Observer{

	Table table;
	TableView midTable;
	
	ArrayList<PlayerArea> Player = new ArrayList<PlayerArea>();
	//PlayerArea Player[] = new PlayerArea[4];
	GridBagConstraints gc;
	
	public GameArea(){
		table = new Table();
		table.addUI(this);
		
		//Sets and configs the layout for the game
		this.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		//Padding
		gc.insets = new Insets(10, 10, 10, 10);
		
	}

			//Called by newGameController
	public void newGame(){
		table.newGame(0,4); 		// Ska göras i en listener

		for(int i = 0; i < table.getPlayers().size(); i++){
			Player.add(new PlayerArea(table.getPlayers().get(i)));
			Player.get(i).updatePlayer();
		}

			//Players need to be created before tableview is made
		midTable = new TableView(table);

		drawPlayerAreas();
		drawTable();
		
		this.updateUI();
		table.play();
	}
	
	private void drawTable(){
			//Mid table
		gc.gridx = 1;
		gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.BOTH;
		this.add(midTable, gc);
	}
	
	private void drawPlayerAreas(){
			
		//Player areas
		for(int i = 0; i < table.getPlayers().size(); i++){
			if(i == 0){
				gc.gridx = 1;
				gc.gridy = 2;
				this.add(Player.get(i),gc);
			}
		
			if(i == 1){
				gc.gridx = 1;
				gc.gridy = 0;
				this.add(Player.get(i),gc);
			}
			
			if(i == 2){
				gc.gridx = 0;
				gc.gridy = 1;
				this.add(Player.get(i),gc);
			}
			
			if(i == 3){
				gc.gridx = 2;
				gc.gridy = 1;
				this.add(Player.get(i),gc);
			}
		}
	}
	
	//Called by table.play()
	@Override
	public void update(Observable o, Object arg) {
		String msg = (String)arg;
		// TODO Auto-generated method stub		
		if(o instanceof Table){
			
			if(msg.compareTo("UpdatePot")==0){
				this.midTable.updatePot();
			}
			
			if(msg.compareTo("UpdateCardsOnTable")==0){
				this.midTable.updateCards(table.getCardsOnTable());
			}
			
			for(int i = 0; i < table.getPlayers().size(); i++){
				if(msg.compareTo("PlayerChoiseShow"+i)==0){
					midTable.showPlayerChoices(i);
					Player.get(i).showCards();

				}
			}
			
			for(int i = 0; i < table.getPlayers().size(); i++){
				if(msg.compareTo("PlayerChoiseHide"+i)==0){
					midTable.hidePlayerChoices(i);
					Player.get(i).hideCards();
					
					this.Player.get(i).updatePlayer();

				}
			}
		}
	
	}
	
	public Table getTable(){
		return table;
	}

	private static final long serialVersionUID = 1L;

}
