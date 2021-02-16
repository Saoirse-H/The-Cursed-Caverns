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

	public void getArcherDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList) {
		for (GameObject temp : EnemiesList) {
			for (GameObject Bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - Bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight()) {
					EnemiesList.remove(temp);
					BulletList.remove(Bullet);
				}
			}
		}
	}
	
	public boolean getWitchDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList) {
		boolean bulletHit = false;
		Point3f bulletCentre = new Point3f();
		
		for (GameObject temp : EnemiesList) {
			for (GameObject bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- bullet.getCentre().getY()) < temp.getHeight()) {
					EnemiesList.remove(temp);
					bulletCentre = bullet.getCentre();
					bulletHit = true;
					BulletList.remove(bullet);
				}
			}
		}
		
		if(bulletHit) {
			for (GameObject temp : EnemiesList) {
				if(temp.getCentre().distance(bulletCentre) <= 100)
					EnemiesList.remove(temp);
			}
		}
		
		return bulletHit;		
	}
	
	public void getBrawlerDamage(CopyOnWriteArrayList<GameObject> EnemiesList, CopyOnWriteArrayList<Bullet> BulletList) {
		
		for (GameObject temp : EnemiesList) {
			for (Bullet bullet : BulletList) {
				if (Math.abs(temp.getCentre().getX() - bullet.getCentre().getX()) < temp.getWidth() 
					&& Math.abs(temp.getCentre().getY()- bullet.getCentre().getY()) < temp.getHeight()) {
					EnemiesList.remove(temp);
					bullet.setEnemiesHit(bullet.getEnemiesHit()+1);
					
					if(bullet.getEnemiesHit() >= 3)
						BulletList.remove(bullet);
				}
			}
		}
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
