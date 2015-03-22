package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class Sepia extends Component{

	public Sepia() {
		super(TaskType.SEPIA);
	}

	@Override
	public Message notify(Message message) {
	//	System.out.println("Am intrat in sepia " + message);
		MessageImage imagine_sepia = (MessageImage) message;
		/*
		outputRed = Math.round( (inputRed * 0.393) + (inputGreen * 0.769) + (inputBlue * 0.189) );
		outputGreen = Math.round( (inputRed * 0.349) + (inputGreen * 0.686) + (inputBlue * 0.168) );
		outputBlue = Math.round( (inputRed * 0.272) + (inputGreen * 0.534) + (inputBlue * 0.131) ); 
		 */
		double input_red , input_green, input_blue;
		for (int j = 0 ; j < imagine_sepia.getHeight() ; j++) {
			for (int i = 0 ; i < imagine_sepia.getWidth() ; i++) {
				input_red = imagine_sepia.getPixels()[j][i][0];
				input_green = imagine_sepia.getPixels()[j][i][1];
				input_blue = imagine_sepia.getPixels()[j][i][2];
				imagine_sepia.getPixels()[j][i][0] = (int) Math.round(input_red * 0.393 + input_green* 0.769 + input_blue * 0.189);
				imagine_sepia.getPixels()[j][i][1] = (int) Math.round(input_red * 0.349 + input_green* 0.686 + input_blue * 0.168);
				imagine_sepia.getPixels()[j][i][2] = (int) Math.round(input_red * 0.272 + input_green* 0.534 + input_blue * 0.131);
				if (imagine_sepia.getPixels()[j][i][0] > 255) imagine_sepia.getPixels()[j][i][0] = 255;
				if (imagine_sepia.getPixels()[j][i][1] > 255) imagine_sepia.getPixels()[j][i][1] = 255;
				if (imagine_sepia.getPixels()[j][i][2] > 255) imagine_sepia.getPixels()[j][i][2] = 255;				
			}
		}
		//!iar nu fac o copie a matricei , se modifica si mesajul primit la input
		return imagine_sepia;
	}

}
