package util;

import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends GameObject {
	
	private String role;
	private int health;
	private long fireRate;
	
	public Player() {}
	
	public Player(String role, int health, long fireRate, String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
		this.role = role;
		this.health = health;
		this.fireRate = fireRate;
	}

	public boolean getArcherDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		
		for (GameObject temp : EnemiesList) {
			for (GameObject Bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - Bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
					map.setEnemyTile(temp.getCentre(), 1);
					EnemiesList.remove(temp);
					BulletList.remove(Bullet);
					bulletHit = true;
				}
			}
		}
		
		return bulletHit;
	}
	
	public boolean getWitchDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		Point3f bulletCentre = new Point3f();
		
		for (GameObject temp : EnemiesList) {
			for (GameObject bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- bullet.getCentre().getY()) < temp.getHeight()) {
					map.setEnemyTile(temp.getCentre(), 1);
					EnemiesList.remove(temp);
					bulletCentre = bullet.getCentre();
					bulletHit = true;
					BulletList.remove(bullet);
				}
			}
		}
		
		if(bulletHit) {
			for (GameObject temp : EnemiesList) {
				if(temp.getCentre().distance(bulletCentre) <= 100) {
					map.setEnemyTile(temp.getCentre(), 1);
					EnemiesList.remove(temp);
				}
			}
		}
		
		return bulletHit;		
	}
	
	public boolean getBrawlerDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList, Map map) {
		boolean bulletHit = false;
		
		for (GameObject temp : EnemiesList) {
			for (Bullet bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- bullet.getCentre().getY()) < temp.getHeight()) {
					map.setEnemyTile(temp.getCentre(), 1);
					EnemiesList.remove(temp);
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
	
	public int gethealth() {
		return health;
	}
	
	public long getFireRate() {
		return fireRate;
	}
}
