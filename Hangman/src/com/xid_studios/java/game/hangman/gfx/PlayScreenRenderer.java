package com.xid_studios.java.game.hangman.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import com.xid_studios.java.game.hangman.Game;

public class PlayScreenRenderer implements Renderer {
    private Font dFont;
    String category = "Easy";
    Boolean puzzleCreated = false;
    @SuppressWarnings("unused")
    private BufferedImage line = null;
    final String LINE_PATH = "/Line.png";
    final String HANGER_PATH = "/Hangman Sprite.png";

    private HangmanSpriteSheet sheet;
    @SuppressWarnings("unused")
    private BufferedImage hanger = null;

    public PlayScreenRenderer() {
        try {
            line = ImageIO.read(Game.class.getResourceAsStream(LINE_PATH));
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);

            sheet = new HangmanSpriteSheet(HANGER_PATH);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }
    }

    @Override
    public void render(Graphics2D g) {
        setHangerImage();
        // g.drawImage(line, 225, 290, 225, 26, null);
        // g.drawImage(hanger, 80, 90, 113, 256, null);
        g.setFont(deriveFont(31));
        // g.drawString(chancesLeft + "", 100, 330);

        g.drawString("Player One", 240, 100);
        g.setFont(deriveFont(20));
        // for (int p = 0; p < pOne.length; p++) {
        // g.drawString(pOne[p], 240 + (p * 20), 125);
        // }

        g.setFont(deriveFont(28));
        if (puzzleCreated) {
            // for (int x = 0; x < puzLength; x++) {
            // g.drawString("" + hid[x], 250 + (x * 20), 330);
            // }
        }
        g.setFont(deriveFont(31));
        g.drawString("Category:", 240, 160);
        g.setFont(deriveFont(20));
        g.drawString(category, 240, 185);
    }

    private void setHangerImage() {
        // if (chancesLeft > 0) {
        // int num = chancesLeft - 7;
        // if (num < 0) {
        // num = num * -1;
        // }
        int num = 3;
        hanger = sheet.getHanger(num);
        // } else {
        // }
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }
}
