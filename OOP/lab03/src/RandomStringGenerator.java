
import java.util.Random;

public class RandomStringGenerator {
	private char[] alpha;
	private int lungime;
	public RandomStringGenerator(int lung , String alfabet){
		alpha = new char[lung + 1];
		lungime = lung;
		alpha = alfabet.toCharArray();
	}
	public String next (){
		Random generator = new Random();
		char[] cuvant = new char[lungime];
		for (int i=0 ; i<lungime ; i++){
			int value = generator.nextInt(lungime);
			cuvant[i] = alpha[value];
		}
		String s = new String (cuvant);
		return s;
	}
}
