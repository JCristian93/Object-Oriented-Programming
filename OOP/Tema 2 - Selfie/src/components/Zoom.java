package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;
import messaging.MessageZoom;

public class Zoom extends Component {
	
	public Zoom() {
		super (TaskType.ZOOM);
	}
	
	@Override
	public Message notify (Message message) {
		//primim un MessageZoom ce va contine matricea de pixeli , dim imaginii si 2 puncte 
		//trebuie sa returnam bucata de matrice intre alea 2 puncte
		MessageZoom zoom = (MessageZoom) message;	//ca sa nu mai fac cast de mii de ori
	//	System.out.println("sunt in zoom :");
	//	System.out.println(zoom.pixxels_width +" "+ zoom.pixxels_height);
	//	System.out.println(zoom.pointLow_y +" "+ zoom.pointHigh_y + " " + zoom.pointLow_x + " " + zoom.pointHigh_x);
		int [][][] imagine_zoomata = new int [zoom.pointHigh_y - zoom.pointLow_y + 1][zoom.pointHigh_x - zoom.pointLow_x + 1][3];
		for (int i = 0 ; i < 3 ; i ++) {
			for (int j = zoom.pointLow_y ; j <= zoom.pointHigh_y ; j++) {
				for (int k = zoom.pointLow_x ; k <= zoom.pointHigh_x ; k++) {
					imagine_zoomata[j - zoom.pointLow_y][k - zoom.pointLow_x][i] = zoom.pixxels[j][k][i];
					
				}
			}
		}
		MessageImage imagine_zoomata_finala = new MessageImage(TaskType.ZOOM , imagine_zoomata , 
							zoom.pointHigh_x - zoom.pointLow_x + 1 , zoom.pointHigh_y - zoom.pointLow_y + 1);
		
		return imagine_zoomata_finala;
	}
	
}
