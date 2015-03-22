import java.util.LinkedList;
import java.util.ListIterator;


public class RadixTree {
	
	private String info;
	private LinkedList<Integer> id;	//lista care retine pozitiile cuvintelor identice 
	private int contor;
	private LinkedList<RadixTree> children;
	
	public RadixTree (){
		this.children = new LinkedList<RadixTree>();
		this.id = new LinkedList<Integer>();
		this.contor=-1;
	}
	//ATENTIE: add va fi apelata doar pe obiectul mare , instantiat in Index
	public void add (String word , RadixTree node){	//daca vrei recursivitate , mai adauga un parametru , fie el nod sau id
		//avem 3 cazuri : 
		//1. cuvintele sunt identice
		//2. cuvintele difera total 
		//3. cuvintele difera partial 
		
		//verificam daca lista e goala ca sa nu avem null error , !sa verifici daca strings sunt diferite de null
/*		if (this.children.size() == 0){
			contor++;
			RadixTree T = new RadixTree();
			T.set_info(word);
			T.set_id(this.contor);
			this.children.add(T);
		}*/	//e posibil sa nu mai trebuiasca cazul in care nu am nimic in lista , oricum compare_first face asta
		//caz 2
		RadixTree aux ;
		aux = node.compare_first_letter(word , node);

		if (aux == null) {	//in caz ca nu gaseste cuvantul in arbore sau nus copii
			contor++;
			RadixTree T = new RadixTree();
			T.set_info(word);
			T.set_id(this.contor);
			node.children.add(T);
		//	System.out.println(contor);  //aux e mereu null , daia contor merge de la 0 la 8
			return;
		}
		//caz 1
		else if (aux.get_info().equals(word)){
			contor++;
			aux.set_id(contor);
			return;
		}
		//caz 3
		else {	
				int i , ok=0;
				for (i=1 ;i<=Math.min(word.length() ,aux.get_info().length()) ; i++){	//cauta partile comune
					
					if (!(aux.get_info().substring(i-1, i).equals(word.substring(i-1, i)))) {
						ok = 1;
						break;
					}
				}
				String parte_comuna;
				if (ok == 1)	parte_comuna = word.substring(0, i-1);	//va deveni info din noul nod creat
				else  parte_comuna = word.substring(0, i);

				word = word.substring(i);	//ce a mai ramas din cuvant;
				String rest = aux.get_info().substring(i);	//ce a mai ramas din info de la nod
				if (word == null){	//cuvantul e inclus in radacina
					RadixTree T = new RadixTree();
					T.set_info(parte_comuna);
					contor++;
					T.set_id(contor);
					T.add_child(aux);
					aux.set_info(rest);
					node.add_child(T);
					return;
				}
				else {
					RadixTree T = new RadixTree();
					RadixTree T2 = new RadixTree();
					T.set_info(parte_comuna);
					T2.set_info(word);
					T.add_child(T2);
					aux.set_info(rest);
					T.add_child(aux);
					contor++;
					T2.set_id(contor);
					node.add_child(T);
					node.add(word, aux);	//nu e gandita linia asta , poate fi retard pusa aici
				}
		}
		return;
	}
	public void afis_prim_nivel(){
		ListIterator<RadixTree> iterator = this.children.listIterator(0);
		while (iterator.hasNext()){
			System.out.println(iterator.next().get_info());
		}
	}
	public LinkedList<Integer> find (String word){	//metoda cu care caut cuvinte de prefix
		//ca si add , find va fi apelata in obiectul principal
		if (word == null) return this.get_id();
		RadixTree aux = this;
		aux = aux.compare_first_letter(word , aux);
		if (aux == null) return null;
		int i ;
		for (i=0 ;i<word.length() ; i++){//cauta partile comune
			if (aux.get_info() == null) break;
			if (!aux.get_info().substring(0, i).equals(word.substring(0, i))) break;	//aici fac substring pe un null
		}
		word = word.substring(i-1);
		aux.find(word);
		return null;
	}
	public RadixTree compare_first_letter (String word , RadixTree node){
		//folosim un Iterator pt parcurgerea listei
		ListIterator<RadixTree> iterator = node.children.listIterator(0);
		RadixTree T ;	//un auxiliar
		while (iterator.hasNext()){
			T = iterator.next();
//			System.out.println(" "+ word + "--" + T.get_info());
//			System.out.println(T.get_info().substring(0,1) + word.substring(0, 1));
			if (T.get_info().length() == 0) return null;
			if (T.get_info().substring(0,1).equals(word.substring(0,1))) {
				System.out.println(" a fost returnat ");
				return T;
			}
		}
		return null;
	}
	public void add_child (RadixTree nod){
		this.children.add(nod);
	}
	public void set_info (String info){
		this.info = info;
	}
	public void set_id (int id){
		this.id.add(id);
	}
	public String get_info (){
		return this.info;
	}
	public LinkedList<Integer> get_id (){
		return this.id;
	}
	public void add_copy (int id){
		this.id.add(id);
	}
}