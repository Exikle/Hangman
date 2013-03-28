package com.xid_studios.java.game.hangman.screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class PlayScreen extends BackgroundCode {
    Image backGround = null;
    TrueTypeFont f1, f2;

    public PlayScreen(int State) {
        super(State);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        super.init(gc, sbg);
        g = g.deriveFont(28f); // set font size
        f1 = new TrueTypeFont(g, false);
        g = g.deriveFont(15f); // set font size
        f2 = new TrueTypeFont(g, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        super.render(gc, sbg, g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        super.update(gc, sbg, delta);
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
