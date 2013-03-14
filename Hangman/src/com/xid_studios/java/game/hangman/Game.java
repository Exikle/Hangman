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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.xid_studios.java.game.hangman.buffer.AbstractWindowBufferStrategy;
import com.xid_studios.java.game.hangman.gfx.HangmanSpriteSheet;

public class Game extends Canvas implements Runnable {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private Boolean running = false;

    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 375;

    private final String[] categories = { "Easy", "Food", "Standard",
            "Geography", "Hard", "Holidays", "Animals", "Sports" };

    private BufferedImage chalkBoard = null;
    private BufferedImage hanger = null;
    private BufferedImage gallows = null;
    private BufferedImage btnBorder = null;
    private BufferedImage line = null;

    final String DEFAULT_P1 = "";
    final String DEFAULT_P2 = "";

    String playerOne = DEFAULT_P1;
    String playerTwo = DEFAULT_P2;
    String[] allPuz;

    String[] pOne = { "", "", "", "", "", "" };

    private Boolean puzzleCreated = false;
    private BufferedImage startDisplayHanger = null;
    private Font dFont = null;

    private final int CHANCES = 7;
    int chancesLeft = CHANCES;
    private int puzLength;

    char[] puzzleSplit;
    char[] hid;

    public final String category = "Easy";
    public String pOneName;

    final String BG_PATH = "/ChalkBackground.png";
    final String GALLOW_PATH = "/Gallows.png";
    final String BUTTON_BORDER_PATH = "/ButtonBorder.png";
    final String LINE_PATH = "/Line.png";
    final String BACK_BUTTON_PATH = "/Back.png";
    final String HANGER_PATH = "/Hangman Sprite.png";

    private HangmanSpriteSheet sheet;
    State currentState = State.START_MENU;

    private Game() {
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        try {
            chalkBoard = ImageIO.read(Game.class.getResourceAsStream(BG_PATH));
            gallows = ImageIO.read(Game.class.getResourceAsStream(GALLOW_PATH));
            btnBorder = ImageIO.read(Game.class
                    .getResourceAsStream(BUTTON_BORDER_PATH));
            line = ImageIO.read(Game.class.getResourceAsStream(LINE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = new HangmanSpriteSheet(HANGER_PATH);
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
            createBufferStrategy(2);
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
        
//        if (currentState == State.START_MENU) {
//            g.setFont(dFont.deriveFont((float) 28));
//            g.drawString("One Player", 240, 100);
//            g.drawImage(btnBorder, 233, 75, 150, 33, null);
//            g.drawImage(startDisplayHanger, 240, 90, 56, 128, null);
//
//            g.drawString("Two Players", 240, 225);
//            g.drawImage(btnBorder, 233, 200, 175, 33, null);
//            g.drawImage(startDisplayHanger, 240, 220, 56, 128, null);
//            g.drawImage(startDisplayHanger, 296, 220, 56, 128, null);
//
//        } else if ((currentState == State.PLAYER_ONE_MENU)
//                || (currentState == State.PLAYER_TWO_MENU)) {
//
//            g.setFont(dFont.deriveFont((float) 28));
//            g.drawString("PLAY", 400, 330);
//            g.drawImage(btnBorder, 390, 295, 80, 50, null);
//
//            if (currentState == State.PLAYER_ONE_MENU) {
//                g.drawString("Player One's Name", 240, 100);
//                // g.drawString(playerOne, 240, 125);
//                g.setFont(dFont.deriveFont((float) 20));
//                for (int p = 0; p < pOne.length; p++) {
//                    g.drawString(pOne[p], 240 + (p * 15), 125);
//                }
//                g.setFont(dFont.deriveFont((float) 28));
//                g.drawString("Categories", 240, 175);
//                g.setFont(dFont.deriveFont((float) 15));
//                for (int c = 0; c < categories.length; c++) {
//                    g.drawString(categories[c], 250, 200 + (c * 15));
//                }
//
//            } else if (currentState == State.PLAYER_TWO_MENU) {
//                g.drawString("Player One's Name", 240, 100);
//                g.drawString("Player Two's Name", 240, 175);
//                g.drawString("Custom Puzzle:", 240, 250);
//                g.setFont(dFont.deriveFont((float) 15));
//                g.drawString("6 Letters Max", 240, 262);
//
//            }
//        } else if (currentState == State.PLAY_SCREEN) {
//            setHangerImage();
//            g.drawImage(line, 225, 290, 225, 26, null);
//            g.drawImage(hanger, 80, 90, 113, 256, null);
//            g.setFont(dFont.deriveFont((float) 31));
//            g.drawString(chancesLeft + "", 100, 330);
//
//            g.drawString("Player One", 240, 100);
//            g.setFont(dFont.deriveFont((float) 20));
//            for (int p = 0; p < pOne.length; p++) {
//                g.drawString(pOne[p], 240 + (p * 20), 125);
//            }
//
//            g.setFont(dFont.deriveFont((float) 28));
//            if (puzzleCreated) {
//                for (int x = 0; x < puzLength; x++) {
//                    g.drawString("" + hid[x], 250 + (x * 20), 330);
//                }
//            }
//            g.setFont(dFont.deriveFont((float) 31));
//            g.drawString("Category:", 240, 160);
//            g.setFont(dFont.deriveFont((float) 20));
//            g.drawString(category, 240, 185);
//        } else if (currentState == State.LOSE_SCREEN) {
//            g.drawImage(line, 225, 290, 225, 26, null);
//            g.setFont(dFont.deriveFont((float) 28));
//            g.drawString("LOSE", 100, 330);
//            // setHangerImage();
//            g.drawImage(hanger, 80, 90, 113, 256, null);
//            for (int x = 0; x < puzLength; x++) {
//                g.drawString("" + puzzleSplit[x], 250 + (x * 20), 330);
//            }
//
//        } else if (currentState == State.WIN_SCREEN) {
//            g.drawImage(line, 225, 290, 225, 26, null);
//            g.setFont(dFont.deriveFont((float) 28));
//            g.drawString("WIN", 100, 330);
//            // setHangerImage();
//            g.drawImage(hanger, 80, 90, 113, 256, null);
//            for (int x = 0; x < puzLength; x++) {
//                g.drawString("" + puzzleSplit[x], 250 + (x * 20), 330);
//            }
//        }

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

    public static void main(final String[] args) {
        new Game().start();
    }

}
