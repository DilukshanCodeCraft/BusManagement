/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussystem;

import javax.swing.JOptionPane;

/**
 *
 * @author v801107
 */
public class dialogbox {
    
    int result;
    public static void main(String[] args) {
        dialogbox dialogbox1=new dialogbox();
        dialogbox1.infoMessageWithOptions("Your message", "Your title");
    }

    public void infoMessageWithOptions(String message, String title) {
        
        Object[] options = {"OK", "Cancel"};

        
        this.result = JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );int result = JOptionPane.showOptionDialog(
        null,
        message,
        title,
        JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]
);


if (result == JOptionPane.YES_OPTION) {
    
    JOptionPane.showMessageDialog(null, "You clicked OK.\n\nMessage: " + message, "Result - " + title, JOptionPane.INFORMATION_MESSAGE);
} else {
    
    JOptionPane.showMessageDialog(null, "You clicked Cancel or closed the dialog.\n\nMessage: " + message, "Result - " + title, JOptionPane.INFORMATION_MESSAGE);
}

    }

    void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


