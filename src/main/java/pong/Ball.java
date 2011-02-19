package pong;

import org.lwjgl.opengl.*;

public class Ball extends Entity {
	// Radius of the ball.
	public static int BALL_SIZE = 16;
	
	// Speed of the ball.
	public static int BALL_SPEED = 4;
	
	/**
	 * Constructor.
	 */
	public Ball() {
		// Call the entity top thing what. Bitches.
		super();
		
		// Origin!
		this.getOrigin().setX(Game.width/2);
		this.getOrigin().setY(Game.height/2);
		
		// Velocities!
		this.getVelocity().setX(Ball.BALL_SPEED);
		this.getVelocity().setY(Ball.BALL_SPEED);
	}
	
	@Override
	public void update(Game g) {
		// Move based on the velocity.
		this.getOrigin().setX(this.getOrigin().getX() + this.getVelocity().getX());
		this.getOrigin().setY(this.getOrigin().getY() + this.getVelocity().getY());
		
		// Which velocity are we flipping?
		boolean invertX = false, invertY = false;
	
		// Check if we've bounced off a wall
		if ((this.getOrigin().getX() >= Game.width) || (this.getOrigin().getX() <= 0))
			invertX = true;
		if ((this.getOrigin().getY() >= Game.height) || (this.getOrigin().getY() <= 0))
			invertY = true;
		
		// Check for paddle bounces.
		for (Entity e : g.getEntities()) {
			if (e instanceof Paddle) {
				// WALL OF COLLISION DETECTION
				// TODO: Uses the balls point of origin rather than a bouncing box.
				if ((this.getOrigin().getX() >= (e.getOrigin().getX() - Paddle.PADDLE_WIDTH/2)) &&
						(this.getOrigin().getX() <= (e.getOrigin().getX() + Paddle.PADDLE_WIDTH/2)) &&
						(this.getOrigin().getY() >= (e.getOrigin().getY() - Paddle.PADDLE_HEIGHT/2)) &&
						(this.getOrigin().getY() <= (e.getOrigin().getY() + Paddle.PADDLE_HEIGHT/2))) {
					
					invertX = true;
					
					// Add moar speed.
					this.getVelocity().setX(this.getVelocity().getX() * 1.1f);
					this.getVelocity().setY(this.getVelocity().getY() * 1.1f);
				}
			}
		}
		
		// Actually flip the velocity depending on what.
		if (invertX)
			this.getVelocity().setX(-this.getVelocity().getX());
		if (invertY)
			this.getVelocity().setY(-this.getVelocity().getY());
	}
	
	@Override
	public void draw(Game g) {
		// Draw a ball! (which is actually a square)
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(this.getOrigin().getX() - (Ball.BALL_SIZE/2), this.getOrigin().getY() - (Ball.BALL_SIZE/2));
			GL11.glVertex2f(this.getOrigin().getX() + (Ball.BALL_SIZE/2), this.getOrigin().getY() - (Ball.BALL_SIZE/2));
			GL11.glVertex2f(this.getOrigin().getX() + (Ball.BALL_SIZE/2), this.getOrigin().getY() + (Ball.BALL_SIZE/2));
			GL11.glVertex2f(this.getOrigin().getX() - (Ball.BALL_SIZE/2), this.getOrigin().getY() + (Ball.BALL_SIZE/2));
		}
		GL11.glEnd();
	}
}
