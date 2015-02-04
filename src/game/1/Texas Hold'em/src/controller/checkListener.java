package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.GameArea;
import model.Player;
import model.Table;


public class checkListener implements ActionListener{
	
	private Player player;
	private Table table;
	private GameArea view;

	public checkListener(Table table, GameArea view){
		this.table = table;
		this.view = view;
	}
	this.player = player;
	
	public void actionPerformed(ActionEvent actionEvent) {
		int tobet = table.findBiggestBet();
				
		if(player.getPoints()<=tobet){
			tobet = player.getPoints();
		}
		
		synchronized(player) {
			player.setBet(tobet);
			player.setmoveAction("Check");
			view.updatePlayer(player.getId());
			player.moved();
			player.notifyAll();
		}
	}
}

