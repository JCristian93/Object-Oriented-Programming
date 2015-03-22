
public class Poligon {
	
	private Punct[] puncte;
	
	public Poligon (int colturi){
		puncte = new Punct[colturi];
		for (int i=0 ; i<colturi ; i++){
			puncte[i] = new Punct ();
		}
	}
	public Poligon (int[] v_puncte){ 
		this(6);
		puncte[1].changeCoords(v_puncte[0], v_puncte[1]);
		puncte[2].changeCoords(v_puncte[2], v_puncte[3]);
		puncte[3].changeCoords(v_puncte[4], v_puncte[5]);	
		puncte[1].afis();
		puncte[2].afis();
		puncte[3].afis();
	}
}
