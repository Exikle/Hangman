package com.xid_studios.java.game.hangman.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xid_studios.java.game.hangman.Game;

public abstract class PlayerMenuRenderer implements Renderer {
    private Font dFont;

    final String BUTTON_BORDER_PATH = "/ButtonBorder.png";
    private BufferedImage btnBorder = null;

    public PlayerMenuRenderer() {
        try {
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }

        try {
            btnBorder = ImageIO.read(Game.class
                    .getResourceAsStream(BUTTON_BORDER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void init(Graphics2D g) {
        g.setFont(deriveFont(28));
        g.drawString("PLAY", 400, 330);
        g.drawImage(btnBorder, 390, 295, 80, 50, null);
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }
}
