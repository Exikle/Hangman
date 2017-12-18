/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dixondcunha
 */
public class Resources {

    public static final boolean DEBUG_APP = true;
    public static final String RES_PATH = "res/";
    public static final String DEFAULT_DIFFICULTY = "Easy";
    public static final String FONT_NAME = "fonts/VTK.ttf";
    public static final String FONT_FILE_PATH = RES_PATH + FONT_NAME;
    public static final String FONT_FILE_NAME = "vtks animal 2";
    public static final String DEFAULT_PLAYER_ONE_NAME = "Player 1";
    public static final String DEFAULT_PLAYER_TWO_NAME = "Player 2";
    public static final String[] DEFAULT_CATEGORIES = {"Easy", "Food", "Standard", "Geography",
        "Hard", "Holidays", "Animals", "Sports"
    };
    
    public static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static final int CATEGORY_AMNT = 8;

    public static Dimension MAIN_WINDOW_DIM = new Dimension(500, 325);

    public static final Image CHALK_BG = loadBackground();
    
    public static final Font GAME_FONT = null;
    
    public static final Screen START_SCREEN = Screen.START;
    

    private static Image loadBackground() {
        try {
            return ImageIO.read(new File(Resources.RES_PATH + "chalkBG.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

}
