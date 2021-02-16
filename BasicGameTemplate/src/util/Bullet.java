package util;

public class Bullet extends GameObject {
	int enemiesHit = 0;
	
	public Bullet() {}
	
	public Bullet(String textureLocation, int width, int height, Point3f centre, char direction, int enemiesHit) {
		super(textureLocation, width, height, centre, direction);
		this.enemiesHit = enemiesHit;
	}
	
	public int getEnemiesHit() {
		return enemiesHit;
	}
	
	public void setEnemiesHit(int enemiesHit) {
		this.enemiesHit = enemiesHit;
	}
}
