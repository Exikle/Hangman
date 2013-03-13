package com.xid_studios.java.game.hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {
    String puzzle;
    Game game;
    State currentState = null;
    String pOneName = "";
    int count;
    final char[] letter = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z' };

    public KeyInputHandler(Game game) {
        game.addKeyListener(this);
        this.game = game;
        currentState = game.currentState;
    }

    @Override
    public void keyPressed(KeyEvent k) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent k) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent k) {
        currentState = game.currentState;
        String key = "" + k.getKeyChar();
        key = key.toUpperCase();
        if (currentState == State.PLAY_SCREEN) {
            for (int x = 0; x < 26; x++) {
                if (key.equals("" + letter[x])) {
                    System.out.println(letter[x]);
                    for (int y = 0; y < game.puzzleSplit.length; y++) {
                        if (key.equalsIgnoreCase("" + game.puzzleSplit[y])) {
                            game.hid[y] = game.puzzleSplit[y];
                            System.out.println("correct");
                        }
                    }
                }
            }
        } else if (currentState == State.PLAYER_ONE_MENU) {
            if (count < 6) {
                game.playerOne += key;
                count++;
            }
            System.out.println(count);
        }
    }
}
