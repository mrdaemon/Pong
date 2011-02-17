package pong;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;


public class Game {
	// Display dimensions.
	public int width = 800;
	public int height = 600;
	
	// True if the game loop should be running, false otherwise.
	public boolean isRunning = false;
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// Well this is probably horrible.
		// What if I just call main again in here?
		// Will it go forever?
		// Maybe it'll be come self-aware.
		Game g = new Game();
		g.setup();
		g.gameLoop();
	}
	
	/**
	 * Tries to initalise a bunch of stuff.
	 * 
	 * @return true if successful, false otherwise.
	 */
	public boolean setup() {
		try {
			// Grab a list of display modes with our specified width and height
			// as the minimum and maximum requirement.
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(
					this.width,
					this.height,
					this.width,
					this.height,
					-1, -1, -1, -1);
			
			// Assume the first one is correct.
			// I don' give a shit.
			Display.setDisplayMode(dm[0]);
			
			// Finally actually make the drawing context.
			Display.create();
		} catch (Exception e) {
			System.out.println("Unable to create OpenGL window.");
			return false;
		}
		
		// Switch to the projection matrix and reset it.
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		// Give us an orthogonal display, setting the coordinates to match
		// the screen size rather than this -1.0 to 1.0 business.
		GL11.glOrtho(0, this.width, this.height, 0, -1, 1);
		
		// Switch to the model view (as in what we're drawing) and reset it.
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		// The game is now probably running!
		this.isRunning = true;
		
		return true;
	}
	
	/**
	 * All that game looping business is in here.
	 * Yep.
	 */
	public void gameLoop() {		
		// Loop while the game is running and is the active window.
		while (this.isRunning && Display.isActive()) {
			// Stop the game if the game window has been asked to close.
			if (Display.isCloseRequested())
				this.isRunning = false;
			
			// Flip the display and clear the next frame for drawing.
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			// Ugly little wait for almost 60fps (roughly 16ms per frame).
			Utils.wait(16);
		}
	}
}
