package components;

import messaging.Message;
import messaging.MessageFlash;
import messaging.MessageImage;
import types.FlashType;
import types.TaskType;

public class Flash extends Component{

	public Flash() {
		super(TaskType.FLASH);
	}

	@Override
	public Message notify(Message message) {
		
		MessageFlash flashlight = (MessageFlash) message;
		int [][][] matrice_pixeli = new int [flashlight.height_matrice][flashlight.width_matrice][3];
	//	System.out.println("statea FLASH : " + flashlight.stare_flash);
		for (int i = 0 ; i < 3 ; i ++) {
			for (int j = 0 ; j < flashlight.height_matrice ; j++) {
				for (int k = 0 ; k < flashlight.width_matrice ; k++) {
					matrice_pixeli[j][k][i] = flashlight.matrice_pixeli[j][k][i];
				}
			}
		}
		
		if (flashlight.stare_flash.compareTo(FlashType.ON) == 0) {
			//adaug 50 la fiecare pixel din matrice , ce depaseste 255 e setat la 255
			for (int i = 0 ; i < 3 ; i ++) {
				for (int j = 0 ; j < flashlight.height_matrice ; j++) {
					for (int k = 0 ; k < flashlight.width_matrice ; k++) {
						matrice_pixeli[j][k][i] += 50;
						if (matrice_pixeli[j][k][i] > 255) matrice_pixeli[j][k][i] = 255;
					}
				}
			}
		}
		else if (flashlight.stare_flash.compareTo(FlashType.AUTO) == 0) {
			// L = Math.round(0.2126*R + 0.7152*G + 0.0722*B) - luminozitatea unui pixel
			//se va face luminozitatea medie
			//Daca luminozitatea medie este mai mica decat 60 atunci vom adauga 50 la toate canalele (RGB) precum am facut la optiunea ON.
			//Altfel lasam imaginea asa cum este.
		//	System.out.println("ba gogule , esti in AUTO!");
			double luminozitate_totala = 0;
			int nr_pixeli = 0;
			for (int j = 0 ; j < flashlight.height_matrice ; j++) {
				for (int k = 0 ; k < flashlight.width_matrice ; k++) {
					luminozitate_totala += Math.round(0.2126*matrice_pixeli[j][k][0] + 
							0.7152 * matrice_pixeli[j][k][1] + 0.0722 * matrice_pixeli[j][k][2]);
					nr_pixeli++;
				}
			}
			if ((double)(luminozitate_totala / (double)nr_pixeli) < 60){
				//in cazul asta facem ca la ON
				for (int i = 0 ; i < 3 ; i ++) {
					for (int j = 0 ; j < flashlight.height_matrice ; j++) {
						for (int k = 0 ; k < flashlight.width_matrice ; k++) {
							matrice_pixeli[j][k][i] += 50;
							if (matrice_pixeli[j][k][i] > 255) matrice_pixeli[j][k][i] = 255;
						}
					}
				}
			}
			//altfel lasam asa cum e
		}
		else {	//cazul pentru OFF
			//nu fac ceva , matricea ramane la fel
		}
		MessageImage flashimage = new MessageImage(TaskType.FLASH, matrice_pixeli, flashlight.width_matrice, flashlight.height_matrice);
		return flashimage;
	}

}
