package pong;

public class ComputerPaddle extends Paddle {
	public ComputerPaddle(boolean b) {
		super(b);
	}
	
	@Override
	public void update(Game g) {
		for (Entity e : g.getEntities()) {
			if (e instanceof Ball) {
				this.getOrigin().setY(e.getOrigin().getY());
				break;
			}
		}
	}
}
