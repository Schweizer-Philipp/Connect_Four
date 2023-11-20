package view;

import util.ImageLoader;
import view.util.BackgroundPanel;
import view.util.ImageButton;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends BackgroundPanel {
    private ImageButton btnRowUp;
    private ImageButton btnRowDown;
    private ImageButton btnColUp;
    private ImageButton btnColdown;
    private ImageButton btnamountOfGameneedToWinUp;
    private ImageButton btnamountOfGameneedToWinDown;

    private JLabel lblRow;
    private JLabel lblCol;
    private JLabel lblamountOfGameneedToWin;
    private JButton btnSafe;
    private OptionManager optionManager;
    private CardLayoutPanel card;


    public OptionPanel(CardLayoutPanel card) {
        super(ImageLoader.load("/res/background.png"));
        this.card = card;
        initVars();
        initComps();
    }

    /**
     * Initializes Variables.
     */
    private void initVars() {
        setLayout(null);
        Image[] arrowUpImage = {

                ImageLoader.load("/res/ArrowUp.png"),
                ImageLoader.load("/res/ArrowUp.png"),
                ImageLoader.load("/res/ArrowUp.png")
        };
        Image[] arrowDownImage = {

                ImageLoader.load("/res/ArrowDown.png"),
                ImageLoader.load("/res/ArrowDown.png"),
                ImageLoader.load("/res/ArrowDown.png")
        };

        btnamountOfGameneedToWinDown = new ImageButton(arrowDownImage, "winsDown");
        btnamountOfGameneedToWinUp = new ImageButton(arrowUpImage, "winsUp");
        btnColdown = new ImageButton(arrowDownImage, "colD");
        btnColUp = new ImageButton(arrowUpImage, "colU");
        btnRowDown = new ImageButton(arrowDownImage, "rowD");
        btnRowUp = new ImageButton(arrowUpImage, "rowU");
        lblCol = new JLabel("7");
        lblRow = new JLabel("6");
        lblamountOfGameneedToWin = new JLabel("4");
        btnSafe = new JButton(new ImageIcon(ImageLoader.load("/res/speicher.png")));
        optionManager = new OptionManager(this, card);
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        setUpButton(btnRowUp, 100, 200);
        setUpButton(btnRowDown, 100, 500);
        setUpButton(btnColUp, 400, 200);
        setUpButton(btnColdown, 400, 500);
        setUpButton(btnamountOfGameneedToWinUp, 700, 200);
        setUpButton(btnamountOfGameneedToWinDown, 700, 500);

        setUpLabel(lblCol, 460, 400);
        setUpLabel(lblRow, 160, 400);
        setUpLabel(lblamountOfGameneedToWin, 760, 400);

        setUpButton(btnSafe, 800, 800);
    }

    private void setUpLabel(JLabel lbl, int x, int y) {
        lbl.setForeground(Color.WHITE);
        lbl.setBackground(Color.WHITE);
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lbl.setBounds(x, y, 50, 50);
        add(lbl);

    }

    private void setUpButton(JButton btn, int x, int y) {
        if (!(btn instanceof ImageButton)) {
            btn.setActionCommand("speichern");
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
        }
        btn.addActionListener(optionManager);
        btn.setBounds(x, y, 150, 150);
        add(btn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.PLAIN, 25));

        g.drawString("Anzahl Zeilen", 80, 180);
        g.drawString("Anzahl Spalten", 380, 180);
        g.drawString("Münzen zum Gewinnen", 650, 180);


    }

    public JLabel getLabelRow() {
        return lblRow;
    }

    public JLabel getLabelCol() {
        return lblCol;
    }

    public JLabel getLabelW() {
        return lblamountOfGameneedToWin;
    }

}
