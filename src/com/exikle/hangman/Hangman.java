package com.exikle.hangman;

/*
 * Hang Man v0.5
 * Created by Dixon D'Cunha
 * Version Update Log

 * v0.1
 * -> Create Board
 * -> Get Puzzles from file
 * -> Randomly choose puzzle
 * -> Output puzzle
 * -> Add buttons for letters

 * v0.2
 * -> Create Catergories
 * -> Change Letter Buttons to Key Buttons
 * -> Created + Added hangman pictures/gallows

 * v0.3
 * -> Created Scoring

 * v0.4
 * -> Added Player 2 Support
 * -> Fixed correct/incorrect letter
 * -> Fixed incorrect color for letters on drawpanel

 * v0.5
 * -> Cleaning up code prerework
 * -> compressed a lot of code
//TODO remake the panels/jframe 
 */
import com.exikle.hangman.objects.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//public class Hangman extends HFrame implements ActionListener,
//        KeyListener {
    
public class Hangman {

    DrawPanel startScreenPanel = new DrawPanel(); //first screen
    DrawPanel dPnl2 = new DrawPanel(); //second screen
    DrawPanel pnlBoard = new DrawPanel(); // Panels where everything is drawn on

    HPanel align; //panel holding categories to be selected
    JPanel pnl2 = new JPanel(); //goes ontop of the pnlBoard

    HPanel gameBoardPanel = new HPanel();

    String playerOneName = Resources.DEFAULT_PLAYER_ONE_NAME;
    String playerTwoName = Resources.DEFAULT_PLAYER_TWO_NAME;
    String currentPuzzleStr = "";
    String selected = Resources.DEFAULT_DIFFICULTY;

    String[] categories = Resources.DEFAULT_CATEGORIES;
    String[] allPuz;
    String[] puzzle;

    ArrayList<String> puzzleWordList = new ArrayList<>();

    HTextField customPuzzleTextField = new HTextField("Custom Puzzle");
    HTextField playerOneTextField = new HTextField("Player");
    HTextField playerTwoTextField = new HTextField("Opponent");

    HLabel playerOneLabel = new HLabel();
    HLabel playerTwoLabel = new HLabel();
    HLabel wordlist = new HLabel();

    HButton[] btnLetters = new HButton[26];
    HButton[] lblWordList = new HButton[8];

    HButton pickOnePlayerButton = new HButton();
    HButton pickTwoPlayersButton = new HButton();

    HButton btnBack = new HButton("Back");
    HButton btnStart = new HButton("Start");
    HButton resetBtn = new HButton("Reset Scores");
    HButton newGameBtn = new HButton("New Game");
    HButton btnMain = new HButton("Menu");

    int puzzleWordLength;
    int count = 0;
    int chances = 7;
    int lineNumber;
    int randomnum;
    int playerOneScore = 0;
    int playerTwoScore = 0;
    int move = 0;
    int rong;
    int players = 1;
//    int theSource = 1;

    int[] wrLetter = new int[26];
    int[] checked = new int[26];

    Icon[] cate = new ImageIcon[7];

    char[] puzle;
    char[] hid;

    Boolean gameDone = false;

    HFrame fr1 = new HFrame(""); //todo remove these and use only one frame

    Font startScreenTitleFont;
    Font headerFont;
    Font currentPuzzleDisplayFont;
    Font alphabetDockFont;

    State currentState;
    Screen currentScreen;

    Image gallowmanImage;

    /**
     *
     */
    public void initializeFonts() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Resources.FONT_FILE_PATH)));
        } catch (IOException | FontFormatException e) {
            {
                Debug.dbgPrint(e + "");
            }

        }
        startScreenTitleFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 50);
        headerFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 16); //
        currentPuzzleDisplayFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 35); //
        alphabetDockFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 22); //
    }

    public void initializeStartMenu() {
        fr1.add(startScreenPanel);
        fr1.setVisible(true);
        fr1.setSize(Resources.MAIN_WINDOW_DIM);
        // ////Chose Player Menu(1)==============>
        // /Player 1 button initialize--------->
        pickOnePlayerButton.setBounds(25, 125, 250, 50);
//        pickOnePlayerButton.addActionListener(this);
        pickOnePlayerButton.setText("Player One");
        pickOnePlayerButton.setFont(Resources.FONT_FILE_NAME, 30);

        startScreenPanel.add(pickOnePlayerButton);
        // /Player 2 button initialize--------->
        pickTwoPlayersButton.setBounds(25, 200, 250, 50);
//        pickTwoPlayersButton.addActionListener(this);
        pickTwoPlayersButton.setText("Player Two");
        pickTwoPlayersButton.setFont(Resources.FONT_FILE_NAME, 30);

        startScreenPanel.add(pickTwoPlayersButton);
        // /End Player Button Initialzing------->
        // ////<========End Chose Player Menu(1)
    }

    public Image updateGallowMan(State newState) {
        try {
            switch (newState) {
                case WALKING:
                    return ImageIO.read(new File(Resources.RES_PATH + "hanger.png"));
                case HANGING:
                    return ImageIO.read(new File(Resources.RES_PATH + "hanger2.png"));
                case DEAD:
                    return ImageIO.read(new File(Resources.RES_PATH + "hanger3.png"));
            }
        } catch (IOException ioe) {
        }
        return null;
    }

    public void initializeVariables() {
        currentState = State.WALKING;
        currentScreen = Resources.START_SCREEN;
    }

    public Hangman() {
//        initializeFonts();
//        initializeVariables();

//        this.addKeyListener(this);
        // Initialize the Checklists------------->
        resetCheckLists();
        // <------- End Initializing Checklists
//        initializeStartMenu();

        // ////Chose Categories Menu(2)========>
        // /Initialize WordList Icon\Label--->
        dPnl2.add(wordlist);
        wordlist.setBounds(100, 75, 150, 75);
        wordlist.setForeground(Color.WHITE);
        wordlist.setFont(Resources.FONT_FILE_NAME, 20);
        wordlist.setText("Word List");
        // /Initialize Go To 1st Menu Button---------->
        dPnl2.add(btnBack);
        btnBack.setBounds(0, 250, 75, 25);
        btnBack.setFont(Resources.FONT_FILE_NAME, 11);
//        btnBack.addActionListener(this);
        // /Initialize Start Game/Go to Board---------->
        dPnl2.add(btnStart);
        btnStart.setBounds(225, 250, 75, 25);
        btnStart.setFont(Resources.FONT_FILE_NAME, 11);
//        btnStart.addActionListener(this);

        // /Add Categories into grid------------->
//        align = createCategoryPanel();
        // /<-------------End Category Initializing and Layout Setiing

        // ////<==================End Chose Player Menu(2)
        // ////Create Playing Board================>
        // Add Board to main form------------->
        pnlBoard.setLayout(new GridLayout(1, 1));
        pnl2.setLayout(new BorderLayout());
        pnl2.add(pnlBoard, BorderLayout.CENTER);
        // <---------End Add Board to main form
        gameBoardPanel.setLayout(new GridLayout(1, 1));
        gameBoardPanel.add(pnl2);
        // Initialize New Game Button---------->
        newGameBtn.setFont(Resources.FONT_FILE_NAME, 11);
//        newGameBtn.addActionListener(this);
//        this.add(newGameBtn);
        newGameBtn.setBounds(225, 0, 100, 25);

        // Initialize Reset Scores Button---------->
        resetBtn.setFont(Resources.FONT_FILE_NAME, 11);
//        resetBtn.addActionListener(this);
//        this.add(resetBtn);
        resetBtn.setBounds(100, 0, 125, 25);

        // Initialize Go To Main Menu Button---------->
        btnMain.setFont(Resources.FONT_FILE_NAME, 11);
//        btnMain.addActionListener(this);
//        this.add(btnMain);
        btnMain.setBounds(0, 0, 100, 25);

//        gallowmanImage = updateGallowMan(State.WALKING);
//        this.add(gameBoardPanel);
//        this.setSize(Resources.MAIN_WINDOW_DIM);
        // ////<===========End Create Playing Board
    }

    /*
     *  Add Categories into grid
     */
    public HPanel createCategoryPanel() {

        HPanel categoryListPanel = new HPanel();
        categoryListPanel.setLayout(new GridLayout(4, 2));

        for (int x = 0; x < Resources.CATEGORY_AMNT; x++) {
            lblWordList[x] = new HButton(categories[x] + "");
            lblWordList[x].setFont(Resources.FONT_FILE_NAME, 12);
            lblWordList[x].setOpaque(false);
            lblWordList[x].setContentAreaFilled(false);
            lblWordList[x].setBorderPainted(false);
//            lblWordList[x].addActionListener(this);
            categoryListPanel.add(lblWordList[x]);
        }

        categoryListPanel.setBounds(25, 125, 250, 100);
        categoryListPanel.setOpaque(false);
        return categoryListPanel;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof HButton) {
            if (source == pickOnePlayerButton) {
                //Switch to next panel to choose categories
                Debug.dbgPrint("Player 1 was pressed");

                fr1.remove(startScreenPanel);
                fr1.add(dPnl2);

                currentScreen = Screen.CATEGORY_A;
                players = 1;
                lblWordList[1].setForeground(Color.BLUE);

                dPnl2.add(align);

                dPnl2.add(playerOneLabel);
                playerOneLabel.setBounds(50, 50, 75, 25);
                playerOneLabel.setFont(Resources.FONT_FILE_NAME, 15);
                playerOneLabel.setForeground(Color.WHITE);
                playerOneLabel.setText("Name");

                dPnl2.add(playerOneTextField);
                playerOneTextField.setBounds(125, 50, 125, 25);
                playerOneTextField.setFont(Resources.FONT_FILE_NAME, 13);

                dPnl2.add(wordlist);
                wordlist.setBounds(100, 75, 150, 75);

                dPnl2.remove(playerTwoLabel);
                dPnl2.remove(playerTwoTextField);
                dPnl2.remove(customPuzzleTextField);
            } else if (source == pickTwoPlayersButton) {

                System.out.println("Player 2 was pressed");
                lblWordList[1].setForeground(Color.BLUE);
                players = 2;
                currentScreen = Screen.CATEGORY_B;

                selected = "Custom";

                fr1.remove(startScreenPanel);
                fr1.add(dPnl2);

                dPnl2.remove(align);

                dPnl2.remove(wordlist);
                dPnl2.add(playerTwoLabel);

                playerTwoLabel.setBounds(75, 125, 100, 25);
                playerTwoLabel.setFont(Resources.FONT_FILE_NAME, 15);
                playerTwoLabel.setForeground(Color.WHITE);
                playerTwoLabel.setText("Opponent");

                dPnl2.add(playerTwoTextField);
                playerTwoTextField.setBounds(175, 125, 100, 25);
                playerTwoTextField.setFont(Resources.FONT_FILE_NAME, 13);

                dPnl2.add(customPuzzleTextField);
                customPuzzleTextField.setBounds(100, 175, 100, 25);

                dPnl2.add(playerOneLabel);
                playerOneLabel.setBounds(75, 75, 75, 25);
                playerOneLabel.setFont(Resources.FONT_FILE_NAME, 15);
                playerOneLabel.setForeground(Color.WHITE);
                playerOneLabel.setText("Name");

                dPnl2.add(playerOneTextField);
                playerOneTextField.setBounds(150, 75, 125, 25);
                playerOneTextField.setFont(Resources.FONT_FILE_NAME, 13);
            }
        }

        if (e.getSource() == btnBack) {
            currentScreen = Screen.START;
            Debug.dbgPrint("Back was pressed");

            fr1.remove(dPnl2);
            fr1.add(startScreenPanel);
//            repaint();
        }
        if (e.getSource() == btnStart) {
            for (int x = 0; x < 8; x++) {
                lblWordList[x].setForeground(Color.BLACK);
                if (e.getSource() == lblWordList[x]) {
                    if (players == 1) {
                        selected = lblWordList[x].getText();
                        lblWordList[x].setForeground(Color.BLUE);
                        System.out.println(x);
                    } else {
                        selected = playerTwoName + "'s Puzzle";
                    }
                }
            }
            currentScreen = Screen.GAME;

            Debug.dbgPrint("Start was pressed");
            playerOneName = playerOneTextField.getText();
            if (players == 2) {
                playerTwoName = playerTwoTextField.getText();
            }
            fr1.setVisible(false);
//            puzzleWordList = Parser.loadPuzzlesFromFile(selected);
            Parser.countLines(selected);
//            try {
//                if (players == 1) {
//                    getPuz();
//                }
//                createPuz();
//            } catch (IOException f) {
//                Debug.dbgPrint("Problem Creating Puzzle");
//            }
//            this.setVisible(true);
//            repaint();
        }

        if (e.getSource() == resetBtn) {
            playerTwoScore = 0;
            playerOneScore = 0;
//            repaint();
        } else if (e.getSource() == newGameBtn) {
            move = 0;
            count = 0;
            gameDone = false;

            resetCheckLists();
//            try {
//                getPuz();
//                createPuz();
//            } catch (IOException f) {
//                Debug.dbgPrint("Problem Creating Puzzle");
//            }
//            repaint();
        }
        if (e.getSource() == btnMain) {
            playerTwoScore = 0;
            playerOneScore = 0;
            move = 0;
            count = 0;
            gameDone = false;
            playerTwoName = "Opponent";
            playerOneName = "Player";

            resetCheckLists();
//            this.setVisible(false);
            fr1.setVisible(true);
            currentScreen = Screen.CATEGORY_A;

//            repaint();
        }
    }

    private void resetCheckLists() {
        for (int x = 0; x < 26; x++) {
            checked[x] = 0;
            wrLetter[x] = 0;
        }
    }

    public class DrawPanel extends HPanel {

//        public void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//            drawBG(g2);
//            switch (currentScreen) {
//                case START:
//                    drawStartMenu(g2);
//                    break;
//                case CATEGORY_A:
//                case CATEGORY_B:
//                    g2.setColor(Color.WHITE);
//                    g2.setFont(startScreenTitleFont);
//                    break;
//                case GAME:
//                    drawCharacter(g2);
//                    drawGallows(g2);
//                    drawLetters(g2);
//
//                    g2.setFont(currentPuzzleDisplayFont);
//                    g2.setColor(Color.BLACK);
//                    for (int x = 0; x < puzzleWordLength; x++) {
//                        g2.drawString("" + hid[x], 25 + 35 * x, 250);
//                    }
//
//                    drawScoreBoard(g2);
//
//                    break;
//                default:
//                    throw new AssertionError(currentScreen.name());
//            }
//        }

        private void drawGallows(Graphics2D g2) {
            g2.setColor(Color.RED);
            g2.fillRect(50, 175, 125, 25);
            g2.fillRect(75, 25, 25, 150);
            g2.fillRect(100, 25, 75, 25);
            g2.fillRect(175, 175, 25, 25);
            g2.setStroke(new BasicStroke(10));
            g2.drawLine(100, 75, 125, 50);
        }

        private void drawLetters(Graphics2D g2) {
            g2.setColor(Color.BLACK);
            g2.setFont(alphabetDockFont);

            for (int x = 0; x < 26; x++) {
                switch (wrLetter[x]) {
                    case 0:
                        g2.setColor(Color.BLACK);
                        break;
                    case 1:
                        g2.setColor(Color.GREEN);
                        break;
                    case 2:
                        g2.setColor(Color.RED);
                        break;
                }
                g2.drawString("" + Resources.ALPHABET[x], 5 + 19 * x, 305);
            }
        }

        private void drawStartMenu(Graphics2D g2) {
            int titleX = Resources.MAIN_WINDOW_DIM.width / 5;
            int titleY = Resources.MAIN_WINDOW_DIM.height / 3;

            g2.setColor(Color.WHITE);
            g2.setFont(startScreenTitleFont);
            g2.drawString("Hang Man", titleX, titleY);
        }

        private void drawBG(Graphics2D g2) {
            g2.drawImage(Resources.CHALK_BG, 0, 0, Resources.MAIN_WINDOW_DIM.width,
                    Resources.MAIN_WINDOW_DIM.height, 0, 0, 300, 300,
                    this); //Draw background
        }

        private void drawCharacter(Graphics2D g2) {

            if ((move < 5) && (move >= 0)) {
                g2.drawImage(gallowmanImage,
                        300 - 25 * move, 125, 350 - 25 * move, 200, 0, 0,
                        Resources.CHARACTER_WIDTH, Resources.CHARACTER_HEIGHT,
                        this);
            }
            if ((move >= 5) && (move < 7)) {
                g2.drawImage(gallowmanImage,
                        300 - 25 * move, 100, 350 - 25 * move, 175,
                        0, 0, Resources.CHARACTER_WIDTH, Resources.CHARACTER_HEIGHT,
                        this);
            }
            if ((move == 7) || (move >= 8)) {
                g2.drawImage(gallowmanImage, 125, 50, 175, 150,
                        0, 0, Resources.CHARACTER_WIDTH, Resources.CHARACTER_HEIGHT,
                        this);
                if (move == 7) {
                    g2.setColor(Color.RED);
                    g2.fillRect(125, 150, 50, 25);
                }
            }
        }

        private void drawScoreBoard(Graphics2D g2) {
            g2.setFont(headerFont);
            g2.setColor(Color.WHITE);
            g2.drawString("Category:", 375, 50);
            g2.drawString(playerOneName, 375, 100);
            g2.drawString(playerTwoName, 375, 150);

            g2.setColor(Color.BLACK);
            g2.drawString(selected, 375, 75);
            g2.drawString("" + playerOneScore, 375, 125);
            g2.drawString("" + playerTwoScore, 375, 175);
        }
    }

    public File getWordFile(String fileName) {
        String fullNameFilePath = Resources.RES_PATH + "Word List/" + fileName + ".txt";
        Debug.dbgPrint(fullNameFilePath);
        return new File(fullNameFilePath);
    }

    public int getLinesInFile(File f) {
        int lines = 0;
        LineNumberReader reader;

        try {
            reader = new LineNumberReader(
                    new FileReader(f));
            lines = reader.getLineNumber();
            Debug.dbgPrint("Lines" + lines);
            reader.close();
        } catch (IOException e) {
        }
        return lines;
    }

    public void createPuzzle(String selectedCategory) {
        int linesInFile = Parser.countLines(selectedCategory);
    }

    public void getPuz() {
        BufferedReader in = null;
        String line = "A B 1";
        File wordFile = getWordFile(selected);
        int num = 0;

//        lineNumber = Parser.countLines(selected);
        lineNumber = getLinesInFile(wordFile);
        Debug.dbgPrint("?Lines in file:" + lineNumber);

        try {
            allPuz = new String[lineNumber];
            in = new BufferedReader(new FileReader(wordFile));
            System.out.println("File Opening");
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening File");
        }

        while (line != null) {
            try {
                line = in.readLine();
                if (line != null) {
                    allPuz[num] = "" + line;
                    num++;
                }
            } catch (IOException e) {
                System.out.println("Problem reading data from file");
            }
        }
        try {
            in.close();
            Debug.dbgPrint("Closing File");
        } catch (IOException e) {
            Debug.dbgPrint("Problem Closing " + e);
        }
    }

    public void createPuz() throws IOException {
        randomnum = (int) (Math.random() * lineNumber);

        if (players == 1) {
            currentPuzzleStr = "" + allPuz[randomnum];
        } else if (players == 2) {
            currentPuzzleStr = customPuzzleTextField.getText();
        }

        Debug.dbgPrint("Current Puzzle: " + currentPuzzleStr);
        puzzleWordLength = currentPuzzleStr.length();
        puzle = new char[puzzleWordLength];
        hid = new char[puzzleWordLength];

        for (int x = 0; x < puzzleWordLength; x++) {
            puzle[x] = Character.toUpperCase(currentPuzzleStr.charAt(x));
            if (puzle[x] == ' ') {
                hid[x] = (' ');
                count += 1;
            } else {
                hid[x] = ('_');
            }
        }
    }
//
//    public void keyTyped(KeyEvent f) {
//        Boolean wrongLetterFlag = true;
//
//        if (gameDone == false) {
//            String key = "" + f.getKeyChar();
//            Boolean rightletter = false;
//            wrongLetterFlag = true;
//            for (int x = 0; x < 26; x++) {
//                if (("" + Resources.ALPHABET[x]).equalsIgnoreCase(key)) {
//                    if (checked[x] == 1) {
//                        JOptionPane.showMessageDialog(this,
//                                "Already pressed " + Resources.ALPHABET[x] + ".");
//                        for (int y = 0; y < puzzleWordLength; y++) {
//                            if (Resources.ALPHABET[x] == puzle[y]) {
//                                hid[y] = puzle[y];
//                                rightletter = true;
//                                wrLetter[x] = 1;
//                                wrongLetterFlag = false;
//                                checked[x] = 1;
//                                if (count == puzzleWordLength) {
//                                    JOptionPane.showMessageDialog(
//                                            this, "You Win");
//                                    gameDone = true;
//                                    playerOneScore += 1;
//                                }
//                            }
//                        }
//                    } else if (checked[x] == 0) {
//                        for (int y = 0; y < puzzleWordLength; y++) {
//                            if (Resources.ALPHABET[x] == puzle[y]) {
//                                hid[y] = puzle[y];
//                                rightletter = true;
//                                wrLetter[x] = 1;
//                                count += 1;
//                                wrongLetterFlag = false;
//                                checked[x] = 1;
//                                if (count == puzzleWordLength) {
//                                    JOptionPane.showMessageDialog(
//                                            this, "You Win");
//                                    gameDone = true;
//                                    playerOneScore += 1;
//                                }
//                            }
//                        }
//                    }
//                    rong = x;
//                }
//            }
//            if (rightletter == false) {
//                for (int x = 0; x < 26; x++) {
//                    if (("" + Resources.ALPHABET[x]).equalsIgnoreCase(key)) {
//                        move++;
//                        wrLetter[x] = 2;
//                        checked[x] = 1;
//                        rong = x;
//                    }
//                }
//            }
//            if (wrongLetterFlag == true) {
//                wrLetter[rong] = 2;
//            }
//            wrongLetterFlag = true;
//            if (move == 7) {
//                gallowmanImage = updateGallowMan(State.HANGING);
//            } else if (move >= 8) {
//                JOptionPane.showMessageDialog(this, "You Lose");
//                gallowmanImage = updateGallowMan(State.DEAD);
//                playerTwoScore += 1;
//                gameDone = true;
//                pnlBoard.setEnabled(false);
//                resetBtn.setEnabled(true);
//                for (int x = 0; x < 26; x++) {
//                    for (int y = 0; y < puzzleWordLength; y++) {
//                        if (Resources.ALPHABET[x] == puzle[y]) {
//                            hid[y] = puzle[y];
//                        }
//                    }
//                }
//            } else {
//                gallowmanImage = updateGallowMan(State.WALKING);
//            }
//            repaint();
//        }
//    }
//
//    public void keyPressed(KeyEvent f) {
//    }
//
//    public void keyReleased(KeyEvent f) {
//    }

}
