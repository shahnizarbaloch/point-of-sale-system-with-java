/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.Color;

/**
 * for main class
 * @author Azeem Mengal
 */
public abstract class QI_Abstract_Class {
    public abstract String getFrameTitle();
    public abstract void setFrameTitle(String title);
    public abstract void setCurrentScreen(String department,String ref); 
    public abstract void doAct(String action); 
    public abstract void noteException(String department,String className,String method ,Exception e);  
    public abstract void showInStatus(String header,String msg,Color color); 
}
