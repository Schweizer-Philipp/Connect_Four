package model.ai;

import controller.GameLogic;
import model.GameToken;
import model.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ai extends Player {
    private GameToken[][] board;
    private Color spielerFarbe;
    private int gespeicherterzug;

    public Ai(Color playerColor, GameLogic gameLogic) {
        super(playerColor, gameLogic);
    }

    public static void main(String[] args) {
        GameToken[][] testGameField = new GameToken[6][7];


        for (int i = 0; i < testGameField.length; i++) {
            for (int j = 0; j < testGameField[i].length; j++) {
                testGameField[i][j] = new GameToken(0,0,0,0);
            }
        }

        testGameField[5][0].setColor(Color.BLUE);
        testGameField[5][1].setColor(Color.BLUE);
        testGameField[5][2].setColor(Color.BLUE);
        testGameField[5][3].setColor(Color.RED);
        testGameField[5][4].setColor(Color.RED);
        testGameField[5][5].setColor(Color.RED);
        testGameField[5][6].setColor(Color.BLUE);


        testGameField[4][1].setColor(Color.BLUE);
        testGameField[4][2].setColor(Color.BLUE);
        testGameField[4][3].setColor(Color.BLUE);
        testGameField[4][4].setColor(Color.RED);

        testGameField[3][3].setColor(Color.RED);
        testGameField[3][4].setColor(Color.BLUE);


        testGameField[2][3].setColor(Color.RED);
        testGameField[2][4].setColor(Color.RED);

        GameLogic g = new GameLogic(null, 0,0);
        g.setamount(4);
        Ai ai = new Ai(Color.red, g);

        System.out.println(ai.move(testGameField, 0, Color.RED));

    }

    /**
     * die methode wird ausgef�hrt sobald der computer am zug ist
     */
    @Override
    public Point move(GameToken[][] board, int depth, Color spielerFarbe) {
        this.spielerFarbe = spielerFarbe;
        int bestValue = (int) Double.NEGATIVE_INFINITY;
        Point bestMove = null;
        Color[][] copyBoard;
        copyBoard = copy(board);

        for(Point move : getAllPossibleMoves(copyBoard)) {
            copyBoard[move.x][move.y] = spielerFarbe;
            int value = minMax(copyBoard, (spielerFarbe.equals(Color.RED)) ? Color.blue : Color.RED, (int) Double.NEGATIVE_INFINITY, (int) Double.POSITIVE_INFINITY, depth);
            if(value >= bestValue){
                bestValue = value;
                bestMove = (Point) move.clone();
            }
            copyBoard[move.x][move.y] = Color.WHITE;
        }

        return bestMove;
    }

    private int minMax(Color[][] board, Color spielerFarbe, int alpha, int beta, int depth) {
        if(getGameLogic().hasDraw(board)) return 0;
        if(getGameLogic().hasWon(board, this.spielerFarbe)) return (int) Double.POSITIVE_INFINITY;
        Color colorPlayer = (spielerFarbe.equals(Color.RED)) ? Color.blue : Color.RED;
        if(getGameLogic().hasWon(board, colorPlayer)) return ((int) Double.NEGATIVE_INFINITY) + 1;

        if(depth == 0) {
            return evaluate(board);
        }
        List<Point> possibleMoves = getAllPossibleMoves(board);

        if(possibleMoves.isEmpty()) {
            return evaluate(board);
        }

        if(this.spielerFarbe.equals(spielerFarbe)) {
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            for(Point move : possibleMoves) {
                board[move.x][move.y] = spielerFarbe;
                int value = minMax(board, colorPlayer, alpha, beta, depth-1);
                board[move.x][move.y] = Color.WHITE;
                maxEval = Math.max(maxEval, value);
                alpha = Math.max(alpha, value);
                if(beta <= alpha) {
                    break;
                }
            }

            return maxEval;
        }

        else {
            int minEval = (int) Double.POSITIVE_INFINITY;
            for(Point move : possibleMoves) {
                board[move.x][move.y] = spielerFarbe;
                int value = minMax(board, colorPlayer, alpha, beta, depth-1);
                board[move.x][move.y] = Color.WHITE;
                minEval = Math.min(minEval, value);
                beta = Math.min(beta, value);
                if(beta <= alpha) {
                    break;
                }
            }

            return minEval;
        }
    }

    public int evaluate(Color[][] board) {
        if(getGameLogic().hasWon(board, this.spielerFarbe)) {
            return (int) Double.POSITIVE_INFINITY;
        }
        else if(getGameLogic().hasWon(board, (this.spielerFarbe.equals(Color.RED)) ? Color.blue : Color.RED)) {
            return ((int) Double.NEGATIVE_INFINITY) + 1;
        }

        int boardEvaluation = 0;

        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                if(!board[i][j].equals(Color.WHITE)){
                    int score = board[0].length - Math.abs(board[0].length - (2 * j));
                    score = this.spielerFarbe.equals(board[i][j]) ? score : -score;
                    boardEvaluation+=score;
                }
            }
        }

        return boardEvaluation;
    }

    public static Color[][] copy(Object[][] board) {
        Color[][] copyBoard = new Color[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                copyBoard[i][j] = (board instanceof GameToken[][]) ? ((GameToken) board[i][j]).getColor() : (Color) board[i][j];
            }
        }
        return copyBoard;
    }

    private List<Point> getAllPossibleMoves(Color[][] board) {
        List<Point> validMoves = new ArrayList<>();
        for (int j = 0; j < board[0].length; j++) {
            for (int i = board.length - 1; i>=0 ; i--) {
                if(board[i][j].equals(Color.white)) {
                    validMoves.add(new Point(i,j));
                    break;
                }
            }
        }
        return validMoves;
    }
}
