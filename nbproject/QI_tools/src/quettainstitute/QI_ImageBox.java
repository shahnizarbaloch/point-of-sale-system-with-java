package quettainstitute;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_ImageBox  implements MouseListener{
    public void setEditAble(boolean editable){
    editAble=editable; 
    pictureCancel.setVisible(editable);
    }
    private boolean editAble=false; //to avoid modification if user don't have permission
    public JLabel bg=new JLabel(); 
    private JButton pictureCancel=new JButton(new ImageIcon("images\\product\\cancel.png")); 
    public String currentFolder=""; 
    int selectCount=0; 
    File[] selectFile=new File[10]; 
    private JLabel size=new JLabel();
    public Database dbl; 
    
    public boolean newProduct=true; //if true it will insert new product and if false then will edit some picture. 
        public QI_ImageBox(Database db){
            //JOptionPane.showMessageDialog(null, "");
            
            dbl=db; 
            bg.setIcon(new ImageIcon("images\\product\\pic.png"));
            bg.setLayout(null);
            bg.addMouseListener(this);
            bg.add(pictureCancel); 
            pictureCancel.addMouseListener(this);
            bg.add(size);  size.setSize(100, 25);
             
         } 
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseClicked(MouseEvent me){
        if(!editAble){return;  }
        
        if(newProduct){
        if(me.getSource()==bg){
    selectPicture(); }
        if(me.getSource()==pictureCancel){
        
           reset();           
        }
        
    
    }else{
    if(me.getSource()==bg){
    selectPictureToEdit();  }
        if(me.getSource()==pictureCancel){
        if(JOptionPane.showConfirmDialog(null, "Are you sure to delete picture of the selected product. ","confirmation",JOptionPane.YES_NO_OPTION)==0){; 
           deletePicture(viewingID, viewingSection); 
           reset(); }
        }


}}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    
      public void selectPicture() {
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpeg", "gif", "bmp");
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(new File(currentFolder));
        fc.showOpenDialog(bg);
        //System.out.println("select file is " + fc.getSelectedFile());
        if (fc.getSelectedFile() != null) {
            currentFolder=fc.getSelectedFile().getParent(); 
            if (fc.getSelectedFile().length() > 2000000) {
           pictureCancel.setBounds(0, 0, 0, 0);
           bg.setIcon(null);
                JOptionPane.showMessageDialog(null, "File is too large\n please select another one.");
                selectPicture();

            } else {
                
                viewImageThumbnail("" + fc.getSelectedFile()); 
                
                //ImageIcon ii=new ImageIcon("" + fc.getSelectedFile()); 
               // size.setText(ii.getIconWidth()+" x "+ii.getIconHeight());
               // size.setLocation(10, bg.getHeight()-25);
               
                /*if(ii.getIconHeight()>bg.getHeight() || ii.getIconWidth()>bg.getWidth()){
                Image im=getScaledImage(ii.getImage(),bg.getWidth(),bg.getHeight()); 
                ii.setImage(im);
                }
                */
                //bg.setIcon(ii); //new ImageIcon("" + fc.getSelectedFile()));
                selectFile[selectCount] = fc.getSelectedFile();
                //pictureCancel.setBounds(235, 5, 20, 20);
                currentFolder=selectFile[selectCount].getParent(); 
                selectCount++; 
                //eo.saveScaledImage((""+fc.getSelectedFile()), "D:\\a.jpg",300,280); 
            }

        }else{
         //   pictureLabel.setIcon(null);
           //     pictureCancel.setBounds(0, 0, 0, 0);
        }

    }
      
      
      
      public void selectPictureToEdit() {
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpeg", "gif", "bmp");
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(new File(currentFolder));
        fc.showOpenDialog(bg);
        //System.out.println("select file is " + fc.getSelectedFile());
        if (fc.getSelectedFile() != null) {
            currentFolder=fc.getSelectedFile().getParent(); 
            if (fc.getSelectedFile().length() > 2000000) {
           pictureCancel.setBounds(0, 0, 0, 0);
           bg.setIcon(null);
                JOptionPane.showMessageDialog(null, "File is too large\n please select another one.");
                selectPicture();

            } else {
                
                viewImageThumbnail("" + fc.getSelectedFile()); 
                replacePicture(fc.getSelectedFile(),viewingID,viewingSection); 
               
               // selectFile[selectCount] = fc.getSelectedFile();
                //pictureCancel.setBounds(235, 5, 20, 20);
               // currentFolder=selectFile[selectCount].getParent(); 
               // selectCount++; 
                //eo.saveScaledImage((""+fc.getSelectedFile()), "D:\\a.jpg",300,280); 
            }

        }else{
         //   pictureLabel.setIcon(null);
           //     pictureCancel.setBounds(0, 0, 0, 0);
        }

    }
      
      
      
      private Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
}
      
      /**
       * submits the picture
       * @param section
       * @param id
       * @return 
       */
      public boolean submit(int section, int id){
          if(selectCount==0)return true; 
          if(savePicture(selectFile[0],section,id)){
              reset(); 
     return true; }else{return false; } 
      
      }
      
      public boolean savePicture(File f, int id,int section){
          if(!f.exists()){return false; }
          String fileName=f.getName(); 
          if(fileName.length()>50){
          fileName=fileName.substring(fileName.length()-49); 
          }
          
          
          boolean tr=dbl.insert("INSERT INTO PICTURE (FORIEGN_ID,SECTION,FILENAME) VALUES (?,?,?)", new Object[]{id,section,fileName}); 
          if(tr){
          //now save picature data. 
              int picID=-1; //the id of saved picture
              try{
              picID=(int)dbl.getValue("SELECT MAX(ID) FROM PICTURE"); 
              }catch(Exception e){}
              if(picID>=0){
          byte[] b=new QI_File().getFileData(f); 
          int l=b.length; 
          int picked=0; 
          int start=0; 
          boolean keepItUp=true; 
              while(keepItUp){
              picked+=10000; 
              if(picked>=l){//if exceeds the limit the limitize it. 
              picked=l-1; 
              keepItUp=false; 
              }
              
              dbl.insert("INSERT INTO PICTURE_DATA (FORIEGN_ID,DATA) VALUES ("+picID+",?)",new Object[]{Arrays.copyOfRange(b, start, picked)}); 
              start=picked; 
              
              }
          }}else{
          
          tr=false; 
          }
         return tr; 
      }
      
      
      
      
      public boolean replacePicture(File f, int id,int section){
          
          
          if(!f.exists()){return false; }
          String fileName=f.getName(); 
          if(fileName.length()>50){
          fileName=fileName.substring(fileName.length()-49); 
          }
          
         boolean tr=false; 
         
          if(deletePicture(id, section)){
              
           tr=dbl.insert("INSERT INTO PICTURE (FORIEGN_ID,SECTION,FILENAME) VALUES (?,?,?)", new Object[]{id,section,fileName}); 
          if(tr){
          //now save picature data. 
              int picID=-1; //the id of saved picture
              try{
              picID=(int)dbl.getValue("SELECT MAX(ID) FROM PICTURE"); 
              }catch(Exception e){}
              if(picID>=0){
          byte[] b=new QI_File().getFileData(f); 
          int l=b.length; 
          int picked=0; 
          int start=0; 
          boolean keepItUp=true; 
              while(keepItUp){
              picked+=10000; 
              if(picked>=l){//if exceeds the limit the limitize it. 
              picked=l-1; 
              keepItUp=false; 
              }
              
              dbl.insert("INSERT INTO PICTURE_DATA (FORIEGN_ID,DATA) VALUES ("+picID+",?)",new Object[]{Arrays.copyOfRange(b, start, picked)}); 
              start=picked; 
              
              }
          }}else{
          
          tr=false; 
          }}
         return tr; 
      }
      
      boolean deletePicture(int id,int section){
          
boolean tr=true;       
          Object dataID=dbl.get("SELECT ID FROM PICTURE WHERE FORIEGN_ID="+id+" AND SECTION="+section); 
          try{
          int dataid=(int)dataID; 
          }catch(Exception e){
          return true; 
          }
          if(dbl.exicuteQury("DELETE FROM PICTURE_DATA WHERE FORIEGN_ID="+dataID)){
          
          if(dbl.exicuteQury("DELETE FROM PICTURE WHERE FORIEGN_ID="+id+" AND SECTION="+section)){
          tr=true; 
          }; 
          }
          
      return tr; 
      }
      
      
      public void reset(){
       selectCount=0; 
       bg.setIcon(new ImageIcon("images\\product\\pic.png"));
       pictureCancel.setBounds(0, 0, 0, 0);
       size.setText(""); 
      
      
      }
      //during showing the details to to store the section and product id. 
      int viewingSection=0; 
      int viewingID=0; 
      public  void display(int section, int id){
          viewingSection=section; 
          viewingID=id; 
          try{
          reset(); 
      String tempPath="D:\\"; 
      Object[][] obj=dbl.getTable("SELECT ID,FILENAME FROM PICTURE WHERE FORIEGN_ID = "+id+" AND SECTION = "+section); 
      tempPath+=obj[0][1].toString(); 
      File f=new File(tempPath); 
      if(f.exists()){f.delete(); }; 
      
      try{
      
          FileOutputStream fout=new FileOutputStream(f); 
      Object[] data=dbl.getFileData("SELECT DATA FROM PICTURE_data WHERE FORIEGN_ID = "+obj[0][0]);     
      int l=data.length;
      
          for (int i = 0; i < l; i++) {
       fout.write((byte[])data[i]);
          }
          
          System.out.println("Length ========= "+l);
      
      fout.close();
      }catch(IOException e){
      
      }
      
      viewImageThumbnail(tempPath); 
      
      //bg.setIcon(new ImageIcon(tempPath));
      
      
          }catch(Exception e){}
      
        
      
      
      }
      
      
      
      public void viewImageThumbnail(String path){
      //    System.out.println("path is = "+path);
          
                ImageIcon ii=new ImageIcon(path); 
                size.setText(ii.getIconWidth()+" x "+ii.getIconHeight());
                size.setLocation(10, bg.getHeight()-25);
                
                int h=bg.getHeight();                 
                int w=bg.getWidth(); 
                
                
                
                
                int nWidth=ii.getIconWidth(); 
                int nHeight=ii.getIconHeight(); 
                    System.out.println("Full Size = "+nWidth+" x "+nHeight);
                if(nHeight>h || nWidth>w){
                    
                    
                    if(nWidth>nHeight){
                 
                    if(nWidth>w){
                        int dif=nWidth-w; 
                        int percent=dif/(nWidth/100); 
                        nHeight=nHeight-((nHeight/100)*percent); 
                        nWidth=nWidth-dif; 
                        }
                    }else{
                        System.out.println(nHeight+" =======H========= "+h);
                        
                        if(nHeight>h){
                        int dif=nHeight-h; 
                            System.out.println("Dif is = "+dif);
                        int percent=dif/(nHeight/100); 
                            System.out.println("percent s = "+percent);
                        nWidth=nWidth-((nWidth/100)*percent); 
                            nHeight=nHeight-dif; 
                        }
                    }
                    
                    System.out.println("Edit size is ================== >> "+nWidth+" x "+nHeight);
                 
                    
                Image im=getScaledImage(ii.getImage(),nWidth,nHeight); 
                ii.setImage(im);
                }
                
      //          size.setSize(100, 25);
                
                bg.setIcon(ii); //new ImageIcon("" + fc.getSelectedFile()));
                pictureCancel.setBounds(235, 5, 20, 20);
      }
     public  void clearThumbnail(){
     
     
     
     } 
    
}
