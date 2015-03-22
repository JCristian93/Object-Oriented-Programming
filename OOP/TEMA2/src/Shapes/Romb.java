package Shapes;

import Screen.Screen;

public class Romb extends BasicShape{

	/**
	 * Metoda ce deseneaza un romb . Ii calculeaza varfurile si apeleaza de mai multe ori
	 * metoda ce deseneaza intre 2 puncte 
	 */
	@Override
	public void draw(Screen screen, int ref, Point centerGrav) {
		
		Point p1 , p2 , p3 , p4 ; 	//varfurile in ordinea acelor de ceas
		p1 = new Point(centerGrav.getX() , centerGrav.getY() - 2 * ref);
		p2 = new Point(centerGrav.getX() + ref , centerGrav.getY());
		p3 = new Point(centerGrav.getX() , centerGrav.getY() + 2 * ref);
		p4 = new Point(centerGrav.getX() - ref , centerGrav.getY());
		screen.drawLineOnScreen(p1, p2, Constants.Symbols.RHOMBUS_SYMBOL);
		screen.drawLineOnScreen(p2, p3, Constants.Symbols.RHOMBUS_SYMBOL);
		screen.drawLineOnScreen(p3, p4, Constants.Symbols.RHOMBUS_SYMBOL);
		screen.drawLineOnScreen(p4, p1, Constants.Symbols.RHOMBUS_SYMBOL);
	}

}
