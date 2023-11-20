package view;

import controller.GameLogic;
import controller.KeyKlicks;
import model.GameToken;
import model.ai.Ai;
import view.util.PlayerIndicator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GameBoard extends JPanel {
    protected static final int CIRCLESIZE = 60;
    protected static final int PLAYBOARDWIDTH = 800;
    protected static final int PLAYBOARDHEIGHT = PLAYBOARDWIDTH;

    private int playBoardRow;
    private int playBoardCol;

    private int count = 0;

    private int rowSpace;
    private int colSpace;

    private GamePanel gamePanel;
    private PlayerIndicator playerIndicator;

    private GameToken[][] gameTokenArray;
    private GameLogic gameLogic;

    public GameBoard(int playBoardRow, int playBoardCol, int amountOfGameneedToWin, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gameLogic = new GameLogic(this, playBoardRow, playBoardCol);
        gameLogic.setamount(amountOfGameneedToWin);
        this.playBoardRow = playBoardRow;
        this.playBoardCol = playBoardCol;
        this.gameTokenArray = new GameToken[playBoardRow][playBoardCol];

        setOpaque(false);
        calculateRowAndColSpace();
        createGameTokenAway();
        createKeyListener();

        if(gameLogic.getspieler1() instanceof Ai) {
            Point point = gameLogic.getspieler1().move(getGameTokenArray(), 5, gameLogic.getcurrentColor());
            getGameTokenArray()[point.x][point.y].setUsed(true);
            getGameTokenArray()[point.x][point.y].setColor(gameLogic.getcurrentColor());
            checkGameState();
        }
    }

    private void createKeyListener() {
        setFocusable(true);
        requestFocus();
        KeyKlicks keyleft = new KeyKlicks("left", this);
        KeyKlicks keyright = new KeyKlicks("right", this);
        KeyKlicks keydown = new KeyKlicks("down", this);
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftClicked");
        this.getActionMap().put("leftClicked", keyleft);
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightClicked");
        this.getActionMap().put("rightClicked", keyright);
        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downClicked");
        this.getActionMap().put("downClicked", keydown);

    }

    private void calculateRowAndColSpace() {
        rowSpace = (800 / playBoardRow - CIRCLESIZE) / 2;
        colSpace = (800 / playBoardCol - CIRCLESIZE) / 2;
    }

    private void createGameTokenAway() {
        for (int i = 0; i < playBoardRow; i++) {
            for (int j = 0; j < playBoardCol; j++) {

                gameTokenArray[i][j] = new GameToken(colSpace + (j * CIRCLESIZE + colSpace * 2 * j),
                        103 + rowSpace + (i * CIRCLESIZE + i * 2 * rowSpace), CIRCLESIZE, CIRCLESIZE);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);
        g2d.drawRect(4, 102, getWidth() - 5, getHeight() - 105);

        for (int i = 0; i < playBoardRow; i++) {
            for (int j = 0; j < playBoardCol; j++) {
                g2d.fillOval(colSpace + (j * CIRCLESIZE + colSpace * 2 * j), 103 + rowSpace + (i * CIRCLESIZE + i * 2 * rowSpace), CIRCLESIZE, CIRCLESIZE);
            }
        }
        for (int i = 0; i < playBoardRow; i++) {
            for (int j = 0; j < playBoardCol; j++) {
                g2d.setColor(gameTokenArray[i][j].getColor());
                g2d.fillOval(gameTokenArray[i][j].getX(), gameTokenArray[i][j].getY(), CIRCLESIZE, CIRCLESIZE);
            }
        }

        g2d.setColor(gameLogic.getcurrentColor());
        g2d.fillOval(gameTokenArray[0][count].getX(), 39, CIRCLESIZE, CIRCLESIZE);

    }

    public int getCount() {
        return count;
    }

    public void changeCount(int count) {
        this.count = count;
    }

    public GameToken[][] getGameTokenArray() {
        return gameTokenArray;
    }

    public void keyLeft() {
        if (count - 1 < 0) {
            for (int i = playBoardCol - 1; i >= 0; i--) {
                if (!gameTokenArray[0][i].isUsed()) {
                    count = i;
                    repaint();
                    break;
                }
            }
        } else {
            for (int i = count - 1; i >= 0; i--) {
                if (!gameTokenArray[0][i].isUsed()) {
                    count = i;
                    repaint();
                    break;
                }
            }
        }
    }

    public void keyRight() {
        if (count + 1 > playBoardCol - 1) {
            for (int i = 0; i <= playBoardCol - 1; i++) {
                if (!gameTokenArray[0][i].isUsed()) {
                    count = i;
                    repaint();
                    break;
                }
            }
        } else {
            for (int i = count + 1; i <= playBoardCol - 1; i++) {
                if (!gameTokenArray[0][i].isUsed()) {
                    count = i;
                    repaint();
                    break;
                }
            }
        }
    }

    public void keyDown() {
        for (int i = playBoardRow - 1; i >= 0; i--) {
            if (!gameTokenArray[i][count].isUsed()) {
                gameTokenArray[i][count].setUsed(true);
                gameTokenArray[i][count].setColor(gameLogic.getcurrentColor());
                repaint();
                break;
            }
        }

        checkGameState();
    }

    public void checkGameState() {
        Color[][] playboard = new Color[gameTokenArray.length][gameTokenArray[0].length];
        for (int i = 0; i < gameTokenArray.length; i++) {
            for (int j = 0; j < gameTokenArray[i].length; j++) {
                playboard[i][j] = gameTokenArray[i][j].getColor();
            }
        }
        if (gameLogic.hasWon(playboard, gameLogic.getcurrentColor())) {
            if (gameLogic.getcurrentColor().equals(Color.BLUE)) {
                gameLogic.getspieler1().increaseWins();
                JOptionPane.showMessageDialog(null, "Spieler 1 hat gewonnen");
                reset();
            } else {
                gameLogic.getspieler2().increaseWins();
                JOptionPane.showMessageDialog(null, "Spieler 2 hat gewonnen");
                reset();
            }
        } else if (gameLogic.hasDraw(playboard)) {
            JOptionPane.showMessageDialog(null, "Unentschieden");
            reset();
        } else {
            gameLogic.changePlayer();

            if(playerIndicator != null) playerIndicator.repaint();
        }
    }

    private void reset() {
        for (int i = 0; i < playBoardRow; i++) {
            for (int j = 0; j < playBoardCol; j++) {
                gameTokenArray[i][j].setColor(Color.WHITE);
                gameTokenArray[i][j].setUsed(false);
            }
        }
        gameLogic.getspieler1().setTurn(true);
        gameLogic.getspieler2().setTurn(false);
        gamePanel.refeshLabel();
        gamePanel.repaint();
        repaint();
        playerIndicator.repaint();
        if(gameLogic.getspieler1() instanceof Ai) {
            Point point = gameLogic.getspieler1().move(getGameTokenArray(), 5, gameLogic.getcurrentColor());
            getGameTokenArray()[point.x][point.y].setUsed(true);
            getGameTokenArray()[point.x][point.y].setColor(gameLogic.getcurrentColor());
            checkGameState();
        }
    }

    public void computerwin() {
        gameLogic.getspieler1().increaseWins();
        reset();
        JOptionPane.showMessageDialog(null, "Computer hat gewonnen");

        for (int i = 0; i < gameTokenArray.length; i++) {
            for (int j = 0; j < gameTokenArray[i].length; j++) {
                System.out.println(gameTokenArray[i][j].isUsed());
            }
        }


    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void draw() {
        JOptionPane.showMessageDialog(null, "Gratulation sie haben gegen den Computer unenschieden gespielt");
        reset();
    }

    public void setIndicator(PlayerIndicator playIndicator) {
        this.playerIndicator = playIndicator;
    }
}
