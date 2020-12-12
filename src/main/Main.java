package main;

import GUI.BootstrapPanel;

import javax.swing.ImageIcon;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ImageIcon icon;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //icon = new ImageIcon("image/book.jpg");
                BootstrapPanel bootstrapPanel = new BootstrapPanel();
                bootstrapPanel.setVisible(true);
            }
        });
    }

}
