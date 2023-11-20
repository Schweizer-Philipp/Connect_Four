package controller;

import view.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyKlicks extends AbstractAction {
    private GameBoard gameBoard;

    private String actionCommand;

    public KeyKlicks(String actionCommand, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.actionCommand = actionCommand;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (actionCommand.equals("left")) {
            gameBoard.keyLeft();
        } else if (actionCommand.equals("right")) {
            gameBoard.keyRight();
        } else if (actionCommand.equals("down")) {
            gameBoard.keyDown();
        }
    }
}
