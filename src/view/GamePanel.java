package view;

import util.ImageLoader;
import view.util.BackgroundPanel;
import view.util.PlayerIndicator;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends BackgroundPanel {

    private JPanel contentPane;
    private JPanel menuPane;
    private GameBoard gameBoard;

    private JLabel lblPlayer1Wins;
    private JLabel lblPlayer2Wins;
    private PlayerIndicator indicator;
    private OptionPanel optionPanel;

    public GamePanel(OptionPanel optionPanel) {
        super(ImageLoader.load("/res/background.png"));
        this.optionPanel = optionPanel;
    }

    private void initVars() {
        contentPane = new JPanel();
        menuPane = new JPanel();
        gameBoard = new GameBoard(Integer.valueOf(optionPanel.getLabelRow().getText()), Integer.valueOf(optionPanel.getLabelCol().getText()), Integer.valueOf(optionPanel.getLabelW().getText()), this);
        lblPlayer1Wins = new JLabel("    Siege: " + gameBoard.getGameLogic().getspieler1().getWins());
        lblPlayer2Wins = new JLabel("    Siege: " + gameBoard.getGameLogic().getspieler2().getWins());
        indicator = new PlayerIndicator(gameBoard.getGameLogic().getspieler1(), gameBoard.getGameLogic().getspieler2());
        gameBoard.setIndicator(indicator);
    }

    private void initComp() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.LINE_AXIS));
        contentPane.setOpaque(false);
        contentPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.PAGE_AXIS));
        menuPane.setOpaque(false);
        menuPane.setPreferredSize(new Dimension(180, 900));
        menuPane.setMinimumSize(new Dimension(180, 900));
        menuPane.setMaximumSize(new Dimension(180, 900));
        menuPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblPlayer1Wins.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer1Wins.setForeground(Color.WHITE);
        lblPlayer1Wins.setFont(new Font("Verdana", Font.BOLD, 20));
        lblPlayer2Wins.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer2Wins.setForeground(Color.WHITE);
        lblPlayer2Wins.setFont(new Font("Verdana", Font.BOLD, 20));

        indicator.setSize(180, 180);
        indicator.setOpaque(false);

        menuPane.add(new JLabel("<html><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></html>"));
        menuPane.add(lblPlayer1Wins);
        menuPane.add(new JLabel("<html><br></html>"));
        menuPane.add(indicator);
        menuPane.add(new JLabel("<html><br></html>"));
        menuPane.add(lblPlayer2Wins);
        menuPane.add(new JLabel("<html><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></html>"));

        gameBoard.setPreferredSize(new Dimension(800, 900));
        gameBoard.setMinimumSize(new Dimension(800, 900));
        gameBoard.setMaximumSize(new Dimension(800, 900));


        contentPane.add(menuPane);
        contentPane.add(gameBoard);

        add(new JLabel("<html><br><br><br><br><br><br></html>"));
        add(contentPane);
    }

    public void startGame() {
        initVars();
        initComp();
    }

    public void refeshLabel() {
        lblPlayer1Wins.setText("    Siege: " + gameBoard.getGameLogic().getspieler1().getWins());
        lblPlayer2Wins.setText("    Siege: " + gameBoard.getGameLogic().getspieler2().getWins());
    }
}