package com.jumpygame;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;


public class LevelOnePanel extends JPanel implements ActionListener{
    JPanel levelOnePanel = new JPanel();
    Sprite sprite;
    Rectangle exitDoor = new Rectangle(50, 30, 25, 30); //exit door for collision later
    private int pointCollection = 0; //increment points
    protected static int levelOnePoints; 

    ArrayList<Rectangle> platforms = new ArrayList<>(); //array of platforms
    ArrayList<Ellipse2D.Double> obsticals = new ArrayList<>(); //array of obsticals
    ArrayList<Ellipse2D.Double> coins = new ArrayList<>();

    private final int Gravity = 1; //gravity
    private int verticalVelocity = 0; //speed and direction of sprite
    Timer timer = new Timer(30, this); 

    public LevelOnePanel(CardLayout card, JPanel panel) {
         //enable key movements 
         this.setFocusable(true);
 
     //timer for collision (and gravity??)
        
         timer.start();
    // Set panel properties
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 500));
    //set points label properties
        JLabel points = new JLabel();
        points.setText("Points: " + pointCollection);
        points.setFont(new Font("Mono", Font.PLAIN, 24));
        
    // Initialize sprite with position and size
        sprite = new Sprite(5, 404, 20, 20);

    //add platforms to list 
        platforms.add(new Rectangle(50, 340, 200, 40));//1
        platforms.add(new Rectangle(300, 270, 200, 40));//2
        platforms.add(new Rectangle(230, 150, 200, 40));//3
        platforms.add(new Rectangle(50, 60, 200, 40));//top 
        platforms.add(new Rectangle(0, 425, 600, 40));//ground

    //add obsticals to list
        obsticals.add(new Ellipse2D.Double(80, 320, 20, 20));//1
        obsticals.add(new Ellipse2D.Double(380, 250, 20, 20));//2
        obsticals.add(new Ellipse2D.Double(300, 130, 20, 20));//3

    //add coins
        coins.add(new Ellipse2D.Double(60, 290, 10, 10)); 
        coins.add(new Ellipse2D.Double(120, 290, 10, 10)); 
        coins.add(new Ellipse2D.Double(180, 290, 10, 10)); 
        coins.add(new Ellipse2D.Double(220, 290, 10, 10)); 
        coins.add(new Ellipse2D.Double(300, 220, 10, 10)); 
        coins.add(new Ellipse2D.Double(360, 220, 10, 10)); 
        coins.add(new Ellipse2D.Double(440, 220, 10, 10)); 
        coins.add(new Ellipse2D.Double(480, 220, 10, 10)); 
        coins.add(new Ellipse2D.Double(270, 100, 10, 10)); 
        coins.add(new Ellipse2D.Double(330, 100, 10, 10)); 
        coins.add(new Ellipse2D.Double(400, 100, 10, 10)); 
        coins.add(new Ellipse2D.Double(100, 15, 10, 10)); 
        coins.add(new Ellipse2D.Double(160, 15, 10, 10)); 
        coins.add(new Ellipse2D.Double(220, 15, 10, 10));
   

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
    
//overridden method to paint static components and manually draw sprite
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
    
    //draw platforms to list for collision detection
        g.setColor(Color.GRAY);
        for(Rectangle platform : platforms){
            g.fillRect(platform.x, platform.y, platform.width, platform.height); 
        }
    //draw obsticals to list for collision detection
        g.setColor(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;
        for(Ellipse2D.Double oval : obsticals){
            g2d.fill(oval);
        }

        g.setColor(Color.YELLOW);
        for(Ellipse2D.Double coin : coins){
            g2d.fill(coin);
        }
    //draw exit door
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 30, 25, 30);

    //draw text on panel
    g.setFont(new Font("Mono", Font.PLAIN, 24));
    g.setColor(Color.WHITE);
    
    // Draw the point text at specific coordinates
    g.drawString("Points: " + pointCollection, 430, 30);

    //manually draw sprite because nothing else freaking worked
        g.setColor(Color.PINK);
        g.fillRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    
    }
//keep track of previous positions
    private int prevY; 

//jumping logic variables
    private int jumpStrength = 10;
    private boolean isJumping; 



//collision detection
    @Override
    public void actionPerformed(ActionEvent e) {
        verticalVelocity += Gravity;
        sprite.setY(sprite.getY() + verticalVelocity); //apply gravity to sprite component
        Rectangle spriteBounds = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()); //set bounds for collision
        @SuppressWarnings("unused")
        boolean collision = false;
        //calculate center and radius of sprite
        int spriteCenterX = sprite.getX() + sprite.getWidth() /2;
        int spriteCenterY = sprite.getY() + sprite.getHeight() /2;
        int spriteRadius = Math.min(sprite.getWidth(), sprite.getHeight()) /2; 

    //detect obstical collision
        for(Ellipse2D.Double oval : obsticals){
            //calculate center and radius of circles 
            double ovalCenterX = oval.getX() + oval.getWidth() /2;
            double ovalCenterY = oval.getY() + oval.getHeight() /2;
            double ovalRadius = oval.getWidth() /2; //since width = height

            //calculate distance between sprite and obstical
            double dX = spriteCenterX - ovalCenterX;
            double dY = spriteCenterY - ovalCenterY;
            double distance = Math.sqrt(dX * dX + dY * dY); 
            //collision for obsticals
            if(distance <= (spriteRadius + ovalRadius)){
                resetGame();
                break; 
            }
        }

    //detect platform collision
        for(Rectangle platform : platforms){
            if(spriteBounds.intersects(platform)){
                //stop bouncing by detecing collision of bottom and top of sprite and platform
                //with a 5 pixel safety net 
                collision = true; 
                if(verticalVelocity > 0 && sprite.getY() + sprite.getHeight() <= platform.getY()+5){
                    sprite.setY(platform.y - sprite.getHeight());
                    isJumping = false;
                    verticalVelocity = 0;
                }else{
                    sprite.setY(prevY);
                }
                /*if(sprite.getX() + sprite.getWidth() >= platform.getX() && sprite.getX() < platform.getX()){
                    sprite.setX(platform.x - sprite.getWidth()); 
                }else if(sprite.getX() + sprite.getWidth() <= platform.getX() && sprite.getX() > platform.getX()){
                    sprite.setX(platform.x + sprite.getWidth());
                }*/
                //sprite.setY(platform.y - 50);
                verticalVelocity = 0; //reset velocity to 0 so sprite stops on platform
                break;
            }
        }

    //detect coin collision
        for(Ellipse2D.Double coin : coins){
            //coin.getBounds2D();
            if(spriteBounds.intersects(coin.getBounds2D())){
                coins.remove(coin); 
                pointCollection ++;
                verticalVelocity = 0;
                break;
            }                                                       //  ^  ^
        } //omg i did this one without chatgpt and it actually worked!! 0  0
        levelOnePoints = pointCollection;
    //resets sprite to ground
        if(sprite.getY() >= 404){
            sprite.setY(404);
            isJumping = false;
            verticalVelocity = 0;
        }

    //move to next level 
        if(spriteBounds.intersects(exitDoor.getBounds())){
            timer.stop(); //you HAVE TO stop ALL THE TIMERS!!!
           GameFrame.levelTwoPanel(); 
            //switchFrame
        }

        repaint();
    }

    //reset sprite coordinates -- change later to "oops" panel
    private void resetGame(){
        sprite.setX(10);
        sprite.setY(404);
        repaint();
    }

}