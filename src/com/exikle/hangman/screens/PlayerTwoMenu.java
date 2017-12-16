package com.exikle.hangman.screens;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerTwoMenu extends BackgroundCode {
    TrueTypeFont f1, f2;

    public PlayerTwoMenu(int State) {
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
        g.drawString("PLAY", 400, 300);
        g.drawString("Player One's Name", 240, 80);
        g.drawString("Player Two's Name", 240, 165);
        g.setFont(f2);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    throws SlickException {
        super.update(gc, sbg, delta);
        Input input = gc.getInput();

        final int FRAME_HEIGHT = 375;
        Rectangle mouse = new Rectangle(Mouse.getX(), FRAME_HEIGHT
                                        - Mouse.getY(), 1, 1);
        Rectangle play = new Rectangle(400, 300, 60, 30);

        if (input.isMousePressed(0)) {
            if (mouse.intersects(play)) {
                System.out.println("Play");
                sbg.enterState(3);
            }

        }
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 2;
    }

}
