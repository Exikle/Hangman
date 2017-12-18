/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exikle.hangman;


/**
 *
 * @author dixondcunha
 */
public class Debug {

    public static void dbgPrint(String str) {
        if (Resources.DEBUG_APP) {
            System.out.println("DBUG::" + str);
        }
        return;
    }

    public void dbgPrompt(String str) {
//            JOptionPane.showMessageDialog(this, str);
        return;
    }
}
