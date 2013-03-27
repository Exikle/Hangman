package com.xid_studios.java.game.hangman.screens;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class PlayScreen extends BasicGameState {
    Image backGround = null;
    TrueTypeFont f1, f2;

    public PlayScreen(int State) {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        backGround = new Image("res/ChalkBackground.png");

        try {
            InputStream inputStream = ResourceLoader
                    .getResourceAsStream("res/EraserDust.ttf");
            Font g = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            g = g.deriveFont(28f); // set font size
            f1 = new TrueTypeFont(g, false);
            g = g.deriveFont(15f); // set font size
            f2 = new TrueTypeFont(g, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        backGround.draw(0, 0);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
