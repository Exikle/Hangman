/**
 * Main Class
 */
package com.xid_studios.java.game.hangman;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.xid_studios.java.game.hangman.gfx.HangmanSpriteSheet;

public class Game extends Canvas implements Runnable {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private Boolean running = false;
    private final int FRAME_WIDTH = 500, FRAME_HEIGHT = 375;
    private final String GAME_NAME = "Hangman";
    private JFrame frame;

    private String bgPath = "/ChalkBackground.png";
    private String manPath = "/Hangman Sprite.png";
    private String gallowPath = "/Gallows.png";

    private BufferedImage chalkBoard = null;
    private BufferedImage hanger = null;
    private BufferedImage gallows = null;

    HangmanSpriteSheet sheet;

    public final int CHANCES = 2;
    int chancesLeft = CHANCES;

    State currentState = State.START_MENU;

    public Game() {
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        try {
            chalkBoard = ImageIO.read(Game.class.getResourceAsStream(bgPath));
            gallows = ImageIO.read(Game.class.getResourceAsStream(gallowPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = new HangmanSpriteSheet(manPath);

        frame = new JFrame(GAME_NAME);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    @Override
    public final void run() {

        while (running) {
            boolean shouldRender = true;
            if (shouldRender) {
                render();
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(chalkBoard, 0, 0, getWidth(), getHeight(), null);

        g.drawImage(gallows, 0, 65, gallows.getWidth(), gallows.getHeight(),
                null);

        if (currentState == State.START_MENU) {
            setHangerImage();
            g.drawImage(hanger, 80, 90, 113, 256, null);
        }

        g.dispose();
        bs.show();
    }

    public void setHangerImage() {
        if (chancesLeft > 0) {
            int num = chancesLeft - 5;
            if (num < 0) {
                num = num * -1;
            }
            hanger = sheet.getHanger(num);
        } else {
            System.out.println("You Lose");
        }
    }

    public static void main(final String[] args) {
        new Game().start();
    }

}
