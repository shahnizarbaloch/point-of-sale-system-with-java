/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Java
 */
public class QI_JTable {
    public void setColumSize(JTable table,int tableWidth,int[] width){
        table.setSize(tableWidth, table.getHeight());
    setColumSize(table,width); 
    
    }
    public void setColumSize(JTable table,int[] width){
        int w=table.getWidth(); 
        if(w<=0){
        w=table.getParent().getWidth();
        }
        int aw=0; 
        for (int i = 0; i < width.length; i++) {
            aw+=width[i]; 
        }
        
        System.out.println(w+"=========="+aw);
        
    try{
    int l=width.length; 
        for (int i = 0; i < l; i++) {
            double wi=(double)((double)w/100)*(((double)width[i]/(double)aw)*100); 
            System.out.println("===>>===="+wi);
            table.getColumnModel().getColumn(i).setPreferredWidth((int)wi);
        }
    }catch(Exception e){}
    }
    
    /*
    public void setColumSize(JTable table,int[] width){
    try{
    int l=width.length; 
        for (int i = 0; i < l; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }
    }catch(Exception e){}
    }
    */
    
    
    
    /**
     * 
     * @param table table to be aligned
     * @param align an array of alignment values 0=center, left=2, right=4 ;  
     */
    public void setColumAlign(JTable table,int[] align){
    try{
    int l=align.length; 
        for (int i = 0; i < l; i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(align[i]);
        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }       
    }catch(Exception e){
    
    }
    }
}