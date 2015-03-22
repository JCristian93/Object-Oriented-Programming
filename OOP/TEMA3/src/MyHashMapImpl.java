import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementarea interfetei MyHashMap . 
 * @author CJ
 *
 * @param <K>	- cheia 
 * @param <V>	- valoarea
 */

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {	
	
	//vector de Buckets 
	//V reprezinta VALOAREA iar K este CHEIA
	ArrayList<MyBucket<K,V>> buckets;
	int numBuckets;
	/**
	 * Constructorul implementarii , seteaza numarul maxim de 
	 * @param numBuckets
	 */
	public MyHashMapImpl(int numBuckets) {
		buckets = new ArrayList<MyBucket<K,V>>(numBuckets);
		for (int i= 0 ; i < numBuckets ; i++ ){
			MyBucket<K, V> galeata = new MyBucket<K,V>();
			buckets.add(galeata);
		}
		this.numBuckets = numBuckets;
	}
	/**
	 * Metoda care preia valoarea elementului ce are cheia key
	 * Am folosit un get "mic" care preia din lista corespunzatoare bucketului la care functia hashCode a key-ului ne directioneaza
	 */
	@Override
	public V get(K key) {
		//preluare de element cu cheia K si returnare valoare element
		//va folosi un get mic din MyBucket
		return (buckets.get(this.translate(key.hashCode()))).get(key); 
	}
	/**
	 * Metoda care creaza un element cu cheia key si valoarea value si il adauga intr-un bucket. Returneaza null sau valoarea anterioara a cheii.
	 * Am folosit un put "mic" care creaza efectiv elementul si face adaugarea in lista de elemente. 
	 */
	@Override
	public V put(K key, V value) {
		//adaugare de element si returnare valoare a ultimului element adaugat
		//va folosi un put mic din MyBucket
		return (buckets.get(this.translate(key.hashCode()))).put(key, value);
	}
	/**
	 * Metoda care sterge elementul cu cheia key si returneaza valoarea elementului sters.
	 * Am folosit un remove "mic" care face stergerea efectiva . 
	 */
	@Override
	public V remove(K key) {
		//stergere de element si returnare valoare element sters
		//va folosi un remove mic din MyBucket
		return (buckets.get(this.translate(key.hashCode()))).remove(key);
	}
	/**
	 * Returneaza numarul de elemente (Entr-uri) din structura noastra . 
	 */
	@Override
	public int size() {
		//retine numarul de Entry-uri 
		int size = 0;	//numar cate elemente nu sunt goale in lista
		for (MyBucket<K, V> t : buckets){
			if (t.isEmpty() == false) {
				size += t.size();
			}
		}
		return size;
	}
	/**
	 * Returneaza lista lista de Bucket-uri.
	 */
	@Override
	public List<? extends Bucket<K, V>> getBuckets() {
		//returneaza vectorul de Buckets
		return buckets;
	}
	//metoda care transforma hashCode-ul primit in formatul pe care il folosim	
	/**
	 * Metoda care primeste un hashCode ca si parametru si returneaza indicele unui bucket
	 * @param translatat - hashCode - ul primit 
	 * @return - indicele bucket-ului 
	 */
	private int translate (int translatat){
		return Math.abs(translatat) % numBuckets;
	}
	/**
	 * Entry reprezinta un element adaugat la structura noastra . Are o cheie si valoare unica.
	 * @author CJ
	 *
	 * @param <K> - tipul cheii
	 * @param <V> - tipul valoarii
	 */
	class MyEntry <K,V> implements Entry<K, V>{
		//datele specifice unui entry
		K cheie;
		V valoare;
		//in constructor setez datele 
		/**
		 * seteaza cheia si valoarea
		 * @param cheie
		 * @param valoare
		 */
		public MyEntry(K cheie , V valoare) {
			this.cheie = cheie;
			this.valoare = valoare;
		}
		/**
		 * seteaza valoarea
		 * @param valoare
		 */
		public void setValue (V valoare) {
			this.valoare = valoare;
		}
		/**
		 * returneaza cheia
		 */
		@Override
		public K getKey() {
			//returneaza cheia
			return cheie;
		}
		/**
		 * returneaza valoarea
		 */
		@Override
		public V getValue() {
			//returneaza valoarea
			return valoare;
		}
		
	}
	/**
	 * Reprezinta "container-ul" pentru Entry-uri . 
	 * @author CJ
	 *
	 * @param <K> - tipul cheii
	 * @param <V> - tipul valorii
	 */
	class MyBucket<K,V> implements Bucket<K, V>{

		//lista de elemente
		LinkedList<MyEntry<K,V>> elemente;
		int empty;
		public MyBucket() {
			elemente = new LinkedList<MyEntry<K,V>>();
			empty = 1;
		}
		/**
		 * verifica daca Bucket-ul e gol sau nu
		 * @return
		 */
		public boolean isEmpty (){
			if (empty == 1) return true;
			return false;
		}
		/**
		 * Intoarce dimensiunea Bucket-ului
		 * @return
		 */
		public int size (){
			return elemente.size();
		}
		@Override
		/**
		 * Returneaza lista de elemente dintr-un Bucket
		 */
		public List<? extends Entry<K, V>> getEntries() {
			//returneaza lista de elemente din Bucket
			return elemente;
		}
		/**
		 * Get "mic" . Returneaza valoarea elementului care are cheia key.
		 * @param key
		 * @return
		 */
		public V get(K key) {
			//preluare de element cu cheia K si returnare valoare element
			for (MyEntry<K, V> t : elemente){
				if (t.getKey().equals(key)) return t.getValue();
			}
			return null;	//in caz ca nu e gasita
		}
		/**
		 * Put "mic" . Seteaza valoarea elementului cu cheia key la value sau creaza un nou element daca el nu exista deja .
		 * @param key
		 * @param value
		 * @return
		 */
		public V put(K key, V value) {
			//adaugare de element si returnare valoare a ultimului element adaugat
			//adauga elementul si returneaza valoarea anterioara a cheii
			//null daca nu mai exista elementul respectiv
			empty = 0;
			for (MyEntry<K, V> t : elemente) {
				if (t.getKey().equals(key)) {
					V aux = t.getValue();
					t.setValue(value);
					return aux;
				}
			}
			MyEntry<K, V> e = new MyEntry<K, V>(key , value);
			elemente.add(e);
			return null;
		}
		/**
		 * Remove "mic" . Sterge elementul cu cheia Key si ii returneaza valoarea . Un fel de pop () de la stive :D .
		 * @param key
		 * @return
		 */
		public V remove(K key) {	//un fel de pop()
			//stergere de element si returnare valoare element sters
			//@return valoarea asociata cu cheia key SAU null daca cheia nu exista
			for (MyEntry<K, V> t : elemente) {
				if (t.getKey().equals(key)) {
					V aux = t.getValue();
					elemente.remove(t);
					return aux;
				}
			}
			return null;
		}

	}
}
