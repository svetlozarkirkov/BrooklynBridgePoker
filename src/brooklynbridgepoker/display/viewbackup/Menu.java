package view;
import model.*;

import java.awt.*;
import javax.swing.*;
import controller.*;

public class Menu extends JPanel{

	Table table;
	public Menu(Table table){
		this.setBackground("resources/images/background.png");
	}
	
	public JMenuBar createMenuBar() { 
		
		JMenuBar menuBar = new JMenuBar();
	
		JMenu mainMenu = new JMenu("Texas Hold'em");
		menuBar.add(mainMenu);
		
		JMenuItem aboutItem = new JMenuItem("About");
		mainMenu.add(aboutItem);
		
		mainMenu.addSeparator();

		JMenuItem newGameItem = new JMenuItem("New Game");
		mainMenu.add(newGameItem);
		
		JMenuItem LoadGameItem = new JMenuItem("Load Game");
		mainMenu.add(LoadGameItem);
		
		mainMenu.addSeparator();

		JMenuItem quitItem = new JMenuItem("Quit");
		mainMenu.add(quitItem);
		
		
		// a listener for the about Item
		aboutListener aboutListener = new aboutListener();
		aboutItem.addActionListener(aboutListener);
		
		// a listener for the New Game Item
		newGameListener newGameListener = new newGameListener(table);
		newGameItem.addActionListener(newGameListener);

		// a listener for the Load Game Item
		loadGameListener loadGameListener = new loadGameListener();
		LoadGameItem.addActionListener(loadGameListener);
		
		// a listener for the Quit item
		quitListener quitListener = new quitListener();
		quitItem.addActionListener(quitListener);

				
		// 2: the Options menu
		JMenu optionsMenu = new JMenu("Options");
		menuBar.add(optionsMenu);

		// create menu items and add listener
		for (int i = 0; i < colorStrings.length; i++) {
			JMenuItem item = new JMenuItem(colorStrings[i]);
			item.addActionListener(colorListener);
		}
		optionsMenu.add(colorMenu);
		
		return menuBar;
	}
	
	private static final long serialVersionUID = 1L;
	
}

