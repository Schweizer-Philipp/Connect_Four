package view;

import javax.swing.*;

public class MainFrame {
    private JFrame mainFrame;
    private CardLayoutPanel cLayoutPanel;

    public MainFrame() {
        initVars();
        initComps();
    }

    /**
     * Initializes Variables.
     */
    private void initVars() {
        mainFrame = new JFrame("4Gewinnt");
        cLayoutPanel = new CardLayoutPanel(mainFrame);
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        mainFrame.setSize(1000, 1000);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setUndecorated(true);
        mainFrame.requestFocus();
        mainFrame.setFocusable(true);
        mainFrame.add(cLayoutPanel);
        mainFrame.setVisible(true);
    }
}
