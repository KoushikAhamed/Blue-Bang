package bb_bulet;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x,y;
	private int sped;
	
	
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		sped = 5;
	}
	
	public void tick(){
		y-= sped;
	}
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 5, 15);
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}

}
