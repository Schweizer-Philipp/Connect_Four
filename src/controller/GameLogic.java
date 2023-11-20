package controller;

import model.GameToken;
import model.Player;
import model.ai.Ai;
import model.player.Human;
import view.GameBoard;

import javax.swing.*;
import java.awt.*;

public class GameLogic {
    private GameToken[][] gameTokenArray;

    private String[] enemyChoices = {"Mensch", "Computer"};

    private GameBoard gameBoard;

    private Player spieler1;
    private Player spieler2;

    private int amount;


    public GameLogic(GameBoard gameBoard, int row, int col) {
        gameTokenArray = new GameToken[row][col];
        this.gameBoard = gameBoard;
        initVars();
    }

    /**
     * Initializes Variables.
     */
    private void initVars() {

        JOptionPane jp = new JOptionPane("Gegen wenn wollen Sie spielen?", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, enemyChoices, null);
        JDialog jd = jp.createDialog(null, "Gegner auswahl");
        jd.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jd.setVisible(true);

        String res = (String) jp.getValue();
        jd.dispose();
        if (res.equals("Computer")) {
            JOptionPane jp1 = new JOptionPane("Wer soll anfangen?", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, enemyChoices, null);
            JDialog jd1 = jp1.createDialog(null, "First or Second");
            jd1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            jd1.setVisible(true);

            String res1 = (String) jp1.getValue();
            jd1.dispose();

            if (res1.equals("Mensch")) {
                spieler1 = new Human(Color.BLUE, this);
                spieler1.setTurn(true);
                spieler2 = new Ai(Color.RED, this);
                spieler2.setTurn(false);
            } else {
                spieler1 = new Ai(Color.BLUE, this);
                spieler1.setTurn(true);
                spieler2 = new Human(Color.RED, this);
                spieler2.setTurn(false);
            }

        }
        if (res.equals("Mensch")) {
            spieler1 = new Human(Color.BLUE, this);
            spieler1.setTurn(true);
            spieler2 = new Human(Color.RED, this);
            spieler2.setTurn(false);
        }
    }


    /**
     * @param gameBoard
     * @return ob ein spiel im unentschieden endet
     */
    public boolean hasDraw(Color[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].equals(Color.WHITE)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param gameBoard   board in dem gesucht wird
     * @param colorPlayer farbe nach der gesucht wird
     * @return ob ein spieler gewonnen hat der die colorplayer hat
     */
    public boolean hasWon(Color[][] gameBoard, Color colorPlayer) {
        int[][] direction = {{1, 0}, {1, -1}, {1, 1}, {0, 1}, {-1, 0}, {-1, 1}, {-1, -1}, {0, -1}};
        int row = gameBoard.length;
        int col = getColAmount(gameBoard);
        for (int[] d : direction) {
            int dRow = d[0];
            int dCol = d[1];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int lastrow = i + amount * dRow;
                    int lastcol = j + amount * dCol;

                    if (0 <= lastrow && lastrow <= row && 0 <= lastcol && lastcol <= col) {
                        boolean hasWon = true;
                        for (int k = 0; k < amount; k++) {
                            if (!gameBoard[i + k * dRow][j + k * dCol].equals(colorPlayer)) {
                                hasWon = false;
                            }
                        }
                        if (hasWon) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int getColAmount(Color[][] gameBoard) {
        int col = 0;
        for (int i = 0; i < gameBoard[0].length; i++) {
            col++;
        }
        return col;

    }

    public Player getspieler1() {

        return spieler1;
    }

    public Player getspieler2() {

        return spieler2;
    }

    /**
     * @return gibt die farbe zur�ck
     */
    public Color getcurrentColor() {
        if (spieler1.isTurn())
            return spieler1.getPlayerColor();
        else
            return spieler2.getPlayerColor();
    }

    /**
     * spieler wird gewechselt und falls ein computer dran ist wird ein computerzug gemacht
     */
    public void changePlayer() {

        spieler1.setTurn(!spieler1.isTurn());
        spieler2.setTurn(!spieler2.isTurn());

        if (spieler1 instanceof Ai && spieler1.isTurn()) {
            Point point = spieler1.move(gameBoard.getGameTokenArray(), 7, getcurrentColor());
            gameBoard.getGameTokenArray()[point.x][point.y].setUsed(true);
            gameBoard.getGameTokenArray()[point.x][point.y].setColor(getcurrentColor());
            gameBoard.checkGameState();
        } else if (spieler2 instanceof Ai && spieler2.isTurn()) {
            Point point = spieler2.move(gameBoard.getGameTokenArray(), 7, getcurrentColor());
            gameBoard.getGameTokenArray()[point.x][point.y].setUsed(true);
            gameBoard.getGameTokenArray()[point.x][point.y].setColor(getcurrentColor());
            gameBoard.checkGameState();
        }

    }

    public void setamount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public GameToken[][] makeMove(GameToken[][] board, int col, Color color) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (!board[i][col].isUsed()) {
                board[i][col].setUsed(true);
                board[i][col].setColor(color);
                break;
            }
        }
        return board;
    }

    /**
     * @param col spalte in der geschaut wird
     * @return wird zeile zur�ck wo die m�nze w�re
     */
    public int getrow(int col) {
        int count = gameTokenArray.length;
        for (int i = gameTokenArray.length - 1; i >= 0; i--) {
            count--;
            if (!gameTokenArray[i][col].isUsed()) {
                break;
            }
        }
        return count;
    }
}
