/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.graphics;

import com.exikle.hangman.Debug;
import com.exikle.hangman.Resources;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author dixondcunha
 */
public class HangmanFonts {

    public static void loadFonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Resources.FONT_FILE_PATH)));
        } catch (IOException | FontFormatException e) {
            {
                Debug.dbgPrint(e + "");
            }

        }
    }
}
