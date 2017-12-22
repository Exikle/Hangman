/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.objects;

import com.exikle.hangman.Resources;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author dixondcunha
 */
public class HFrame extends JFrame {

    public HFrame(String label) {
        super(label);
        this.setFrameStyle();
    }

    public HFrame() {
        this.setFrameStyle();
    }

    private void setFrameStyle() {
        this.setSize(Resources.MAIN_WINDOW_DIM);
//        this.setUndecorated(true);// Take out preset border
        this.setVisible(true);
        this.setContentPane(new BackgroundImage());
        this.centerFrameOnScreen();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class BackgroundImage extends JComponent {

        public final Image BG_IMAGE = Resources.CHALK_BG;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(BG_IMAGE, 0, 0, Resources.MAIN_WINDOW_DIM.width,
                    Resources.MAIN_WINDOW_DIM.height, 0, 0, 300, 300,
                    this);
        }
    }

    private void centerFrameOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

}
