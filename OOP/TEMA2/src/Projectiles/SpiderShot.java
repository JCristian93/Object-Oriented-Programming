package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Dreptunghi;
import Shapes.Point;

public class SpiderShot extends SimpleShell {

	public SpiderShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru SpiderShot .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {

		shapeChangingDist = 42 + (7 * 7 * currentTime.get_h() + 7 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate((int) Math.round (Math.sin(shapeChangingDist * Math.PI / 2)), (int) Math.round (Math.cos(shapeChangingDist * Math.PI / 2)));
			ref = ref - shapeChangingDist / 10 - 7;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 7;
		shooterPosition = shooterPosition.translate((int) Math.round (Math.sin(dist * Math.PI / 2)), (int) Math.round (Math.cos(dist * Math.PI / 2)));
		shape = new Dreptunghi();
		hitScreenAction(shooterPosition, ref);
	}

}
