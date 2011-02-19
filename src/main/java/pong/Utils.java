package pong;

public class Utils {
	/**
	 * Crappy little ugly wait for a cerain amount of time function.
	 * Uses 100% CPU while it waits.
	 * 
	 * @param millisecs Time in milliseconds to wait.
	 */
	public static void wait(int millisecs) {
		long nextTime = System.currentTimeMillis() + millisecs;
		
		// Basically do nothing until the time is reached.
		while (System.currentTimeMillis() <= nextTime);
	}
}
