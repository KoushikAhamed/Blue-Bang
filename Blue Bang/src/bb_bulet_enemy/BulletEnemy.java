package bb_bulet_enemy;

import java.awt.Color;
import java.awt.Graphics;

public class BulletEnemy {
	private int x,y;
	private int sped;
	
	
	
	public BulletEnemy(int x, int y){
		this.x = x;
		this.y = y;
		sped = 10;
	}
	
	public void tick(){
		y+= sped;
	}
	public void render(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(x, y, 4, 15);
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}

}
