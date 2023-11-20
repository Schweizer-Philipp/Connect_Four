package controller;

import view.CardLayoutPanel;
import view.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {

    private CardLayoutPanel cardLayoutPanel;
    private GamePanel gamePanel;

    public ButtonController(CardLayoutPanel cardLayoutPanel, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.cardLayoutPanel = cardLayoutPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            new Thread(() ->
            {
                try {
                    Thread.sleep(200);
                    gamePanel.startGame();
                    cardLayoutPanel.changePanel("game");
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }

            }).start();

        } else if (e.getActionCommand().equals("option")) {
            cardLayoutPanel.changePanel("option");
        }

    }

}
