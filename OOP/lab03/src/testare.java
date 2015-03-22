
public class testare {
	public void f (){
		new Integer(2+3);
	}
	public void g (){
		int nr = 2+3;
	}
	private long run(int a) { 
	    long start = System.nanoTime(); 
	    if (a == 1){
	    	f();  
	    	return System.nanoTime() - start;
	    }
	    g();
    	return System.nanoTime() - start;
	} 
	public void showUsedMemory() { 
	    long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); 
	    System.out.println(usedMem); 
	} 
	public static void main(String[] args){
		//ex1 nu are nevoie de compilare 
		int[] vector = {1,2,3,4,5,6};
		//ex2
		Poligon instanta = new Poligon (vector);		
		//ex3
		testare test = new testare();
		System.out.println(test.run(1) + " " + test.run(2));
		//ex4
		String[] cuvant = new String[3];
		cuvant[0] = "a";
		cuvant[1] = "b";
		cuvant[2] = "c";
		test.showUsedMemory();
		String a = new String("abc");
		test.showUsedMemory();
		//ex5
		RandomStringGenerator test2 = new RandomStringGenerator(6 , "abcdef");
		System.out.println(test2.next());
		//ex6
		PasswordMaker test3 = new PasswordMaker("Cristian" , "Jalba" , 20);
		System.out.println(test3.getPassword());
		//ex7
		Test test4 = new Test (3,4);
		Test test5 = new Test (5,6);
		System.out.println("Test4:" + test4.nr1 + " "+ test4.nr2 + "\nTest5:" + test5.nr1 +" "+ test5.nr2);
		
		//GUI
		Interfata GUI = new Interfata();
		GUI.createAndShowGUI(test3.getPassword());
		GUI.createAndShowGUI("****************************");
		
	}	
}
