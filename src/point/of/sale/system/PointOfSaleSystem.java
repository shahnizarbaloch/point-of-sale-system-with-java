/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point.of.sale.system;

import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Anonymous
 */
public class PointOfSaleSystem {
    
    
    
    PointOfSaleSystem(){
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //Hover is the method of actions which is being called here in constructor because it will be called just once 
            //Hover();
        } 
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {
        }
        
    }
    
    

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //Here i am calling the Design class which contains all the design of the software
            Design mainObj = new Design();
//            ImageIcon frameicon = new ImageIcon("src\\images\\frame_Icon.png");
//            mainObj.setIconImage(frameicon.getImage());
            //making it visible
            mainObj.setVisible(true);
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
            
        }
        
    }
    
}
