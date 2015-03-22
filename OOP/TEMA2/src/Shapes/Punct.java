package Shapes;

import Screen.Screen;

public class Punct extends BasicShape {

	/**
	 * Metoda ce deseneaza un Punct . 
	 */
	@Override
	public void draw(Screen screen, int ref, Point centerGrav) {
		
		Point p = new Point (centerGrav.getX() , centerGrav.getY());
		screen.drawLineOnScreen(p, p, Constants.Symbols.DOT_SYMBOL);
	}

}
