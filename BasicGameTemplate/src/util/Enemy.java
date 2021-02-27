/* 
 * Written by Saoirse Houlihan
 * 17340803
 */
package util;
public class Enemy extends GameObject{
	private boolean hitPlayer;
	
	public Enemy(String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
		hitPlayer = false;
	}
	
	//move function
	public void move(Vector3f direction, Map map) {
		map.setEnemyTile(this.getCentre(), 1);
		this.getCentre().applyVector(direction);
		map.setEnemyTile(this.getCentre(), 0);
	}
	
	public boolean getHitPlayer() {
		return hitPlayer;
	}
	
	public void setHitPlayer(boolean hit) {
		this.hitPlayer = hit;
	}
}
