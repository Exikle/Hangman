package com.xid_studios.java.game.hangman;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class MouseInputHandler implements MouseListener, MouseMotionListener {
    int posX = 0, posY = 0;
    Boolean allowed = false;

    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 375;

    private final Game game;
    private State currentState = null;
    private State previousState = null;

    private final Rectangle close = new Rectangle(445, 30, 30, 35);
    private final Rectangle back = new Rectangle(400, 30, 37, 37);
    private final Rectangle player1 = new Rectangle(240, 80, 190, 20);
    private final Rectangle player2 = new Rectangle(240, 200, 190, 20);
    private final Rectangle play = new Rectangle(392, 298, 466, 341);

    private Rectangle mouse;

    public MouseInputHandler(Game game) {
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
        this.game = game;
        currentState = game.currentState;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        System.out.println(m.getX() + "," + m.getY());
        mouse = new Rectangle(m.getX(), m.getY(), 1, 1);

        // System.out.println("State: " + currentState);

        if (mouse.intersects(close)) {
            game.running = false;

            System.exit(0);
        } else if (mouse.intersects(back)) {
            if (previousState != null) {
                game.currentState = game.currentState.previousState();
                currentState = game.currentState.previousState();
            }
        } else if (currentState == State.START_MENU) {
            if (mouse.intersects(player1)) {
                previousState = currentState;
                game.currentState = State.PLAYER_ONE_MENU;
                currentState = State.PLAYER_ONE_MENU;
            }
            if (mouse.intersects(player2)) {
                previousState = currentState;
                game.currentState = State.PLAYER_TWO_MENU;
                currentState = State.PLAYER_TWO_MENU;
            }
        } else if ((currentState == State.PLAYER_ONE_MENU)
                || (currentState == State.PLAYER_TWO_MENU)) {
            if (mouse.intersects(play)) {
                previousState = currentState;
                game.currentState = State.PLAY_SCREEN;
                currentState = State.PLAY_SCREEN;
                new ImportPuzzles(game, game.category);
                game.createPuzzle();
                if (currentState == State.PLAYER_ONE_MENU) {
                    // get player name and category picked
                } else if (currentState == State.PLAYER_TWO_MENU) {
                    // get both player names and the custom puzzle (and
                    // category?)
                }
            }
        } else if (currentState == State.PLAY_SCREEN) {

        } else if (currentState == State.WIN_SCREEN) {

        } else if (currentState == State.LOSE_SCREEN) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent m) {
        mouse = new Rectangle(m.getX(), m.getY(), 1, 1);
        Rectangle unDragable = new Rectangle(18, 18, FRAME_WIDTH - 18,
                FRAME_HEIGHT - 18);
        if (!mouse.intersects(unDragable)) {
            posX = m.getX();
            posY = m.getY();
            allowed = true;
        } else {
            allowed = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent m) {
    }

    public void mouseDragged(MouseEvent m) {
        if (allowed) {
            game.frame.setLocation(m.getXOnScreen() - posX, m.getYOnScreen()
                    - posY);
            System.out.println("Dropped it");
        }

    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
