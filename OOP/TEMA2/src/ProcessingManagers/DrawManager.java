package ProcessingManagers;	//era IOManager

import Shapes.Point;

public class DrawManager {
	
	/**
	 * Draws a line in the given matrix betweenn the given points;
	 * the line is drawn with the given symbol
	 * 
	 * @param matrix	area in which to draw the line
	 * @param start		the first end of the line to be drawn
	 * @param end		the second end of the line to be drawn
	 * @param symbol	the symbol with which to draw the line
	 */
	public static void drawLine(char[][] matrix, Point start, Point end, 
			char symbol) {
		Point swap;
		if(start.getX() > end.getX()) {
			swap = start;
			start = end;
			end = swap;
		} else if(start.getX() == end.getX() && start.getY() > end.getY()) {
			swap = start;
			start = end;
			end = swap;
		}		
		int ySize 		= matrix.length,
			xSize 		= matrix[0].length,
			distanceOnX	= Math.abs(start.getX() - end.getX()),
			distanceOnY	= Math.abs(start.getY() - end.getY()),
			startX		= start.getX(),
			startY		= start.getY(),
			endX		= end.getX(),
			endY		= end.getY();
		boolean notTranspose = true;
		if(distanceOnX < distanceOnY) {
				Point start2= new Point(startY,startX), 
					  end2 	= new Point(endY,endX);
				int aux;

				start = start2;
				end = end2;
				aux = xSize;
				xSize = ySize;
				ySize = aux;
				notTranspose = false;
		}
		startX		= start.getX();
		startY		= start.getY();
		endX		= end.getX();
		endY		= end.getY();
		
		if (startX != endX) {
			float m = (float)(startY-endY)/(startX-endX);
			float n = startY - m*startX;
			int   x	= Math.min(startX, endX),
				  xMax= Math.max(startX, endX),
				  y;
			
			for (; x <= xMax; x++) {
				y = (int)(m*x + n);
				
				if (x >= 0 && x < xSize && y >= 0 && y < ySize) 
					if(notTranspose)
						matrix[y][x] = symbol;
					else
						matrix[x][y] = symbol;
			}
		} else {
			int y 	= Math.min(startY, endY),
				yMax= Math.max(startY, endY);
			
			for (; y <= yMax; y++) {
				if (startX >= 0 && startX < xSize && y >= 0 && y < ySize)
					if(notTranspose)
						matrix[y][startX] = symbol;
					else
						matrix[startX][y] = symbol;
			}
		}
	}

}
