package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.NewGame;
import view.TableThread;
import model.Table;

public class newGameListener implements ActionListener{
	
	private Table table;
	private NewGame newGameDialog;
	private Thread t; 
	
	public newGameListener(Table table, NewGame newGameDialog,Thread t){
		this.table = table;
		this.newGameDialog = newGameDialog;
		this.t=t;
	}
	
	public void actionPerformed(ActionEvent ae) {
		newGameDialog.setVisible(true);
		
		if(t!=null){
			t.interrupt();
    		
    		try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch(newGameDialog.getChoise()){
		case 0:
			table.newGame(0,2,newGameDialog.getStartingCash());
			break;
		case 1:
			table.newGame(1,1,newGameDialog.getStartingCash());
			break;
		case 2:
			table.newGame(2,0,newGameDialog.getStartingCash());
			break;
		}
		
		t = new TableThread("PlayerThread",table);
        t.start();
	}
}
	
	
