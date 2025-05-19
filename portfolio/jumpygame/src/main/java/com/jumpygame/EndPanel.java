package com.jumpygame;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel{
    JPanel endPanel = new JPanel();
    JButton playAgain = new JButton();
    JButton quit = new JButton();

    public EndPanel(CardLayout card, JPanel panel){
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.setBackground(Color.BLACK);
        endPanel.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 500));

        //button appearance
        playAgain.setPreferredSize(new Dimension(200, 60));//button dimensions because setSize() doesn't typically work
        playAgain.setBackground(Color.GREEN); //button color
       
        playAgain.setText("PLAY AGAIN"); //text for button
        playAgain.setFont(new Font("Mono", Font.BOLD, 26));//font specifications
       
        playAgain.setFocusPainted(false);//gets rid of textblox border
        playAgain.setBorderPainted(false);//gets rid of button border

        //button appearance
        quit.setPreferredSize(new Dimension(100, 40));//button dimensions because setSize() doesn't typically work
        quit.setBackground(Color.GREEN); //button color
        
        quit.setText("QUIT"); //text for button
        quit.setFont(new Font("Mono", Font.BOLD, 26));//font specifications
       
        quit.setFocusPainted(false);//gets rid of textblox border
        quit.setBorderPainted(false);//gets rid of button border
      

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //vertical alignment

        this.add(Box.createVerticalGlue()); //add space at top
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playAgain); 

        this.add(Box.createRigidArea(new Dimension(0, 20))); //20px between buttons

        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(quit); 
        this.add(Box.createVerticalGlue()); 

        

        playAgain.addActionListener( e -> {
            GameFrame.levelOnePanel(); 
            panel.setFocusable(true);
            panel.requestFocusInWindow(); 
        });

        quit.addActionListener(e -> {
           GameFrame.close(); 
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Mono", Font.PLAIN, 36));
        g.drawString("Total Points: " + LevelOnePanel.levelOnePoints, 180, 70);
    }
}
