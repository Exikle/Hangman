package com.xid_studios.java.game.hangman.screens;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class StartMenu extends BasicGameState {
    Image backGround = null;
    TrueTypeFont f;

    public StartMenu(int State) {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        backGround = new Image("res/ChalkBackground.png");

        try {
            InputStream inputStream = ResourceLoader
                    .getResourceAsStream("res/EraserDust.ttf");
            Font g = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            g = g.deriveFont(50f); // set font size
            f = new TrueTypeFont(g, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        backGround.draw(0, 0);
        g.setFont(f);
        g.drawString("1 Player", 250, 112);
        g.drawString("2 Player", 250, 212);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            System.exit(0);
        }

        if (input.isMousePressed(0)) {
            final int FRAME_HEIGHT = 375;
            Rectangle mouse = new Rectangle(Mouse.getX(), FRAME_HEIGHT
                    - Mouse.getY(), 1, 1);
            Rectangle playerOneButton = new Rectangle(250, 120, 200, 40);
            Rectangle playerTwoButton = new Rectangle(250, 225, 200, 40);
            if (mouse.intersects(playerOneButton)) {
                sbg.enterState(1);
            } else if (mouse.intersects(playerTwoButton)) {
                sbg.enterState(2);
            }
        }

    }

    @Override
    public int getID() {
        return 0;
    }

}
