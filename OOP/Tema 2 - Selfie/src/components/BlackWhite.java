package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class BlackWhite extends Component {

	public BlackWhite() {
		super(TaskType.BLACK_WHITE);
	}

	@Override
	public Message notify(Message message) {
	//	System.out.println("Am intrat in blackWhite");
		MessageImage imagine_black_white = (MessageImage) message;
		/*
		outputRed = Math.round( (inputRed * 0.3) + (inputGreen * 0.59) + (inputBlue * 0.11) );
		outputGreen = Math.round( (inputRed * 0.3) + (inputGreen * 0.59) + (inputBlue * 0.11) );
		outputBlue = Math.round( (inputRed * 0.3) + (inputGreen * 0.59) + (inputBlue * 0.11) ); 
		 */
		double input_red , input_green, input_blue;
		for (int j = 0 ; j < imagine_black_white.getHeight() ; j++) {
			for (int i = 0 ; i < imagine_black_white.getWidth() ; i++) {
				input_red = imagine_black_white.getPixels()[j][i][0];
				input_green = imagine_black_white.getPixels()[j][i][1];
				input_blue = imagine_black_white.getPixels()[j][i][2];
				imagine_black_white.getPixels()[j][i][0] = (int) Math.round(input_red * 0.3 + input_green* 0.59 + input_blue * 0.11);
				imagine_black_white.getPixels()[j][i][1] = (int) Math.round(input_red * 0.3 + input_green* 0.59 + input_blue * 0.11);
				imagine_black_white.getPixels()[j][i][2] = (int) Math.round(input_red * 0.3 + input_green* 0.59 + input_blue * 0.11);
				if (imagine_black_white.getPixels()[j][i][0] > 255) imagine_black_white.getPixels()[j][i][0] = 255;
				if (imagine_black_white.getPixels()[j][i][1] > 255) imagine_black_white.getPixels()[j][i][1] = 255;
				if (imagine_black_white.getPixels()[j][i][2] > 255) imagine_black_white.getPixels()[j][i][2] = 255;
				
			}
		}
		//!iar nu fac o copie a matricei , se modifica si mesajul primit la input
		return imagine_black_white;
	}

}
