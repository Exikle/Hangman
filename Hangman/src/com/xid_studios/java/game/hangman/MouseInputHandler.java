package com.xid_studios.java.game.hangman;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {

    Game game;
    State currentState = null;
    State previousState = null;

    Rectangle close = new Rectangle(445, 30, 25, 25);
    Rectangle back = new Rectangle(0, 0, 10, 10);
    Rectangle player1 = new Rectangle(240, 80, 190, 20);
    Rectangle player2 = new Rectangle(240, 200, 190, 20);
    Rectangle play = new Rectangle(392, 298, 466, 341);
    Rectangle unDragable;

    Rectangle mouse;

    public MouseInputHandler(Game game) {
        game.addMouseListener(this);
        this.game = game;
        currentState = game.currentState;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        // System.out.println(m.getX() + "," + m.getY());
        mouse = new Rectangle(m.getX(), m.getY(), 1, 1);

        // System.out.println("State: " + currentState);

        if (mouse.intersects(close)) {
            System.exit(0);
        } else if (mouse.intersects(back)) {
            if (previousState != null) {
                game.currentState = previousState;
                currentState = previousState;
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
        unDragable = new Rectangle(25, 25, 450, 325);
        if (!mouse.intersects(unDragable)) {
            // code for dragging
        }
    }

    @Override
    public void mouseReleased(MouseEvent m) {
        // TODO Auto-generated method stub

    }

}
