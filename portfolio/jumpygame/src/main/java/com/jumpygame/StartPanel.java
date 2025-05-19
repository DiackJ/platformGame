package com.jumpygame;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel {
    JPanel startJPanel = new JPanel(); //set panel
    JButton startButton = new JButton(); //create button

    public StartPanel(CardLayout card, JPanel panel){
       //add event listener for button click and the event path
        startButton.addActionListener(e ->{
            card.show(panel, "Level One"); 
            revalidate();
            panel.setFocusable(true);
            this.setFocusable(false);
            //JOptionPane.showMessageDialog(null, "button clicked"); //debug message to check if working
           
        });

    //button appearance
        startButton.setPreferredSize(new Dimension(200, 60));//button dimensions because setSize() doesn't typically work
        startButton.setBackground(Color.GREEN); //button color
        //startButton.setForeground(Color.BLACK);
        startButton.setText("START"); //text for button
        startButton.setFont(new Font("Mono", Font.BOLD, 26));//font specifications
       
        startButton.setFocusPainted(false);//gets rid of textblox border
        startButton.setBorderPainted(false);//gets rid of button border
        
         
        this.setBackground(Color.BLACK); //setting the current StartPanel bkground to black
        startJPanel.setBackground(Color.BLACK);//sets panel to black
    //set constraints and layout of panel
        startJPanel.setLayout(new GridBagLayout()); //centers
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; 

        startJPanel.add(startButton, gbc); //add button to panel
        this.setLayout(new BorderLayout());//set layout
        this.add(startJPanel, BorderLayout.CENTER); //add panel to main StartPanel and center layout

    }

   

}
