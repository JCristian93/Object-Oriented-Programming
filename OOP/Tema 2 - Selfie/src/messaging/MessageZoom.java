package messaging;

import types.TaskType;

public class MessageZoom extends Message {
	
	//momentan doar stocam datele ce vor fi prelucrate in Zoom din components
	public int[][][] pixxels ;		
	public int pixxels_width , pixxels_height;	
	public int pointLow_x , pointLow_y , pointHigh_x , pointHigh_y;
	
	public MessageZoom (TaskType type , int[][][] pixxels , 
							int pixxels_width , int pixxels_height , 
								int pointLow_x , int pointLow_y , int pointHigh_x , int pointHigh_y) {
		super (type);
		this.pixxels = pixxels;
		this.pixxels_width = pixxels_width;
		this.pixxels_height = pixxels_height;
		this.pointHigh_x = pointHigh_x;
		this.pointHigh_y = pointHigh_y;
		this.pointLow_x = pointLow_x;
		this.pointLow_y = pointLow_y;
	}
	//daca vreau tipul , sau alte chestii de baza sunt mostenite din Message
	
}
