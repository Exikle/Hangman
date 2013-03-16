package com.xid_studios.java.game.hangman.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xid_studios.java.game.hangman.Game;

public class BackGroundRenderer {

    private BufferedImage chalkBoard = null;
    private BufferedImage gallows = null;
    private BufferedImage back = null;

    final String BG_PATH = "/ChalkBackground.png";
    final String GALLOW_PATH = "/Gallows.png";
    final String BACK_BUTTON_PATH = "/back.png";
    private Font dFont;

    public BackGroundRenderer() {
        try {
            chalkBoard = ImageIO.read(Game.class.getResourceAsStream(BG_PATH));
            gallows = ImageIO.read(Game.class.getResourceAsStream(GALLOW_PATH));
            back = ImageIO.read(HangmanSpriteSheet.class
                    .getResourceAsStream(BACK_BUTTON_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 375;
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        g.drawImage(chalkBoard, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.drawImage(back, 400, 28, 37, 37, null);

        g.drawImage(gallows, 0, 65, gallows.getWidth(), gallows.getHeight(),
                null);

        g.setColor(Color.WHITE);
        g.setFont(deriveFont(38));
        g.drawString("Hangman", 45, 58);
        g.drawString("X", 445, 58);

        g.setFont(deriveFont(15));
        g.drawString("Xid Studios", 200, 58);
    }

    public Font deriveFont(int size) {
        Font f = dFont.deriveFont((float) size);
        return f;
    }
}
