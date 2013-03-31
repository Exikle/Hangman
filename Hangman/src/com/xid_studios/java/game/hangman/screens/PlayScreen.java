package com.xid_studios.java.game.hangman.screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.xid_studios.java.game.hangman.InformationHolder;

public class PlayScreen extends BackgroundCode {
    Image backGround = null;
    TrueTypeFont f1, f2;
    String playerOne;
    String playerTwo;

    public PlayScreen(int State) {
        super(State);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        super.init(gc, sbg);
        g = g.deriveFont(28f); // set font size
        f1 = new TrueTypeFont(g, false);
        g = g.deriveFont(20f); // set font size
        f2 = new TrueTypeFont(g, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        super.render(gc, sbg, g);
        g.setFont(f1);
        g.drawString("Player Name", 240, 75);
        g.drawString("Opponent Name", 240, 135);
        g.setFont(f2);
        g.drawString("-" + playerOne, 240, 110);
        g.drawString("-" + playerTwo, 240, 160);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        super.update(gc, sbg, delta);
        playerOne = InformationHolder.playerOneName;
        playerTwo = InformationHolder.playerTwoName;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
