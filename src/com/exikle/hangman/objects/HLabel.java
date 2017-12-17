/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman.objects;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author dixondcunha
 */
public class HLabel extends JLabel {

        public HLabel(String label) {
            super(label);
            setStyle();
        }

        public HLabel() {
            super();
            setStyle();
        }

        private void setStyle() {
            return;
        }

        public void setFont(String fontName, int size) {
            this.setFont(new Font(fontName, Font.PLAIN, size));
        }
    }