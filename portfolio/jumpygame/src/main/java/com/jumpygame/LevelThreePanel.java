package com.jumpygame;

import java.util.*;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class LevelThreePanel extends JPanel implements ActionListener{
    static JPanel levelThreePanel = new JPanel(); 
    Sprite sprite; 
    Rectangle exitDoor = new Rectangle(0, 30, 25, 30);
    int beamHeight = 170;
    Rectangle beam = new Rectangle(410, 240, 40, beamHeight); 

    ArrayList<Rectangle> platforms = new ArrayList<>(); 
    ArrayList<Ellipse2D.Double> obsticals = new ArrayList<>(); 
    ArrayList<Ellipse2D.Double> coins = new ArrayList<>(); 
    ArrayList<Ellipse2D.Double> removedCoins = new ArrayList<>();

    private int velocity = 0; 
    private final int Gravity = 1; 
    Timer timer = new Timer(30, this);
    BeamThread beamThread = new BeamThread(this);

    public LevelThreePanel(CardLayout card, JPanel panel){
        this.setVisible(true);
        
        timer.start();
        sprite = new Sprite(5, 420, 20, 20);
        this.setFocusable(true); 
        this.requestFocusInWindow(); 
        //this.addKeyListener(this);

        
        Thread thread = new Thread(beamThread);
        thread.start();

        this.setBackground(Color.BLACK); 
        this.setPreferredSize(new Dimension(600, 500)); 
        

        //add platforms
        platforms.add(new Rectangle(0, 60, 90, 40)); //TOP
        platforms.add(new Rectangle(60, 100, 200, 40)); //7
        platforms.add(new Rectangle(330, 100, 100, 40)); //6
        platforms.add(new Rectangle(170, 200, 100, 40)); //5
        platforms.add(new Rectangle(220, 230,350, 40)); //4
        platforms.add(new Rectangle(50, 280, 70, 40)); //3
        platforms.add(new Rectangle(200, 350, 100, 40)); //2
        platforms.add(new Rectangle(350, 400, 100, 40)); //1
        platforms.add(new Rectangle(0, 425, 600, 40)); //GROUND
        //add obsticals
        obsticals.add(new Ellipse2D.Double(300, 405, 20, 20)); 
        obsticals.add(new Ellipse2D.Double(150, 405, 20, 20));//ground^
        obsticals.add(new Ellipse2D.Double(230, 330, 20, 20));//p2
        obsticals.add(new Ellipse2D.Double(330, 210, 20, 20));
        obsticals.add(new Ellipse2D.Double(450, 210, 20, 20));//p4^
        obsticals.add(new Ellipse2D.Double(130,80, 20, 20));//p7
        //add coins
        coins.add(new Ellipse2D.Double(500, 365, 10, 10)); 
        coins.add(new Ellipse2D.Double(560, 365, 10, 10));//ground^
        coins.add(new Ellipse2D.Double(365, 360, 10, 10));//p1
        coins.add(new Ellipse2D.Double(210, 310, 10, 10));
        coins.add(new Ellipse2D.Double(240, 310, 10, 10));
        coins.add(new Ellipse2D.Double(280, 310, 10, 10));//p2^
        coins.add(new Ellipse2D.Double(80, 240, 10, 10));//p3
        coins.add(new Ellipse2D.Double(240, 160, 10, 10));
        coins.add(new Ellipse2D.Double(190, 160, 10, 10));//p5^
        coins.add(new Ellipse2D.Double(300, 190, 10, 10));
        coins.add(new Ellipse2D.Double(400, 190, 10, 10));
        coins.add(new Ellipse2D.Double(510, 190, 10, 10));//p4^
        coins.add(new Ellipse2D.Double(380, 60, 10, 10));//p6
        coins.add(new Ellipse2D.Double(110, 60, 10, 10));
        coins.add(new Ellipse2D.Double(170, 60, 10, 10));
        coins.add(new Ellipse2D.Double(230, 60, 10, 10));//p7


        //key bindings which will hopefully make the key events work better when 
        //panel is switched

        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUP");
        actionMap.put("moveUP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevY = sprite.getY(); 
                if(!isJumping){
                    velocity = -jumpStrength;
                    isJumping = true;
                }
                if(isJumping){
                    velocity = -jumpStrength;
                }
                sprite.setY(sprite.getY() - 5);
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDOWN");
        actionMap.put("moveDOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isJumping = false;
                sprite.setY(sprite.getY() + 5);
                repaint();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLEFT");
        actionMap.put("moveLEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isJumping){
                   sprite.setX(sprite.getX() - 10);  
                }
                sprite.setX(sprite.getX() - 10);
                repaint(); 
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRIGHT");
        actionMap.put("moveRIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isJumping){
                   sprite.setX(sprite.getX() + 10);  
                }
                sprite.setX(sprite.getX() + 10);
                repaint();
            }
        });

    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //draw platforms
        g.setColor(Color.GRAY);
        for(Rectangle platform : platforms){
            g.fillRect(platform.x, platform.y, platform.width, platform.height); 
        }

        //beam
        g.setColor(Color.GRAY);
        g.fillRect(410, 240, 40, beamHeight);

        //exit door
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 30, 25, 30);

        //draw sprite
        g.setColor(Color.PINK);
        g.fillRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        //draw obsticals
        g.setColor(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;
        for(Ellipse2D.Double rocks : obsticals){
            g2d.fill(rocks); 
        }

        //draw coins
        g.setColor(Color.YELLOW);
        Graphics2D gd2 = (Graphics2D) g;
        for(Ellipse2D.Double coin : coins){
            gd2.fill(coin); 
        }

        //ponts text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Mono", Font.PLAIN, 24));
        g.drawString("Points: " + LevelOnePanel.levelOnePoints, 450, 50);
    }

    private int prevY;
    private int jumpStrength = 10;
    private boolean isJumping = false;
    private int removeCoins = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
       velocity += Gravity;
       sprite.setY(sprite.getY() + velocity); 
       Rectangle spriteBounds = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()); 
       @SuppressWarnings("unused")
    boolean collision = false;

       if(sprite.getY() >= 404){
        sprite.setY(404); 
        velocity = 0;
        isJumping = false; 
       }

       for(Rectangle platform : platforms){
            if(spriteBounds.intersects(platform)){
                collision = true; 
                if(velocity > 0 && sprite.getY() + sprite.getHeight() <= platform.y + 5){
                    sprite.setY(platform.y - sprite.getHeight());
                    isJumping = false;
                    velocity = 0; 
                }else{
                    sprite.setY(prevY); 
                }
            }
        }

        int spriteCenterX = sprite.getX() + sprite.getWidth()/2;
        int spriteCenterY = sprite.getY() + sprite.getHeight()/2;
        int spriteRadius = Math.min(sprite.getWidth(), sprite.getHeight())/2; 

        for(Ellipse2D.Double rocks : obsticals){
            double rocksCenterX = rocks.getX() + rocks.getWidth()/2;
            double rocksCenterY = rocks.getY() + rocks.getHeight()/2;
            double rocksRadius = rocks.getWidth()/2;

            double distX = spriteCenterX - rocksCenterX; 
            double distY = spriteCenterY - rocksCenterY;
            double distance = Math.sqrt(distX * distX + distY * distY); 

            if(distance <= (spriteRadius + rocksRadius)){
                resetSprite(); 
                break;
            }
        }

        
        for(Ellipse2D.Double coin : coins){
            double coinCenterX = coin.getX() + coin.getWidth()/2;
            double coinCenterY = coin.getY() + coin.getHeight()/2;
            double coinRadius = coin.getWidth()/2;

            double distX = spriteCenterX - coinCenterX; 
            double distY = spriteCenterY - coinCenterY;
            double distance = Math.sqrt(distX * distX + distY * distY); 

            if(distance <= (spriteRadius + coinRadius)){
                coins.remove(coin);
                LevelOnePanel.levelOnePoints++;
                removedCoins.add(coin);
                removeCoins++;
                break;
            }
        }

        Rectangle beamBounds = new Rectangle(410, 240, 40, beamHeight);
        if(spriteBounds.intersects(beamBounds)){
            resetSprite();
            LevelOnePanel.levelOnePoints -= removeCoins;
            coins.addAll(removedCoins); 
        }

        Rectangle exitBounds = new Rectangle(exitDoor);
        if(spriteBounds.intersects(exitBounds)){
            timer.stop(); 
            GameFrame.endingPanel();    
        }

       repaint();
        
    }

    public void resetSprite(){
        sprite.setY(404);
        sprite.setX(5);
        repaint(); 
    }
  

}
