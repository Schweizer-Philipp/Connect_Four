package model;

import controller.GameLogic;

import java.awt.*;

public abstract class Player {
    private Color playerColor;
    private int wins;
    private boolean turn;
    private GameLogic gameLogic;

    public Player(Color playerColor, GameLogic gameLogic) {
        this.setGameLogic(gameLogic);
        this.playerColor = playerColor;
        wins = 0;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public int getWins() {
        return wins;
    }

    public void increaseWins() {
        this.wins++;
    }

    public abstract Point move(GameToken[][] board, int depth, Color spielerFarbe);

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
}
