package com.jumpygame;

import javax.swing.*;
import java.awt.*; 

public class Sprite extends JComponent{
    private int x, y, width, height;

    public Sprite(int x, int y, int width, int height){
        this.x = 10;
        this.y = 404;
        this.width = width;
        this.height = height; 
        setPreferredSize(new Dimension(width, height));
        setBounds(x, y, width, height);
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.PINK); // Color of the sprite
        g.fillRect(0, 0, width, height); // Draw the sprite at (0, 0) within its own bounds
    }
}
