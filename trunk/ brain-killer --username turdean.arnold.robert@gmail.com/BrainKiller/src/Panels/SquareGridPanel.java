package Panels;

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Operations.SquareGridOperations;
import SuperPanels.TheGamePanel;

@SuppressWarnings("serial")
/**
 * A jetek tablazatat vezerlo panel.
 * @author Administrator
 *
 */
public class SquareGridPanel extends JPanel {
	TheGamePanel gamePanel;
	public SquarePanel grids[][];
	Image smileys[];
	int i=6; int j=2;
	int delay = 1000;
	int smileysID[];
	public Timer test;
	int formationCode;
	int numberOfElement;
	public int gameScore;
	int gameOver;
	boolean buyBoomSmiley;
	boolean buyRainbowSmiley;
	
	/**
	 * Konstruktor.
	 * @param locationX Peldanyosito osztaly X koordinataja.
	 * @param locationY Peldanyosito osztaly Y koordinataja.
	 * @param panelWidth Peldanyosito osztaly szelessege.
	 * @param panelHeight Peldanyosito osztaly magassaga.
	 * @param p Peldanyosito osztaly (TheGamePanel)
	 */
	public SquareGridPanel(int locationX, int locationY, int panelWidth, int panelHeight, final TheGamePanel p) {
		this.setLayout(null);
		this.gamePanel = p;
		this.setSize(panelWidth, panelHeight);
		this.setLocation(locationX, locationY);
		this.gameScore = 0;
		this.gameOver = 0;
		this.buyRainbowSmiley = false;
		this.buyBoomSmiley = false;
		// Opening Smiley Images
		smileys = new Image[7];
		try {
			smileys[0] = ImageIO.read(new File("smiley1_1.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[1] = ImageIO.read(new File("smiley2.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[2] = ImageIO.read(new File("smiley3.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[3] = ImageIO.read(new File("smiley4.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[4] = ImageIO.read(new File("smiley5.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[5] = ImageIO.read(new File("smiley6.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		
		try {
			smileys[6] = ImageIO.read(new File("smiley7.png"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		smileysID = new int[7];
		smileysID[0] = 1;
		smileysID[1] = 2;
		smileysID[2] = 3;
		smileysID[3] = 4;
		smileysID[4] = 5;
		smileysID[5] = 6;
		smileysID[6] = 7;
		// -----------------------------------------------------------------------------------
		
		grids = new SquarePanel[13][6];
		for(int i = 0;i < 13; i++)
			for(int j = 0; j < 6; j++) {
				grids[i][j] = new SquarePanel((int)(j*panelWidth*0.167),(int)(i*panelHeight*0.077),
						(int)(panelWidth*0.167),(int)(panelHeight*0.077),i,j);
				this.add(grids[i][j]);
			}

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) { // ha nem tortent semmi
				if (SquareGridOperations.moveDown(grids) == 0) {
					numberOfElement = 0;
					
					int bonusScore = SquareGridOperations.checkForCaboom(grids);
					if (!buyRainbowSmiley)
						gameScore += bonusScore;
					
					if ( bonusScore == 0 && !buyRainbowSmiley && !buyBoomSmiley) 
						numberOfElement = SquareGridOperations.formationCodeToNumberOfElement
							(SquareGridOperations.createFormation(grids, smileys, smileysID,0));
					if ( bonusScore == 0 && buyRainbowSmiley) {
						numberOfElement = SquareGridOperations.formationCodeToNumberOfElement
						(SquareGridOperations.createFormation(grids, smileys, smileysID,1));
						buyRainbowSmiley = false;
					}
					if ( bonusScore == 0 && buyBoomSmiley) {
						numberOfElement = SquareGridOperations.formationCodeToNumberOfElement
						(SquareGridOperations.createFormation(grids, smileys, smileysID,2));
						buyBoomSmiley = false;
					}
					System.out.println("Score: " + gameScore);
					gamePanel.setGameScore(gameScore);
				}
				if (numberOfElement != SquareGridOperations.getNumberOfElement(grids))
					SquareGridOperations.moveFastDown(grids);
				
				if (SquareGridOperations.checkIfGameIsOver(grids)) { // a jateknak vege van-e ?
					int bonusScore = SquareGridOperations.checkForCaboom(grids);
					if (!buyRainbowSmiley)
						gameScore += bonusScore;
					SquareGridOperations.moveFastDown(grids);
					if (SquareGridOperations.checkIfGameIsOver(grids)) {
						System.out.println("GameOver");
						test.stop();
						gamePanel.gameOver();
					}
				} 
			}
		};
		test = new Timer(delay, taskPerformer);

		test.start();	
		Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalKeyLisetener(), AWTEvent.KEY_EVENT_MASK);
	}
	
	/**
	 * Kirajzolas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOpaque(false);
	}
	
	/**
	 * Jatek pauselasa.
	 */
	public void pauseTheGame() {
		test.stop();
	}
	
	/**
	 * Jatek folytatasa
	 */
	public void resumeTheGame() {
		test.start();
	}
	
	/**
	 * Globalis key listener amely a jatekpanelben ervenyes csak. Vezerli a jatek alatti iranyitast.
	 * @author Administrator
	 *
	 */
	private class GlobalKeyLisetener implements AWTEventListener {
		/**
		 * Megvalositas.
		 */
		public void eventDispatched(AWTEvent e) {
            if (e instanceof KeyEvent && (e.getID() == KeyEvent.KEY_PRESSED)) {
            	switch (((KeyEvent)e).getKeyCode()) {
                case KeyEvent.VK_LEFT:
                	if (numberOfElement == SquareGridOperations.getNumberOfElement(grids))
                		SquareGridOperations.moveLeft(grids);
                    break;
                case KeyEvent.VK_RIGHT:
                	if (numberOfElement == SquareGridOperations.getNumberOfElement(grids))
                		SquareGridOperations.moveRight(grids);
                	break;
                case KeyEvent.VK_DOWN:
                	SquareGridOperations.moveDown(grids);
                	break;
                case KeyEvent.VK_SPACE:
                	if (numberOfElement == SquareGridOperations.getNumberOfElement(grids))
                		SquareGridOperations.changeSmileysColors(grids, numberOfElement);
                	break;
                case KeyEvent.VK_B:
                	if (gameScore > 300) {
                		buyRainbowSmiley = true;
                		gameScore -= 300;
                	}
                	break;
                case KeyEvent.VK_V:
                	if (gameScore > 400) {
                		buyBoomSmiley = true;
                		gameScore -= 401;
                	}
                	break;
            }
            return;
            }
        }
    }
}

