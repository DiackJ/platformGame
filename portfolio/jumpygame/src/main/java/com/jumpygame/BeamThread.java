package com.jumpygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BeamThread implements Runnable, ActionListener{
    LevelThreePanel panel;

    public BeamThread(LevelThreePanel panel){
        this.panel = panel; 
    }

    @Override
    public void run(){
        Timer timer = new Timer(15, new ActionListener() { //better to use swing timer instead of regular timer
            boolean goingUp = false;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (panel.beamHeight >= 160) {
                    goingUp = false;  // Change direction to down
                } else if (panel.beamHeight <= 110) {
                    goingUp = true;   // Change direction to up
                }

                // Adjust the beam height based on direction
                if (goingUp) {
                    panel.beamHeight++;
                } else {
                    panel.beamHeight--;
                }
            

                // Repaint the panel to reflect height changes
                panel.repaint();
            
            
            }
        });
    
    
        timer.start();  // Start the animation 
        
        
    
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

   
}
