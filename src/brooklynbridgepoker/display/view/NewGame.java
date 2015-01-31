package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewGame extends JDialog {

	private JButton okButton = new JButton("Start Game");
	private JComboBox<String> PlayerChoise = new JComboBox<String>();
	private static String[] PlayerChoiseString = { "Player vs Player", "Player vs Computer", "Computer vs Computer" };
	private JComboBox<String> StartingCash = new JComboBox<String>();
	private static String[] StartingCashString = { "500", "1000", "1500" };
	private int choise;
	private int startingCash;
	
	public NewGame(JFrame owner) {
		super(owner, "New Game", true); // true -> Modal
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,0));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(250,200));

		for (int i = 0; i < PlayerChoiseString.length; i++) {
			PlayerChoise.addItem(PlayerChoiseString[i]);
		}
		
		for (int i = 0; i < StartingCashString.length; i++) {
			StartingCash.addItem(StartingCashString[i]);
		}

		panel.add(new JLabel("Choose Players"));
		panel.add(PlayerChoise);
		panel.add(new JLabel("Choose Starting Cash"));
		panel.add(StartingCash);
		panel.add(okButton);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeOnOK();
			}
		});

		this.setLocation(((owner.getWidth()/2)-125), ((owner.getHeight()/2)-100));
		this.getContentPane().add(panel);
		this.pack();
		this.setResizable(false);
	}

	public int getChoise() {
		return choise;
	}
	public int getStartingCash() {
		return startingCash;
	}

	private void closeOnOK() {
		choise = (int) PlayerChoise.getSelectedIndex();
		
		switch((int) StartingCash.getSelectedIndex()){
		case 0:
			startingCash = 500;
			break;
		case 1:
			startingCash = 1000;
			break;
		case 2:
			startingCash = 1500;
			break;
		}
		setVisible(false);
	}

	private static final long serialVersionUID = 1L;
}
