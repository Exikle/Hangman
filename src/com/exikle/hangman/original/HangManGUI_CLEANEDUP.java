package com.exikle.hangman.original;

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
 * -> Created+Added hangman pictures/gallows
 * v0.3
 * -> Created Scoring
 * v0.4
 * -> Added Player 2 Support
 * -> Fixed correct/incorrect letter
 * -> Fixed wrong color for letters on drawpanel
 * v0.5
 * -> Cleaning up code prerework
 */
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
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
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HangManGUI_CLEANEDUP extends JFrame implements ActionListener,
        KeyListener {

    final String RES_PATH = "res/";
    final String DEFAULT_DIFFICULTY = "Easy";
    final String FONT_NAME = "fonts/VTK.ttf";
    final String FONT_FILE_PATH = RES_PATH + FONT_NAME;
    final String FONT_FILE_NAME = "vtks animal 2";
    final String DEFAULT_PLAYER_ONE_NAME = "Player 1";
    final String DEFAULT_PLAYER_TWO_NAME = "Player 2";
    final String[] DEFAULT_CATEGORIES = {"Easy", "Food", "Standard", "Geography",
        "Hard", "Holidays", "Animals", "Sports"
    };

    final int CATEGORY_AMNT = 8;

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

    String playerOneName = DEFAULT_PLAYER_ONE_NAME;
    String playerTwoName = DEFAULT_PLAYER_TWO_NAME;
    String currentPuzzle = "";
    String selected = DEFAULT_DIFFICULTY;

    String[] categories = DEFAULT_CATEGORIES;
    String[] allPuz;
    String[] puzzle;

    HTextField customPuzzleTextField = new HTextField("Custom Puzzle");
    HTextField playerOneTextField = new HTextField("Player");
    HTextField playerTwoTextField = new HTextField("Opponent");

    HLabel playerOneLabel = new HLabel();
    HLabel playerTwoLabel = new HLabel();
    HLabel wordlist = new HLabel();

    HButton[] btnLetters = new HButton[26];
    HButton[] close = new HButton[3];
    HButton[] lblWordList = new HButton[8];

    HButton player1 = new HButton();
    HButton player2 = new HButton();

    HButton btnBack = new HButton("Back");
    HButton btnStart = new HButton("Start");
    HButton resetBtn = new HButton("Reset Scores");
    HButton newGameBtn = new HButton("New Game");
    HButton btnMain = new HButton("Menu");

    int length;
    int count = 0;
    int chances = 7;
    int linenum;
    int randomnum;
    int pScore = 0;
    int oScore = 0;
    int theSource = 1;
    int move = 0;
    int rong;
    int players = 1;

    int[] wrLetter = new int[26];
    int[] checked = new int[26];

    Icon[] cate = new ImageIcon[7];

    //TODO replace with JLABELS using custom fonts
    Icon py1 = new ImageIcon(RES_PATH + "Player 1.png");
    Icon py2 = new ImageIcon(RES_PATH + "Player 2.png");
    Icon wList = new ImageIcon(RES_PATH + "WordList.png");
    Icon name = new ImageIcon(RES_PATH + "Name.png");
    Icon opponent = new ImageIcon(RES_PATH + "Opponent.png");
    Icon closeIMG = new ImageIcon(RES_PATH + "closeBtn.png");

    char[] puzle;
    char[] hid;
    char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    Boolean wrong = true;
    Boolean gameDone = false;

    HFrame fr1 = new HFrame(""); //todo remove these and use only one frame
    HFrame fr2 = new HFrame(""); //perhaps create a new panel, add stuff to that adn then add panel to fram temporarily

    Font startScreenTitleFont;
    Font f5;
    Font f7;
    Font f8;

    ClassLoader cl = HangManGUI_CLEANEDUP.class.getClassLoader();

    URL imageURL = cl.getResource(RES_PATH + "chalkBG.png");
    URL gallowmanImagePath = cl.getResource(RES_PATH + "hanger.png");
    URL imageURL3 = cl.getResource(RES_PATH + "alphaDock.png");

    Image image;
    Image gallowmanImage;
    Image image3;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    // <----------------------- End Import Images
    public void initializeFonts() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FONT_FILE_PATH)));
        } catch (IOException | FontFormatException e) {
            {
                e.printStackTrace();
            }

        }
        startScreenTitleFont = new Font(FONT_FILE_NAME, Font.PLAIN, 50);
        f5 = new Font(FONT_FILE_NAME, Font.PLAIN, 16); //
        f7 = new Font(FONT_FILE_NAME, Font.PLAIN, 35); //
        f8 = new Font(FONT_FILE_NAME, Font.PLAIN, 22); //
    }

    public void setHFont(int newSize) {
        //TODO
    }

    public static void main(String[] args) {
        new HangManGUI_CLEANEDUP();
    }

    public HangManGUI_CLEANEDUP() {
        initializeFonts();
        this.addKeyListener(this);
        this.setFocusable(true);
        // Initialize the Checklists------------->
        resetCheckLists();
        // <------- End Initializing Checklists

        // Close button---------------------------> //TODO make this into only one
        for (int x = 0; x < 3; x++) {
            close[x] = new HButton();
            close[x].addActionListener(this);
            close[x].setBounds(275, 0, 25, 25);
            close[x].setIcon(closeIMG);
        }
        startScreenPanel.add(close[0]);// Add Close button to 1st Screen
        dPnl2.add(close[1]);// Add Close button to 2nd Screen
        this.add(close[2]);// Add Close button to Board

        close[2].setBounds(475, 0, 25, 25);
        // <------End Close Button

        // Put image into Image Variable------------->
        if (imageURL != null) {
            image = toolkit.createImage(imageURL);
            gallowmanImage = toolkit.createImage(gallowmanImagePath);
            image3 = toolkit.createImage(imageURL3);
        }
        // <-------------Put image into Image Variable

        // ////Chose Player Menu(1)==============>
        // /Player 1 button initialize--------->
        player1.setBounds(25, 125, 250, 50);
        player1.addActionListener(this);
        player1.setIcon(py1);
//        player1.setText("Player One");
//        player1.setFont(FONT_FILE_NAME, 11);
        startScreenPanel.add(player1);
        // /Player 2 button initialize--------->
        player2.setBounds(25, 200, 250, 50);
        player2.addActionListener(this);
        player2.setIcon(py2);
        startScreenPanel.add(player2);
        // /End Player Button Initialzing------->
        fr1.add(startScreenPanel);
        fr1.setVisible(true);
        fr1.setSize(300, 300);
        fr1.centerFrameOnScreen();
        // ////<========End Chose Player Menu(1)

        // ////Chose Categories Menu(2)========>
        // /Initialize WordList Icon\Label--->
        dPnl2.add(wordlist);
        wordlist.setBounds(100, 75, 150, 75);
        wordlist.setIcon(wList);
        // /Initialize Go To 1st Menu Button---------->
        dPnl2.add(btnBack);
        btnBack.setBounds(0, 250, 75, 25);
        btnBack.setFont(FONT_FILE_NAME, 11);
        btnBack.addActionListener(this);
        // /Initialize Start Game/Go to Board---------->
        dPnl2.add(btnStart);
        btnStart.setBounds(225, 250, 75, 25);
        btnStart.setFont(FONT_FILE_NAME, 11);
        btnStart.addActionListener(this);

        // /Add Categories into grid------------->
        align = createCategoryPanel();
        // /<-------------End Category Initializing and Layout Setiing

        fr2.add(dPnl2);
        fr2.setSize(300, 300);
        fr2.centerFrameOnScreen();
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
        newGameBtn.setFont(FONT_FILE_NAME, 11);
        newGameBtn.addActionListener(this);
        this.add(newGameBtn);
        newGameBtn.setBounds(225, 0, 100, 25);

        // Initialize Reset Scores Button---------->
        resetBtn.setFont(FONT_FILE_NAME, 11);
        resetBtn.addActionListener(this);
        this.add(resetBtn);
        resetBtn.setBounds(100, 0, 125, 25);

        // Initialize Go To Main Menu Button---------->
        btnMain.setFont(FONT_FILE_NAME, 11);
        btnMain.addActionListener(this);
        this.add(btnMain);
        btnMain.setBounds(0, 0, 100, 25);

        this.add(pnl7);
        this.setUndecorated(true);// Take out preset border
        this.setVisible(false);
        this.setLocation(200, 200);
        this.setSize(500, 325);
        // ////<===========End Create Playing Board
        return;
    }

    /*
     *  Add Categories into grid
     */
    public JPanel createCategoryPanel() {

        JPanel categoryListPanel = new JPanel();
        categoryListPanel.setLayout(new GridLayout(4, 2));

        for (int x = 0; x < CATEGORY_AMNT; x++) {
            lblWordList[x] = new HButton(categories[x] + "");
            lblWordList[x].setFont(FONT_FILE_NAME, 12);
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

        }

        if (e.getSource() == player1) {
            System.out.println("Player 1 was pressed");
            fr1.setVisible(false);
            fr2.setVisible(true);
            theSource = 2;
            players = 1;
            lblWordList[1].setForeground(Color.BLUE);

            dPnl2.add(align);
            // align.setBounds(25, 125, 250, 100);
            // align.setOpaque(false);

            dPnl2.add(playerOneLabel);
            playerOneLabel.setBounds(50, 50, 75, 25);
            playerOneLabel.setFont(FONT_FILE_NAME, 11);
            playerOneLabel.setIcon(name);

            dPnl2.add(playerOneTextField);
            playerOneTextField.setBounds(125, 50, 125, 25);
            playerOneTextField.setFont(FONT_FILE_NAME, 13);

            dPnl2.add(wordlist);
            wordlist.setBounds(100, 75, 150, 75);

            dPnl2.remove(playerTwoLabel);
            dPnl2.remove(playerTwoTextField);
            dPnl2.remove(customPuzzleTextField);
        } else if (e.getSource() == player2) {
            System.out.println("Player 2 was pressed");
            lblWordList[1].setForeground(Color.BLUE);
            players = 2;
            theSource = 2;
            selected = "Custom";
            fr1.setVisible(false);
            fr2.setVisible(true);
            dPnl2.remove(align);

            dPnl2.remove(wordlist);
            dPnl2.add(playerTwoLabel);

            playerTwoLabel.setBounds(75, 125, 100, 25);
            playerTwoLabel.setFont(FONT_FILE_NAME, 11);
            playerTwoLabel.setIcon(opponent);

            dPnl2.add(playerTwoTextField);
            playerTwoTextField.setBounds(175, 125, 100, 25);
            playerTwoTextField.setFont(FONT_FILE_NAME, 13);

            dPnl2.add(customPuzzleTextField);
            customPuzzleTextField.setBounds(100, 175, 100, 25);

            dPnl2.add(playerOneLabel);
            playerOneLabel.setBounds(75, 75, 75, 25);
            playerOneLabel.setFont(FONT_FILE_NAME, 11);
            playerOneLabel.setIcon(name);

            dPnl2.add(playerOneTextField);
            playerOneTextField.setBounds(150, 75, 125, 25);
            playerOneTextField.setFont(FONT_FILE_NAME, 13);

        }
        if (e.getSource() == btnBack) {
            theSource = 1;
            System.out.println("Back was pressed");
            fr2.setVisible(false);
            fr1.setVisible(true);
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
            System.out.println("Start was pressed");
            playerOneName = playerOneTextField.getText();
            if (players == 2) {
                playerTwoName = playerTwoTextField.getText();
            }
            fr2.setVisible(false);
            try {
                if (players == 1) {
                    getPuz();
                }
                createPuz();
            } catch (IOException f) {
                System.out.println("Problem Creating Puzzle");
            }
            this.setVisible(true);
        }
        for (int x = 0; x < 3; x++) {
            if (e.getSource() == close[x]) {
                System.exit(0);
            }
        }
        if (e.getSource() == resetBtn) {
            oScore = 0;
            pScore = 0;
            repaint();
        } else if (e.getSource() == newGameBtn) {
            gallowmanImagePath = cl.getResource("hanger.png");
            gallowmanImage = toolkit.createImage(gallowmanImagePath);
            move = 0;
            count = 0;
            gameDone = false;

            resetCheckLists();
            try {
                getPuz();
                createPuz();
            } catch (IOException f) {
                System.out.println("Problem Creating Puzzle");
            }
            repaint();
        }
        if (e.getSource() == btnMain) {
            oScore = 0;
            pScore = 0;
            move = 0;
            count = 0;
            gameDone = false;
            playerTwoName = "Opponent";
            playerOneName = "Player";

            resetCheckLists();
            this.setVisible(false);
            fr2.setVisible(true);
            theSource = 2;
        }
    }

//    private void changeToStartMenu(){
//        
//    }
    private void resetCheckLists() {
        for (int x = 0; x < 26; x++) {
            checked[x] = 0;
            wrLetter[x] = 0;
        }
        return;
    }

    public class HTextField extends JTextField {

        public HTextField(String label) {
            super(label);
            this.setStyle();
        }

        public HTextField() {
            this.setStyle();
        }

        private void setStyle() {
            this.setOpaque(false);
            this.setBorder(null);
        }

        public void setFont(String fontName, int size) {
            this.setFont(new Font(fontName, Font.PLAIN, size));
        }

    }

    public class HLabel extends JLabel {

        public HLabel(String label) {
            super(label);
            setStyle();
        }

        public HLabel() {
            super();
            setStyle();
        }
        
        private void setStyle(){
            return;
        }

        public void setFont(String fontName, int size) {
            this.setFont(new Font(fontName, Font.PLAIN, size));
        }
    }

    public class HButton extends JButton {

        public HButton() {
            this.setButtonStyle();
        }

        public HButton(String label) {
            this.setText(label);
            this.setButtonStyle();
        }

        private void setButtonStyle() {
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setFocusable(false);
        }

        public void setFont(String fontName, int size) {
            this.setFont(new Font(fontName, Font.PLAIN, size));
        }
    }

    public class HPanel extends JPanel {

        public HPanel() {

        }
    }

    public class HFrame extends JFrame {

        public HFrame(String label) {
            super(label);
            this.setFrameStyle();
        }

        public HFrame() {
            this.setFrameStyle();
        }

        private void setFrameStyle() {
            this.setUndecorated(true);// Take out preset border
            this.setVisible(false);
        }

        public void centerFrameOnScreen() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            return;
        }
    }

    public class DrawPanel extends JPanel {

        public DrawPanel() {
            this.setLayout(null);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            if ((theSource == 1) || (theSource == 2)) {
                g2.drawImage(image, 0, 0, 300, 300, 0, 0, 300, 300,
                        this);
                g2.setColor(Color.BLACK);
                g2.setFont(startScreenTitleFont);
                if (theSource == 1) {
                    g2.drawString("Hang Man", 12, 100);
                }
                g2.setColor(Color.BLACK);
            } else if (theSource == 3) {
                g2.drawImage(image, 0, 0, 500, 275, 0, 0, 300, 300,
                        this);
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
                g2.setColor(Color.YELLOW);
                g2.drawLine(365, 25, 365, 215);
                g2.drawLine(25, 210, 375, 210);
                g2.setColor(Color.RED);
                g2.fillRect(50, 175, 125, 25);
                g2.fillRect(75, 25, 25, 150);
                g2.fillRect(100, 25, 75, 25);
                g2.fillRect(175, 175, 25, 25);
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(100, 75, 125, 50);
                g2.setColor(Color.BLACK);
                g2.setFont(f8);
                g2.drawImage(image3, 0, 275, 500, 325, 0, 0, 320, 48,
                        this);
                for (int x = 0; x < 26; x++) {
                    if (wrLetter[x] == 0) {
                        g2.setColor(Color.BLACK);
                    } else if (wrLetter[x] == 1) {
                        g2.setColor(Color.GREEN);
                    } else if (wrLetter[x] == 2) {
                        g2.setColor(Color.RED);
                    }
                    g2.drawString("" + letter[x], 5 + 19 * x, 305);
                }
                g2.setFont(f7);
                g2.setColor(Color.BLACK);
                for (int x = 0; x < length; x++) {
                    g2.drawString("" + hid[x], 25 + 35 * x, 250);
                }
                g2.setFont(f5);
                g2.setColor(Color.WHITE);
                g2.drawString("Category:", 375, 50);
                g2.drawString(playerOneName, 375, 100);
                g2.drawString(playerTwoName, 375, 150);
                g2.setColor(Color.BLACK);
                g2.drawString(selected, 375, 75);
                g2.drawString("" + pScore, 375, 125);
                g2.drawString("" + oScore, 375, 175);
            }
        }
    }

    public void getPuz() throws IOException {
        String[] fields;
        BufferedReader in = null;
        String line = "A B 1";
        File f = new File(RES_PATH + "Word List/" + selected + ".txt");
        int num = 0;
        LineNumberReader reader = new LineNumberReader(
                new FileReader(f));
        while ((reader.readLine()) != null) {
        }
        linenum = reader.getLineNumber();
        reader.close();
        try {
            allPuz = new String[linenum];
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
        randomnum = (int) (Math.random() * linenum);
        if (players == 1) {
            currentPuzzle = "" + allPuz[randomnum];
        } else if (players == 2) {
            currentPuzzle = customPuzzleTextField.getText();
        }
        System.out.println(currentPuzzle);
        length = currentPuzzle.length();
        puzle = new char[length];
        hid = new char[length];
        for (int x = 0; x < length; x++) {
            puzle[x] = (currentPuzzle.charAt(x));
            puzle[x] = Character.toUpperCase(puzle[x]);
            if (puzle[x] == ' ') {
                hid[x] = (' ');
                count += 1;
            } else {
                hid[x] = ('_');
            }
        }
    }

    public void keyTyped(KeyEvent f) {
        if (gameDone == false) {
            String key = "" + f.getKeyChar();
            Boolean rightletter = false;
            wrong = true;
            for (int x = 0; x < 26; x++) {
                if (("" + letter[x]).equalsIgnoreCase(key)) {
                    if (checked[x] == 1) {
                        JOptionPane.showMessageDialog(this,
                                "Already pressed " + letter[x] + ".");
                        for (int y = 0; y < length; y++) {
                            if (letter[x] == puzle[y]) {
                                hid[y] = puzle[y];
                                rightletter = true;
                                wrLetter[x] = 1;
                                wrong = false;
                                checked[x] = 1;
                                if (count == length) {
                                    JOptionPane.showMessageDialog(
                                            this, "You Win");
                                    gameDone = true;
                                    pScore += 1;
                                }
                            }
                        }
                    } else if (checked[x] == 0) {
                        for (int y = 0; y < length; y++) {
                            if (letter[x] == puzle[y]) {
                                hid[y] = puzle[y];
                                rightletter = true;
                                wrLetter[x] = 1;
                                count += 1;
                                wrong = false;
                                checked[x] = 1;
                                if (count == length) {
                                    JOptionPane.showMessageDialog(
                                            this, "You Win");
                                    gameDone = true;
                                    pScore += 1;
                                }
                            }
                        }
                    }
                    rong = x;
                }
            }
            if (rightletter == false) {
                for (int x = 0; x < 26; x++) {
                    if (("" + letter[x]).equalsIgnoreCase(key)) {
                        move++;
                        wrLetter[x] = 2;
                        checked[x] = 1;
                        rong = x;
                    }
                }
            }
            if (wrong == true) {
                wrLetter[rong] = 2;
            }
            wrong = true;
            if (move == 7) {
                gallowmanImagePath = cl.getResource("hanger2.png");
                gallowmanImage = toolkit.createImage(gallowmanImagePath);
            } else if (move >= 8) {
                gallowmanImagePath = cl.getResource("hanger3.png");
                JOptionPane.showMessageDialog(this, "You Lose");
                oScore += 1;
                gameDone = true;
                pnlBoard.setEnabled(false);
                resetBtn.setEnabled(true);
                gallowmanImage = toolkit.createImage(gallowmanImagePath);
                for (int x = 0; x < 26; x++) {
                    for (int y = 0; y < length; y++) {
                        if (letter[x] == puzle[y]) {
                            hid[y] = puzle[y];
                        }
                    }
                }
            } else {
                gallowmanImagePath = cl.getResource("hanger.png");
                gallowmanImage = toolkit.createImage(gallowmanImagePath);
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent f) {
    }

    public void keyReleased(KeyEvent f) {
    }
}
