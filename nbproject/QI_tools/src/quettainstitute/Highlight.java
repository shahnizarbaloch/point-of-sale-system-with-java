/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.Color;
import java.awt.Component;

/**
 *
 * @author Java
 */
public class Highlight {
    
    public void hightLightError(Component comp,Color color){
    new Thread(new Runnable(){public void run(){
        Color bc=comp.getBackground(); 
        
        //for (int i = 0; i < 4; i++) {
            comp.setBackground(color);
            try{
            Thread.sleep(1000);
            }catch(Exception e){}
            comp.setBackground(bc);
        //}
    
    }}).start();
    
    }
    
}
