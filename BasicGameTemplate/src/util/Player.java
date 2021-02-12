package util;

public class Player extends GameObject {
	
	private String role;
	private int health;
	private int fireRate;
	
	public Player() {}
	
	public Player(String role, int health, int fireRate, String textureLocation, int width, int height, Point3f centre, char direction) {
		super(textureLocation, width, height, centre, direction);
		this.role = role;
		this.health = health;
		this.fireRate = fireRate;
	}

	public String getRole() {
		return role;
	}
	
	public int health() {
		return health;
	}
	
	public int fireRate() {
		return fireRate;
	}
}
