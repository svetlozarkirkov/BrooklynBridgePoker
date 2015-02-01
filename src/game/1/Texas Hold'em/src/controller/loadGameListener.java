package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import view.TableThread;
import model.Player;
import model.Table;

public class loadGameListener implements ActionListener{
	private Table table;
    private JFileChooser chooser;
    private Thread thread;

	public loadGameListener(Table table, Thread thread){
		this.table = table;
		chooser = new JFileChooser();
		this.thread = thread;
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent ae) {		
		//openFile()
		if(thread!=null){
			thread.interrupt();
 		
    		try {
    			thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	        int returnName = chooser.showOpenDialog(null);	 
	        if (returnName == JFileChooser.APPROVE_OPTION) {
	        	ObjectInputStream ois = null;
				try {
					FileInputStream fin = new FileInputStream(chooser.getSelectedFile().getPath());
					ois = new ObjectInputStream(fin);
					
					table.loadGame((ArrayList<Player>) ois.readObject());

				} catch (IOException ie) {
					JOptionPane.showMessageDialog(chooser,
						    "Could not open File",
						    "Load Error",
						    JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(chooser,
						    "Could not open File",
						    "Load Error",
						    JOptionPane.ERROR_MESSAGE);
				} finally {
					try {
						if(ois != null) ois.close();
					} catch (IOException e) {}
				}
	        }

	        //Might need to tell ui to update and restart the game thread
			thread = new TableThread("PlayerThread",table);
	        thread.start();
	}
}
