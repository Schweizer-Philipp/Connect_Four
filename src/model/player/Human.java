package model.player;

import controller.GameLogic;
import model.GameToken;
import model.Player;

import java.awt.*;

public class Human extends Player {

    public Human(Color playerColor, GameLogic gameLogic) {
        super(playerColor, gameLogic);
    }


    @Override
    public Point move(GameToken[][] board, int depth, Color spielerFarbe) {
        return new Point(0, 0);
    }
}
