package com.xid_studios.java.game.hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyInputHandler implements KeyListener {
    String puzzle;
    private final Game game;
    private State currentState = null;
    String pOneName = "";
    private int count;
    private final char[] letter = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z' };
    public int[] letterMask = new int[26];
    int correctCount;
    private int incorrectCount;

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
            boolean wrongPick = false;
            for (int x = 0; x < 26; x++) {
                if (key.equals("" + letter[x])) {
                    int originalCount = correctCount;
                    int holdStringIndex = 0;
                    for (int y = 0; y < game.puzzleSplit.length; y++) {
                        if (key.equalsIgnoreCase("" + game.puzzleSplit[y])) {
                            holdStringIndex = y;
                            game.hid[y] = game.puzzleSplit[y];
                            if (letterMask[holdStringIndex] != 1) {
                                correctCount++;
                                System.out.println("C:" + correctCount);
                            } else {
                                return;
                            }
                        }
                    }
                    if (originalCount == correctCount) {
                        wrongPick = true;
                    } else {
                        letterMask[holdStringIndex] = 1;
                    }
                }

            }
            if (wrongPick) {
                game.chancesLeft--;
                incorrectCount++;
                System.out.println("IC:" + incorrectCount);
            }
            checkWin();
        } else if (currentState == State.PLAYER_ONE_MENU) {
            if (count < 6) {
                game.pOne[count] = key.substring(0);
                count++;
            } else if (count == 6) {
                game.pOne[5] = key.substring(0);
            }
            System.out.println(count);
        }
    }

    private void checkWin() {
        if (correctCount == game.puzzleSplit.length) {
            System.out.println("Win!");
            game.currentState = State.WIN_SCREEN;
        } else if (incorrectCount == 7) {
            System.out.println("Lose");
            game.currentState = State.LOSE_SCREEN;
        }
    }
}
