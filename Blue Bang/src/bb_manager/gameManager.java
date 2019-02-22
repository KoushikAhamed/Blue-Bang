package bb_manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;



import bb_bulet.Bullet;
import bb_bulet_enemy.BulletEnemy;
import bb_collission.CollissionEffect;
import bb_collission.EnemyDead;
import bb_collission.PlayerWonded;
import bb_display.Display;
import bb_enemy.Enemy;
import bb_entity.Player;
import bb_setup.GameSetup;


public class gameManager implements KeyListener {
	
	private Player player;
	public static ArrayList<Bullet>bullet;
	public static ArrayList<BulletEnemy>bullet_enemy;
	public static ArrayList<Enemy>enemies;
	public CollissionEffect bang;
	public EnemyDead boom;
	public PlayerWonded wond;
	
	
	private long current;
	private long delay;
	private int health;
	private int score;
	private int numOfCollission;
	private int enemyBusted;
	
	
	
	private int start;
	
	
	
	
	public gameManager(){
		
	}
	
	public void init(){
		Display.frame.addKeyListener(this);
		player = new Player((GameSetup.gameWidth/2)+50, (GameSetup.gameHeight-60)+50);
		
		player.init();
		bullet = new ArrayList<Bullet>();
		bullet_enemy = new ArrayList<BulletEnemy>();
		enemies = new ArrayList<Enemy>();
		bang = new CollissionEffect(50,50);
		boom = new EnemyDead(50, 50);
		wond = new PlayerWonded(50, 50);
		
	
		
		current = System.nanoTime();
		delay =2000;
		
		health = player.getHealth();
		score = 0;
		numOfCollission = 0;
		enemyBusted=0;
		
		
		
		
	}
	
	public void tick(){
		if(start==1){
		player.tick();
		//bullet
		for(int i =0;i<bullet.size();i++){
			bullet.get(i).tick();
		}
		
		long breaks = (System.nanoTime()-current)/2000000;
		
		if(breaks>delay){
		for(int i=0; i<2;i++){
				Random rand = new Random();
				int randX = rand.nextInt(450);
				int randY = rand.nextInt(50);
				if(health>0){
				enemies.add(new Enemy(randX, -randY));
				}
			}
		current = System.nanoTime();
		}
		
		for(int i=0;i<enemies.size();i++){
		enemies.get(i).tick();
		for(int j =0;j<bullet_enemy.size();j++){
			bullet_enemy.get(j).tick();
		}
		}
		
		}
		}
		
	
	
	public void render(Graphics g){
		if(start==1){
		player.render(g);
		
		
		for (int i=0;i<bullet.size();i++){
			bullet.get(i).render(g);
		}
		for(int k=0;k<enemies.size();k++){
		for (int i=0;i<bullet_enemy.size();i++){
			if(!(bullet_enemy.get(i).getX()<=70 || bullet_enemy.get(i).getX()>=360 || bullet_enemy.get(i).getY()>=410)){
			bullet_enemy.get(i).render(g);
			}
		}
		}
		//draw bullets
		for(int i= 0; i<bullet.size();i++ ){
			if(bullet.get(i).getY()<=50){
				bullet.remove(i);
				i--;
			}
		}
		
		
		
		//draw enemis
		for(int i=0;i<enemies.size();i++){
			if(!(enemies.get(i).getX()<=50 || enemies.get(i).getX()>=400 || enemies.get(i).getY()>=400)){
				
				if(enemies.get(i).getY()>= 50){
					enemies.get(i).render(g);
				}
			}
			
			//draw enemy bullets
			for(int j= 0; j<bullet_enemy.size();j++ ){
				if(bullet_enemy.get(j).getY()>=400){
					bullet_enemy.remove(j);
					j--;
				}
			}
			
		}
		
		
		//enemies
		for ( int i=0;i<enemies.size();i++){
			
			int ex = enemies.get(i).getX();
			int ey = enemies.get(i).getY();
			
			//collision of enemy and player
			/* if(r1.x < r2.x + width &&
			        r1.x + width > r2.x &&
			        r1.y <r2.y + width &&
			        r1.y + width > r2.y) */
			
			//r1 = player
			//r2 = enemies
			
			int px = player.getX();
			int py = player.getY();
			
			if(px < ex + 15 && px + 25 > ex && py < ey + 15 && py + 25> ey){
				enemies.remove(i);
				i--;
				health = health-5;
				numOfCollission++;
				
				bang.render(g);
				bang.render(g);
				bang.render(g);
				
				if(health<=0){
					
					enemies.removeAll(enemies);
					player.setHealth(0);
					start = 2;
					
				}
			}
			
			
			// bullets
			for(int j =0;j<bullet.size();j++){
				
				int bx = bullet.get(j).getX();
				int by = bullet.get(j).getY();
				
				
				//collision of enemy and bullet
				/* if(r1.x < r2.x + width &&
				        r1.x + width > r2.x &&
				        r1.y <r2.y + width &&
				        r1.y + width > r2.y) */
				
				//r1 = enemies
				//r2 = bullets
				
				
				
				if(ex < bx + 5 && ex + 40 > bx && ey < by + 5 && ey + 40> by){
					enemies.remove(i);
					i--;
					
					bullet.remove(j);
					j--;
					score+=5;
					enemyBusted++;
					boom.render(g);
					//so.sound1(this.sss);
				}
				 
			}
			
			for(int j =0;j<bullet_enemy.size();j++){
				int bx = bullet_enemy.get(j).getX();
				int by = bullet_enemy.get(j).getY();
				
				//collision of player and bullets_enemy
				/* if(r1.x < r2.x + width &&
				        r1.x + width > r2.x &&
				        r1.y <r2.y + width &&
				        r1.y + width > r2.y) */
				
				//r1 = player
				//r2 = bullets_enemy
				
				if(px < bx + 5 && px + 40 > bx && py < by + 5 && py + 40> by){
					health--;
					
					bullet_enemy.remove(j);
					j--;
					wond.render(g);
					
					if(health<=0){
						
						enemies.removeAll(enemies);
						player.setHealth(0);
						start = 2;
						
					}
				}
			}
			
			g.setColor(Color.darkGray);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Score: "+score, 70, 500);
			g.drawString("Health: "+health, 70, 540);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Number Of Collissions: "+numOfCollission, 250, 500);
			g.drawString("Number Of Enemy Busted: "+enemyBusted, 250, 520);
		}
	
		}
		
		else if(start == 2){
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.drawString("Game Over", 120, (GameSetup.gameHeight/2)+50);
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.PLAIN, 30));
			g.drawString("Press Enter To Start Again", 70, (GameSetup.gameHeight/2)+100);
		}
		
		else{
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.PLAIN, 30));
			g.drawString("Press Enter To Start", 120, (GameSetup.gameHeight/2)+50);
		}
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if(source == KeyEvent.VK_ENTER){
		  start = 1;
		  init();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
