package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Point;
import Shapes.Punct;

/**
 * Cel mai de baza proiectil , reprezinta baza arborelui de mostenire . 
 * Nu este afectat de modificari are directiei , referintei .
 * @author CJ
 *
 */
public class SimpleShell extends Projectile {

	
	public SimpleShell(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea metodei shoot prezentata in Projectile specifica pentru acest tip de proiectil 
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {

		shape = new Punct();	//am instantiat pe shape ca Punct pentru a folosi draw-ul corespunzator
		hitScreenAction(shooterPosition, ref);	
	}
	/**
	 * Metoda comuna pentru toate proiectilele . Implementarea este comuna pentru toate
	 */
	@Override
	protected void hitScreenAction(Point shooterPosition, int ref) {
		// TODO Auto-generated method stub
		shape.draw(screen, ref, shooterPosition);	//se apeleaza metoda draw din clasa Punct
	}

}
