package bb_collission;



import java.awt.Color;
import java.awt.Graphics;

public class EnemyDead {
	private int x,y;
	private int sped;
	
	
	
	public EnemyDead(int x, int y){
		this.x = x;
		this.y = y;
		sped = 5;
	}
	
	public void tick(){
		y+= sped;
	}
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect(x, y, 400, 400);
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}

}
