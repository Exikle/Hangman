package com.xid_studios.java.game.hangman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {

    Game game;
    State currentState;

    public MouseInputHandler(Game game) {
        game.addMouseListener(this);
        this.game = game;
        currentState = game.currentState;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        if (currentState == State.START_MENU) {
            System.out.println("Start menu clicked");
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
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent m) {
        // TODO Auto-generated method stub

    }

}
