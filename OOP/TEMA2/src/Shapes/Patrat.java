package Shapes;

import Screen.Screen;

public class Patrat extends BasicShape {
	
	/**
	 * Metoda ce deseneaza un patrat . Ii calculeaza varfurile si apeleaza de mai multe ori
	 * metoda ce deseneaza intre 2 puncte 
	 */ 
	@Override
	public void draw(Screen screen, int ref, Point centerGrav) {
		Point p1 , p2 , p3 , p4 ; 	//varfurile in ordinea acelor de ceas
		p1 = new Point(centerGrav.getX() + ref, centerGrav.getY() - ref);
		p2 = new Point(centerGrav.getX() + ref , centerGrav.getY() + ref);
		p3 = new Point(centerGrav.getX() -ref , centerGrav.getY() + ref);
		p4 = new Point(centerGrav.getX() - ref, centerGrav.getY() - ref);
		screen.drawLineOnScreen(p1, p2, Constants.Symbols.SQUARE_SYMBOL);
		screen.drawLineOnScreen(p2, p3, Constants.Symbols.SQUARE_SYMBOL);
		screen.drawLineOnScreen(p3, p4, Constants.Symbols.SQUARE_SYMBOL);
		screen.drawLineOnScreen(p4, p1, Constants.Symbols.SQUARE_SYMBOL);
	}
}
