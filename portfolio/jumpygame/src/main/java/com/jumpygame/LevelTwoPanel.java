package com.jumpygame;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyAdapter;
import java.awt.geom.Ellipse2D;

public class LevelTwoPanel extends JPanel implements ActionListener{
    JPanel levelTwoPanel = new JPanel();
    Sprite sprite; //"import" sprite
    Rectangle exitDoor = new Rectangle(550, 20, 25, 30);
    //LevelOnePanel levelOnePanel;
    Timer timer = new Timer(30, this); 

    ArrayList<Rectangle> platforms = new ArrayList<>();
    ArrayList<Ellipse2D.Double> obsticals = new ArrayList<>(); 
    ArrayList<Ellipse2D.Double> coins = new ArrayList<>();
    
    private final int Gravity =1;
    private int verticalVelocity = 0;

    public LevelTwoPanel(CardLayout card, JPanel panel){

        this.setFocusable(true);
        this.requestFocusInWindow();
        //this.addKeyListener(this);
 
        
        timer.start(); 

        //set panel preferences 
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 500));
        sprite = new Sprite(5, 420, 20, 20);
        
        //add platforms to list
        platforms.add(new Rectangle(0, 425, 600, 40)); //GROUND
        platforms.add(new Rectangle(150, 340, 100, 40)); //platform 1
        platforms.add(new Rectangle(300, 300, 150, 40 )); //platform 2
        platforms.add(new Rectangle(150, 220, 100, 40)); //platform 3
        platforms.add(new Rectangle(450, 220, 200, 40)); //platform 4
        platforms.add(new Rectangle(300, 180, 200, 40)); //platform 5
        platforms.add(new Rectangle(30, 160, 100, 40)); //platform 6
        platforms.add(new Rectangle(360, 90, 200, 40)); //platform 7
        platforms.add(new Rectangle(480, 50, 100, 40)); //platform 8 (TOP)

        //add obsticals to list
        obsticals.add(new Ellipse2D.Double(180, 320, 20, 20));
        obsticals.add(new Ellipse2D.Double(350, 280, 20 , 20));
        obsticals.add(new Ellipse2D.Double(200, 200, 20, 20));
        obsticals.add(new Ellipse2D.Double(520, 200, 20, 20));
        obsticals.add(new Ellipse2D.Double(380, 70, 20, 20));
        
        //add coins to list (there's probably a more effecient way to do this, i just don't know it)
        //platform 1 coins 
        coins.add(new Ellipse2D.Double(160, 300, 10, 10)); 
        coins.add(new Ellipse2D.Double(230, 300, 10, 10));
        //platform 2 coins
        coins.add(new Ellipse2D.Double(320, 260, 10, 10));
        coins.add(new Ellipse2D.Double(380, 260, 10, 10));
        coins.add(new Ellipse2D.Double(420, 260, 10, 10));
        //platform 3 coins
        coins.add(new Ellipse2D.Double(150, 180, 10, 10));
        coins.add(new Ellipse2D.Double(200, 180, 10, 10));
        coins.add(new Ellipse2D.Double(240, 180, 10, 10));
        //platforms' 4 & 5 coins
        coins.add(new Ellipse2D.Double(320, 140, 10, 10));
        coins.add(new Ellipse2D.Double(380, 140, 10, 10));
        coins.add(new Ellipse2D.Double(460, 140, 10, 10));
        coins.add(new Ellipse2D.Double(560, 180, 10, 10));
        //platform 6 coins
        coins.add(new Ellipse2D.Double(80, 120, 10, 10));
        //platform 7 coins
        coins.add(new Ellipse2D.Double(380, 50, 10, 10));
        coins.add(new Ellipse2D.Double(430, 50, 10, 10));

        //JLabel pointCounter = new JLabel();
        //pointCounter.setText("Points: " + levelTwoPoints);
        //pointCounter.setFont(new Font("Mono" , Font.PLAIN, 24)); 

        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUP");
        actionMap.put("moveUP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevY = sprite.getY(); 
                if(!isJumping){
                    verticalVelocity = -jumpStrength;
                    isJumping = true;
                }
                if(isJumping){
                    verticalVelocity = -jumpStrength;
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
                sprite.setX(sprite.getX() - 10);
                if(isJumping){
                   sprite.setX(sprite.getX() - 10);  
                }
                repaint(); 
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRIGHT");
        actionMap.put("moveRIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprite.setX(sprite.getX() + 10);
                if(isJumping){
                   sprite.setX(sprite.getX() + 10);  
                }
                repaint();
            }
        });
    
    }

    //draw components
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //platforms
        g.setColor(Color.GRAY);
        for(Rectangle platform : platforms){
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }

        //obsticals
        g.setColor(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;
        for(Ellipse2D.Double rocks : obsticals){
            g2d.fill(rocks); 
        }

        //coins
        Graphics2D gd2 = (Graphics2D) g;
        g.setColor(Color.YELLOW);
        for(Ellipse2D.Double coin : coins){
            gd2.fill(coin); 
        }

        //manually draw sprite
        g.setColor(Color.PINK);
        g.fillRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        //exit door
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(550, 20, 25, 30);

        //points
        g.setColor(Color.WHITE);
        g.setFont(new Font("Mono" , Font.PLAIN, 24));
        g.drawString("Points: " + LevelOnePanel.levelOnePoints, 20, 30); 
    }

    private boolean isJumping;
    private int jumpStrength = 10;
    private int prevY;  

    @Override
    public void actionPerformed(ActionEvent e){
        verticalVelocity += Gravity;
        sprite.setY(sprite.getY() + verticalVelocity); 
        Rectangle spriteBounds = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        @SuppressWarnings("unused")
        boolean collision = false; 

        if(sprite.getY() >= 404){
            sprite.setY(404); 
            isJumping = false;
            verticalVelocity = 0;
        }

        for(Rectangle platform : platforms){
            if(spriteBounds.intersects(platform)){
                collision = true; 
                if(verticalVelocity > 0 && sprite.getY() + sprite.getHeight() <= platform.y + 5){
                    sprite.setY(platform.y - sprite.getHeight());
                    isJumping = false;
                    verticalVelocity = 0; 
                }else{
                    sprite.setY(prevY); 
                }
            }
        }

        int spriteCenterX = sprite.getX() + sprite.getWidth() /2;
        int spriteCenterY = sprite.getY() + sprite.getHeight() /2;
        int spriteRadius = Math.min(sprite.getWidth(), sprite.getHeight()) /2; 
        for(Ellipse2D.Double rock : obsticals){
            double rockCenterX = rock.getX() + rock.getHeight() /2;
            double rockCenterY = rock.getY() + rock.getWidth() /2;
            double rockRadius = rock.getWidth() /2; 
            double distX = spriteCenterX - rockCenterX;
            double distY = spriteCenterY - rockCenterY;
            double distance = Math.sqrt(distX * distX + distY * distY); 

            if(distance <= (spriteRadius + rockRadius)){
                resetSprite(); 
                break;
            }
        }

        for(Ellipse2D.Double coin : coins){
            double coinCenterX = coin.getX() + coin.getHeight() /2;
            double coinCenterY = coin.getY() + coin.getWidth() /2;
            double coinRadius = coin.getWidth() /2;
            double dX = spriteCenterX - coinCenterX;
            double dY = spriteCenterY - coinCenterY;
            double distance = Math.sqrt(dX * dX + dY * dY); 

            if(distance <= (spriteRadius + coinRadius)){
                coins.remove(coin); 
                LevelOnePanel.levelOnePoints++;
                break; 
            }
         }

         Rectangle exitBounds = new Rectangle(550, 20, 25, 30);
         if(spriteBounds.intersects(exitBounds)){
            //timer.stop();
            GameFrame.levelThreePanel();
            //timer.stop(); //need to stop timer to prevent glitch 
         }

        repaint();
       


    }

    public void resetSprite(){
        sprite.setX(10);
        sprite.setY(404);
        verticalVelocity = 0;
        repaint(); 
    }

}
