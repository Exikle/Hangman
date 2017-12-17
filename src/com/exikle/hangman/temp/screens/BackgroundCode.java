package com.exikle.hangman.temp.screens;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
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

public class BackgroundCode extends BasicGameState {
    Image backGround = null;
    TrueTypeFont f;
    int s;
    Font g = null;

    public BackgroundCode(int State) {
        s = State;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
    throws SlickException {
        backGround = new Image("res/ChalkBackground.png");
        try {
            InputStream inputStream = ResourceLoader
                                      .getResourceAsStream("res/EraserDust.ttf");
            g = Font.createFont(Font.TRUETYPE_FONT, inputStream);

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
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }
        if (input.isMouseButtonDown(0)) {
            System.out.println(input.getMouseX() + "," + input.getMouseY());
            Rectangle close = new Rectangle(445, 33, 21, 23);
            Rectangle home = new Rectangle(400, 27, 40, 31);
            Rectangle mouse = new Rectangle(input.getMouseX(),
                                            input.getMouseY(), 21, 23);
            if (mouse.intersects(close)) {
                Display.destroy();
                System.exit(0);
                System.out.println("Closed");
            }
            if (mouse.intersects(home)) {
                sbg.enterState(0);
            }
        }

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return s;
    }

}
