package com.exikle.hangman.temp.screens;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenu extends BackgroundCode {
    TrueTypeFont f;

    public StartMenu(int State) {
        super(State);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
    throws SlickException {
        super.init(gc, sbg);
        g = g.deriveFont(50f); // set font size
        f = new TrueTypeFont(g, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    throws SlickException {
        super.render(gc, sbg, g);
        g.setFont(f);
        g.drawString("1 Player", 250, 112);
        g.drawString("2 Player", 250, 212);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    throws SlickException {
        Input input = gc.getInput();
        super.update(gc, sbg, delta);

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
