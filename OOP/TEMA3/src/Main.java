import java.util.ArrayList;
import java.util.Random;


public class Main {
	
	public static void main (String [] args){
		
		//PRIMUL TEST
		ArrayList<Student> lista = new ArrayList<Student>();
		MyHashMapImpl<Student, Integer> HASH = new MyHashMapImpl<Student, Integer>(20);
		for (int i = 0 ; i <= 2000 ; i++){
			Random rng = new Random();
			Student stud = new Student(generateString(rng, "abcdef", 6) , rng.hashCode() % 101);
			lista.add(stud);
			HASH.put(stud, rng.hashCode()%11);
		}
		long inainte , dupa;
		inainte = System.currentTimeMillis();
		for (int i = 0 ; i<=2000 ; i++ ){
			HASH.get(lista.get((int)(Math.random()*2000)));
		}
		dupa = System.currentTimeMillis();
		System.out.println(dupa - inainte);
		
		//AL DOILEA TEST
		//Mai jos este testata MyHashMapImpl pt LazyStudent 
		
		ArrayList<LazyStudent> lista2 = new ArrayList<LazyStudent>();
		MyHashMapImpl<LazyStudent, Integer> HASH2 = new MyHashMapImpl<LazyStudent, Integer>(20);
		for (int i = 0 ; i <= 2000 ; i++){
			Random rng = new Random();
			LazyStudent stud = new LazyStudent(generateString(rng, "abcdef", 6) ,rng.hashCode() % 101);
			lista2.add(i, stud);
			HASH2.put(stud, rng.hashCode()%11);
		}
		inainte = System.currentTimeMillis();
		for (int i = 0 ; i<=2000 ; i++ ){
			HASH2.get(lista2.get((int)(Math.random()*2000)));
		}
		dupa = System.currentTimeMillis();
		System.out.println(dupa - inainte);

		//Observam ca timpul de executie pentru al doilea test este mai mare decat timpul pentru primul test
	}

	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
}
