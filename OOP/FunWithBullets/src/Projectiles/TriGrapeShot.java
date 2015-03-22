package Projectiles;

import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

public class TriGrapeShot extends Projectile {

	private Point Cg;
	
	public TriGrapeShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
			//cu ocazia asta ne asiguram ca se construieste obiectul
		shapeChangingDist = 42 + (currentTime.get_h()  + currentTime.get_m() + currentTime.get_s());
		Cg = new Point(-1, -1);	//daca Cg are -1 , -1 inseamna ca nu a fost tras proiectilul
	}

	@Override
	public void shoot(int dist, Point shooterPosition) {	//incomplet , lipseste esenta
		//shooterPosition reprezinta centrul de greutate in cazul in care ramane nemodificata de atmosfera traiectoria
		Cg = shooterPosition.translate(dist, 0);
		
	}
	public boolean compare (int dist){	//cred ca se poate gasi o modalitate mai simpla , maybe ..
		if (dist <= shapeChangingDist ) return true;
		else return false;
	}
	public boolean isEmpty (){	//probabil nu o voi folosi evere
		if (Cg.getX() == -1 || Cg.getY() == -1) return true;
		else return false;
	}
	@Override
	protected void hitScreenAction(Point shooterPosition, int ref) {	//calculeaza referinta cand trece la o alta stare
		
		ref = ref - shapeChangingDist/10 - 1;	//mai trebuie gandit pe aici , the order is pretty fucked up
		class forma extends Shapes.BasicShape {
			@Override
			public void draw(Screen screen, int ref, Point Cg) {
				Point v[] = new Point[3];
				v[0] = Cg.translate(0 , (-2)*ref);
				v[1] = Cg.translate(ref , ref);
				v[2] = Cg.translate(-ref , ref);
				screen.drawLineOnScreen(v[0], v[1], Constants.Symbols.TRIANGLE_SYMBOL);
				screen.drawLineOnScreen(v[1], v[2], Constants.Symbols.TRIANGLE_SYMBOL);
				screen.drawLineOnScreen(v[2], v[0], Constants.Symbols.TRIANGLE_SYMBOL);
			}
			
		}
		forma obiect = new forma ();
		obiect.draw(screen, ref, Cg);
/*		new Shapes.BasicShape(){	//metoda a doua de a implementa draw , care evident e mai simpla
			@Override
			public void draw(Screen screen, int ref, Point centerGrav) {
				// TODO Auto-generated method stub
				
			}
		};
*/
	}
}
