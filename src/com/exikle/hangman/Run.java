/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman;

import com.exikle.hangman.graphics.HangmanFonts;

/**
 *
 * @author dixondcunha
 */
public class Run {

    public static void main(String[] args) {
        HangmanFonts.loadFonts();
        new Hangman();
    }
}
