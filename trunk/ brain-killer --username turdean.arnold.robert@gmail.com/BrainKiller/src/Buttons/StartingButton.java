package Buttons;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import SuperPanels.TheStartingPanel;
@SuppressWarnings("serial")
/**
 * 
 * @author Administrator
 *	Olyan gomb amely a kezdokepernyon helyeszkedik el
 */
public class StartingButton extends JPanel {
	private BufferedImage image;
	private int panelWidth;
	private int panelHeight;
	private String image1;
	private String image2;
	private TheStartingPanel instantiatesMethod;

	/**
	 * 
	 * @param panelWidth A hivo panel szelessege
	 * @param panelHeight A hivo panel magassaga
	 * @param locationX A hivo panel X helyzete
	 * @param locationY A hivo panel Y helyzete
	 * @param image11 A gomb kinezete
	 * @param image22 A gomb kinezete ha az eger rajta van
	 * @param methodName Meghatarozza mi tortenik ha megnyomjuk a gombot
	 * @param p Hivatkozas a peldanyosito osztalyra (TheStartingPanel kell legyen)
	 */
	public StartingButton(int panelWidth, int panelHeight, int locationX, 
			int locationY, String image11,  String image22, final String methodName, final TheStartingPanel p){
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		this.image1 = image11;
		this.image2 = image22;
		instantiatesMethod = p;
		try {
		image = ImageIO.read(new File(image1));
		} catch (IllegalArgumentException  e) {	
		} catch (IOException e) {
		}
		this.setFocusable(true);
		this.setSize(panelWidth,panelHeight);
		this.setLocation(locationX, locationY);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e1) {
					switch(methodName) {
					case "exit":
						exitTheFrame();
						break;
					case "startgame":
						startGame();
						break;
					}
			}
			public void mouseEntered(MouseEvent e) {
				try {
					image = ImageIO.read(new File(image2));
					} catch (IllegalArgumentException  e1) {	
					} catch (IOException e1) {
					}
				repaint();
			}
			public void mouseExited(MouseEvent e) {
				try {
					image = ImageIO.read(new File(image1));
					} catch (IllegalArgumentException  e1) {	
					} catch (IOException e1) {
					}
				repaint();
			}
		});
	}
	/**
	 * Kilep a jatekobol
	 */
	private void exitTheFrame() {
		System.exit(0);
	}
	
	/**
	 * Elinditja a jatekot
	 */
	private void startGame() {
		instantiatesMethod.setVisible(false);
		instantiatesMethod.mainFrame.setCurrentSuperPanel("TheGamePanel");
	}
	/**
	 * A gomb kirajzolasa
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOpaque(false);
		g.drawImage(image, 0, 0,panelWidth,panelHeight, null);
	}

}
