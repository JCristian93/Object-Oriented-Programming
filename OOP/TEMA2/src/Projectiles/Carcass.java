package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Patrat;
import Shapes.Point;

public class Carcass extends HeatedShot {

	public Carcass(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru Carcass .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (2 * 2 * currentTime.get_h() + 2 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate(0 , shapeChangingDist);
			ref = ref - shapeChangingDist / 10 - 2;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 2;
		shooterPosition = shooterPosition.translate(0 , dist);
		shape = new Patrat();
		hitScreenAction(shooterPosition, ref);
	}
}
