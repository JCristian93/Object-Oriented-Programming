
public class PasswordMaker {
	final int MAGIC_NUMBER = 7;
	String First;
	String Last;
	int AGE;
	public PasswordMaker (String firstName , String lastName , int age){
		First = firstName;
		Last = lastName;
		AGE = age;
	}
	public String getPassword (){
		String pass = new String();
		RandomStringGenerator rand = new RandomStringGenerator (MAGIC_NUMBER , "abcdefg");
		pass = First.substring(First.length()-(AGE%3), First.length());
		pass = pass + rand.next() + Integer.toString(AGE) + Last;
		return pass;
	}
}
