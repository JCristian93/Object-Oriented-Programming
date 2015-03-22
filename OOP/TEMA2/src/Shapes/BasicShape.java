package Shapes;

import Screen.Screen;

/**
 * Class that defines the general behaviour of a shape
 */
public abstract class BasicShape {
	/**
	 * Draws the current shape on the given screen
	 * 
	 * @param screen		screen to draw on
	 * @param ref			reference distance
	 * @param centerGrav	center of gravity of the current shape on screen
	 */
	public abstract void draw(Screen screen, int ref, 
			Point centerGrav);
}
