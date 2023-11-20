package view.util;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image background;

    public BackgroundPanel(Image background) {
        this.background = background;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (background != null)
            g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
