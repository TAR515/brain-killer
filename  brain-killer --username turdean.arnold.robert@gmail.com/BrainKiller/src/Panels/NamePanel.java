package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import Buttons.GameButton;
import SuperPanels.TheGamePanel;

@SuppressWarnings("serial")
	/**
	 * A nevet bekero es top 10 listat megjelenito panel.
	 * @author Administrator
	 *
	 */
public class NamePanel extends JPanel {
	private Image background;
	JList<String> names;
	JList<String> scores;
	DefaultListModel<String> model;
	DefaultListModel<String> model2;
	public JTextField tx;
	TheGamePanel theGamePanel;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	/**
	 * Konstruktor.
	 * @param TheGamePanelX  A peldanyosito osztaly X koordinataja.
	 * @param TheGamePanelY A peldanyosito osztaly Y koordinataja.
	 * @param TheGamePanelWidth A peldanyosito osztaly szelessege.
	 * @param TheGamePanelHeight A peldanyosito osztaly magassaga.
	 * @param gp Hivatkozas a peldanyosito TheGamePanel osztalyra.
	 */
	public NamePanel(int TheGamePanelX,int TheGamePanelY, int TheGamePanelWidth, int TheGamePanelHeight,TheGamePanel gp) {
		this.setSize(TheGamePanelWidth - (TheGamePanelWidth / 12), TheGamePanelHeight/2 + (TheGamePanelWidth / 6));
		this.setLocation(TheGamePanelWidth / 32, TheGamePanelHeight / 8);
		this.theGamePanel = gp;
		this.setFocusable(true);
		try {
			background = ImageIO.read(new File("scorepanel.jpg"));
		} catch (IllegalArgumentException  e) {	
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		} catch (IOException e) {
				System.out.println("Error in oppening gamepanel.jpg: " + e);
		}
		this.setLayout(null);
		// BestNames
		model = new DefaultListModel();
		String[] items = {"NoNameNoScore","NoNameNoScore","NoNameNoScore","NoNameNoScore","NoNameNoScore",
				"NoNameNoScore","NoNameNoScore","NoNameNoScore","NoNameNoScore","NoNameNoScore"};
		for (int i=0; i<items.length; i++) {
		    model.add(i, items[i]);
		}
		names = new JList( model );
		Font displayFont = new Font("Serif", Font.BOLD, 18);
		names.setFont(displayFont);
		names.setBackground(new Color(243,236,210));
		names.enableInputMethods(false);
		names.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		this.add(names);
			
		Insets insets = this.getInsets();
		Dimension size = names.getPreferredSize();
		names.setBounds(insets.left + this.getWidth() / 8,insets.top + this.getHeight() / 6,
			            size.width+50, size.height);
		// Best Scores
		model2 = new DefaultListModel();
		for (int i=0; i<items.length; i++) {
		    model2.add(i, items[i]);
		}
		scores = new JList( model2 );
		scores.setFont(displayFont);
		scores.setBackground(new Color(243,236,210));
		scores.enableInputMethods(false);
		scores.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		this.add(scores);
			
		Insets insets2 = this.getInsets();
		Dimension size2 = scores.getPreferredSize();
		scores.setBounds(insets2.left + this.getWidth() / 2 + this.getWidth() / 10,insets2.top + this.getHeight() / 6,
			            size2.width+50, size2.height);
		// TextField
		tx = new JTextField("                          ");
		tx.setText("");
		this.add(tx);
		Insets insets3 = this.getInsets();
		Dimension size3 = tx.getPreferredSize();
		tx.setBounds(insets3.left + this.getWidth() / 8,insets3.top + this.getHeight() / 2 + this.getHeight()/4,
			            size3.width+50, size3.height);
		// Buttons
		GameButton exit = new GameButton(this.getWidth()/8,this.getHeight()/8,this.getWidth() / 2 + this.getWidth() / 5, 
				this.getHeight() / 2 + this.getHeight()/3,"exit2.png","exit2_click.png","exit",theGamePanel);
		this.add(exit);
		
		GameButton save = new GameButton(this.getWidth()/8,this.getHeight()/8,this.getWidth() / 2 - this.getWidth()/3, 
				this.getHeight() / 2 + this.getHeight()/3,"save.png","save_click.png","saveTheScore",theGamePanel);
		this.add(save);
		
		this.setVisible(false);
	}
	/**
	 * Adatok betoltese.
	 * @param name A jatekos neve.
	 * @param score A jatekos altal elert pontszam.
	 */
	public void loadData(String name, int score) {
		loadNames(loadScores(score),name);
	}
	
	/**
	 * Betolti a file-bol a neveket.
	 * @param ind A jatekos pontja.
	 * @param name A jatekos neve
	 */
	private void loadNames(int ind, String name) {
		StringBuilder[] contents = new StringBuilder[10];
		contents[0] = new StringBuilder();
		contents[1] = new StringBuilder();
		contents[2] = new StringBuilder();
		contents[3] = new StringBuilder();
		contents[4] = new StringBuilder();
		contents[5] = new StringBuilder();
		contents[6] = new StringBuilder();
		contents[7] = new StringBuilder();
		contents[8] = new StringBuilder();
		contents[9] = new StringBuilder();
		try {
		      BufferedReader input =  new BufferedReader(new FileReader("bestnames.txt"));
		      try {
		        String line = null;
		        int i = 0;
		        while (( line = input.readLine()) != null){
		          contents[i++].append(line);
		        }
		      }
		      finally {
		        input.close();
		      }
		}
		catch (IOException ex){
		   ex.printStackTrace();
		}
		int j = 0;
		for (int i = 0; i < 10; i++) {
			if (i != ind) 
				model.add(i, contents[j++].toString());
			else {
				model.add(i, name);
				names.setSelectedIndex(i);
			}
		}
		PrintWriter pw = null;
	    try {
	        pw = new PrintWriter(new FileWriter("bestnames.txt"));
	        for (int i = 0; i < 10; i++)
	        	pw.println(model.getElementAt(i).toString());
	      pw.flush();

	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	    finally {
	      if (pw != null)
	        pw.close();
	      
	    }
	}
	
	/**
	 * A filebol feltolti a pontokat.
	 * @param score A jatekos altal elert pontszam.
	 * @return A toplistaban hanyadik poziciot erte el a jatekos.
	 */
	private int loadScores(int score) {
		StringBuilder[] contents = new StringBuilder[10];
		contents[0] = new StringBuilder();
		contents[1] = new StringBuilder();
		contents[2] = new StringBuilder();
		contents[3] = new StringBuilder();
		contents[4] = new StringBuilder();
		contents[5] = new StringBuilder();
		contents[6] = new StringBuilder();
		contents[7] = new StringBuilder();
		contents[8] = new StringBuilder();
		contents[9] = new StringBuilder();
		try {
		      BufferedReader input =  new BufferedReader(new FileReader("bestscores.txt"));
		      try {
		        String line = null;
		        int i = 0;
		        while (( line = input.readLine()) != null){
		          contents[i++].append(line);
		        }
		      }
		      finally {
		        input.close();
		      }
		}
		catch (IOException ex){
		   ex.printStackTrace();
		}
		int k = -1; int j = 0; 
		for (int i = 0; i < 10; i++) {
			if (Integer.parseInt(contents[j].toString()) > score)
				model2.set(i, contents[j++].toString());
			else if ( i == j) {
				model2.set(i, Integer.toString(score));
				k = j;
			}
			else model2.set(i, contents[j++].toString());
		}
		// SAVING TO FILE
		PrintWriter pw = null;
	    try {
	        pw = new PrintWriter(new FileWriter("bestscores.txt"));
	        for (int i = 0; i < 10; i++)
	        	pw.println(model2.getElementAt(i).toString());
	      pw.flush();

	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	    finally {
	      if (pw != null)
	        pw.close();
	      
	    }
	    scores.setSelectedIndex(k);
		return k;
	}
	/**
	 * Kirajzolas.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0,this.getWidth(),this.getHeight(), null);
	}
}
