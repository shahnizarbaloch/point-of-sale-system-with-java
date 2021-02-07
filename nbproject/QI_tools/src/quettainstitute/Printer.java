package quettainstitute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Azeem
 */
public class Printer {
    public Printer(){
    
    
    
    
    
    }
    
    public  void print3(final Component comp, float scale,boolean landScap) {
    final float SCALE = scale;
    PrinterJob job = PrinterJob.getPrinterJob();
  
     PrintRequestAttributeSet atr = new HashPrintRequestAttributeSet();
     
            if(landScap)atr.add(OrientationRequested.LANDSCAPE); 
//PrinterResolution pr = new PrinterResolution(400, 900, PrinterResolution.DPI);
           //   atr.add(pr);
           
        
  //  PageFormat pf = job.pageDialog(atr);
//    P//aper paper=pf.getPaper(); 
    //paper.setImageableArea(3.0, 3.0, 0.85, 0.85);
 //   alert("- "+paper.getImageableX()+"- "+paper.getImageableX()+"- "+paper.getImageableY()+"- "+paper.getImageableWidth()+"- "+paper.getImageableHeight()); 
    
    
    job.setPrintable(new Printable() {
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException
             {
                 //Paper p=new Paper(); 
                // p.setImageableArea(15, 15, 700, 480);
                 
               //  pf.setPaper(p);
                 
            if (page * pf.getImageableHeight() >= SCALE * comp.getHeight())
                
                return NO_SUCH_PAGE;
            
            
            ((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY()
            
               - page * pf.getImageableHeight());
            ((Graphics2D)g).scale(SCALE, SCALE);
            comp.printAll(g);
            return PAGE_EXISTS;
        }
    });
    if (job.printDialog())
        
        try {
            
            
            
        
       job.print(atr);
            
        }
        catch (PrinterException ex) {}
}
  
    
    public  void print(final Component comp, float scale,boolean landScap) {
    final float SCALE = scale;
    PrinterJob job = PrinterJob.getPrinterJob();
  
     PrintRequestAttributeSet atr = new HashPrintRequestAttributeSet();
     
            if(landScap)atr.add(OrientationRequested.LANDSCAPE); 
            
//PrinterResolution pr = new PrinterResolution(400, 900, PrinterResolution.DPI);
           //   atr.add(pr);
           
        
  //  PageFormat pf = job.pageDialog(atr);
//    P//aper paper=pf.getPaper(); 
    //paper.setImageableArea(3.0, 3.0, 0.85, 0.85);
 //   alert("- "+paper.getImageableX()+"- "+paper.getImageableX()+"- "+paper.getImageableY()+"- "+paper.getImageableWidth()+"- "+paper.getImageableHeight()); 
    
    
    job.setPrintable(new Printable() {
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException
             {
                 //Paper p=new Paper(); 
                // p.setImageableArea(15, 15, 700, 480);
                 
               //  pf.setPaper(p);
                 
            if (page * pf.getImageableHeight() >= SCALE * comp.getHeight())
                
                return NO_SUCH_PAGE;
            
            
            ((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY()
            
               - page * pf.getImageableHeight());
            //((Graphics2D)g).scale(SCALE, SCALE);
            ((Graphics2D)g).scale(SCALE, SCALE);
            
                 
            comp.printAll(g);
            return PAGE_EXISTS;
        }
    });
    if (job.printDialog())
        
        try {
            
            
            
        
       job.print(atr);
            
        }
        catch (PrinterException ex) {}
}
  
  
    
    
      public  void print(final Component comp, float scale,boolean landScap,PrinterJob job) {
    final float SCALE = scale;
     
  
     PrintRequestAttributeSet atr = new HashPrintRequestAttributeSet();
     //aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
            if(landScap)atr.add(OrientationRequested.LANDSCAPE);
    
    job.setPrintable(new Printable() {
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException
             {
                 
                 
            if (page * pf.getImageableHeight() >= SCALE * comp.getHeight())
                
                return NO_SUCH_PAGE;
            
            
            ((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY()
            
               - page * pf.getImageableHeight());
            //((Graphics2D)g).scale(SCALE, SCALE);
            ((Graphics2D)g).scale(SCALE, SCALE);
            
                 //jl.setPreferredSize(new Dimension(ii.getIconWidth(),ii.getIconHeight()));
            
            
            
            
           // comp.validate();
           // comp.doLayout();
//           comp.paint(g);
            //paint(g); 
            comp.printAll(g);
            return PAGE_EXISTS;
        }
    });
    
        
        try {
            
            
            
        
       job.print(atr);
            
        }
        catch (PrinterException ex) {}
}
  
    
    
    
    
    
    int I=0; 
   public  void printMultiple(final Component[] comp, float scale,boolean landScap) {
    final float SCALE = scale;
    PrinterJob job = PrinterJob.getPrinterJob();
  
     PrintRequestAttributeSet atr = new HashPrintRequestAttributeSet();
     
            if(landScap)atr.add(OrientationRequested.LANDSCAPE); 
              if (!job.printDialog())return; 
//PrinterResolution pr = new PrinterResolution(400, 900, PrinterResolution.DPI);
           //   atr.add(pr);
           
        
  //  PageFormat pf = job.pageDialog(atr);
//    P//aper paper=pf.getPaper(); 
    //paper.setImageableArea(3.0, 3.0, 0.85, 0.85);
 //   alert("- "+paper.getImageableX()+"- "+paper.getImageableX()+"- "+paper.getImageableY()+"- "+paper.getImageableWidth()+"- "+paper.getImageableHeight()); 
    
       for (int i = 0; i < comp.length; i++) {
           I=i; 
       
    job.setPrintable(new Printable() {
         
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException
             {
                 
                
                 
                 //Paper p=new Paper(); 
                // p.setImageableArea(15, 15, 700, 480);
                 
               //  pf.setPaper(p);
                 
                 
                     
                 
                 if (page * pf.getImageableHeight() >= SCALE * comp[I].getHeight())
                
                return NO_SUCH_PAGE;
            
            
            ((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY()
            
               - page * pf.getImageableHeight());
            ((Graphics2D)g).scale(SCALE, SCALE);
            comp[I].printAll(g);
                 
            return PAGE_EXISTS;
        }
    });
    
   

        try {
       job.print(atr);
        }
        catch (PrinterException ex) {}}
}
    
    
    JPanel jp=new JPanel(); 
    JLabel lb=new JLabel("Quetta institute 1234567890"); 
    public void printThisTable(JTable table){
    lb.setFont(new Font("Arial",0,11));
   // JOptionPane.showMessageDialog(null, "Now println");
    
    JFrame f=new JFrame(); 
    
    f.setLayout(null);
   f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setUndecorated(false);
    f.setVisible(true);
    JScrollPane jsp=new JScrollPane(jp); 
   f.add(jsp);
    f.setBounds(0, 0, 1024, 750); 
    jp.setBorder(BorderFactory.createLineBorder(Color.black));
    jsp.setBounds(100, 0, 900, 600); 
    //jp.setPreferredSize(new Dimension(800,1000));
    jp.setSize(520, 730);
   jp.setPreferredSize(new Dimension(520,730));
    
    jp.setBackground(Color.white);
    jp.setLayout(null);
    jp.add(lb); lb.setBounds(10, 10, 500, 200); lb.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
    
    //table.getColumnModel().get
    int l=table.getRowCount(); 
    //String[] th=
            
            
    
    //print(jp, 0.9f, false);
    }
    
    
}
