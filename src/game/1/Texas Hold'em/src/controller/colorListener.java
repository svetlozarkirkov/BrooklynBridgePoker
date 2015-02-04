package controller;

import view.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class colorListener implements ActionListener{
	
	private static String[] colorStrings = { "Red", "Blue", "Green" };
	private static Color[] colors = { Color.red, Color.blue, Color.green };
	private Menu panel;
	
	public colorListener(Menu test){
		this.panel = test;
	}

	public void actionPerformed(ActionEvent ae) {
		String cmdStr = ae.getActionCommand();
		setColor(cmdStr);
	}
	
	private void setColor(String clrStr) {
		for (int i = 0; i < colorStrings.length; i++) {
			if (clrStr.equalsIgnoreCase(colorStrings[i])) {
				panel.setBackground(colors[i]);
				return;
			}
		}
		panel.setBackground(Color.gray);
	}

}
