package com.xid_studios.java.game.hangman.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;

public class PlayerOneRenderer extends PlayerMenuRenderer {
    private Font dFont;

    private final String[] categories = { "Easy", "Food", "Standard",
            "Geography", "Hard", "Holidays", "Animals", "Sports" };

    public PlayerOneRenderer() {
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
        // g.drawString(playerOne, 240, 125);
        g.setFont(deriveFont(20));
        // for (int p = 0; p < pOne.length; p++) {
        // g.drawString(pOne[p], 240 + (p * 15), 125);
        // }
        g.setFont(deriveFont(28));
        g.drawString("Categories", 240, 175);
        g.setFont(deriveFont(15));
        for (int c = 0; c < categories.length; c++) {
            g.drawString(categories[c], 250, 200 + (c * 15));
        }
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }
}
