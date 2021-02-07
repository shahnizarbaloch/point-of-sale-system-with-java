/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Java
 */
public class QI_Dialog {
    
    
    
    public int confirm(Component parent,String message,String title,Font font){
    JLabel jl=new JLabel(message); 
    jl.setFont(font);
    return JOptionPane.showConfirmDialog(parent, jl, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
    }
    
    public int error(Component parent,String message,String title,Font font){
    JLabel jl=new JLabel(message); 
    jl.setFont(font);
    return JOptionPane.showConfirmDialog(parent, jl, title,  JOptionPane.ERROR_MESSAGE); 
    }
    
    public int inform(Component parent,String message,String title, Font font){
    JLabel jl=new JLabel(message); 
    jl.setFont(font);
    return JOptionPane.showConfirmDialog(parent, jl, title, JOptionPane.INFORMATION_MESSAGE); 
    }
    
    
}
