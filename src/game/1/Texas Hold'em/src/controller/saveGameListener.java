package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import model.Table;

public class saveGameListener implements ActionListener{

	private Table table;
    private JFileChooser chooser;

	public saveGameListener(Table table){
		this.table = table;
		chooser = new JFileChooser();
	}

	public void actionPerformed(ActionEvent e) {
		    chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("/home/me/Desktop"));
		    int retrival = chooser.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
	        		ObjectOutputStream oos = null;

	        		try{
	        			FileOutputStream fop = new FileOutputStream(chooser.getSelectedFile()+".save");
	        			oos = new ObjectOutputStream(fop);
	        			oos.writeObject(table.getSaveFile());
	        		}
	        		finally {
	        			oos.close();
	        		}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
	}
}
