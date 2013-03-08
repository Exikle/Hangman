package com.xid_studios.java.game.hangman.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HangmanSpriteSheet {

    public String path;
    public int width;
    public int height;
    BufferedImage image = null;

    public HangmanSpriteSheet(String path) {
        try {
            image = ImageIO.read(HangmanSpriteSheet.class
                    .getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public BufferedImage getHanger(int c) {
        BufferedImage hangerImg = image.getSubimage((113 * c), 0,
                113, 256);
        return hangerImg;
    }

}
