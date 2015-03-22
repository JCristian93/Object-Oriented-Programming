Readme Tema 2 POO : Selfie
@author Jalba Cristian

Implementarea temei a pornit de la scheletul dat in link-ul temei . Am inceput 
prin a creea componentele necesare centrelor de mesaje , conform specificatiilor
din enunt . In unele componente se poate vedea ca am creeat o copie a matricei de
pixeli din mesaj . Am facut asta pentru a ma asigura ca nu stric mesajul primit 
in componenta . Ulterior mi-am dat seama  ca nu voi mai folosi mesajul in forma 
lui intacta dar implementarea era deja facuta si nu am vrut sa mai intarzii cu 
progresul temei . 

In crearea mesajelor am luat ca exemplu MessageLoad si MessageSave . Am preferat
sa nu modific clasa abstracta MessageCenter asa ca am facut o implementare a ei
numita ConcreteMessageCenter in care am implementat algoritmul de publish exact
ca in pseudo-codul din cerinta . Am mai adaugat la algortim cateva verificari 
daca lista de componente din centru este sau nu null . 

Ca probleme aparute pot spune ca nu pusesem in Blur Math.round acolo unde faceam
impartirea sumei vecinilor la numarul de vecini pe fiecare bucata din pixxel . 

In principiu am respectat la litera toti pasii din enunt , nu am adaugat altceva 
decat o implementare a MessageCenter-ului . Nu am folosit Factory deoarece nu a 
fost absolut necesar . Cateodata mai am niste membri ai mesajelor ce i-am lasat 
publici pentru a nu mai implementa getter si setter . 

In SimulationManager in buildNetwork am citit din fisierul de configurare 
structura retelei . Pur si simplu am creeat obiecte de tip ConcreteMessageCenter
si le-am setat listele de vecini si componente , apoi la final am legat 
SimulationManager de primul centru din lista  (le-am legat folosind variabila 
messageCenter ) . 

Dupa ce am construit reteaua am citit de la input linie cu linie comenzile date
la STDIN pana cand intalnesc exit . Fiecare linie citita am parsat-o si am 
creeat mesaje corespunzatoare fiecarei bucati din comanda . Fiecare astfel de 
mesaj il trimit cu publish in cadrul retelei unde se activeaza algoritmul de
publish si se returneaza un mesaj cu rezultatul asteptat .  

Deasemenea pentru a putea urmari decursul mesajelor prin retea am lasat afisarea
din publish .

Alte explicatii se afla si in comentariile din cod.

