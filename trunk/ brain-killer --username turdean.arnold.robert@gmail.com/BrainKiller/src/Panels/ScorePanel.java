package Panels;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

import SuperPanels.TheGamePanel;

@SuppressWarnings("serial")
/**
 * A jetekos pontjat megjelenito panel.
 * @author Administrator
 *
 */
public class ScorePanel extends JPanel {
	TheGamePanel gamePanel;
	public JTextField gameScore;
	//JTextField PlayerName;
	/**
	 * 
	 * @param panelWidth Peldanyosito osztaly szelessege.
	 * @param panelHeight Peldanyosito osztaly magassaga.
	 * @param locationX Peldanyosito osztaly X koordinataja.
	 * @param locationY Peldanyosito osztaly Y koordinataja.
 	 * @param gamePanel Peldanyosito osztaly ( TheGamePanel )
	 */
	public ScorePanel(int panelWidth, int panelHeight, int locationX, 
			int locationY, final TheGamePanel gamePanel) {
		this.setLayout(new FlowLayout());
		this.setSize(panelWidth, panelHeight);
		this.setLocation(locationX, locationY);
		this.gamePanel = gamePanel;
		Font f = new Font("Rod", Font.BOLD, 25);

		gameScore = new JTextField("0");
		gameScore.setOpaque(false); 
		gameScore.setBorder(null);
		gameScore.setFont(f);
		gameScore.setEditable(false);
		this.add(gameScore);
	}
	
	/**
	 * Bealitja a jatekos pontjat!
	 * @param score A jatekos pontja.
	 */
	public void setScore (int score) {
		String aString = Integer.toString(score);
		gameScore.setText(aString);
	}
	
	/**
	 * Kirajzolas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOpaque(false);
	}
}
