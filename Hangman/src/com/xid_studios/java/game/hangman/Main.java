package com.xid_studios.java.game.hangman;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.xid_studios.java.game.hangman.screens.*;

public class Main extends StateBasedGame {

    public final static String GAME_NAME = "Hangman - Xid Studios";

    final static int FRAME_WIDTH = 500;
    final static int FRAME_HEIGHT = 375;

    int startMenu = 0;
    int pOneMenu = 1;
    int pTwoMenu = 2;
    int playScreen = 3;
    int loseMenu = 4;
    int winMenu = 5;

    public Main() {
        super(GAME_NAME);
        this.addState(new StartMenu(startMenu));
        this.addState(new PlayerOneMenu(pOneMenu));
        this.addState(new PlayerTwoMenu(pTwoMenu));
        this.addState(new PlayScreen(playScreen));
        this.addState(new LoseScreen(loseMenu));
        this.addState(new WinScreen(winMenu));

    }

    // @Override
    // public void init(GameContainer gc) throws SlickException {
    //
    // }
    //
    // @Override
    // public void update(GameContainer gc, int delta) throws SlickException {
    //
    // }
    //
    // public void render(GameContainer gc, Graphics g) throws SlickException {
    //
    // }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main());

        app.setDisplayMode(FRAME_WIDTH, FRAME_HEIGHT, false);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(startMenu).init(gc, this);
        this.getState(pOneMenu).init(gc, this);
        this.getState(pTwoMenu).init(gc, this);
        this.getState(playScreen).init(gc, this);
        this.getState(loseMenu).init(gc, this);
        this.getState(winMenu).init(gc, this);

        this.enterState(startMenu);

    }
}
