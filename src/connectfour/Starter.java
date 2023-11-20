package connectfour;

import view.MainFrame;

import javax.swing.*;

public class Starter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
