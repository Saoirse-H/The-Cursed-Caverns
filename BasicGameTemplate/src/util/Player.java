package util;

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
