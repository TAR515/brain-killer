package SuperPanels;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Buttons.GameButton;
import Main.MainFrame;
import Panels.NamePanel;
import Panels.ScorePanel;
import Panels.SquareGridPanel;


@SuppressWarnings("serial")
/**
 * A jatekot megjelenito Super Panel.
 * @author Administrator
 *
 */
public class TheGamePanel extends JPanel {
	public MainFrame mainFrame;
	Image background;
	private int gpWidth;
	private int gpHeight;
	public ScorePanel scorePanel;
	public SquareGridPanel squarePanel;
	public NamePanel namePanel;
	
	/**
	 * Konstruktor
	 * @param width A panel szepessege
	 * @param height A panel magassaga
	 * @param mf A pendanyosito frame ( Main Frame )
	 */
	public TheGamePanel(int width, int height,MainFrame mf) {
		gpWidth = width;
		gpHeight = height;
		this.setBounds(0, 0, gpWidth, gpHeight);
		this.setLayout(null);
		mainFrame = mf;
		try {
		background = ImageIO.read(new File("gamepanel22.jpg"));
		} catch (IllegalArgumentException  e) {	
			System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
			System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		namePanel = new NamePanel(this.getX(), this.getY(), this.getWidth(), this.getHeight(),this);
		this.add(namePanel);
		
		squarePanel = new SquareGridPanel((int)(width*0.0635),(int)(height*0.0655),
				(int)(width*0.623),(int)(height*0.845),this);
		this.add(squarePanel);
	
		GameButton exit = new GameButton((int)(0.10*width),(int)(0.10*height),(int)(0.85*width),(int)(0.69*height),
				"exit.png","exit_click.png","exit",this);
		this.add(exit);
		
		scorePanel = new ScorePanel((int)(0.15*width),(int)(0.12*height),(int)(0.84*width),
				(int)(0*height),this);
		this.add(scorePanel);
		
		GameButton pause = new GameButton((int)(0.10*width),(int)(0.07*height),(int)(0.85*width),(int)(0.33*height),
				"pause.png","pause_click.png","pause",this);
		this.add(pause);

	}
	
	/**
	 * A jatek veget valositja meg.
	 */
	public void gameOver() {
		namePanel.setVisible(true);
	}
	
	/**
	 * Beallitja a jatekos jelenlegi pontjat
	 * @param score A jatekos pontja
	 */
	public void setGameScore(int score) {
		scorePanel.setScore(score);
	}
	
	/**
	 * Kirajzolas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(background, 0, 0,gpWidth,gpHeight, null);
	}
}
