package pong;

public class Point {
	private float x, y;
	
	public Point() {
		this.x = this.y = 0;
	}
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
}
