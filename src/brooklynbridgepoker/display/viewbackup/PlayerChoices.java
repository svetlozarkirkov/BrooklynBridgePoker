package view;

import java.awt.Color;
import controller.*;
import javax.swing.*;
import model.Player;
import model.Table;

public class PlayerChoices extends JPanel{
	
	private JButton rise;
	private JButton check;
	private JButton fold;
	private JButton cancel;
	private JSlider slider;
	private JButton bet;
	private JLabel amount;

	JPanel choicePanel = new JPanel();
	JPanel risePanel = new JPanel();
	
	public PlayerChoices(Player player, Table table){
		//this.setBackground(Color.red);
		
		choicePanel = new JPanel();
		risePanel = new JPanel();
		choicePanel.setBackground(Color.red);
		risePanel.setBackground(Color.red);
		
		rise = new JButton("Bet");
		check = new JButton("Check");
		fold = new JButton("Fold");
		cancel = new JButton("Cancel");
		bet = new JButton("Bet");
		amount = new JLabel(" $");
		
		slider = new JSlider(10, player.getPoints());
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		
		choicePanel.add(rise);
		choicePanel.add(check);
		choicePanel.add(fold);
		risePanel.add(cancel);
		risePanel.add(slider);
		risePanel.add(amount);
		risePanel.add(bet);
		
		this.add(choicePanel);
		this.add(risePanel);
		risePanel.setVisible(false);
		
		riseListener riseListener = new riseListener(this);
		rise.addActionListener(riseListener);
		
		checkListener checkListener = new checkListener(player,table);
		check.addActionListener(checkListener);
		
		foldListener foldListener = new foldListener(player);
		fold.addActionListener(foldListener);
		
		betListener betListener = new betListener(this);
		bet.addActionListener(betListener);
		slider.addChangeListener(betListener);
		
		cancelListener cancelListener = new cancelListener(this);
		cancel.addActionListener(cancelListener);

		hide();
	}
		
	public void bet(){
		choicePanel.setVisible(false);
		risePanel.setVisible(true);
	}
	
	public void makeChoise(){
		choicePanel.setVisible(true);
		risePanel.setVisible(false);
	}
	
	
	public void hide(){
		choicePanel.setVisible(false);
		risePanel.setVisible(false);
	}

	public int getSliderValue() {
		return slider.getValue();
	}
	
	public void updateAmount(){
		amount.setText(getSliderValue() + " $");
	}
	
	
	private static final long serialVersionUID = 1L;

}
