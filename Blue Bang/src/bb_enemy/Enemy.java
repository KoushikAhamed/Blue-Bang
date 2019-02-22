package bb_enemy;


import java.awt.Graphics;


import bb_bulet_enemy.BulletEnemy;
import bb_graphics.LoadImage;
import bb_manager.gameManager;

public class Enemy {
	private int x,y;
	

	
	private long currentTime;
	private long delay;
	
	public Enemy(int x,int y){
		this.x =x;
		this.y = y;
		
		currentTime = System.nanoTime();
		delay = 10;
		
	
		
		
	}
	
	public void tick(){
		y+=1;
		
		
			long breaks = (System.nanoTime()-currentTime)/1000000;
			if (breaks > delay){
				gameManager.bullet_enemy.add(new BulletEnemy(x+18, y+40));
				}
			currentTime = System.nanoTime();
		
		
	}
	
	public void render(Graphics g){
		
		g.drawImage(LoadImage.enemy_image,x, y, 40, 50,null);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

}
