/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author Java
 */
public class QI_JTextField {
    public static int INT_VALIDATION=0; 
    public static int DOUBLE_VALIDATION=1; 
    public static int STRING_VALIDATION=2; 
    /**
     * WORD_CASE_VALIDATION=3
     */
    public static int WORD_CASE_VALIDATION=3;
    
    public void validateThis(JTextField jtf, int validationType){
    jtf.addFocusListener(
            new FocusListener(){
                public void focusGained(FocusEvent fe){((JTextField)fe.getSource()).selectAll();}
                public void focusLost(FocusEvent fe){
                }});
        
        KeyListener kl= new KeyListener(){
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){
        String v=jtf.getText(); 
        if(v.isEmpty()){return; }
        int kc=ke.getKeyCode(); 
        if(kc<=40){return;  }
        switch(validationType){
            case 0: 
                try{
                if(Integer.parseInt(v)<0){
                    jtf.setText("");
                } 
                }catch(NumberFormatException e){
                jtf.setText("");
                }
                break; 
       
                
           case 1: 
               if(v.endsWith(".")){
               v+="0"; 
               }
                try{
                Double.parseDouble(v); 
                }catch(NumberFormatException e){
                jtf.setText("");
                }
                break; 
               
            case 2: 
               QI_Str qiStr=new QI_Str(); 
                v=qiStr.removeIlligalChars(v); 
                v=qiStr.removeSQL(v); 
                v=qiStr.sentenceCase(v); 
                jtf.setText(v);
                break;    
               
            case 3: 
               QI_Str qiStr2=new QI_Str(); 
                v=qiStr2.removeIlligalChars(v); 
                v=qiStr2.removeSQL(v); 
                v=qiStr2.sentenceCase(v); 
                v=qiStr2.wordCase(v); 
                jtf.setText(v);
                break;    
               
               
            }
        
        
        
        

        
        
    }
    public void keyTyped(KeyEvent ke){}
    
    };
        
jtf.addKeyListener(kl);
    }
    
    
    
    
    
        
    
    
    
    
}
