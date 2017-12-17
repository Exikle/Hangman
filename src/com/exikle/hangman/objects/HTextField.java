/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.objects;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author dixondcunha
 */
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
