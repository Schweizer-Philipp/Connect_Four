package view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MotionPanel extends JPanel {

    private Point initialClick;

    public MotionPanel(final JFrame parent) {
        //requestFocus();
        //setFocusable(true);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getPoint().y >= 25 && e.getPoint().y <= 85 &&
                        e.getPoint().x >= 885 && e.getPoint().x <= 955)
                    System.exit(0);

                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;

                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                int x = thisX + xMoved;
                int y = thisY + yMoved;

                parent.setLocation(x, y);
            }
        });
    }

}
