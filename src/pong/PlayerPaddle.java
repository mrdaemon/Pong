package pong;

import org.lwjgl.input.*;

public class PlayerPaddle extends Paddle {
	public static float PADDLE_SPEED = 16;
	
	@Override
	public void update(Game g) {
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			this.getOrigin().setY(this.getOrigin().getY() - PlayerPaddle.PADDLE_SPEED);
		else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			this.getOrigin().setY(this.getOrigin().getY() + PlayerPaddle.PADDLE_SPEED);
		
		if (this.getOrigin().getY() < Paddle.PADDLE_MARGIN)
			this.getOrigin().setY(Paddle.PADDLE_MARGIN);
		if (this.getOrigin().getY() > Game.height - Paddle.PADDLE_MARGIN)
			this.getOrigin().setY(Game.height - Paddle.PADDLE_MARGIN);
	}
}
