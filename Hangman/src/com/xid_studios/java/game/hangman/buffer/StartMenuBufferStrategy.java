package com.xid_studios.java.game.hangman.buffer;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class StartMenuBufferStrategy {
    BufferStrategy bs;

    public StartMenuBufferStrategy(BufferStrategy bs) {
        this.bs = bs;
    }

    public void render() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        // g.setFont(dFont.deriveFont((float) 28));
        g.drawString("One Player", 240, 100);
        // g.drawImage(btnBorder, 233, 75, 150, 33, null);
        // g.drawImage(startDisplayHanger, 240, 90, 56, 128, null);

        g.drawString("Two Players", 240, 225);
        // g.drawImage(btnBorder, 233, 200, 175, 33, null);
        // g.drawImage(startDisplayHanger, 240, 220, 56, 128, null);
        // g.drawImage(startDisplayHanger, 296, 220, 56, 128, null);

    }

}
