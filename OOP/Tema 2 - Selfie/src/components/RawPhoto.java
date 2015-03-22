package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class RawPhoto extends Component{

	public RawPhoto() {
		super(TaskType.RAW_PHOTO);
	}

	@Override
	public Message notify(Message message) {
		MessageImage imagine_rasturnata = (MessageImage) message;
	//	System.out.println("sunt in raw , dim_imagine : " + imagine_rasturnata.getHeight() + " " + imagine_rasturnata.getWidth());
		int pixxel_aux;
		for (int i = 0 ; i < imagine_rasturnata.getWidth() ; i++) {
			for (int j = 0 ; j <= imagine_rasturnata.getHeight() / 2 ; j++) {
				//pentru a avea complexitate spatiala O(1) , fac interschimbare cu diferente
				//si nu mai fac o copie a imaginii cum am facut la celelalte sa nu stric mesajul
				for (int k = 0 ; k < 3 ; k++) {
		/*			imagine_rasturnata.getPixels()[j][i][k] = Math.abs(imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k]
																- imagine_rasturnata.getPixels()[j][i][k]);
					if (imagine_rasturnata.getPixels()[j][i][k] < imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k]) {
						imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k] -= imagine_rasturnata.getPixels()[j][i][k];
					}
					else {
						imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k] += imagine_rasturnata.getPixels()[j][i][k];
					}
					if (imagine_rasturnata.getPixels()[j][i][k] > imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k]) {
						imagine_rasturnata.getPixels()[j][i][k] += imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k];
					}
					else {
						imagine_rasturnata.getPixels()[j][i][k] -= imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k];
					}
		*/	
					pixxel_aux = imagine_rasturnata.getPixels()[j][i][k];
					imagine_rasturnata.getPixels()[j][i][k] = imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k];
					imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() - j - 1][i][k] = pixxel_aux;
				}
			}
			for (int k = 0 ; k < 3 ; k++) {
				if (imagine_rasturnata.getHeight() % 2 == 0) {
					pixxel_aux = imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight() / 2][i][k];
					imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight()/2][i][k] = imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight()/2 - 1][i][k];
					imagine_rasturnata.getPixels()[imagine_rasturnata.getHeight()/2 - 1][i][k] = pixxel_aux;
				}
			}
		}
		//! mesajul primit ca parametru a fost modificat , deoarece s-a modificat obiectul la care am facut referinta
		return imagine_rasturnata;
	}

}
