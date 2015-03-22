ReadMe tema 2 POO - Fun with bullets 

@Author : Jalba Cristian 323 CA

Implementarea temei a pornit de la scheletul de cod primit pe elf.cs.pub.ro/poo.
Pentru a respecta cerintele temei am folosit un fel de arbore de mostenire , 
mai exact clasa Projectile e parintele clasei SimpleShell , SimpleShell are
ca si copil pe SpiderShot si asa mai departe . Ideea este ca toate tipurile
de proiectile se transforma in alte tipuri mai putin SimpleShell . Asadar este 
natural ca SimpleShell sa fie "radacina" arborelui de mosteniri . Relatiile de 
mosteniri au reiesit din cerinta temei . Comoditatea acestei idei de implementare
reiese din usurinta de a face o transformare , pur si simplu apelezi metoda shoot
din clasa parinte . Am intampinat anumite probleme , dar pana la urma am gasit 
solutii elegante . Spre exemplu atunci cand am folosit super.shoot(dist, shooterPosition);
si am intrat in shoot-ul din parinte , ma asteptam ca apelul  hitScreenAction(shooterPosition, ref); 
sa apeleze metoda hitScreenAction(shooterPosition, ref); din clasa parinte , dar in mod
bizar apela metoda copilului , nu a parintelui . Acest neajuns a fost rezolvat prin 
generalizarea metodei hitScreenAction(shooterPosition, ref); astfel incat sa poata fi
folosita de catre orice copil . Folosindu-ma de polymorfism l-am schimbat pe shape in functie
de clasa in care ma aflam . 

Am uitat sa precizez ca am creat inca 5 clase ce extind clasa BasicShape . 
Acestea au rolul de a desena formele mentionate in cerinta (patrat , dreptunghi , 
triunghi , romb , punct ) . Acel shape mentionat anterior este de tip BasicShape 
si el este instantiat in clasa ce contine forma ce vrem sa o desenam pentru proiectilul respectiv.

Am modificat TimeManager sa primeasca un string cu data tragerii din care va extrage 
ora , minutul , secunda , ce vor fi folosite pentru calculul lui shapeChangingDist
(distanta la care se transforma un proiectil).

In Main am folosit BufferedReader pentru citirea din fisier . Am gasit pe google in special
pe Mykong.com ca aceasta ar fi cea mai eficienta metoda si am folosit-o in consecinta.
De asemenea am preluat cod de pe Mykong pe care l-am adaptat la problema mea .
Am facut acest lucru deoarece nu sunt familiarizat cu citirea si scrierea din fisiere.
Pentru scriere am folosit BufferedWriter in metoda public void Debug_Screen (String path).
String path reprezinta numele fisierului de intrare primit ca argument din apelul metodei in Main .
Am implementat scrierea in Screen deoarece aveam acces direct la matricea ecranului si in fond
outputul problemei este chiar ecranul dupa ce a fost prelucrat . 
public void Debug_Screen (String path) este apelata in Main dupa ce nu mai sunt modificari ale
ecranului de facut . 

Tema insasi nu a fost dificila din punct de vedere al design-ului dar a necesitat un efort 
decent in a implementa toate tipicurile sale si a rezolva bugurile aparute . 
Codul nu e plin cu comentarii deoarece mare parte din el e de la sine inteles . 
Daca aveti intrebari la care nu am putut raspunde in acest ReadMe sau in comentariile din cod 
ma puteti contacta la adresa de mail : jalba.cristian0493@gmail.com   

Va urez o zi placuta :)