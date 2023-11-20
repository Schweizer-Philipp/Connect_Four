package model;

import java.awt.*;

public class GameToken {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private boolean used;

    public GameToken(int x, int y, int width, int height) {
        this.color = Color.WHITE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        setUsed(true);
        this.color = color;
    }


    public boolean isUsed() {
        return used;
    }


    public void setUsed(boolean used) {
        this.used = used;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


}
