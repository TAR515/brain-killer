package Buttons;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Operations.SquareGridOperations;
import SuperPanels.TheGamePanel;

/**
 * aaaaaaaaaaaaaa
 */
@SuppressWarnings("serial")
/**
 * Olyan gombok amelyek a TheGamePanel -en helyeszkednek el.
 * @author Administrator
 */
public class GameButton extends JPanel {
	private BufferedImage buttonImage;
	private BufferedImage mouseOnButtonImage;
	private BufferedImage image;
	private int panelWidth;
	private int panelHeight;
	private String image1;
	private String image2;
	private String buttonAction;
	private TheGamePanel instantiatesMethod;

	/**
	 * 
	 * @param panelWidth a hivo panel merete
	 * @param panelHeight a hivo panel merete
	 * @param locationX a hivo panel X koordinataja
	 * @param locationY a hivo panel Y koordinataja
	 * @param image11 -- Button Image A gomb kinezete
	 * @param image22 -- Mouse on Button Image A gomb kinezete ha az eger rajta van
	 * @param methodName Itt adjuk meg, h milyen muveletet vegezzen el a gomb a megnyomasakor
	 * @param p A peldanyosito osztaly ( TheGameButton osztaly kell legyen )
	 */
	public GameButton(int panelWidth, int panelHeight, int locationX, 
			int locationY, String image11,  String image22, final String methodName, final TheGamePanel p){
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		this.buttonAction = methodName;
		this.image1 = image11;
		this.image2 = image22;
		this.instantiatesMethod = p;
		try {
			buttonImage = ImageIO.read(new File(image1));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		
		try {
			mouseOnButtonImage = ImageIO.read(new File(image2));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		image = buttonImage;
		this.setFocusable(true);
		this.setSize(panelWidth,panelHeight);
		this.setLocation(locationX, locationY);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e1) {
					switch(buttonAction) {
					case "exit":
						exitTheFrame();
						break;
					case "pause": 
						pauseTheGame();
						break;
					case "resume": 
						resumeTheGame();
						break;
					case "saveTheScore":
						saveTheScore();
						break;
					case "restartTheGame":
						restartTheGame();
						break;
					}
			}
			public void mouseEntered(MouseEvent e) {
				image = mouseOnButtonImage;
				repaint();
			}
			public void mouseExited(MouseEvent e) {
				image = buttonImage;
				repaint();
			}
		});
	}
	/**
	 * Ujrainditja a jatekot
	 */
	private void restartTheGame() {
		instantiatesMethod.namePanel.setVisible(false);
		SquareGridOperations.clearAll(instantiatesMethod.squarePanel.grids);
		instantiatesMethod.squarePanel.gameScore = 0;
		instantiatesMethod.squarePanel.test.restart();
		try {
			buttonImage = ImageIO.read(new File("save.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		
		try {
			mouseOnButtonImage = ImageIO.read(new File("save_click.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		image = mouseOnButtonImage;
		repaint();
		buttonAction = "saveTheScore";
	}
	
	/**
	 * A jatekos nevenek es a pontjanak az elmentese
	 */
	private void saveTheScore() {
		if ( !instantiatesMethod.namePanel.tx.getText().toString().equals("") )
			instantiatesMethod.namePanel.loadData(instantiatesMethod.namePanel.tx.getText().toString(), 
					Integer.parseInt(instantiatesMethod.scorePanel.gameScore.getText().toString()));
		try {
			buttonImage = ImageIO.read(new File("restart.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		
		try {
			mouseOnButtonImage = ImageIO.read(new File("restart_click.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		image = mouseOnButtonImage;
		repaint();
		buttonAction = "restartTheGame";
	}
	
	/**
	 * Bezarja a programot
	 */
	private void exitTheFrame() {
		System.exit(0);
	}
	/**
	 * Szunetelteti a jetek menetet
	 */
	public void pauseTheGame() {
		instantiatesMethod.squarePanel.pauseTheGame();
		try {
			buttonImage = ImageIO.read(new File("resume.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		
		try {
			mouseOnButtonImage = ImageIO.read(new File("resume_click.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		image = mouseOnButtonImage;
		repaint();
		buttonAction = "resume";
	}
	
	/**
	 * Folytatja a jatek menetet
	 */
	public void resumeTheGame() {
		instantiatesMethod.squarePanel.resumeTheGame();
		try {
			buttonImage = ImageIO.read(new File("pause.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		
		try {
			mouseOnButtonImage = ImageIO.read(new File("pause_click.png"));
		} catch (IllegalArgumentException  e) {
			System.out.println("Error in opening file: " + e);
		} catch (IOException e) {
			System.out.println("Error in opening file: " + e);
		}
		image = mouseOnButtonImage;
		repaint();
		buttonAction = "pause";
	}
	/**
	 * Kirajzolo fuggveny
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOpaque(false);
		g.drawImage(image, 0, 0,panelWidth,panelHeight, null);
	}

}
