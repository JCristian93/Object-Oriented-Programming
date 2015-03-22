package Shapes;

import Screen.Screen;

public class Triunghi extends BasicShape{
	
	/**
	 * Metoda ce deseneaza un triunghi . Ii calculeaza varfurile si apeleaza de mai multe ori
	 * metoda ce deseneaza intre 2 puncte 
	 */
	@Override
	public void draw(Screen screen, int ref, Point centerGrav) {
		
		Point p1 , p2 , p3 ;
		p1 = new Point(centerGrav.getX() , centerGrav.getY() - 2 * ref);
		p2 = new Point(centerGrav.getX() + ref , centerGrav.getY() + ref);
		p3 = new Point(centerGrav.getX() - ref , centerGrav.getY() + ref);
		screen.drawLineOnScreen(p1, p2, Constants.Symbols.TRIANGLE_SYMBOL);
		screen.drawLineOnScreen(p2, p3, Constants.Symbols.TRIANGLE_SYMBOL);
		screen.drawLineOnScreen(p3, p1, Constants.Symbols.TRIANGLE_SYMBOL);
	}

}
