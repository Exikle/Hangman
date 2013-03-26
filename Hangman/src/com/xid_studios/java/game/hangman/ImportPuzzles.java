package com.xid_studios.java.game.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

class ImportPuzzles {
    private final String category;
    private String[] allPuz;
    private int linenum;

    public ImportPuzzles(Game game, String category) {
        this.category = category;
        getPuz();
        game.allPuz = allPuz;
    }

    void getPuz() {
        BufferedReader in = null;
        String line = "";
        File f = new File("res/Word List/" + category + ".txt");
        int num = 0;
        LineNumberReader reader = null;

        try {
            reader = new LineNumberReader(new FileReader(f));
        } catch (FileNotFoundException e2) {
            System.out.println("File Not Found");
        }
        @SuppressWarnings("unused")
        String lineRead = "";
        try {
            if (reader != null) {
                while ((lineRead = reader.readLine()) != null) {
                }
            }
        } catch (IOException e1) {
            System.out.println("Error 1");
        }
        if (reader != null) {
            linenum = reader.getLineNumber();
        }
        try {
            reader.close();
        } catch (IOException e1) {
            System.out.println("Error 2");
        }
        try {
            allPuz = new String[linenum];
            in = new BufferedReader(new FileReader(f));
            System.out.println("File Opening");
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening File");
        }
        while (line != null) {
            try {
                if (in != null) {
                    line = in.readLine();
                }
                if (line != null) {
                    allPuz[num] = "" + line;
                    num++;
                }
            } catch (IOException e) {
                System.out.println("Problem reading data from file");
            }
        }

    }
}
