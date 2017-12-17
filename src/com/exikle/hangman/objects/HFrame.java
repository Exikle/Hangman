/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.objects;

import java.awt.Dimension;
import java.awt.Toolkit;
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
//            this.setUndecorated(true);// Take out preset border
            this.setVisible(false);
        }

        public void centerFrameOnScreen() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            return;
        }

    }
