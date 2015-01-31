package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.ChoicePanel;
import view.GameArea;
import model.Player;


public class betListener implements ActionListener, ChangeListener{
	
		
	private Player player;
	private GameArea view;
	private ChoicePanel choiceView;
	private int tobet;
	
	public betListener(GameArea view, ChoicePanel choiceView){
		this.view = view;
		this.choiceView = choiceView;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public void actionPerformed(ActionEvent ae) {
		synchronized(player) {
		player.setBet(tobet);
		player.setmoveAction("Rised");
		view.updatePlayer(player.getId());
		player.moved();
		player.notifyAll();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tobet = choiceView.getSliderValue();
		choiceView.updateAmount();
	}

}
