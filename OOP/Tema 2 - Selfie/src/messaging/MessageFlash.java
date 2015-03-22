package messaging;

import types.FlashType;
import types.TaskType;

public class MessageFlash extends Message{

	public FlashType stare_flash;
	public int [][][] matrice_pixeli;
	public int width_matrice , height_matrice;
	
	public MessageFlash(TaskType taskType , FlashType stare_flash,
								int [][][] matrice_pixeli , int width_matrice , int height_matrice) {
		super(taskType);
		this.stare_flash = stare_flash;
		this.matrice_pixeli = matrice_pixeli;
		this.width_matrice = width_matrice;
		this.height_matrice = height_matrice;
	}
	
	public void change_flash_stare (FlashType stare) {
		this.stare_flash = stare;
	}

}
