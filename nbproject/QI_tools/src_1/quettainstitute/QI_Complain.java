/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Java
 */
public class QI_Complain {
    
    public void reportError(Object CLASS,Object method,Object ref,Exception exception){
        new Thread(new Runnable(){public void run(){
        //chars
        char tab=(char)9; 
        char enter=(char)10; 
        
        //messege
        String data="Start--------------------------------------------------------------"+enter; 
         data+=""+tab+"method"+tab+tab+"="+tab+method+enter;     
         data+=""+tab+"ref"+tab+tab+tab+"="+tab+ref+enter;     
         data+=""+tab+"message"+tab+tab+"="+tab+exception.getMessage()+enter;
         data+=enter; 
         data+=tab+"Exception "+enter+enter; 
         StackTraceElement[] ste=exception.getStackTrace(); 
         int l=ste.length; 
         for (int i = 0; i < l; i++) {
            data+=""+tab+tab+ste[i]+enter; 
        }
         
         
        data+="End"+enter+enter+enter+enter; 
    //    System.out.println(data);
         File f=new File("Err"); 
    
    if(!f.exists()){f.mkdir(); }
    
            System.out.println("Reported=------");
    
    
    try {
     File file=new File("Err\\"+CLASS+".err");    
     if(!file.exists()){
     file.createNewFile(); 
     }
    Files.write(Paths.get("Err\\"+CLASS+".err"), data.getBytes(), StandardOpenOption.APPEND);
}catch (IOException e) {
    //exception handling left as an exercise for the reader
}
    
    }}).start();
    }
    
    public void read(){
    
    try{
    FileInputStream fin=new FileInputStream("Err\\Sale.err"); 
    int c=0; 
    
    while(c!=-1){
        c=fin.read(); 
        System.out.println(c);
        
        
    }
    
    }catch(IOException e){
    
    
    }
    
    
}
}
