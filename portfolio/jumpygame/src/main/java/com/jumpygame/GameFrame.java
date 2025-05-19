package com.jumpygame;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private static CardLayout card; //manages multiple panels within one container
    private static JPanel panel; //to have different panels like start, levels, ending, etc.
    static JFrame frame = createGUIFrame();
//constructor to initialize cards and panels
    public GameFrame(){
        card = new CardLayout();
        panel = new JPanel(card); 
       // JPanel startPanelClass = new StartPanel(card, panel); 

    //initialize different panels for switching 
        JPanel start = new StartPanel(card, panel); 
        JPanel levelOne = new LevelOnePanel(card, panel);
        JPanel levelTwo = new LevelTwoPanel(card, panel);
        JPanel levelThree = new LevelThreePanel(card, panel);  
        JPanel ending = new EndPanel(card, panel);

    //add panels to main panel container
        panel.add(start, "Start"); 
        panel.add(levelOne, "Level One");
        panel.add(levelTwo, "Level Two");
        //levelTwo.requestFocusInWindow();
        panel.add(levelThree, "Level Three");
        panel.add(ending, "End Panel"); 

    //add main panel container to JFrame 
       //JFrame frame = createGUIFrame();
       frame.add(panel); 
        
        card.show(panel, "Start"); //shows start panel initially
       
    }

//main JFrame
    public static JFrame createGUIFrame(){
        JFrame f = new JFrame(); //declare window
        f.setSize(600, 500); //set window size
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when click x
        //f.getContentPane().setBackground(Color.BLACK); //to set bground color, need to get pane
        f.setVisible(true); //show window
        
       return f;
    }

    public JPanel startPanel(){
        JPanel panel = new JPanel();

        return panel; 
    }

    public static JPanel levelOnePanel(){
      card.show(panel, "Level One");
      /*Component levelOnePanel = new LevelTwoPanel(card, panel);
    //System.out.println("level two..");
      levelOnePanel.setFocusable(true);
      SwingUtilities.invokeLater(() -> {
        levelOnePanel.requestFocusInWindow();  // Request focus after panel is shown
    });*/
      return panel;  
    }

    public static JPanel levelTwoPanel(){
      card.show(panel, "Level Two");
     
      panel.setFocusable(true); 
      return panel; 
    }

    public static JPanel levelThreePanel(){
       card.show(panel, "Level Three");
       panel.setVisible(true);
      panel.revalidate();
      panel.repaint(); 
      panel.requestFocusInWindow();
      
      return panel; 
    }

    public static JPanel endingPanel(){
        card.show(panel, "End Panel"); 
        panel.revalidate();
        panel.repaint();
        panel.requestFocusInWindow();
        return panel; 
    }

    public static void close(){
      frame.dispose(); 
    }
    
}
