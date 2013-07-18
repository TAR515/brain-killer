package Main;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Calculations.*;
//import Calculations.FrameModifier;
import SuperPanels.TheGamePanel;
import SuperPanels.TheStartingPanel;


@SuppressWarnings("serial")
/**
 * 
 * @author Administrator
 *	Main Frame ami letrehozza a framet.
 */
public class MainFrame extends JFrame {
	Dimension screenSize;
	private int frameSizeWidth;
	private int frameSizeHeight;
	
	/**
	 *  Main Frame konstruktora beallit es hozzaad komponenseket.
	 */
	MainFrame() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSizeWidth = FrameModifier.getFrameSizeWidth(screenSize);
		frameSizeHeight = FrameModifier.getFrameSizeHeight(screenSize);
		this.setBounds(FrameModifier.getFrameLocationX(screenSize),
				FrameModifier.getFrameLocationY(screenSize),
				frameSizeWidth,
				frameSizeHeight);
	
		
		TheStartingPanel p = new TheStartingPanel(FrameModifier.getFrameSizeWidth(screenSize),
				FrameModifier.getFrameSizeHeight(screenSize),this);

		add(p);
		
		// Initial...
		this.setCursor(getToolkit().createCustomCursor(new ImageIcon("mycursor4.gif").getImage(), new Point(0, 0), "My Cursor"));
		//this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("brainkiller_ico.gif"));
		this.setTitle("Brain Killer");
		this.setResizable(false);
		this.setFocusable(true);
		
		this.setVisible(true);
		Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalKeyLisetener(), AWTEvent.KEY_EVENT_MASK);
		Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalMouseLisetener(), AWTEvent.MOUSE_EVENT_MASK);
		Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalMouseMotionLisetener(), AWTEvent.MOUSE_MOTION_EVENT_MASK);
		this.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			  }
		});
	}
	
	/**
	 * 
	 * @param s Megvaltoztatja a megjelenitett SuperPanelt a megadott - ra
	 * 	Megvaltoztatja a megjelenitett SuperPanelt
	 */
	public void setCurrentSuperPanel(String s) {
		switch (s) {
		case "TheGamePanel":
			TheGamePanel gp = new TheGamePanel(frameSizeWidth,frameSizeHeight,this);
			add(gp);
			break;
		}
		
	}
	/**
	 * 
	 * @author Administrator
	 * Globalis Key Listener osztaly, ervenyes az egesz projekten belul. ESC - kilepes
	 */
	private class GlobalKeyLisetener implements AWTEventListener {
		/**
		 * Esc billentyu lekezelese!
		 */
		public void eventDispatched(AWTEvent e) {
            if (e instanceof KeyEvent && (e.getID() == KeyEvent.KEY_PRESSED)) {
                switch (((KeyEvent)e).getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        java.lang.System.exit(0);
                        break;
                }
                return;
            }
        }
    }
	
	/**
	 * 
	 * @author Administrator
	 * Globalis Mouse Listener osztaly, ervenyes az egesz projekten belul. A Frame mozgatasat valositja meg.
	 */
	private class GlobalMouseLisetener implements AWTEventListener {
		/**
		 * Megvalositja a kert muveleteket.
		 */
		public void eventDispatched(AWTEvent e) {
			if (e instanceof MouseEvent) {
				switch(e.getID()) {
				case MouseEvent.MOUSE_PRESSED:
					FrameModifier.setCursorClickedPosition(((MouseEvent) e).getPoint());
					setCursor(getToolkit().createCustomCursor(new ImageIcon("move2.gif").getImage(), new Point(0, 0), "My Cursor2"));
					break;
				case MouseEvent.MOUSE_RELEASED:
					setCursor(getToolkit().createCustomCursor(new ImageIcon("mycursor4.gif").getImage(), new Point(0, 0), "My Cursor"));
					break;
				}
				return;
			}
        }
    }
	
	/**
	 * 
	 * @author Administrator
	 * Globalis Mouse Motion Listener, ervenyes az egesz projekten belul. A Frame mozgatasat valositja meg.
	 */
	private class GlobalMouseMotionLisetener implements AWTEventListener {
		@SuppressWarnings("deprecation")
		/**
		 * Megvalositja a kert muveleteket.
		 */
		public void eventDispatched(AWTEvent e) {
			if (e instanceof MouseEvent) {
				switch(e.getID()) {
				case MouseEvent.MOUSE_DRAGGED:
						FrameModifier.setFramePosition(getLocation());
						FrameModifier.setCursorCurrentPosition(((MouseEvent) e).getPoint());
						Point newFramePosition = FrameModifier.frameMoving();
						move(newFramePosition.x,newFramePosition.y);
						setCursor(getToolkit().createCustomCursor(new ImageIcon("move2.gif").getImage(), new Point(0, 0), "My Cursor"));
				}
				return;
			}
        }
    }

}
