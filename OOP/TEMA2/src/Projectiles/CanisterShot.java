package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Dreptunghi;
import Shapes.Point;

public class CanisterShot extends HeatedShot {

	public CanisterShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru CanisterShot .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (3 * 3 * currentTime.get_h() + 3 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate(-shapeChangingDist , 0);
			ref = ref - shapeChangingDist / 10 - 3;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 3;
		shooterPosition = shooterPosition.translate(-dist , 0);
		shape = new Dreptunghi();
		hitScreenAction(shooterPosition, ref);
	}
}
