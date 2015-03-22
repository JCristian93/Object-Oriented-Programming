ReadMe tema 3 la POO - Tabela de dispersie

@Author : Jalba Cristian 323 CA

Rezolvarea temei a pornit de la scheletul de cod primit de pe site . Tot ce a trebuit sa fac
a fost sa implementez interfata MyHashMap din scheletul de cod fara a modifica structura 
acestuia . 
Ideea principala a fost sa creez metode noi gen acele versiuni "mici" ale put-ului , get-ului 
si remove-ului simplificandu-mi astfel munca ce a trebuit sa o depun . Daca nu le-as fi creat 
atunci as fi avut probleme datorate genericitatii codului . Am incercat sa folosesc un 
Array de Buckets in loc de ArrayList si sa fac cast la Object dar nu a mers acest lucru . 

Am implementat metodele in functie de precizatiile din cod si din cerinta . 

In Main am facut cele 2 teste cerute in a doua parte a cerintei . Am creat 2 instante ale 
MyHashMapImpl ( HASH si HASH2 ) prin care am adaugat valori random de tip Student si LazyStudent
si am facut 2000 de cautari pentru fiecare tip in parte . Ce am observat a fost ca din 10 rulari 
succesive , de fiecare data timpul de executie al testului cu Student a fost de 2-3 ori mai mic
decat timpul de executie al testului cu LazyStudent . Explicatia pentru acest comportament 
cred ca reiese din faptul ca hashCode-ul lui LazyStudent este constant . Astfel toate 
adaugarile se fac intr-un singur Bucket . Deci cu cat mai multe adaugari cu atat mai multe
elemente in lista corespunzatoare Bucket-ului  , deci cautarile vor deveni din ce in ce mai 
ingreunate . 

Alte informatii relevante se regasesc in cod si comentarii .
Daca aveti intrebari va raspund cu caldura pe adresa de mail : jalba.cristian0493@gmail.com