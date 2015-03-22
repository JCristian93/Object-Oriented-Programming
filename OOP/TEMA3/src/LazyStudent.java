/**
 * mostenita din Student , difera doar cu hashCode
 * @author CJ
 *
 */
public class LazyStudent extends Student{
	
	/**
	 * seteaza numele si varsta
	 * @param nume
	 * @param varsta
	 */
	public LazyStudent(String nume, int varsta) {
		super(nume, varsta);
		// TODO Auto-generated constructor stub
	}
	/**
	 * de data aceasta functia de HASH e constanta
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 17;
	}

}
