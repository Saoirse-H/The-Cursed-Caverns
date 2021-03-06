/* 
 * Written by Saoirse Houlihan
 * 17340803
 */
package util;

import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends GameObject {
	
	private String role;
	private int health;
	private long fireRate;
	private long invincibilityTimer;
	private boolean hasKey;
	
	public Player() {}
	
	public Player(String role, int health, long fireRate, String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
		this.role = role;
		this.health = health;
		this.fireRate = fireRate;
		hasKey = false;
		invincibilityTimer = 0;
	}

	public boolean getArcherDamage(CopyOnWriteArrayList<Enemy> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		
		for (Enemy enemy : EnemiesList) {
			for (Bullet Bullet : BulletList) {
				if (Math.abs(enemy.getCentre().getX() - Bullet.getCentre().getX()) < enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- Bullet.getCentre().getY()) < enemy.getHeight()) {
					map.setEnemyTile(enemy.getCentre(), 1);
					EnemiesList.remove(enemy);
					BulletList.remove(Bullet);
					bulletHit = true;
				}
			}
		}
		
		return bulletHit;
	}
	
	public boolean getWitchDamage(CopyOnWriteArrayList<Enemy> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		Point3f bulletCentre = new Point3f();
		
		for (Enemy enemy : EnemiesList) {
			for (Bullet bullet : BulletList) {
				if (Math.abs(enemy.getCentre().getX() - bullet.getCentre().getX()) < enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- bullet.getCentre().getY()) < enemy.getHeight()) {
					map.setEnemyTile(enemy.getCentre(), 1);
					EnemiesList.remove(enemy);
					bulletCentre = bullet.getCentre();
					bulletHit = true;
					BulletList.remove(bullet);
				}
			}
		}
		
		if(bulletHit) {
			for (Enemy enemy : EnemiesList) {
				if(enemy.getCentre().distance(bulletCentre) <= 48) {
					map.setEnemyTile(enemy.getCentre(), 1);
					EnemiesList.remove(enemy);
				}
			}
		}
		
		return bulletHit;		
	}
	
	public boolean getBrawlerDamage(CopyOnWriteArrayList<Enemy> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		
		for (Enemy enemy : EnemiesList) {
			for (Bullet bullet : BulletList) {
				if (Math.abs(enemy.getCentre().getX() - bullet.getCentre().getX()) < enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- bullet.getCentre().getY()) < enemy.getHeight()) {
					map.setEnemyTile(enemy.getCentre(), 1);
					EnemiesList.remove(enemy);
					bullet.setEnemiesHit(bullet.getEnemiesHit()+1);
					bulletHit = true;
					
					if(bullet.getEnemiesHit() >= 3)
						BulletList.remove(bullet);
				}
			}
		}
		
		return bulletHit;
	}

	public String getRole() {
		return role;
	}
	
	public int getHealth() {
		return health;
	}
	
	public long getFireRate() {
		return fireRate;
	}
	
	public boolean getHasKey() {
		return hasKey;
	}
	
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
	
	public long getInvincibilityTimer() {
		return invincibilityTimer;
	}
	
	public void setInvincibilityTimer(long invincibility) {
		this.invincibilityTimer = invincibility;
	}
}
