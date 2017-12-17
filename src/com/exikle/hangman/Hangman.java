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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import javax.imageio.ImageIO;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman extends HFrame implements ActionListener,
        KeyListener {

    DrawPanel startScreenPanel = new DrawPanel(); //first screen
    DrawPanel dPnl2 = new DrawPanel(); //second screen
    DrawPanel pnlBoard = new DrawPanel(); // Panels where everything is drawn on

    JPanel align; //panel holding categories to be selected
    JPanel pnl2 = new JPanel(); //goes ontop of the pnlBoard

    JPanel pnl3 = new JPanel();
    JPanel pnl4 = new JPanel();
    JPanel pnl5 = new JPanel();
    JPanel pnl6 = new JPanel();
    JPanel pnl7 = new JPanel();

    String playerOneName = Resources.DEFAULT_PLAYER_ONE_NAME;
    String playerTwoName = Resources.DEFAULT_PLAYER_TWO_NAME;
    String currentPuzzleStr = "";
    String selected = Resources.DEFAULT_DIFFICULTY;

    String[] categories = Resources.DEFAULT_CATEGORIES;
    String[] allPuz;
    String[] puzzle;

    HTextField customPuzzleTextField = new HTextField("Custom Puzzle");
    HTextField playerOneTextField = new HTextField("Player");
    HTextField playerTwoTextField = new HTextField("Opponent");

    HLabel playerOneLabel = new HLabel();
    HLabel playerTwoLabel = new HLabel();
    HLabel wordlist = new HLabel();

    HButton[] btnLetters = new HButton[26];
//    HButton close = new HButton(); //TODO make into one
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
    int linenumber;
    int randomnum;
    int playerOneScore = 0;
    int playerTwoScore = 0;
    int theSource = 1;
    int move = 0;
    int rong;
    int players = 1;

    int[] wrLetter = new int[26];
    int[] checked = new int[26];

    Icon[] cate = new ImageIcon[7];

    //TODO replace with JLABELS using custom fonts
//    Icon py1 = new ImageIcon(RES_PATH + "Player 1.png"); //replaced
//    Icon py2 = new ImageIcon(RES_PATH + "Player 2.png"); //replaced
//    Icon wList = new ImageIcon(Resources.RES_PATH + "WordList.png");
//    Icon name = new ImageIcon(Resources.RES_PATH + "Name.png");
//    Icon opponent = new ImageIcon(Resources.RES_PATH + "Opponent.png");
//    Icon closeIMG = new ImageIcon(RES_PATH + "closeBtn.png"); //removed completly, will need to rework it
    char[] puzle;
    char[] hid;

//    Boolean wrong = true;
    Boolean gameDone = false;

    // ------------ I'm new'
    HFrame hmWindow;
    HPanel bgPanel;
    // till here

    HFrame fr1 = new HFrame(""); //todo remove these and use only one frame
//    HFrame fr2 = new HFrame(""); //perhaps create a new panel, add stuff to that adn then add panel to fram temporarily

    Font startScreenTitleFont;
    Font headerFont;
    Font f7;
    Font f8;

    State currentState = State.WALKING;

    Image image;
    Image gallowmanImage;
    Image image3;

    /**
     *
     */
    public void initializeFonts() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Resources.FONT_FILE_PATH)));
        } catch (IOException | FontFormatException e) {
            {
//                e.printStackTrace();
            }

        }
        startScreenTitleFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 50);
        headerFont = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 16); //
        f7 = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 35); //
        f8 = new Font(Resources.FONT_FILE_NAME, Font.PLAIN, 22); //
    }

//    public HPanel createChalkboard() {
//        final Image bgimg = toolkit.createImage(imageURL);
//        return new HPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(bgimg, 0, 0, 300, 300, 0, 0, 300, 300,
//                        this);
//            }
//        };
//    }
    public void initializeStartMenu() {
        // ////Chose Player Menu(1)==============>
        // /Player 1 button initialize--------->
        pickOnePlayerButton.setBounds(25, 125, 250, 50);
        pickOnePlayerButton.addActionListener(this);
//        pickOnePlayerButton.setIcon(py1);
        pickOnePlayerButton.setText("Player One");
        pickOnePlayerButton.setFont(Resources.FONT_FILE_NAME, 30);
        startScreenPanel.add(pickOnePlayerButton);
        // /Player 2 button initialize--------->
        pickTwoPlayersButton.setBounds(25, 200, 250, 50);
        pickTwoPlayersButton.addActionListener(this);
//        pickTwoPlayersButton.setIcon(py2);
        pickTwoPlayersButton.setText("Player Two");
        pickTwoPlayersButton.setFont(Resources.FONT_FILE_NAME, 30);
        startScreenPanel.add(pickTwoPlayersButton);
        // /End Player Button Initialzing------->
        // ////<========End Chose Player Menu(1)
        return;
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
            ioe.printStackTrace();
        }
        return null;
    }

    public void initializeImages() {

        try {
            image = ImageIO.read(new File(Resources.RES_PATH + "chalkBG.png"));
            gallowmanImage = updateGallowMan(State.WALKING);
            image3 = ImageIO.read(new File(Resources.RES_PATH + "alphaDock.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return;
    }

    public Hangman() {
        initializeFonts();
//
//        hmWindow = new HFrame();
//        bgPanel = createChalkboard();
//        hmWindow.getContentPane().add(bgPanel);
//        
//        hmWindow.setVisible(true);
//        hmWindow.setSize(500, 325);
//        

        this.addKeyListener(this);
//        this.setFocusable(true);
        // Initialize the Checklists------------->
        resetCheckLists();
        // <------- End Initializing Checklists

        // Close button---------------------------> //TODO make this into only one
//        for (int x = 0; x < 3; x++) {
//            close[x] = new HButton();
//            close[x].addActionListener(this);
//            close[x].setBounds(275, 0, 25, 25);
//            close[x].setIcon(closeIMG);
//        }
//        close = initializeCloseButton();
//        startScreenPanel.add(close);// Add Close button to 1st Screen
//        dPnl2.add(close);// Add Close button to 2nd Screen
//        this.add(close);// Add Close button to Board
//        close[2].setBounds(475, 0, 25, 25);
        // <------End Close Button
        initializeImages();
        initializeStartMenu();

        fr1.add(startScreenPanel);
        fr1.setVisible(true);
//        fr1.setSize(300, 300);
        fr1.setSize(Resources.MAIN_WINDOW_DIM);
//        fr1.centerFrameOnScreen();

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
        btnBack.addActionListener(this);
        // /Initialize Start Game/Go to Board---------->
        dPnl2.add(btnStart);
        btnStart.setBounds(225, 250, 75, 25);
        btnStart.setFont(Resources.FONT_FILE_NAME, 11);
        btnStart.addActionListener(this);

        // /Add Categories into grid------------->
        align = createCategoryPanel();
        // /<-------------End Category Initializing and Layout Setiing

//        fr2.add(dPnl2);
//        fr2.setSize(300, 300);
//        fr2.centerFrameOnScreen();
        // ////<==================End Chose Player Menu(2)
        // ////Create Playing Board================>
        // Add Board to main form------------->
        pnlBoard.setLayout(new GridLayout(1, 1));
        pnl2.setLayout(new BorderLayout());
        pnl2.add(pnlBoard, BorderLayout.CENTER);
        // <---------End Add Board to main form
        pnl7.setLayout(new GridLayout(1, 1));
        pnl7.add(pnl2);
        // Initialize New Game Button---------->
        newGameBtn.setFont(Resources.FONT_FILE_NAME, 11);
        newGameBtn.addActionListener(this);
        this.add(newGameBtn);
        newGameBtn.setBounds(225, 0, 100, 25);

        // Initialize Reset Scores Button---------->
        resetBtn.setFont(Resources.FONT_FILE_NAME, 11);
        resetBtn.addActionListener(this);
        this.add(resetBtn);
        resetBtn.setBounds(100, 0, 125, 25);

        // Initialize Go To Main Menu Button---------->
        btnMain.setFont(Resources.FONT_FILE_NAME, 11);
        btnMain.addActionListener(this);
        this.add(btnMain);
        btnMain.setBounds(0, 0, 100, 25);

        this.add(pnl7);
//        this.setUndecorated(true);// Take out preset border
//        this.setVisible(false);
        this.setSize(Resources.MAIN_WINDOW_DIM);
//        this.centerFrameOnScreen();

        // ////<===========End Create Playing Board
        return;
    }

    /*
     *  Add Categories into grid
     */
    public JPanel createCategoryPanel() {

        JPanel categoryListPanel = new JPanel();
        categoryListPanel.setLayout(new GridLayout(4, 2));

        for (int x = 0; x < Resources.CATEGORY_AMNT; x++) {
            lblWordList[x] = new HButton(categories[x] + "");
            lblWordList[x].setFont(Resources.FONT_FILE_NAME, 12);
            lblWordList[x].setOpaque(false);
            lblWordList[x].setContentAreaFilled(false);
            lblWordList[x].setBorderPainted(false);
            lblWordList[x].addActionListener(this);
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
//            fr1.setVisible(false);
                fr1.remove(startScreenPanel);
                fr1.add(dPnl2);
//            fr2.setVisible(true);
                theSource = 2;
                players = 1;
                lblWordList[1].setForeground(Color.BLUE);

                dPnl2.add(align);

                dPnl2.add(playerOneLabel);
                playerOneLabel.setBounds(50, 50, 75, 25);
                playerOneLabel.setFont(Resources.FONT_FILE_NAME, 15);
//                playerOneLabel.setIcon(name);
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
                theSource = 2;
                selected = "Custom";

                fr1.remove(startScreenPanel);
                fr1.add(dPnl2);

                dPnl2.remove(align);

                dPnl2.remove(wordlist);
                dPnl2.add(playerTwoLabel);

                playerTwoLabel.setBounds(75, 125, 100, 25);
                playerTwoLabel.setFont(Resources.FONT_FILE_NAME, 15);
//                playerOneLabel.setIcon(opponent);
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
//                playerOneLabel.setIcon(name);
                playerOneLabel.setForeground(Color.WHITE);
                playerOneLabel.setText("Name");

                dPnl2.add(playerOneTextField);
                playerOneTextField.setBounds(150, 75, 125, 25);
                playerOneTextField.setFont(Resources.FONT_FILE_NAME, 13);
            }
        }

        if (e.getSource() == btnBack) {
            theSource = 1;
            Debug.dbgPrint("Back was pressed");

            fr1.remove(dPnl2);
            fr1.add(startScreenPanel);
            repaint();
        }
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
        if (e.getSource() == btnStart) {
            theSource = 3;
            Debug.dbgPrint("Start was pressed");
            playerOneName = playerOneTextField.getText();
            if (players == 2) {
                playerTwoName = playerTwoTextField.getText();
            }
            fr1.setVisible(false);
            try {
                if (players == 1) {
                    getPuz();
                }
                createPuz();
            } catch (IOException f) {
                Debug.dbgPrint("Problem Creating Puzzle");
            }
            this.setVisible(true);
            repaint();
        }
//        for (int x = 0; x < 3; x++) {
//            if (e.getSource() == close[x]) {
//                System.exit(0);
//            }
//        }
        if (e.getSource() == resetBtn) {
            playerTwoScore = 0;
            playerOneScore = 0;
            repaint();
        } else if (e.getSource() == newGameBtn) {
//            gallowmanImagePath = cl.getResource("hanger.png");
//            gallowmanImage = toolkit.createImage(gallowmanImagePath);
//            gallowmanImage = ImageIO.read(new File(RES_PATH + "hanger.png"));
            move = 0;
            count = 0;
            gameDone = false;

            resetCheckLists();
            try {
                getPuz();
                createPuz();
            } catch (IOException f) {
                Debug.dbgPrint("Problem Creating Puzzle");
            }
            repaint();
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
            this.setVisible(false);
            fr1.setVisible(true);
            theSource = 2;
            repaint();
        }
    }

    private void resetCheckLists() {
        for (int x = 0; x < 26; x++) {
            checked[x] = 0;
            wrLetter[x] = 0;
        }
        return;
    }

//    public void centerFrameOnScreen() {
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
//        return;
//    }
    public class DrawPanel extends JPanel {

        public DrawPanel() {
            this.setLayout(null);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0, 0, Resources.MAIN_WINDOW_DIM.width, Resources.MAIN_WINDOW_DIM.height, 0, 0, 300, 300,
                    this); //Draw background

            if ((theSource == 1) || (theSource == 2)) {
                g2.setColor(Color.WHITE);
                g2.setFont(startScreenTitleFont);
                if (theSource == 1) {
                    g2.drawString("Hang Man", 12, 100);
                }
                g2.setColor(Color.BLACK);
            } else if (theSource == 3) {
                if ((move < 5) && (move >= 0)) {
                    g2.drawImage(gallowmanImage, 300 - 25 * move, 125,
                            350 - 25 * move, 200, 0, 0, 131, 300,
                            this);
                }
                if ((move >= 5) && (move < 7)) {
                    g2.drawImage(gallowmanImage, 300 - 25 * move, 100,
                            350 - 25 * move, 175, 0, 0, 131, 300,
                            this);
                }
                if ((move == 7) || (move >= 8)) {
                    g2.drawImage(gallowmanImage, 125, 50, 175, 150, 0, 0,
                            131, 300, this);
                    if (move == 7) {
                        g2.setColor(Color.RED);
                        g2.fillRect(125, 150, 50, 25);
                    }
                }
//                g2.setColor(Color.YELLOW);
//                g2.drawLine(365, 25, 365, 215);
//                g2.drawLine(25, 210, 375, 210);
                g2.setColor(Color.RED);
                g2.fillRect(50, 175, 125, 25);
                g2.fillRect(75, 25, 25, 150);
                g2.fillRect(100, 25, 75, 25);
                g2.fillRect(175, 175, 25, 25);
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(100, 75, 125, 50);
                g2.setColor(Color.BLACK);
                g2.setFont(f8);
                
                for (int x = 0; x < 26; x++) {
                    if (wrLetter[x] == 0) {
                        g2.setColor(Color.BLACK);
                    } else if (wrLetter[x] == 1) {
                        g2.setColor(Color.GREEN);
                    } else if (wrLetter[x] == 2) {
                        g2.setColor(Color.RED);
                    }
                    g2.drawString("" + Resources.ALPHABET[x], 5 + 19 * x, 305);
                }
                g2.setFont(f7);
                g2.setColor(Color.BLACK);
                for (int x = 0; x < puzzleWordLength; x++) {
                    g2.drawString("" + hid[x], 25 + 35 * x, 250);
                }
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
    }

    public void getPuz() throws IOException {
//        String[] fields;
        BufferedReader in = null;
        String line = "A B 1";
        File f = new File(Resources.RES_PATH + "Word List/" + selected + ".txt");
        int num = 0;
        LineNumberReader reader = new LineNumberReader(
                new FileReader(f));
        while ((reader.readLine()) != null) {
        }
        linenumber = reader.getLineNumber();
        reader.close();
        try {
            allPuz = new String[linenumber];
            in = new BufferedReader(new FileReader(f));
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
            if (line != null) {
            }
        }
        try {
            in.close();
            System.out.println("Closing File");
        } catch (IOException e) {
            System.out.println("Problem Closing " + e);
        }
    }

    public void createPuz() throws IOException {
        randomnum = (int) (Math.random() * linenumber);

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

    public void keyTyped(KeyEvent f) {
        Boolean wrongLetterFlag = true;

        if (gameDone == false) {
            String key = "" + f.getKeyChar();
            Boolean rightletter = false;
            wrongLetterFlag = true;
            for (int x = 0; x < 26; x++) {
                if (("" + Resources.ALPHABET[x]).equalsIgnoreCase(key)) {
                    if (checked[x] == 1) {
                        JOptionPane.showMessageDialog(this,
                                "Already pressed " + Resources.ALPHABET[x] + ".");
                        for (int y = 0; y < puzzleWordLength; y++) {
                            if (Resources.ALPHABET[x] == puzle[y]) {
                                hid[y] = puzle[y];
                                rightletter = true;
                                wrLetter[x] = 1;
                                wrongLetterFlag = false;
                                checked[x] = 1;
                                if (count == puzzleWordLength) {
                                    JOptionPane.showMessageDialog(
                                            this, "You Win");
                                    gameDone = true;
                                    playerOneScore += 1;
                                }
                            }
                        }
                    } else if (checked[x] == 0) {
                        for (int y = 0; y < puzzleWordLength; y++) {
                            if (Resources.ALPHABET[x] == puzle[y]) {
                                hid[y] = puzle[y];
                                rightletter = true;
                                wrLetter[x] = 1;
                                count += 1;
                                wrongLetterFlag = false;
                                checked[x] = 1;
                                if (count == puzzleWordLength) {
                                    JOptionPane.showMessageDialog(
                                            this, "You Win");
                                    gameDone = true;
                                    playerOneScore += 1;
                                }
                            }
                        }
                    }
                    rong = x;
                }
            }
            if (rightletter == false) {
                for (int x = 0; x < 26; x++) {
                    if (("" + Resources.ALPHABET[x]).equalsIgnoreCase(key)) {
                        move++;
                        wrLetter[x] = 2;
                        checked[x] = 1;
                        rong = x;
                    }
                }
            }
            if (wrongLetterFlag == true) {
                wrLetter[rong] = 2;
            }
            wrongLetterFlag = true;
            if (move == 7) {
                gallowmanImage = updateGallowMan(State.HANGING);
            } else if (move >= 8) {
                JOptionPane.showMessageDialog(this, "You Lose");
                gallowmanImage = updateGallowMan(State.DEAD);
                playerTwoScore += 1;
                gameDone = true;
                pnlBoard.setEnabled(false);
                resetBtn.setEnabled(true);
                for (int x = 0; x < 26; x++) {
                    for (int y = 0; y < puzzleWordLength; y++) {
                        if (Resources.ALPHABET[x] == puzle[y]) {
                            hid[y] = puzle[y];
                        }
                    }
                }
            } else {
                gallowmanImage = updateGallowMan(State.WALKING);
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent f) {
    }

    public void keyReleased(KeyEvent f) {
    }

}
