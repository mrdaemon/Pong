package pong;

import org.lwjgl.opengl.GL11;

public abstract class Paddle extends Entity {
	public static int PADDLE_WIDTH = 16;
	public static int PADDLE_HEIGHT = 64;
	public static int PADDLE_MARGIN = 16;
	
	// Is the paddle on the right side of the screen?
	private boolean isOnRight = false;
	
	public Paddle() {
		// This is pretty horrible but it basically calls the thing below.
		// I dunno.
		this(false);
	}
	public Paddle(boolean isOnRight) {
		this.isOnRight = isOnRight;
		
		if (this.isOnRight)
			this.setOrigin(new Point(Game.width - Paddle.PADDLE_MARGIN, Game.height/2));
		else
			this.setOrigin(new Point(Paddle.PADDLE_MARGIN, Game.height/2));
	}

	@Override
	public void draw(Game g) {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(this.getOrigin().getX() - (Paddle.PADDLE_WIDTH/2), this.getOrigin().getY() - (Paddle.PADDLE_HEIGHT/2));
			GL11.glVertex2f(this.getOrigin().getX() + (Paddle.PADDLE_WIDTH/2), this.getOrigin().getY() - (Paddle.PADDLE_HEIGHT/2));
			GL11.glVertex2f(this.getOrigin().getX() + (Paddle.PADDLE_WIDTH/2), this.getOrigin().getY() + (Paddle.PADDLE_HEIGHT/2));
			GL11.glVertex2f(this.getOrigin().getX() - (Paddle.PADDLE_WIDTH/2), this.getOrigin().getY() + (Paddle.PADDLE_HEIGHT/2));
		}
		GL11.glEnd();
	}
	
	public boolean isOnRight() { return this.isOnRight; }
	public void setIsOnRight(Boolean b) { this.isOnRight = b; }
}
