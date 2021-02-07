package quettainstitute;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
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
   
    
    
    
    
    
    
    
    
     static final int BUFFER = 2048;
     /**
      * De compress zip files. 
      * @param zip the file having zip extension
      */
   public void deCompress(String zip){
       System.out.println("zip address = "+zip);
      try {
         BufferedOutputStream dest = null;
         FileInputStream fis = new 
	   FileInputStream(zip);
         ZipInputStream zis = new 
	   ZipInputStream(new BufferedInputStream(fis));
         ZipEntry entry;
         while((entry = zis.getNextEntry()) != null) {
            System.out.println("Extracting: " +entry);
            int count;
            byte data[] = new byte[BUFFER];
            // write the files to the disk
            FileOutputStream fos = new 
	      FileOutputStream(entry.getName());
            dest = new 
              BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) 
              != -1) {
               dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
         }
         zis.close();
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
    
    
    public boolean unZip(String zipFile){
      
        String outputFolder=zipFile.replace(".zip", ""); 
//ZipOutputStream z; 
     byte[] buffer = new byte[1024];

     try{

    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();

    	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);

           //System.out.println("file unzip : "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	//System.out.println("Done");
return true; 
    }catch(IOException ex){
       ex.printStackTrace();
       return false; 
    }
   }
    
    
    
    
}
