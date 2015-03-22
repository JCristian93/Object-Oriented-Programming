Acesta este un Readme pt tema 1 la POO (Radix Tree)
author : Jalba Cristian 323 CA

	Inainte de a incepe implementarea temei am avut urmatoarea dilema:
Care e cea mai accesibila modalitate de a rezolva tema ? Puteam sa am o clasa Node
si o clasa RadixTree care sa extinda clasa Node , dar aceasta abordare s-a dovedit 
a fi prea complicata. Asa ca am preferat sa pun tot codul intr-o clasa RadixTree 
care va actiona ca o clasa de tip Node.
	Sunt 2 metode principale in cod , add si find . In fiecare din acestea 2 
sunt apelate alte metode recursive care fac parcurgerea prin arbore , cautarea de
cuvinte in arbore , afisarea de subarbore , etc . add si find sunt nerecursive ca 
sa pot trata acele cazuri speciale in care arborele este gol si trebuie sa adaug 
primul element sau se gaseste cuvantul pe primul nivel din arbore . 
	Cum am spus si mai sus RadixTree se comporta ca un nod , deci va avea 
urmatoarele atribute : 
   *info - reprezinta informatia utila de pe nod , mai exact cuvantul pe care il 
dam ca parametru pt functia add . 
   *copii - reprezinta lista de noduri pe care o are fiecare nod . Orice nod din 
arbore are copii , care vor avea copii la randul lor , deci lista e de tip RadixTree
   *iduri - reprezinta lista pozitiilor cuvintelor din textul dat . Este o lista 
deoarece cuvintele pot aparea de mai multe ori in text si trebuie inregistrate 
toate pozitiile duplicatelor . Am ales LinkedList si la copii si la iduri deoarece 
eram familiarizat cu LinkedList de mai mult timp . Stiu ca solutia cea mai eficienta 
este ArrayList dar moemntan nu am vrut sa incerc ceva nou . 
   *contor - numara cate cuvinte sunt adaugate in arbore , dupa ce se adauga un nod
se adauga contor la lista sa de iduri
   Celelalte 2 atribute sunt folosite pentru afisare , le-am mentionat in comentarii in cod . 
	In metoda privata add_recursiv determin acele 3 String-uri iar in functie de 
lungimile imi dau seama pe ce caz trebuie sa merg . 
	Metoda first e folosita atat in add cat si in fid . Ea cauta primul copil al 
nodului dat care are prima litera egala cu prima litera a cuvantului dat ca parametru.
Ma bazez pe proprietatea RadixTree-ului ca toate nodurile din arbore vor avea copii cu 
prima litera unica . Astfel eficientizez foarte mult cautarea in arbore . 
	Metoda cautare din find parcurge cuvantul primit ca parametru pana cand
acesta este inclus in info-ul unui nod . Toate acele ifuri se asigura ca acopar
toate cazurile posibile astfel incat recursivitatea sa aiba sens . 
	afisare_subarbore si Debug sunt metode asemanatoare deoarece ambele 
afiseaza in BFS arborele ce are ca radacina nodul primit ca parametru. Singura diferenta
intre cele 2 este variabila statica din afisare_subarbore ce reprezinta outputul si este
afisata in find.
	Singurul motiv pentru care am pastrat Debug este faptul ca am folosit aceasta 
metoda atunci cand m-am asigurat ca este corecta inserarea mea . 
	Pentru mai multe informatii verificati comentariile din cod precum si javadoc-ul
generat. 

	Pentru intrebari suplimentare ma puteti contacta la urmatoarea adresa : 
					
						jalba.cristian0493@gmail.com
 

