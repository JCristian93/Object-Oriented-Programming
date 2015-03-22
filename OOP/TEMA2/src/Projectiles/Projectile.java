package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Basic class for all projectiles. 
 */
public abstract class Projectile {
	protected Screen screen;
	protected BasicShape shape;
	protected int shapeChangingDist;
	protected int ref;
	protected TimeManager currentTime;
	
	/**
	 * Constructorul clasei abstracte Projectile. 
	 * @param screen
	 * @param ref
	 * @param currentTime
	 */
	public Projectile(Screen screen, int ref, TimeManager currentTime) {
		this.screen = screen;
		this.ref = ref;
		this.currentTime = currentTime;
	}

	/**
	 * The action that takes place when the projectile is propagating.
	 * At each propagation step, the shape of the projectile changes
	 * due to the corrosive atmosphere
	 * 
	 * @param dist				distance between the shooter and the screen
	 * @param shooterPosition	the position from which the shooter fires
	 */
	public abstract void shoot(int dist, Point shooterPosition);
	
	/**
	 * The action that takes places when the projectile hits the screen 
	 * 
	 * @param shooterPosition	the position from which the shooter fires
	 * @param ref				reference distance			
	 */
	protected abstract void hitScreenAction(Point shooterPosition, int ref);
}
