package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/** 
 * Egy elemet megjelenito/megvalosito panel.
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SquarePanel extends JPanel {	
	private int panelWidth;
	private int panelHeight;
	private Image img;
	private boolean isCurrent;
	private int currentX;
	@SuppressWarnings("unused")
	private int currentY;
	private int imageID;
	private boolean isDead;
	
	/**
	 * Konstruktor
	 * @param locationX Ennek a penelnek az X koordinataja.
	 * @param locationY Ennek a penelnek az Y koordinataja.
	 * @param panelWidth Ennek a penelnek a szelessege.
	 * @param panelHeight Ennek a penelnek a magassaga.
	 * @param x Jelenlegi X koordinata.
	 * @param y Jelenlegi Y koordinata.
	 */
	public SquarePanel(int locationX, int locationY, int panelWidth, int panelHeight, int x, int y) {
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		this.currentX = x;
		this.currentY = y;
		this.isCurrent = false;
		this.setBounds(locationX, locationY, panelWidth, panelHeight);
		this.imageID = 0;
		this.isDead = false;
	}
	
	/**
	 * Megnezi, hogy "halott"-e az adott elem.
	 * @return True - halott / False - nem halott
	 */
	public boolean getIsDead() {
		return isDead;
	}
	
	/**
	 * Beallitja, hogy halott e az adott elem.
	 * @param d Bool: True - halott / False - nem halott
	 */
	public void setIsDead(boolean d) {
		this.isDead = d;
	}
	
	/** 
	 * 
	 * @return Az adott elem kepet teriti vissza.
	 */
	public Image getImage() {
		return img;
	}
	
	/**
	 * Beallitja az adott elem kinezetet(kepet)
	 * @param i Adott kep.
	 * @param imgID Az adott kep ID-ja.
	 */
	public void setImage(Image i, int imgID) {
		this.imageID = imgID;
		if (i != null && imgID != 0)
			img = i;
		repaint();
	}
	
	/**
	 * Vissateriti az adott elem kepet
	 * @return Vissateriti az adott elem kepet
	 */
	public int getImageID() {
		return imageID;
	}
	
	/** 
	 * Beallitja az adott elem kepet.
	 * @param imgID Adott kep.
	 */
	public void setImageID(int imgID) {
		this.imageID = imgID;
	}
	
	/** 
	 * Beallitja az adott elem kepet
	 * @param i Adott kep.
	 * @param imgID A kep ID ja.
	 * @param b Az adott elem esik e jelnleg lefele vagy sem: True - esik False nem esik.
	 */
	public void setImage(Image i, int imgID, boolean b) {
		this.isCurrent = b;
		this.imageID = imgID;
		if (i != null )
			img = i;
		repaint();
	}
	
	/**
	 * 
	 * @return True - az adott elem esik lefele / False - az adott elem nem esik lefele.
	 */
	public boolean isCurrent() {
		return this.isCurrent;
	}
	
	/**
	 * Beallitja, hog az adott elem esik-e vagy sem lefele.
	 * @param b True- esik / False - nem esik
	 */
	public void setCurrent(boolean b) {
		this.isCurrent = b;
	}
	
	/**
	 * Kirajzolas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOpaque(false);
		if (currentX < 3) g.setColor(Color.red);
		else g.setColor(Color.white);
		
		if (this.imageID != 0 ) 
			g.drawImage(img, 0, 0,panelWidth,panelHeight, null);
		g.drawRect(0, 0, panelWidth, panelHeight);
	}
}
