package Calculations;

import java.awt.Dimension;

/**
 * 
 * @author Administrator
 *	A Frame meretere/helyere vonatkozo informaciokat szamit ki
 */
public class ScreenSizeCalculations {
	
	/**
	 * 
	 * @param screenSize A kepernyo meretet kell megadnunk
	 * @return Visszateriti a Frame szelesseget 
	 */
	static final public int getFrameSizeWidth(Dimension screenSize) {
		return screenSize.width/2;
	}
	
	/**
	 * 
	 * @param screenSize A kepernyo meretet kell megadnunk
	 * @return Visszateriti a Frame magassagat
	 */
	static final public int getFrameSizeHeight(Dimension screenSize) {
		return 15*screenSize.height/16;
	}
	
	/**
	 * 
	 * @param screenSize A kepernyo meretet kell megadnunk
	 * @return Visszateriti a Frame X koordinatajat
	 */
	static final public int getFrameLocationX(Dimension screenSize) {
		return screenSize.width/4;
	}
	
	/**
	 * 
	 * @param screenSize A kepernyo meretet kell megadnunk
	 * @return Visszateriti a Frame Y koordinatajat
	 */
	static final public int getFrameLocationY(Dimension screenSize) {
		return screenSize.height/64;
	}
}
