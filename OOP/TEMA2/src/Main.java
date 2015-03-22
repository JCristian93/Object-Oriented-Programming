
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ProcessingManagers.TimeManager;
import Projectiles.CanisterShot;
import Projectiles.Carcass;
import Projectiles.ChainShot;
import Projectiles.HeatedShot;
import Projectiles.Projectile;
import Projectiles.Shrapnel;
import Projectiles.SimpleShell;
import Projectiles.SpiderShot;
import Projectiles.TriGrapeShot;
import Screen.Screen;
import Shapes.Point;
/**
 * Tema 2 la POO 
 * @author CJ
 *
 */
public class Main {
	/**
	 * Fisierul de iesire va fi args[0]_out
	 * @param args - args[0] reprezinta numele fisierului de intrare.
	 */
	
	public static void main(String[] args) {
		BufferedReader br = null;
		int screen_x , screen_y , N , ref , dist , pos_x , pos_y; 
		Screen ecran ;
		try {
 
			String sCurrentLine , tip_proiectil = ""  , date = "";
 
			br = new BufferedReader(new FileReader(new File(args[0])));
			sCurrentLine = br.readLine();	//dimensiunile ecranului
			String [] coordonate = sCurrentLine.split(" ");
			
			//dimensiunile ecranului
			screen_x = Integer.parseInt(coordonate[0]);
			screen_y = Integer.parseInt(coordonate[1]);
			ecran = new Screen(screen_x, screen_y);
			
			//numarul de proiectile
			sCurrentLine = br.readLine();
			N = Integer.parseInt(sCurrentLine);
			Projectile proiectil;
			while ((sCurrentLine = br.readLine()) != null) {	//repetitia principala a temei
				//obtinem datele de intrare din fiecare linie a fisierului
				coordonate = sCurrentLine.split(" ");
				tip_proiectil += coordonate[0];
				//referinta
				ref = Integer.parseInt(coordonate[1]);
				//ora la care se trage proiectilul
				date += coordonate [2];
				//distanta de parcurs
				dist = Integer.parseInt(coordonate[3]);
				//punctul de plecare
				pos_x = Integer.parseInt(coordonate[4]);
				pos_y = Integer.parseInt(coordonate[5]);
				Point shooterPosition = new Point(pos_x , pos_y);
				//aici facem apelurile rezolvarii temei 
				if (tip_proiectil.equals(Constants.ProjectileNames.SIMPLE_SHELL)){
					proiectil = new SimpleShell(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.SPIDER_SHOT)){
					proiectil = new SpiderShot(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.SHRAPNEL)){
					proiectil = new Shrapnel(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.HEATED_SHOT)){
					proiectil = new HeatedShot(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.TRI_GRAPE_SHOT)){
					proiectil = new TriGrapeShot(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.CHAIN_SHOT)){
					proiectil = new ChainShot(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.CANNISTER_SHOT)){
					proiectil = new CanisterShot(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else if (tip_proiectil.equals(Constants.ProjectileNames.CARCASS)){
					proiectil = new Carcass(ecran , ref , new TimeManager(date));
					proiectil.shoot(dist, shooterPosition);
				}
				else System.out.println("ERROR : Nu corespunde unui tip predefinit! ");
				
				//aici se termina rezolvarea temei , sper sa mearga :D
				
				tip_proiectil = "";	//resetez stringurile ca sa nu se pastreze valori deja folosite
				date = "";
			}
			ecran.Debug_Screen(args[0]);	//afisarea ecranului
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
