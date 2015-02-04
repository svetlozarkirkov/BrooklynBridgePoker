package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;

public class Game implements Runnable{

	private String title;
	private int width;
	private int height;
	
	private Thread thread;
	private boolean isRunning;
	private Display display;
	
	private BufferStrategy bs;
	private Graphics g;
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		this.isRunning = false;
	}
	
	public void init(){
		new Display(this.title, this.width, this.height);
		
	}
	
	private void tick(){
		
	}
	
	private void render() {
		this.bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null){
			display.getCanvas().getBufferStrategy();
		}
		return;
	}
	@Override
	public void run() {
		init();
		
		while (isRunning){
			tick();
			render();
		}
		stop();
	}
	
	public synchronized void start(){
		if (this.isRunning){
			return;
		}
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void stop(){
		if (!this.isRunning){
			return;
		}
		this.isRunning = false;
		try{
			this.thread.join();
		} catch (InterruptedException e) {
			 e.printStackTrace();
		}
	}
}
