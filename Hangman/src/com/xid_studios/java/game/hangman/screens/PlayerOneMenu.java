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

public class PlayerOneMenu extends BasicGameState {
    Image backGround = null;
    TrueTypeFont f1, f2;

    private final String[] categories = { "Easy", "Food", "Standard",
            "Geography", "Hard", "Holidays", "Animals", "Sports" };

    public PlayerOneMenu(int State) {

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
        g.setFont(f1);
        g.drawString("PLAY", 400, 300);
        g.drawString("Player One's Name", 240, 80);
        g.drawString("Categories", 240, 165);
        g.setFont(f2);

        for (int c = 0; c < categories.length; c++) {
            g.drawString(categories[c], 250, 195 + (c * 15));
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
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
        return 1;
    }

}
