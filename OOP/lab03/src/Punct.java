
public class Punct {

	private double a;
	private double b;
	public Punct(){
		a = 0;
		b = 0;
	}
	public Punct(double x , double y){
		a = x;
		b = y;
	}
	public void changeCoords (double x1 , double  x2){
		a = x1;
		b = x2;
	}
	public void afis (){
		System.out.println("(" + a + "," + b + ")");
	}
}
