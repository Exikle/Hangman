/**
 * Main Class
 */
package com.xid_studios.java.game.hangman;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.xid_studios.java.game.hangman.gfx.BackGroundRenderer;

public class Game extends Canvas implements Runnable {

    BackGroundRenderer bg = new BackGroundRenderer();
    JFrame frame;
    private static final long serialVersionUID = 1L;
    Boolean running = false;

    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 375;

    final String DEFAULT_P1 = "";
    final String DEFAULT_P2 = "";

    String playerOne = DEFAULT_P1;
    String playerTwo = DEFAULT_P2;
    String[] allPuz;

    String[] pOne = { "", "", "", "", "", "" };

    private Boolean puzzleCreated = false;

    private final int CHANCES = 7;
    int chancesLeft = CHANCES;
    private int puzLength;

    char[] puzzleSplit;
    char[] hid;

    public final String category = "Easy";
    public String pOneName;

    final String BACK_BUTTON_PATH = "/Back.png";
    State currentState = State.START_MENU;

    private Game() {
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        MouseInputHandler mInput = new MouseInputHandler(this);
        // KeyInputHandler kInput = new KeyInputHandler(this);

        String GAME_NAME = "Hangman";
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
        bg.render(g);
        currentState.getRenderer().render(g);

        g.dispose();
        bs.show();
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
