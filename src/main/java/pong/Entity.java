package pong;

public abstract class Entity {
	private Point origin;
	private Point velocity;
	
	public Entity() {
		this.origin = new Point();
		this.velocity = new Point();
	}
	
	public abstract void update(Game g);
	public abstract void draw(Game g);
	
	public Point getOrigin() { return this.origin; }
	public Point getVelocity() { return this.velocity; }
	
	public void setOrigin(Point p) { this.origin = p; }
	public void setVelocity(Point p) { this.velocity = p; }
}
