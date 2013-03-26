package com.xid_studios.java.game.hangman.screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenu extends BasicGameState {
    Image backGround = null;
    Image gallows;

    public StartMenu(int State) {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        backGround = new Image("res/ChalkBackground.png");
        gallows = new Image("res/Gallows.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        backGround.draw(0, 0);
        gallows.draw(0, 60);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            System.exit(0);
        }

    }

    @Override
    public int getID() {
        return 0;
    }

}
