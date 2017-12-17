/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.objects;

import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author dixondcunha
 */
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
