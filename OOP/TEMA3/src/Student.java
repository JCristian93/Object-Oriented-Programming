/**
 * clasa folosita pentru testele de eficienta
 * @author CJ
 *
 */
public class Student {

	String nume;
	int varsta;
	/**
	 * seteaza numele si varsta
	 * @param nume
	 * @param varsta
	 */
	public Student (String nume , int varsta){
		this.nume = nume;
		this.varsta = varsta;
	}
	/**
	 * returneaza numele
	 * @return
	 */
	public String get_nume (){
		return this.nume;
	}
	/**
	 * returneaza varsta 
	 * @return
	 */
	public int get_varsta (){
		return this.varsta;
	}
	/**
	 * functia de HASH a fost supraincarcata . 
	 */
	@Override
	public int hashCode() {
		int h = 17;
	    h = 37 * h + nume.hashCode();
	    h = 37 * h + varsta;
		return h;
	}
	/**
	 * functia equals a fost supraincarcata astfel incat sa faca corect compararea.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if ((this.varsta == ((Student)obj).varsta) &&
				(this.nume.equals(((Student)obj).nume))) return true;
		return false;
	}
	
}
