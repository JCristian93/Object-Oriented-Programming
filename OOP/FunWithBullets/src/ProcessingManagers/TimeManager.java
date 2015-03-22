package ProcessingManagers;

/**
 * Time object
 */
public class TimeManager {
	private int h,m,s;

	// TODO => implement the body of the class (set class fields, make ways 
	// 			for other classes to work with a TimeManager object)
	public void split_Time (String date){
		String [] tmp ;
		tmp = date.split(":");
		h = Integer.parseInt(tmp[1]);
		m = Integer.parseInt(tmp[2]);
		s = Integer.parseInt(tmp[3]);
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