package com.xid_studios.java.game.hangman.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xid_studios.java.game.hangman.Game;

public class StartMenuRenderer implements Renderer {
    private Font dFont;

    private BufferedImage startDisplayHanger = null;

    private HangmanSpriteSheet sheet;
    final String HANGER_PATH = "/Hangman Sprite.png";

    final String BUTTON_BORDER_PATH = "/ButtonBorder.png";
    private BufferedImage btnBorder = null;

    public StartMenuRenderer() {
        try {
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);

            sheet = new HangmanSpriteSheet(HANGER_PATH);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }

        try {
            btnBorder = ImageIO.read(Game.class
                    .getResourceAsStream(BUTTON_BORDER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawAHangman();
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(deriveFont(28));
        g.drawString("One Player", 240, 100);
        g.drawImage(btnBorder, 233, 75, 150, 33, null);
        g.drawImage(startDisplayHanger, 240, 90, 56, 128, null);

        g.drawString("Two Players", 240, 225);
        g.drawImage(btnBorder, 233, 200, 175, 33, null);
        g.drawImage(startDisplayHanger, 240, 220, 56, 128, null);
        g.drawImage(startDisplayHanger, 296, 220, 56, 128, null);
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }

    public void drawAHangman() {
        startDisplayHanger = sheet.getHanger(6);
    }
}
