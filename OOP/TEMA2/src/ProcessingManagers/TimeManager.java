package ProcessingManagers;

/**
 * Time object
 */
public class TimeManager {
	private int h,m,s;

	// TODO => implement the body of the class (set class fields, make ways 
	// 			for other classes to work with a TimeManager object)
	public TimeManager (String date){
		String [] tmp ;
		tmp = date.split(":");
		h = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		s = Integer.parseInt(tmp[2]);
	}
	public int get_h(){
		return h;
	}
	public int get_m(){
		return m;
	}
	public int get_s(){
		return s;
	}
}