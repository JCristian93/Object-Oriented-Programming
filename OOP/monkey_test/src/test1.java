
public class test1 {

	public static void main (String [] args){
		String info ="anamaria" ,word = "ano",comun = new String() , rest_word = new String() , rest_node_info = new String();
		int ok=1;
		for (int i=1 ; i<=Math.min(word.length(), info.length()); i++){
			if (!word.substring(0 , i).equals(info.substring(0 , i))) {
				ok=0;
				break;
			}
			comun = word.substring(0 , i);
			rest_word = word.substring(i);
			rest_node_info = info.substring(i);
		}
		System.out.println("cuvant : " + word);
		System.out.println("info : " + info);
		System.out.println("comun : " + comun);
		System.out.println("rest info : " + rest_node_info);
		System.out.println("rest word : " + rest_word);
		System.out.println(ok);
	}
}
