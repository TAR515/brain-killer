package SuperPanels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Buttons.StartingButton;
import Main.MainFrame;


@SuppressWarnings("serial")
/**
 * A jatek menujet megvalosito panel.
 * @author Administrator
 *
 */
public class TheStartingPanel extends JPanel {
	private BufferedImage background;
	public MainFrame mainFrame;
	private int width; private int height;
	
	/**
	 * Konstruktor.
	 * @param width A panel szelessege.
	 * @param height A panel magassaga.
	 * @param mf A peldanyosito frame ( Main Frame )
	 */
	public TheStartingPanel(int width, int height,MainFrame mf){
		this.setLayout(null);
		mainFrame = mf;
		this.width = width;
		this.height = height;
		try {
			background = ImageIO.read(new File("startingscreenbackground_logo.jpg"));
		} catch (IllegalArgumentException  e) {	
			System.out.println("Error in oppening startingscreenbackground_logo.jpg: " + e);
		} catch (IOException e) {
			System.out.println("Error in oppening startingscreenbackground_logo.jpg: " + e);
		}
		// Adding two buttons.
		StartingButton exit = new StartingButton((int)(0.08*width),(int)(0.08*height),
				(int)(0.85*width),(int)(0.32*height),
				"exit.png","exit_click.png","exit",this);
		this.add(exit);
		StartingButton startGame = new StartingButton((int)(0.14*width),(int)(0.07*height),
				(int)(0.24*width),(int)(0.24*height),
				"startgame3.png","startgame3_click.png","startgame",this);
		this.add(startGame);
	}
	
	/**
	 * Kirajzolas.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,width,
				height, null);
	}
}
