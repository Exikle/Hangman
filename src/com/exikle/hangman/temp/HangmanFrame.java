package com.exikle.hangman.temp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.exikle.hangman.original.HangManGUI;

/*
 * Import Images - , Close button, back button
 * add frame to hFrame, create a class for close button, & backbutton
 * make the frame movable if user holds the edges?
 */
class HangmanFrame extends JFrame implements ActionListener {

	static HangmanFrame hFrame = new HangmanFrame(
	    "Hangman - Xid Studios");

	private final Image[] image = new Image[5];

	// HangmanButton closeBtn = new HangmanButton("Close");
	HangmanButton closeBtn = new HangmanButton("Close");

	private int posX = 0;

	private int posY = 0;

	private final static String NO_TITLE = "";

	private static int height, width;

	public HangmanFrame() {
		this(NO_TITLE);
	}

	private HangmanFrame(String str) {
		super(str);
		try {
			ClassLoader cl = HangManGUI.class.getClassLoader();
			String PIC_PATH = "pictures/";
			URL[] imgURL = {
				cl.getResource(PIC_PATH + "chalkBG.png"),
				cl.getResource(PIC_PATH + "bottomChalkBorder.png"),
				cl.getResource(PIC_PATH + "topChalkBorder.png"),
				cl.getResource(PIC_PATH + "leftChalkBorder.png"),
				cl.getResource(PIC_PATH + "rightChalkBorder.png")
			};
			if (imgURL != null) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int x = 0; x < 5; x++)
					image[x] = toolkit.createImage(imgURL[x]);
			}
		} catch (Exception e) {
			System.out.println("Problem Importing Images");
		}
		FramePanel fPanel = new FramePanel();
		this.add(fPanel);
		this.setUndecorated(true);
		// this.addMouseListener(new MouseAdapter() {
		// public void mousePressed(MouseEvent e) {
		// posX = e.getX();
		// posY = e.getY();
		// }
		// });
		// this.addMouseMotionListener(new MouseAdapter() {
		// public void mouseDragged(MouseEvent evt) {
		// setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen()
		// - posY);
		//
		// }
		// });
		fPanel.setLayout(null);
		closeBtn.addActionListener(this);
		placeObjects();
		fPanel.add(closeBtn);
	}

	private class FramePanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			getDimension();
			// the chalk board part
			g2.drawImage(image[0], 0, 0, width, height, 0, 0, 300,
			             300, this);

		}
	}

	void placeObjects() {
		getDimension();
		closeBtn.setBounds(0, 0, 25, 25);
	}

	void getDimension() {
		height = this.getHeight();
		width = this.getWidth();
	}

	public static void main(String[] args) {
		hFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		hFrame.setVisible(true);
		hFrame.setSize(350, 350);
		hFrame.getDimension();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == closeBtn) {
			System.out.println("Close");
			System.exit(0);
		}
	}

}
