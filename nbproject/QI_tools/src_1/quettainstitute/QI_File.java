package quettainstitute;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_File {
     static String VIRSION="1.0"; 
    
    
    /**
     * it reads the text the given file. 
     * @param fileName the file to read from
     * @return the data of file. 
     */
    public String getFileText(String fileName){
    try{
          FileInputStream fin=new FileInputStream(fileName);
          byte[] b=new byte[fin.available()]; 
          fin.read(b); 
          return new String(b); 
      }catch(IOException e){}
    return ""; 
                }
    
    /**
     * save the text into the specified file. 
     * @param fileName full address and the name of file. 
     * @param txt data to be saved into file. 
     * @return true if successful
     */
    public boolean saveTxt(String fileName,String txt){
        try{
            FileOutputStream fout=new FileOutputStream(fileName); 
            fout.write(txt.getBytes());
           }catch(IOException e){
            return false;
           }
        return true; 
}
    
    public byte[] getFileData(File f){
    
    try{
    FileInputStream fin=new FileInputStream(f); 
    byte[] b=new byte[fin.available()]; 
    fin.read(b);
    fin.close();
    return b; 
    }catch(IOException e){}
    return new byte[0]; 
    }
    
    
}
