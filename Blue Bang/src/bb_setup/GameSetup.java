package bb_setup;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import bb_display.Display;
import bb_graphics.LoadImage;
import bb_manager.gameManager;

public class GameSetup implements Runnable {
	
	private String title;
	private int width, height;
	
	private Thread thread;
	
	private boolean running;
	private BufferStrategy buffer;
	
	
	private Display display;
	private gameManager manager;
	
	private Graphics g;
	

	public static final int gameWidth= 400;
	public static final int gameHeight = 400;
	
	
	public GameSetup(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
	
	}
	
	public void init(){
		display = new Display(title, width, height);
		LoadImage.init();
		manager =new gameManager();
		manager.init();
	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		if(thread == null){
		thread = new Thread(this);
		thread.start();
		}
		
	}
	
	public synchronized void stop() {
		if(!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick(){
		manager.tick();

	}
	public void render(){
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		//draw
		
		g.drawImage(LoadImage.image,50, 50, gameWidth, gameHeight,null);
		manager.render(g);

		
		
		//end of draw
		
		buffer.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		int fps = 199;
		double timePerTic = 1000000000/fps;  // i sec = 100000000 nano sec
		double delta = 0;
		long current =System.nanoTime();
		
		while(running){
			delta = delta + (System.nanoTime()-current)/timePerTic;
			current = System.nanoTime();
			if(delta>=1){
				tick();
			    render();
			    delta--;
			    
			}
			
			
		    
		}
		
	}

}
