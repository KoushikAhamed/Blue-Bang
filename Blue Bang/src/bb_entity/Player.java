package bb_entity;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bb_bulet.Bullet;
import bb_display.Display;
import bb_graphics.LoadImage;
import bb_manager.gameManager;

public class Player implements KeyListener{
	private int x,y;
	private boolean left, right,up,down;
	private boolean fire;
	
	private long currentTime;
	private long delay;
	
	private int health;
	
	
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	public void init(){
		Display.frame.addKeyListener(this);
		currentTime = System.nanoTime();
		delay = 10;
		health = 100;
	}
	public void tick(){
		if(!(health<=0)){
			
		
		
		if(left){
			if(x>=50){
				x-=2;
//				System.out.print("pressed left ");
//				System.out.println("x"+x+"y"+y);
			}	
		}
		
		if(right){
			if(x<450-60){
				x+= 2;
//				System.out.print("pressed right  ");
//				System.out.println("x"+x+"y"+y);
			}
		}
		if(up){
			if(y>=50){
				y-= 2;
//				System.out.print("pressed up  ");
//				System.out.println("x"+x+"y"+y);
			}
			
		}
		if(down){
			if(y<=450-60){
				y+= 2;
//				System.out.print("pressed down  ");
//				System.out.println("x"+x+"y"+y);
			}
		}
		if(fire){
			long breaks = (System.nanoTime()-currentTime)/1000000;
			if (breaks > delay){
				gameManager.bullet.add(new Bullet(x+27, y));
				}
			currentTime = System.nanoTime();
		}
	}
	}
	public void render(Graphics g){
		if(!(health<=0)){
		
		g.drawImage(LoadImage.player_image,x, y, 60, 60,null);
		}
	}
	
	public void keyPressed(KeyEvent e){
		int source = e.getKeyCode();
		if(source == KeyEvent.VK_LEFT){
			left = true;
		}
		if (source == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(source == KeyEvent.VK_UP){
			up = true;
		}
		if(source == KeyEvent.VK_DOWN){
			down = true;
		}
		if(source == KeyEvent.VK_SPACE){
			fire = true;
		}
	}
	
	public void keyReleased(KeyEvent e){
		int source = e.getKeyCode();
		if(source == KeyEvent.VK_LEFT){
			left = false;
		}
		if (source == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(source == KeyEvent.VK_UP){
			up = false;
		}
		if(source == KeyEvent.VK_DOWN){
			down = false;
		}
		if(source == KeyEvent.VK_SPACE){
			fire = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int h){
		this.health = h;
	}
	

}
