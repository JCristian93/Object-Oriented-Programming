package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class Blur extends Component{

	public Blur() {
		super(TaskType.BLUR);
	}

	@Override
	public Message notify(Message message) {
	//	System.out.println("Am intrat in blur");
		MessageImage imagine_blur = (MessageImage) message;
		//facem o copie a matricei de pixeli deoarece depindem de vecini
		int [][][] blurred_img = new int [imagine_blur.getHeight()][imagine_blur.getWidth()][3];
		for (int j = 0 ; j < imagine_blur.getHeight() ; j++) {
			for (int i = 0 ; i < imagine_blur.getWidth() ; i++) {
				for (int k = 0 ; k < 3 ; k++) {
					blurred_img[j][i][k] = imagine_blur.getPixels()[j][i][k];
				}
			}
		}
		/* 
    	outputRed = Math.round( suma_valorilor_de_rosu_a_vecinilor / numarul_de_vecini );
    	outputGreen= Math.round( suma_valorilor_de_verde_a_vecinilor / numarul_de_vecini );
    	outputBlue = Math.round( suma_valorilor_de_albastru_a_vecinilor / numarul_de_vecini );

		 */
		int suma_vecini = 0 , nr_vecini = 0;	//suma asta e pentru toate culorile , se reseteaza cand schimb culoarea
		//trebuie sa aplicam efectul de 10 ori
		if (imagine_blur.getHeight() == 1 && imagine_blur.getWidth() == 1) {
			for (int i = 0 ; i < 3; i++) {
				blurred_img[0][0][i] = 0;
			}
			MessageImage imagine_blurata_finala = new MessageImage(TaskType.BLUR, blurred_img, 
					imagine_blur.getWidth(), imagine_blur.getHeight());
			return imagine_blurata_finala;
		}
		for (int loop = 0 ; loop < 10 ; loop ++) {
			if (imagine_blur.getHeight() == 1) {
				for (int i = 0 ; i < imagine_blur.getWidth() ; i++) {
					for (int k = 0 ; k < 3 ; k++) {
						suma_vecini = 0;
						if (i == 0){
							suma_vecini += blurred_img[0][1][k];
							nr_vecini = 1;
						}
						else if (i == imagine_blur.getWidth() - 1) {
							suma_vecini += blurred_img[0][imagine_blur.getWidth() - 2][k];
							nr_vecini = 1;
						}
						else {
							nr_vecini = 2;
							suma_vecini += blurred_img[0][i-1][k];
							suma_vecini += blurred_img[0][i+1][k];
						}
						imagine_blur.getPixels()[0][i][k] = Math.round((float)suma_vecini / (float)nr_vecini); 
					}
				}
			}
			else if (imagine_blur.getWidth() == 1) {
				for (int i = 0 ; i < imagine_blur.getHeight() ; i++) {
					for (int k = 0 ; k < 3 ; k++) {
						suma_vecini = 0;
						if (i == 0){
							suma_vecini += blurred_img[1][0][k];
							nr_vecini = 1;
						}
						else if (i == imagine_blur.getHeight() - 1) {
							suma_vecini += blurred_img[imagine_blur.getHeight() - 2][0][k];
							nr_vecini = 1;
						}
						else {
							nr_vecini = 2;
							suma_vecini += blurred_img[i-1][0][k];
							suma_vecini += blurred_img[i+1][0][k];
						}
						imagine_blur.getPixels()[i][0][k] = Math.round((float)suma_vecini / (float)nr_vecini); 
					}
				}
			}
	
			else {
			for (int j = 0 ; j < imagine_blur.getHeight() ; j++) {
				for (int i = 0 ; i < imagine_blur.getWidth() ; i++) {
					for (int k = 0 ; k < 3; k++) {
						suma_vecini = 0;
						if (i == 0 && j == 0) {
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j+1][i+1][k];
							nr_vecini = 3;
						}
						else if (i == 0 && j == imagine_blur.getHeight() - 1) {
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j-1][i+1][k];
							nr_vecini = 3;
						}
						else if (i == imagine_blur.getWidth() - 1 && j == 0) {
							suma_vecini += blurred_img[j][i-1][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j+1][i-1][k];
							nr_vecini = 3;
						}
						else if (i == imagine_blur.getWidth() - 1 && j == imagine_blur.getHeight() - 1) {
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j][i-1][k];
							suma_vecini += blurred_img[j-1][i-1][k];						
							nr_vecini = 3;
						}
						else if (i == 0) {
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j+1][i+1][k];
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j-1][i+1][k];
							nr_vecini = 5;
						}
						else if (j == 0) {
							suma_vecini += blurred_img[j][i-1][k];
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j+1][i+1][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j+1][i-1][k];
							nr_vecini = 5;
						}
						else if (i == imagine_blur.getWidth() - 1) {
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j + 1][i - 1][k];
							suma_vecini += blurred_img[j][i - 1][k];
							suma_vecini += blurred_img[j - 1][i - 1][k];
							nr_vecini = 5;
						}
						else if (j == imagine_blur.getHeight() - 1) {
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j][i-1][k];
							suma_vecini += blurred_img[j-1][i-1][k];
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j-1][i+1][k];
							nr_vecini = 5;
						}
						else {
							//cazul normal cu 8 vecini
							nr_vecini = 8;
							suma_vecini += blurred_img[j+1][i+1][k];
							suma_vecini += blurred_img[j+1][i][k];
							suma_vecini += blurred_img[j+1][i-1][k];
							suma_vecini += blurred_img[j-1][i+1][k];
							suma_vecini += blurred_img[j-1][i][k];
							suma_vecini += blurred_img[j-1][i-1][k];
							suma_vecini += blurred_img[j][i+1][k];
							suma_vecini += blurred_img[j][i-1][k];
						}
						imagine_blur.getPixels()[j][i][k] = Math.round((float)suma_vecini / (float)nr_vecini); 
					}
				}
				}	
			}
			//acum trebuie sa actualizez blurred_img	
			for (int j = 0 ; j < imagine_blur.getHeight() ; j++) {
				for (int i = 0 ; i < imagine_blur.getWidth() ; i++) {
					for (int k = 0 ; k < 3 ; k++) {
						blurred_img[j][i][k] = imagine_blur.getPixels()[j][i][k];
					}
				}
			}
		}
		MessageImage imagine_blurata_finala = new MessageImage(TaskType.BLUR, blurred_img, 
														imagine_blur.getWidth(), imagine_blur.getHeight());
		return imagine_blurata_finala;
	}

}
