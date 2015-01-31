package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import controller.*;
import model.Player;
import model.Table;

public class ChoicePanel extends JPanel{

	private JButton check;
	private JButton rise;
	private JButton fold;
	private JButton bet;
	private JButton cancel;
	private JSlider slider;
	private JLabel amount;
	private JPanel makeChoice;
	private JPanel makeBet;

	public ChoicePanel(Table table, GameArea view){
		
		this.table = table;
		makeChoice = new JPanel();
		makeBet = new JPanel();
		makeChoice.setVisible(false);
		makeBet.setVisible(false);
		
		check = new JButton("Check");
		rise = new JButton("Raise");
		fold = new JButton("Fold");
		bet = new JButton("Bet");
		cancel = new JButton("Cancel");
		slider = new JSlider();
		amount = new JLabel();
		
		makeBet.add(cancel);
		makeBet.add(slider);
		makeBet.add(amount);
		makeBet.add(bet);
		
		makeChoice.add(check);
		makeChoice.add(fold);
		makeChoice.add(rise);
		
		checkListener = new checkListener(table,view);
		check.addActionListener(checkListener);
		
		foldListener = new foldListener(view);
		fold.addActionListener(foldListener);
		
		betListener = new betListener(view, this);
		bet.addActionListener(betListener);
		slider.addChangeListener(betListener);
		
		rise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMakeBet();
				hideMakeChoice();
				}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideMakeBet();
				showMakeChoice();
				}
		});

		this.add(makeChoice);
		this.add(makeBet);
	}
	
	public void useChoicePanel(Player player){
		checkListener.setPlayer(player);
		foldListener.setPlayer(player);
		betListener.setPlayer(player);
		
		if(player.getPoints()<=table.findBiggestBet()){
			slider.setMaximum(player.getPoints());
			slider.setMinimum(player.getPoints());
		}
		else{
			slider.setMaximum(player.getPoints());
			slider.setMinimum(table.findBiggestBet());
		}

		hideMakeBet();
		showMakeChoice();
	}
	
	public int getSliderValue() {
		return slider.getValue();
	}
	
	private void showMakeBet(){
		makeBet.setVisible(true);
	}
	
	private void hideMakeBet(){
		makeBet.setVisible(false);
	}
	
	private void showMakeChoice(){
		makeChoice.setVisible(true);
	}
	
	private void hideMakeChoice(){
		makeChoice.setVisible(false);
	}

	public void hideChoice(){
		makeChoice.setVisible(false);
		makeBet.setVisible(false);
	}
	
	public void updateAmount(){
		amount.setText(getSliderValue() + " $");
	}
	
	private static final long serialVersionUID = 1L;

}
