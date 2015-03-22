package Screen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Shapes.Point;

/**
 * Class that represents the screen to be projected on
 */
public class Screen {
	private char[][] matrix;
	
	/**
	 * Builds the necessary data of the screen
	 * 
	 * @param sizeX	screen size on X axis
	 * @param sizeY	screen size on Y axis
	 */
	public Screen(int sizeX, int sizeY) {

		matrix = new char[sizeX][sizeY];
		for (int i=0 ; i<sizeX ; i++)
			for (int j=0 ; j<sizeY ; j++)
				matrix[i][j] = Constants.Symbols.SCREEN_SYMBOL;
	}
	/**
	 * Metoda de afisare a ecranului . Este folosita doar in Main si face scrierea efectiva in fisier_out
	 * @param path - reprezinta numele fisierului de intrare primit din Main
	 */
	public void Debug_Screen (String path) {
		try {
			 
			File file = new File(path + "_out");
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(content);
			
			String afis = "";
			for (int i = 0 ; i < matrix.length ; i++) {
				for (int j = 0 ; j < matrix.length ; j++)
					afis += matrix[i][j];
				bw.write(afis);
				bw.write("\n");
				afis = "";
			}
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Draws a line on the screen between the given points
	 * 
	 * @param startPoint	the first end of the line
	 * @param endPoint		the second end of the line
	 * @param symbol		the symbol the line is drawn with
	 */
	public void drawLineOnScreen(Point startPoint, Point endPoint, 
			char symbol) {

		ProcessingManagers.DrawManager.drawLine(matrix , startPoint, endPoint, symbol);

	}
	
	/**
	 * Draws multiple lines, each defined by startPoints[index] and
	 * endPoints[index] 
	 * 
	 * @param startPoints	array of first ends of the lines
	 * @param endPoints		array of second ends of the lines
	 * @param symbol		the symbol with which ALL lines are drawn
	 */
	public void drawMultipleLinesOnScreen(Point[] startPoints,
			Point[] endPoints, char symbol) {

		for (int i=0 ; i<startPoints.length ; i++){
			ProcessingManagers.DrawManager.drawLine(matrix , startPoints[i], endPoints[i], symbol);
		}
	}
}
