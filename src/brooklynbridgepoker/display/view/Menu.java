package view;
import model.*;
import java.awt.*;
import javax.swing.*;
import controller.*;

public class Menu extends JPanel{
	
	private static String[] colorStrings = { "Red", "Blue", "Green" };
	
	private Table table;
	private NewGame newGameDialog;
	private About aboutDialog;
	private Thread thread;
	
	public Menu(GameArea game, NewGame newGameDialog,About aboutDialog){
		this.setBackground(Color.GREEN);
		this.table = game.getTable();
		this.newGameDialog = newGameDialog;
		this.aboutDialog = aboutDialog;
		this.thread = game.getThread();
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
		
		JMenuItem SaveGameItem = new JMenuItem("Save Game");
		mainMenu.add(SaveGameItem);
		
		mainMenu.addSeparator();

		JMenuItem quitItem = new JMenuItem("Quit");
		mainMenu.add(quitItem);
		
		
			// a listener for the about Item
		aboutListener aboutListener = new aboutListener(aboutDialog);
		aboutItem.addActionListener(aboutListener);
		
			// a listener for the New Game Item
		newGameListener newGameListener = new newGameListener(table, newGameDialog, thread);
		newGameItem.addActionListener(newGameListener);

			// a listener for the Load Game Item
		loadGameListener loadGameListener = new loadGameListener(table, thread);
		LoadGameItem.addActionListener(loadGameListener);
		
		saveGameListener saveGameListener = new saveGameListener(table);
		SaveGameItem.addActionListener(saveGameListener);
		
			// a listener for the Quit item
		quitListener quitListener = new quitListener();
		quitItem.addActionListener(quitListener);

				
			// 2: the Options menu
		JMenu optionsMenu = new JMenu("Options");
		menuBar.add(optionsMenu);

			// a sub menu
		JMenu colorMenu = new JMenu("Change Background Color");

		colorListener colorListener = new colorListener(this);

			// create menu items and add listener
		for (int i = 0; i < colorStrings.length; i++) {
			JMenuItem item = new JMenuItem(colorStrings[i]);
			item.addActionListener(colorListener);
			colorMenu.add(item);
		}
		optionsMenu.add(colorMenu);
		
		return menuBar;
	}

	private static final long serialVersionUID = 1L;

}

