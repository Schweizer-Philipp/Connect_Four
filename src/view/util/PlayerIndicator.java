package view.util;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerIndicator extends JPanel {

    private Player spieler1;
    @SuppressWarnings("unused")
    private Player spieler2;

    public PlayerIndicator(Player spieler1, Player spieler2) {
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c1 = Color.BLUE;
        Color c2 = Color.RED;

        int circleSize = 130;

        int shadowY = (spieler1.isTurn()) ? 3 : circleSize + 12;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(3, shadowY, circleSize + 6, circleSize + 6);

        g2d.setColor(c1);
        g2d.fillOval(6, 6, circleSize, circleSize);

        g2d.setColor(c2);
        g2d.fillOval(6, circleSize + 15, circleSize, circleSize);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));

        g2d.drawString("Spieler 1", 67 - g2d.getFontMetrics().stringWidth("Spieler 1") / 2, 76);
        g2d.drawString("Spieler 2", 67 - g2d.getFontMetrics().stringWidth("Spieler 2") / 2, 218);
    }
}
