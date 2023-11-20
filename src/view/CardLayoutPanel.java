package view;

import view.util.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class CardLayoutPanel extends MotionPanel {
    private CardLayout cl;
    private MenuePanel menuePanel;
    private GamePanel gamePanel;
    private OptionPanel optionPanel;

    public CardLayoutPanel(JFrame parent) {
        super(parent);
        initVars();
        initComps();
    }

    /**
     * Initializes Variables.
     */
    private void initVars() {
        cl = new CardLayout();
        optionPanel = new OptionPanel(this);
        gamePanel = new GamePanel(optionPanel);
        menuePanel = new MenuePanel(this, gamePanel);
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        setLayout(cl);
        add("menue", menuePanel);
        add("game", gamePanel);
        add("option", optionPanel);
        changePanel("menue");
    }

    public void changePanel(String panel) {
        cl.show(this, panel);
    }
}
