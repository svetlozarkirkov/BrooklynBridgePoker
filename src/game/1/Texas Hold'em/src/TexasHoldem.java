import view.*;
import view.Menu;

import java.awt.*;

import javax.swing.*;

public class TexasHoldem {

	public static void main(String args[]) {		
		//creating the game
		GameArea GameArea = new GameArea();		
		JFrame frame = new JFrame("Texas Hold'em - Brooklyn Bridge Team");
		frame.setSize(1280, 960);
		
		NewGame newGameDialog = new NewGame(frame);
		About aboutDialog = new About(frame);
		Menu panel = new Menu(GameArea,newGameDialog, aboutDialog);

		//Setting The MenuBar
		JMenuBar menuBar = panel.createMenuBar();
		frame.setJMenuBar(menuBar);
		frame.add(panel);
		frame.getContentPane().add(GameArea);


		//Setting options for the frame
		//frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(1100,800));

	}
}

