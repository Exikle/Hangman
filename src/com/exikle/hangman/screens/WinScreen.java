package com.exikle.hangman.screens;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinScreen extends BasicGameState {

    public WinScreen(int State) {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame arg1)
            throws SlickException {
        gc.setShowFPS(false);
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(GameContainer gc, StateBasedGame arg1, int arg2)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 5;
    }

}
