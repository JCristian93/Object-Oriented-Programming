package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Point;
import Shapes.Romb;

public class HeatedShot extends SpiderShot {

	public HeatedShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru HeatedShot .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (6 * 6 * currentTime.get_h() + 6 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate(0,(int) Math.round (Math.cos(shapeChangingDist * Math.PI / 2)));
			ref = ref - shapeChangingDist / 10 - 6;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 6;
		shooterPosition = shooterPosition.translate(0, (int) Math.round (Math.cos(dist * Math.PI / 2)));
		shape = new Romb();
		hitScreenAction(shooterPosition, ref);
	}
}
