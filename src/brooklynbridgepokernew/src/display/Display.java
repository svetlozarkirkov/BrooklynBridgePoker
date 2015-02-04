package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas {
	
	private int width;
	private int height;
	private String title;
	
	private JFrame frame;
	private Canvas canvas;
	
	public Display(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		
		createFrame();
	}
	
	public Canvas getCanvas1() {
		return this.canvas;
	}
	
	private void createFrame(){
		frame = new JFrame(this.title);
		frame.setSize(this.width, this.height);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(this.width, this.height));
		canvas.setMaximumSize(new Dimension(this.width, this.height));
		canvas.setMinimumSize(new Dimension(this.width, this.height));
		
		frame.add(canvas);
		frame.pack();
	}
	
	 public Canvas getCanvas() {
	        return this.canvas;
	    }
}
