package view;
import model.Table;

public class TableThread extends Thread{
	Table table;

	public TableThread(String name,Table table){
		super(name);
		this.table = table;
	}

	public void run() {
		table.play();
	}
}