package com.xid_studios.java.game.hangman;

import javax.swing.JFrame;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.xid_studios.java.game.hangman.screens.LoseScreen;
import com.xid_studios.java.game.hangman.screens.PlayScreen;
import com.xid_studios.java.game.hangman.screens.PlayerOneMenu;
import com.xid_studios.java.game.hangman.screens.PlayerTwoMenu;
import com.xid_studios.java.game.hangman.screens.StartMenu;
import com.xid_studios.java.game.hangman.screens.WinScreen;

public class Game extends StateBasedGame {

    public final static String GAME_NAME = "Hangman - Xid Studios";
    static JFrame frame;
    static int FRAME_WIDTH = 500;
    static int FRAME_HEIGHT = 375;

    int startMenu = 0;
    int pOneMenu = 1;
    int pTwoMenu = 2;
    int playScreen = 3;
    int loseMenu = 4;
    int winMenu = 5;

    public Game() {
        super(GAME_NAME);
        this.addState(new StartMenu(startMenu));
        this.addState(new PlayerOneMenu(pOneMenu));
        this.addState(new PlayerTwoMenu(pTwoMenu));
        this.addState(new PlayScreen(playScreen));
        this.addState(new LoseScreen(loseMenu));
        this.addState(new WinScreen(winMenu));
        ImportXML.importValues();
        FRAME_WIDTH = InformationHolder.width;
        FRAME_HEIGHT = InformationHolder.height;
    }

    public static void main(String[] args) throws SlickException {
        frame = new JFrame();
        CanvasGameContainer app = new CanvasGameContainer(new Game());
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.add(app);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
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
