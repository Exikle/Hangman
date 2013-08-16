package com.xidstudios.java.game.hangman.screens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.xidstudios.java.game.hangman.InformationHolder;

public class PlayerOneMenu extends BackgroundCode implements KeyListener {
    TrueTypeFont f1, f2;

    private final String[] categories = { "Easy", "Food", "Standard",
            "Geography", "Hard", "Holidays", "Animals", "Sports" };

    String[] playerOneName = { " ", " ", " ", " ", " ", " " };
    int nameCount = 0;

    Boolean nameCreationStarted = false, nameCreated = false, firstMove = true;

    int selected = 0;

    public PlayerOneMenu(int State) {
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
        g.setFont(f1);
        g.drawString("PLAY", 400, 300);
        g.drawString("Player One's Name", 240, 80);
        for (int x = 0; x < 6; x++) {
            g.drawString(playerOneName[x], 240 + (20 * x), 120);
        }
        g.drawString("Categories", 240, 165);
        g.setFont(f2);

        for (int c = 0; c < categories.length; c++) {
            if (selected == c) {
                g.setColor(Color.orange);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(categories[c], 250, 195 + (c * 15));
        }
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
                String name = "";
                for (int x = 0; x < 6; x++) {
                    name += playerOneName[x];
                }

                System.out.println("Playtime " + name);
                InformationHolder.playerOneName = name;
                InformationHolder.playerTwoName = "Computer";

                if (screenChangeAllowed()) {
                    sbg.enterState(3);
                } else {
                    System.out.println("Not allowed");
                }

            }
        }

        if (input.isKeyPressed(Input.KEY_BACK)) {
            if ((playerOneName[nameCount] == " ") && (nameCount != 0)) {
                playerOneName[nameCount - 1] = " ";
            } else {
                playerOneName[nameCount] = " ";
            }
            nameCount--;
            if (nameCount < 0) {
                nameCount = 0;
            }
        }

        if (nameCount == 6) {
            nameCount = 5;
        }
    }

    private boolean screenChangeAllowed() {
        Boolean allow = false;
        if (nameCreated == true)
            allow = true;
        return allow;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (Character.isLetter(c)) {
            if (nameCreationStarted == false) {
                nameCreationStarted = true;
                nameCreated = true;
            }
            playerOneName[nameCount] = "" + c;
            playerOneName[nameCount] = playerOneName[nameCount].toUpperCase();
            nameCount += 1;
        } else {
            // do other stuff
        }

    }

    @Override
    public void keyReleased(KeyEvent k) {
//        if (!firstMove) {
//            firstMove = true;
//        }

    }

    @Override
    public void keyTyped(KeyEvent k) {
        if (firstMove) {
            if (k.getKeyCode() == KeyEvent.VK_UP) {
                if (selected != 0) {
                    selected -= 1;
                }
                firstMove = false;
            }
            if (k.getKeyCode() == KeyEvent.VK_DOWN) {
                if (selected != categories.length) {
                    selected += 1;
                }
                firstMove = false;
            }
            System.out.println(selected);
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
    }

}
