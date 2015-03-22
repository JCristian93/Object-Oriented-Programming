package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Point;
import Shapes.Triunghi;

public class TriGrapeShot extends Shrapnel {

	public TriGrapeShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Implementarea lui shoot specifica pentru TriGrapeShot .
	 */
	@Override
	public void shoot(int dist, Point shooterPosition) {
		
		shapeChangingDist = 42 + (1 * 1 * currentTime.get_h() + 1 * currentTime.get_m() + currentTime.get_s()) % 42;
		if (shapeChangingDist <= dist) {
			shooterPosition = shooterPosition.translate(shapeChangingDist , 0);
			ref = ref - shapeChangingDist / 10 - 1;
	//		super.set_ref(ref);
			dist = dist - shapeChangingDist;
			super.shoot(dist, shooterPosition);
			return;
		}
		ref = ref - dist / 10 - 1;
		shooterPosition = shooterPosition.translate(dist , 0);
		shape = new Triunghi();
		hitScreenAction(shooterPosition, ref);
	}

}
