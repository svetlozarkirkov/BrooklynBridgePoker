package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameArea;
import model.Player;

public class foldListener implements ActionListener{
	private Player player;
	private GameArea view;
	
	public foldListener(GameArea view){
		this.view = view;
	}
	
	public static void setPlayer(Player player){
		player = player;
	}
	
	public void actionPerformed(ActionEvent ae) {		
		synchronized(player) {
			player.fold();
			player.setmoveAction("Folded");
			view.updatePlayer(player.getId());
			player.moved();
			player.notifyAll();
		}
	}
}
