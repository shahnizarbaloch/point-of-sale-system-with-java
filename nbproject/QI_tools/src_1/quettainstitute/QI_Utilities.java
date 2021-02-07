package quettainstitute;


import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */

public class QI_Utilities {
    public long startOfStopWtch=0;
    
    /**
     * just for debugging . to know the duration of some code process. 
     * @param m 0 to start 1 to end. 
     */
 public void stopWatch(int m){
 
 long duration=0; 
  
 long end=0; 
 switch(m){
     case 0:
         startOfStopWtch=duration=System.currentTimeMillis();
        
         break; 
     case 1: 
         end=System.currentTimeMillis();
         duration=end-startOfStopWtch; 
         JOptionPane.showMessageDialog(null,duration);
         
         break;     
 
 
 }
 
 
 
 }
 
 
}
