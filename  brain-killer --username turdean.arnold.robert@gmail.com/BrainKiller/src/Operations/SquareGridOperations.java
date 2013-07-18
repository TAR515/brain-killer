package Operations;

import java.awt.Image;

import Panels.SquarePanel;

/**
 * 
 * @author Administrator
 * Az osszes olyan muveletet valositja meg amely szugseges ahhoz, hogy a jatek mukodjon. Ugy mint pl: alakzatok mozgatasa turlese generalasa stb.
 */
public final class SquareGridOperations {
	
	/**
	 * Az osszes lefele eso alakzatot egyel lejebb lepteti at.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @return Visszateriti, hogy hany darab alakzatot mozgatott egyel lejebb.
	 */
	static public int moveDown(SquarePanel p[][]) {
		int actionPerformed = 0;
		for (int j = 0; j < 6; j++) {
			for(int k = 12; k > 0 ; k--) {
				if (p[k][j].getImageID() == 0 && p[k-1][j].getImageID() != 0) {
					p[k][j].setImage(p[k-1][j].getImage(), p[k-1][j].getImageID());
					p[k-1][j].setImage(null,0);
					p[k-1][j].setCurrent(false);
					p[k][j].setCurrent(true);
					actionPerformed++;
				}
				else p[k][j].setCurrent(false);
			}
		}
		return actionPerformed;
	}
	
	/**
	 * Az osszes lefele mozgathato alakzatot egyetlen lepessel a leheto leglejebb mozgatja.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @return Visszateriti, hogy hany darab alakzatot sikerul mozgatnia.
	 */
	static public int moveFastDown(SquarePanel p[][]) {
		int actionPerformed = 0;
		int actionsNow = 1;
		while (actionsNow != 0) {
			actionsNow = 0;
			for (int j = 0; j < 6; j++) {
				for(int k = 12; k > 0 ; k--) {
					if (p[k][j].getImageID() == 0 && p[k-1][j].getImageID() != 0) {
						p[k][j].setImage(p[k-1][j].getImage(), p[k-1][j].getImageID());
						p[k-1][j].setImage(null,0);
						p[k-1][j].setCurrent(false);
						p[k][j].setCurrent(true);
						actionPerformed++;
						actionsNow++;
					}
					else p[k][j].setCurrent(false);
				}
			}
		}
		return actionPerformed;
	}
	
	/**
	 * A lefele eso alakzatokat egyel balra mozgatja amennyiben ez lehetseges.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 */
	static public void moveLeft(SquarePanel p[][]) {
		boolean canMove = true;
		for(int i = 12; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (p[i][j].isCurrent()) {
					if ((j-1) < 0 || p[i][j-1].getImageID() != 0)
						canMove = false;
					if ((j-1)>=0 && p[i][j-1].getImageID() == 0 && canMove) {
						p[i][j-1].setImage(p[i][j].getImage(), p[i][j].getImageID());
						p[i][j].setImage(null, 0);
						p[i][j].setCurrent(false);
						p[i][j-1].setCurrent(true);
					}
				}
			}
		}
	}
	
	/**
	 * A lefele eso alakzatokat egyel balra mozgatja amennyiben ez lehetseges.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 */
	static public void moveRight(SquarePanel p[][]) {
		boolean canMove = true;
		for(int i = 12; i >= 0; i--) {
			for (int j = 5; j >= 0; j--) {
				if (p[i][j].isCurrent()) {
					if ((j+1)>5 || p[i][j+1].getImageID() != 0)
						canMove = false;
					if ((j+1)<=5 && p[i][j+1].getImageID() == 0 && canMove) {
						p[i][j+1].setImage(p[i][j].getImage(), p[i][j].getImageID());
						p[i][j].setImage(null, 0);
						p[i][j].setCurrent(false);
						p[i][j+1].setCurrent(true);

					}
				}
			}
		}
	}
	
	/**
	 * Ellenorzi, hogy a jateknak vege van - e 
	 * @param p eg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @return True - a jateknak vege / False - a jateknak NINCS vege
	 */
	static public boolean checkIfGameIsOver(SquarePanel p[][]) {
		boolean gameOver = false;
		for (int i = 0; i < 3;i++)
			for (int j = 0; j < 6; j++)
				if (!p[i][j].isCurrent() && p[i][j].getImageID() != 0)
					gameOver = true;
		return gameOver;
	}

	/**
	 * Letrehozza a kovetkezo megjelenitendo alakatokat ( random )
	 * @param p eg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @param img Alakzatok kinezeteket taralmazo IMG tomb.
	 * @param imgID Alakzatok ID-jakat tartalmazo tomb.
	 * @param isSuper Amennyiben kulonleges alakzatot kell rajzoljunk 1/2-t adunk at.
	 * @return Visszateriti a legeneralt alakzat ID-jet.
	 */
	static public int createFormation(SquarePanel p[][],Image img[], int imgID[], int isSuper) {
		int randFormation = 0;
		if (isSuper == 0) {
			int randColor = (int)(Math.random()*111);
			
			randFormation = (int) (Math.random()*6);
			if (randFormation == 0) {
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][3].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
			else if (randFormation == 1) {
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[0][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
			else if (randFormation == 2) {
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
			else if (randFormation == 3) {
				p[0][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
			else if (randFormation == 4) {
				p[0][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
			else if (randFormation == 5) {
				p[0][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[0][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][1].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
				randColor = (int)(Math.random()*111);
				p[1][2].setImage(img[randColorToTrueColor(randColor)-1], randColorToTrueColor(randColor), true);
			}
		}
		else if (isSuper == 1) {
			p[2][2].setImage(img[5], imgID[5], true);
			randFormation = 6;
		}
		else if (isSuper == 2) {
			p[2][2].setImage(img[6], imgID[6], true);
			randFormation = 7;
		}
		return randFormation;
	}
	
	/**
	 * Random szinID-t alakit at konkret szinID-ra.
	 * @param randColor Egy random szamot adunk at.
	 * @return Visszateriti a random szamot atalakitva a konkret szinre.
	 */
	static private int randColorToTrueColor(int randColor) {
		if (randColor > -1 && randColor < 26)
			return 1;
		if (randColor > 25 && randColor < 51)
			return 2;
		if (randColor > 50 && randColor < 76)
			return 3;
		if (randColor > 75 && randColor < 101)
			return 4;
		return 5;
	}
	
	/**
	 *  Visszateriti, hogy hany darab elem esik eppen lefele.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @return Visszateriti, hogy hany darab elem esik eppen lefele.
	 */
	static public int getNumberOfElement(SquarePanel p[][]) {
		int number = 0;
		for(int i = 12; i >= 0; i--) {
			for (int j = 5; j >= 0; j--) {
				if (p[i][j].isCurrent())
					number++;
			}
		}
		return number;
	}
	
	/**
	 * 
	 * @param formationCode A legeneralt alakzat formacios kodjat kell atadni.
	 * @return Visszateriti, hogy az adott alakzat hany darab elemet tartalmaz.
	 */
	static public int formationCodeToNumberOfElement(int formationCode) {
		if (formationCode == 6 || formationCode == 7)
			return 1;
		else if (formationCode == 0 || formationCode == 1 || formationCode == 3)
			return 3;
		else if (formationCode == 2 || formationCode == 4)
			return 2;
		else return 4;
	}
	
	/**
	 * Felrobasztja az osszes alakzatot a palyan amelyekre teljesulnek a feltetelek.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @return Visszateriti, hogy mennyi pontot kapunk az adott alakzatok felrobbanasaert.
	 */
	static public int checkForCaboom(SquarePanel p[][]) {
		int bonusScore = 0;
		
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++) {
				if ( p[i][j].getImageID() == 7 && i==12 ) 
					p[i][j].setIsDead(true);
				else if (p[i][j].getImageID() == 7 && p[i+1][j].getImageID() != 0) {
					int k = i;
					while (k < 13) { 
						p[k++][j].setIsDead(true);
					}
				}
			}
		
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++) {
				if ( p[i][j].getImageID() == 6 && i==12 ) 
					p[i][j].setIsDead(true);
				else if (p[i][j].getImageID() == 6 && p[i+1][j].getImageID() != 0) {
					if (p[i+1][j].getImageID() != 5)
						for (int k = 0; k < 13;k++)
							for (int l = 0; l < 6; l++)
								if (p[k][l].getImageID() == p[i+1][j].getImageID())
									p[k][l].setIsDead(true);
					p[i][j].setIsDead(true);
					bonusScore++;
				}
			}
		
		int sameElementsNumber = 0;
		int k = 0; int l = 0;
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++) {
				sameElementsNumber = 0;
				k = i; l = j;
				while (k < 13 && l < 6 && p[i][j].getImageID() == p[k][l++].getImageID() && p[i][j].getImageID() != 0 && p[i][j].getImageID() != 5) 
					sameElementsNumber++;
				if (sameElementsNumber > 2) {
					k = i; l = j; 
					while (sameElementsNumber != 0) {
						p[k][l++].setIsDead(true);
						sameElementsNumber--;
						bonusScore += 10;
					}
				}
				k = i; l = j; sameElementsNumber = 0;
				while (k < 13 && l < 6 && p[i][j].getImageID() == p[k++][l].getImageID() && p[i][j].getImageID() != 0 && p[i][j].getImageID() != 5) 
					sameElementsNumber++;
				if (sameElementsNumber > 2) {
					k = i; l = j;
					while (sameElementsNumber != 0) {
						p[k++][l].setIsDead(true);
						sameElementsNumber--;
						bonusScore += 10;
					}
				}
				k = i; l = j; sameElementsNumber = 0;
				while (k < 13 && l < 6 && p[i][j].getImageID() == p[k++][l++].getImageID() && p[i][j].getImageID() != 0 && p[i][j].getImageID() != 5) 
					sameElementsNumber++;
				if (sameElementsNumber > 2) {
					k = i; l = j; 
					while (sameElementsNumber != 0) {
						p[k++][l++].setIsDead(true);
						sameElementsNumber--;
						bonusScore += 10;
					}
				}
				k = i; l = j; sameElementsNumber = 0;
				while (k < 13 && l > -1 && p[i][j].getImageID() == p[k++][l--].getImageID() && p[i][j].getImageID() != 0 && p[i][j].getImageID() != 5) 
					sameElementsNumber++; 
				if (sameElementsNumber > 2) {
					k = i; l = j; 
					while (sameElementsNumber != 0) {
						p[k++][l--].setIsDead(true);
						sameElementsNumber--;
						bonusScore += 10;
					}
				}
			}
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++)
				if (p[i][j].getIsDead()) {
					p[i][j].setImage(null, 0);
					p[i][j].setIsDead(false);
				}
		return bonusScore;
	}
	
	/**
	 * Forgatja az adott lefele eso alakzatot.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 * @param numberOfCurrents Hany alakzat esik eppen lefele.
	 */
	static public void changeSmileysColors(SquarePanel p[][], int numberOfCurrents) {
		Image img = null;
		int imgID = 0;
		Image newImg = null;
		int newImgID = 0;
		int foundCurrents = 0;
		SquarePanel temp = null;
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++) {
				if (p[i][j].isCurrent() && foundCurrents == 0) {
					temp = p[i][j];
					img = temp.getImage();
					imgID = temp.getImageID();
					foundCurrents++;
				}
				else if (p[i][j].isCurrent() && foundCurrents != numberOfCurrents-1 && foundCurrents != 0) {
					newImg = p[i][j].getImage();
					newImgID = p[i][j].getImageID();
					p[i][j].setImage(img, imgID);
					img = newImg;
					imgID = newImgID;
					foundCurrents++;
				}
				else if (p[i][j].isCurrent() && foundCurrents == numberOfCurrents-1) {
					newImg = p[i][j].getImage();
					newImgID = p[i][j].getImageID();
					p[i][j].setImage(img, imgID);
					temp.setImage(newImg, newImgID);
					foundCurrents++;
				}
			}
				
	}
	
	/**
	 * Kitorli az egesz palyat.
	 * @param p Meg kell adnunk egy SquarePanel-eket tartalmazo ketdimenzios tombot.
	 */
	static public void clearAll(SquarePanel p[][]) {
		for (int i = 0; i < 13;i++)
			for (int j = 0; j < 6; j++)
				p[i][j].setImage(null, 0, false);
	}
}
