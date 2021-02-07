/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.AWTException;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.sql.Connection;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Java
 */
public class Language {
    private String languageName=""; 
    private int languageID=0; 
    
    Database dbl; 
    // Construcot
    public Language(Connection conn){
    dbl=new Database(conn); 
    
    }
    
    
    
    
    
    //Object[] sentence; 
    public Properties sentence=new Properties(); 
    public String fontFace=""; 
    public String keyBoard=""; 
    public int textAlign=2; 
    public void load(String lang,int startSentence){
        languageID=getLanguageID(lang); 
        languageName=lang; 
        Object[][] td=dbl.getTable("SELECT id,"+lang+" FROM APP.LABELS WHERE ID>="+startSentence+" AND ID<"+(startSentence+100)+" ORDER BY ID ASC"); 
        int l=td.length; 
        for (int i = 0; i < l; i++) {
        sentence.setProperty(""+td[i][0], td[i][1]+"");     
        }
        Object[][] td2=dbl.getTable("SELECT FONT,KEYBOARD,TEXT_ALIGN FROM APP.LANGUAGE WHERE ID="+languageID); 
       fontFace=td2[0][0].toString();
       keyBoard=td2[0][1].toString();
       textAlign=td2[0][2].toString().equalsIgnoreCase("left")?JTextField.LEFT:JTextField.RIGHT;
    }
    
    
    /**
     * to fetch the font size Like font_size1
     * @param fontNo integer of no 1 meant font_size1 so on. 
     * @return 
     */
    public Font getFont(int fontNo){
       Font f=new Font("Arial",0,1); 
       try{
     return new Font(fontFace,0,(int)dbl.get("SELECT FONT_SIZE"+fontNo+" FROM APP.LANGUAGE WHERE ID="+languageID)); 
       }catch(Exception e){
       e.printStackTrace();
       }
       return f; 
    }
    
    
    
    
    public void setLanguage(String language){
        languageID=getLanguageID(language); 
        if(languageID>0){
        languageName=language; 
        }else{
        language=""; 
        }
    
    }
    
    
    
    
    
    

public int getLanguageID(String language){
    
return (int)dbl.get("SELECT ID FROM APP.LANGUAGE WHERE LANGUAGE = '"+language+"'"); 
}

public void switchKeyBoard(boolean defaultKeyboard){
if(defaultKeyboard){
    switchKeyboard("English",0);
        }else{
    switchKeyboard(languageName,0);
} 


}
  




//int rounds=0; 
String fromLanguage=""; 
    public void switchKeyboard(String languageName,int round){
      //  Locale locale = Locale.getDefault();
        
        //System.out.println(Locale.getDefault());
          
         
         
             
             InputContext is = InputContext.getInstance();
         String currentLanguage=is.getLocale().getDisplayLanguage(); 
       if(fromLanguage.equalsIgnoreCase(currentLanguage) || currentLanguage.equalsIgnoreCase(languageName) ){
       fromLanguage=""; 
       return; 
       }
        System.out.println(round+"==================="+currentLanguage+" ====== "+languageName);
       
       
           if(round==0){
           fromLanguage=currentLanguage; 
           }
         
         
            
           if(!languageName.equalsIgnoreCase(currentLanguage)){    
             
    Robot robot;
    try {
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_ALT);

        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_ALT);
        try{
        
        Thread.sleep(100);
        }catch(InterruptedException e){}
        
        //if(rounds==4){rounds=0; return; }
        //rounds++; 
        
        switchKeyboard(languageName,round); 
    } catch (AWTException e1) {
        e1.printStackTrace();
    }
    
    }
  
         
   
    
    }
    



}
