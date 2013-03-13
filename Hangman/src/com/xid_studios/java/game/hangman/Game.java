/**
 * Main Class
 */
package com.xid_studios.java.game.hangman;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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

    private final String[] categories = { "Easy", "Food", "Standard", "Geography", "Hard",
            "Holidays", "Animals", "Sports" };

    private BufferedImage chalkBoard = null;
    private BufferedImage hanger = null;
    private BufferedImage gallows = null;
    private BufferedImage btnBorder = null;

    private final String DEFAULT_P1 = "";
    private final String DEFAULT_P2 = "";

    String playerOne = DEFAULT_P1;
    String playerTwo = DEFAULT_P2;
    String[] allPuz;

    private Boolean puzzleCreated = false;
    private BufferedImage startDisplayHanger = null;
    private Font dFont = null;

    private final HangmanSpriteSheet sheet;

    private final int CHANCES = 7;
    private final int chancesLeft = CHANCES;
    private int puzLength;

    char[] puzzleSplit;
    char[] hid;

    State currentState = State.START_MENU;
    public final String category = "Easy";
    public String pOneName;

    private Game() {
        int FRAME_WIDTH = 500;
        int FRAME_HEIGHT = 375;
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        try {
            String bgPath = "/ChalkBackground.png";
            chalkBoard = ImageIO.read(Game.class.getResourceAsStream(bgPath));
            String gallowPath = "/Gallows.png";
            gallows = ImageIO.read(Game.class.getResourceAsStream(gallowPath));
            String buttonPath = "/ButtonBorder.png";
            btnBorder = ImageIO
                    .read(Game.class.getResourceAsStream(buttonPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String manPath = "/Hangman Sprite.png";
        sheet = new HangmanSpriteSheet(manPath);
        MouseInputHandler mInput = new MouseInputHandler(this);
        KeyInputHandler kInput = new KeyInputHandler(this);
        setStartDisplayHanger();

        try {
            String fontPath = "res/EraserDust.ttf";
            File f = new File(fontPath);
            FileInputStream in = new FileInputStream(f);
            dFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            System.out.println("Problem Creating Font");
        }

        String GAME_NAME = "Hangman";
        JFrame frame = new JFrame(GAME_NAME);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    synchronized void start() {
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
            render();
        }
    }

    void render() {
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

        g.setColor(Color.WHITE);
        g.setFont(dFont.deriveFont((float) 38));
        g.drawString("Hangman", 45, 58);
        g.drawString("X", 445, 58);

        g.setFont(dFont.deriveFont((float) 15));
        g.drawString("Xid Studios", 200, 58);

        if (currentState == State.START_MENU) {
            g.setFont(dFont.deriveFont((float) 28));
            g.drawString("One Player", 240, 100);
            g.drawImage(btnBorder, 233, 75, 150, 33, null);
            g.drawImage(startDisplayHanger, 240, 90, 56, 128, null);

            g.drawString("Two Players", 240, 225);
            g.drawImage(btnBorder, 233, 200, 175, 33, null);
            g.drawImage(startDisplayHanger, 240, 220, 56, 128, null);
            g.drawImage(startDisplayHanger, 296, 220, 56, 128, null);

        } else if ((currentState == State.PLAYER_ONE_MENU)
                || (currentState == State.PLAYER_TWO_MENU)) {

            g.setFont(dFont.deriveFont((float) 28));
            g.drawString("PLAY", 400, 330);
            g.drawImage(btnBorder, 390, 295, 80, 50, null);

            if (currentState == State.PLAYER_ONE_MENU) {
                g.drawString("Player One's Name", 240, 100);
                g.drawString(playerOne, 240, 125);
                g.drawString("Categories", 240, 175);
                g.setFont(dFont.deriveFont((float) 15));
                for (int c = 0; c < categories.length; c++) {
                    g.drawString(categories[c], 250, 200 + (c * 15));
                }

            } else if (currentState == State.PLAYER_TWO_MENU) {
                g.drawString("Player One's Name", 240, 100);
                g.drawString("Player Two's Name", 240, 175);
                g.drawString("Custom Puzzle:", 240, 250);
                g.setFont(dFont.deriveFont((float) 15));
                g.drawString("6 Letters Max", 240, 262);

            }
        } else if (currentState == State.PLAY_SCREEN) {
            setHangerImage();
            g.drawImage(hanger, 80, 90, 113, 256, null);
            g.setFont(dFont.deriveFont((float) 31));
            g.drawString(chancesLeft + "", 100, 330);

            // for (int x = 0; x < puzLength; x++) {
            //
            // }
            if (puzzleCreated) {
                for (int x = 0; x < puzLength; x++) {
//                    g.drawString("" + puzzleSplit[x], 260 + (x * 40), 330);
                    g.drawString("" + hid[x], 250 + (x * 20), 330);
                }
            }
        }

        g.dispose();
        bs.show();
    }

    void setHangerImage() {
        if (chancesLeft > 0) {
            int num = chancesLeft - 7;
            if (num < 0) {
                num = num * -1;
            }
            hanger = sheet.getHanger(num);
        } else {
            System.out.println("You Lose");
        }
    }

    void setStartDisplayHanger() {
        startDisplayHanger = sheet.getHanger(6);
    }

    public static void main(final String[] args) {
        new Game().start();
    }

    public void createPuzzle() {
        int length = allPuz.length;
        int randomNum = (int) (Math.random() * length);
        String puzzle = "" + allPuz[randomNum];
        puzzle = puzzle.toUpperCase();
        System.out.println(puzzle);
        puzLength = puzzle.length();
        puzzleSplit = new char[puzLength];
        hid = new char[puzLength];

        for (int x = 0; x < puzLength; x++) {
            puzzleSplit[x] = (puzzle.charAt(x));
            if (puzzleSplit[x] == ' ') {
                hid[x] = (' ');
            } else
                hid[x] = ('-');
        }
        puzzleCreated = true;
    }

}
