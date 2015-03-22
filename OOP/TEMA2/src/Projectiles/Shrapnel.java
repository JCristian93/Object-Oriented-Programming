package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Patrat;
import Shapes.Point;

public class Shrapnel extends SpiderShot {

	public Shrapnel(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru Shrapnel .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (5 * 5 * currentTime.get_h() + 5 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate((int) Math.round (Math.sin(shapeChangingDist * Math.PI / 2)), 0);
			ref = ref - shapeChangingDist / 10 - 5;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 5;
		shooterPosition = shooterPosition.translate((int) Math.round (Math.sin(dist * Math.PI / 2)), 0);
		shape = new Patrat();
		hitScreenAction(shooterPosition, ref);
	}
}
