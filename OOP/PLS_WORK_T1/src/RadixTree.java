/**
	RadixTree - Tema 1 la POO
 */
import java.util.LinkedList;
import java.util.ListIterator;

/**Am implementat toata tema intr-o singura clasa deoarece mi s-a parut 
 * extrem de comoda abordarea aceasta.
 * 
 * @author Jalba Cristian
 */
public class RadixTree {
	
	private String info;	//informatia utila pe fiecare nod
	private LinkedList<RadixTree> copii;	//copii fiecarui nod
	private LinkedList<Integer>	iduri ;		//pozitiile duplicatelor fiecarui cuvant
	//in caz ca un cuvant nu are duplicate lista va avea doar pozitia cuvantului respectiv
	static int contor;	//variabila statica ce contorizeaza de cate ori apelez functia add
	//e foarte utila ca sa pot stabili lista de id-uri pt fiecare nod
	static int numar_aparitii;	//variabila statica ce ma ajuta sa construiesc outputul
	//in metoda find . numar_aparitii reprezinta mai exact primul numar din output;
	static String afisare;	//output-ul efectiv pt fiecare apel al metodei find
	/**
	 * constructorul clasei , aloca memorie pentru listele iduri si copii
	 */
	public RadixTree() {
		iduri = new LinkedList<Integer>();
		copii = new LinkedList<RadixTree>();	
	}
	/**metoda ce adauga un cuvant in arbore respectand proprietatile RadixTree
	 * 
	 * @param word	reprezinta cuvantul ce va fi adaugat
	 */
	public void add (String word) {
		contor++;
		if (this.first(word , this) == null) {
			RadixTree aux = new RadixTree();
			aux.info = word;
			aux.iduri.add(contor);
			this.copii.add(aux);
			return;
		}
		this.add_recursiv (word , this.first(word , this) , this);
		return;
	}
	/**metoda asta e apelata doar in main ca sa seteze contor la -1
	 * contor e variabila statica si vreau sa isi pastreze valoare pe parcursul programului
	 */
	public void set_contor () {
		RadixTree.contor = -1;
	}
	/**metoda privata recursiva care trateaza toate cazurile principale de adaugare in arbore
	 * 
	 * @param word	- cuvantul ce va fi adaugat in arbore
	 * @param node	- nodul gasit cu first
	 * @param parent	- parintele nodului gasit cu first
	 */
	private void add_recursiv (String word , RadixTree node , RadixTree parent) {
		String comun = new String() , rest_word = new String() , rest_node_info = new String();
		int ok = 1;
		if (node == null) {	//adaugare simpla cand un nodul are copii
			RadixTree aux = new RadixTree();
			aux.info = word;
			aux.iduri.add(contor);
			parent.copii.add(aux);
			return;
		}
		//in acest for se determina acele 3 String-uri cruciale in metoda asta
		for (int i = 1 ; i <= Math.min(word.length() , node.info.length()) ; i++) {
			if (!word.substring(i - 1 , i).equals(node.info.substring(i - 1 , i))) {
				ok = 0;
				break;
			}
			comun = comun + word.substring(i - 1 , i);
			rest_word = word.substring(i);
			rest_node_info = node.info.substring(i);
		}
		if (ok == 0) {	//ok  e 0 cand rest_word difera de null
			RadixTree addon = new RadixTree() , addon_child = new RadixTree();
			parent.copii.remove(node);
			addon.info = comun;
			addon_child.info = rest_word;
			addon_child.iduri.add(contor);
			node.info = rest_node_info;
			addon.copii.add(node);
			addon.copii.add(addon_child);
			parent.copii.add(addon);
			return;
		}
		else {	//ok e 1 deci rest_word sau rest_node_info e null inseamna ca inserarea depinde de lungimea 
				//lui word in raport cu lungimea informatiei de pe nod
			if (word.length() > node.info.length()) {
				add_recursiv(rest_word , node.first(rest_word , node) , node);
				return;
			}
			else if (word.length() < node.info.length()) {
				RadixTree aux = new RadixTree();
				parent.copii.remove(node);
				aux.info = comun;
				aux.iduri.add(contor);
				node.info = rest_node_info;
				aux.copii.add(node);
				parent.copii.add(aux);
				return;
			}
			else {	//cazul in care se gaseste duplicat
				node.iduri.add(contor);
			}
		}
	}
	/** metoda aceasta cauta primul nod care are prima litera egala cu prima litera a lui word
	 * 
	 * @param word	- cuvantul pt care se va compara prima litera cu nodurile din arbore
	 * @param node	- nodul pe ai carui copii se face cautarea nodului cu proprietatea de mai sus
	 * @return	- returneaza nodul gasit sau null in cazul in care nu e gasit nodul
	 */
	private RadixTree first (String word , RadixTree node) {

		if (node.copii == null || node.copii.size() == 0) return null;
		ListIterator<RadixTree> iterator = node.copii.listIterator();
		RadixTree aux;
		while (iterator.hasNext()) {
			aux = iterator.next();
			if (aux.info.substring(0 , 1).equals(word.substring(0 , 1))) {
				return aux;
			}
		}
		return null;
	}
	/**metoda recursiva ce percurge cuvantul prin arbore pana nu mai poate fi parcurs
	 * 
	 * @param word	- prefixul ce trebuie parcurs
	 * @param node	- nodul curent cu care s-a ajuns cautarea
	 * @param parent	- parintele nodului cu care s-a ajuns cautarea
	 * @return	- returneaza nodul unde s-a oprit parcurgerea prefixului in arbore
	 */
	private RadixTree cautare (String word , RadixTree node , RadixTree parent) {
		
		int  i;	
		if (node == null) return null;
		for (i = 0 ; i < Math.min(word.length() , node.info.length()) ; i++) {
			if (!word.substring(i , i + 1).equals(node.info.substring(i , i + 1))) {
				break;
			}
		}
		if ( i < word.length() && i < node.info.length()) return null; 
		if ( i == word.length() && i <= node.info.length()) return node;
		if (i == node.info.length() && i < word.length()) {	
			word = word.substring(i);
			return cautare (word , first(word, node) , node);
		}
		return null;
	}
	/**metoda recursiva ce are ca scop afisarea in BFS a tuturor id-urilor nodurilor
	 * reamintesc ca id-ul reprezinta pozitiile cuvintelor respective in textul dat
	 *
	 * @param node	- radacina subarborelui cu care se incepe afisarea in BFS
	 * @return	- stringul returnat reprezinta output-ul construit recursiv , 
	 * mai exact acea variabila statica afisare
	 */
	private String afisare_subarbore (RadixTree node) {
		ListIterator<RadixTree> iterator = node.copii.listIterator();
		RadixTree aux;
		while (iterator.hasNext()) {
			aux = iterator.next();
			if (aux.iduri.size() != 0) {
				for (int i = 0 ; i < aux.iduri.size() ; i++) {
					numar_aparitii++;
					afisare = afisare + " " + aux.iduri.get(i);
				}
			}
			afisare_subarbore(aux);
		}
		return(afisare);
	}
	/**metoda nerecursiva ce e apelata in main si face cautarea cuvantului dat ca parametru
	 * 
	 * @param word	- cuvantul ce trebuie cautat
	 */
	public void find (String word) {
		
		afisare = "";
		numar_aparitii = 0;
		if (this.first(word, this) != null) {
			RadixTree node = cautare (word , this.first(word, this) , this);	
			if (node == null) {
				System.out.println("0");
				return;
			}
			if (node.iduri.size() != 0) {
			for (int i = 0 ; i < node.iduri.size() ; i++) {
				numar_aparitii++;
				afisare = afisare + " " + node.iduri.get(i);
			}
		}
		afisare = afisare_subarbore(node);
		System.out.println(numar_aparitii + afisare );	
		}
		else System.out.println("0");
	}
	/**metoda ce afiseaza in BFS tot arborele , e apelata in main si exista pur si simplu pentru ca
	 * mi-a fost foarte utila in a verifica daca metodele add si add_recursiv functioneaza corect
	 * 
	 * @param node	- radacina subarborelui 
	 */
	public void Debug (RadixTree node) {
		ListIterator<RadixTree> iterator = node.copii.listIterator();
		String afis = new String ();
		RadixTree aux;
		while (iterator.hasNext()) {
			aux = iterator.next();
			afis = afis + aux.info + aux.iduri;
			Debug (aux);
		}
		System.out.println(afis);
	}	
}
