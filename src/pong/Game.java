package pong;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;

import org.lwjgl.input.*;

import java.util.ArrayList;

public class Game {
	// Display dimensions.
	public static int width = 800;
	public static int height = 600;
	
	// Entity list.
	private ArrayList<Entity> entities;
	
	// True if the game loop should be running, false otherwise.
	private boolean isRunning = false;
	
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
					Game.width,
					Game.height,
					Game.width,
					Game.height,
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
		GL11.glOrtho(0, Game.width, Game.height, 0, -1, 1);
		
		// Switch to the model view (as in what we're drawing) and reset it.
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		// Make a new entity list.
		this.entities = new ArrayList<Entity>();
		
		// Make a keyboard business fuck.
		try {
			Keyboard.create();
		} catch (Exception e) {
			// Shit.
			System.out.println("Unable to create keyboard controller.");
			return false;
		}
		
		// Balls and shit!
		this.entities.add(new Ball());
		this.entities.add(new PlayerPaddle());
		this.entities.add(new ComputerPaddle(true));
		
		// The game is now probably running!
		this.isRunning = true;
	
		// Everything went OK!
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
			
			// Poll the keyboard.
			Keyboard.poll();
			
			// Loop through the entities and update and draw.
			for (Entity e : this.entities) {
				e.update(this);
				e.draw(this);
			}
			
			// Flip the display and clear the next frame for drawing.
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			// Ugly little wait for almost 60fps (roughly 16ms per frame).
			Utils.wait(16);
		}
	}
	
	/**
	 * Getter for the entity class.
	 * @return ArrayList of entities.
	 */
	public ArrayList<Entity> getEntities() { return this.entities; }
}
