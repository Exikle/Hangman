package com.xid_studios.java.game.hangman.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;

public class PlayerTwoRenderer extends PlayerMenuRenderer {
    private Font dFont;

    public PlayerTwoRenderer() {
        try {
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }
    }

    @Override
    public void render(Graphics2D g) {
        init(g);
        // TODO: implement body
        g.drawString("Player One's Name", 240, 100);
        g.drawString("Player Two's Name", 240, 175);
        g.drawString("Custom Puzzle:", 240, 250);
        g.setFont(deriveFont(15));
        g.drawString("6 Letters Max", 240, 262);
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }
}
