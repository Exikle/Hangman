package com.exikle.hangman.temp;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

class HangmanButton extends JButton {
	private static final Icon NO_ICON = null;
	private static final String NO_STRING = null;
	private Font dFont = null;

	public HangmanButton() {
		this(NO_STRING, NO_ICON);
	}

	public HangmanButton(String string) {
		this(string, NO_ICON);
	}

	public HangmanButton(Icon icon) {
		this(NO_STRING, icon);
	}

	private HangmanButton(String s, Icon i) {
		setModel(new DefaultButtonModel());
		init(s, i);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);

		try {
			File f = new File("VTK.ttf");
			FileInputStream in = new FileInputStream(f);
			dFont = Font.createFont(Font.TRUETYPE_FONT, in);
			this.setFont(dFont.deriveFont(10f));
		} catch (Exception e) {
			System.out.println("Problem Creating Font");
		}
	}

	public void setFont(int num) {
		this.setFont(dFont.deriveFont((float) num));
	}
}
