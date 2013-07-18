package Calculations;

import java.awt.Point;
/**
 * 
 * @author Administrator
 *  Kiszamol magara a framre vonatkozo koordinatakat.
 */
public class FrameModifier extends ScreenSizeCalculations {
	static Point framePosition;
	static Point cursorClicedPosition;
	static Point cursorCurrentPosition;
	
	/**
	 * 
	 * @param p Initializalja a framePosition -t
	 */
	static public void setFramePosition(Point p) {
		framePosition = p;
	}
	/**
	 * 
	 * @param p Initializalja a cursorClicedPosition-t
	 */
	static public void setCursorClickedPosition(Point p) {
		cursorClicedPosition = p;
	}
	
	/**
	 * 
	 * @param p Initializalja a cursorCurrentPosition-t
	 */
	static public void setCursorCurrentPosition(Point p) {
		cursorCurrentPosition = p;
	}
	
	/**
	 * A frame mozgatasanal kiszamolja az uj koordinatakat amire ki kell rajzolni a framet. 
	 * @return Visszateriti azt a pontot amire ujra kell rajzolni a framet.
	 */
	static public Point frameMoving() {
		int x,y;
		int dx,dy;
		dx = cursorClicedPosition.x - framePosition.x; 
		dy = cursorClicedPosition.y - framePosition.y;
		x = cursorCurrentPosition.x - dx;
		y = cursorCurrentPosition.y - dy;
		return new Point(x,y);
	}
}
