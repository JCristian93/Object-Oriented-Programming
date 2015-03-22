package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Point;
import Shapes.Triunghi;

public class ChainShot extends Shrapnel {

	public ChainShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	/**
	 * Implementarea lui shoot specifica pentru ChainShot .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (4 * 4 * currentTime.get_h() + 4 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate(0 , -shapeChangingDist);
			ref = ref - shapeChangingDist / 10 - 4;
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 4;
		shooterPosition = shooterPosition.translate(0 , -dist);
		shape = new Triunghi();
		hitScreenAction(shooterPosition, ref);
	}
}
