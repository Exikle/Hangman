package com.xid_studios.java.game.hangman.screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenu extends BasicGameState {
    Image backGround = null;

    public StartMenu(int State) {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        backGround = new Image("res/ChalkBackground.png");gc.setShowFPS(false);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        backGround.draw(0, 0);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

}
