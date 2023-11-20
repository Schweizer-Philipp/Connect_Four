package view.util;

import util.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImageButton extends JButton implements MouseListener {

    private Image[] images;
    private AudioPlayer pressSound;
    private AudioPlayer releaseSound;

    public ImageButton(Image[] images, String actionCommand) {
        this.images = images;
        setActionCommand(actionCommand);
        setIcon(new ImageIcon(images[0]));
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(this);


    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressSound = new AudioPlayer("/res/btnPress.wav");
        pressSound.play();
        setIcon(new ImageIcon(images[2]));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releaseSound = new AudioPlayer("/res/btnRelease.wav");
        releaseSound.play();
        setIcon(new ImageIcon(images[0]));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setIcon(new ImageIcon(images[1]));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setIcon(new ImageIcon(images[0]));
    }
}
