package quettainstitute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_tools {
    QI_tools(){
        //Database dbl=new Database(); 
        //System.out.println(new Address(dbl).registerAddress(""));
       // System.out.println(new Address(dbl).getAddress(30));
       // System.out.println(new Person(dbl).getPersonID(" Aslam ", "ADD"));
        
         //System.out.println(new Person(dbl).registerPerson("Azeem khan", "sumalabad"));
       // double d=9.89899; 
        
        //System.out.println(x);  
        
       }
    
    
    void units(){
    Database dbl=new Database();  
    dbl.connectToRemoteDatabase("localhost", 1527); 
    //new Units(dbl).createUnit( 500, 4, 10, true,"Pack"); 
    Units un=new Units(dbl);
    un.loadUnits(24278);
       // System.out.println(un.convertPackageToUnit("CTN")); 
    
    }
    
    
    void personAndAddress(){
    JFrame f=new JFrame(); 
    f.setBounds(200,100,500,500); 
    Database dbl=new Database(); 
    dbl.connectToRemoteDatabase("localhost", 1527); 
    f.setVisible(true);
    f.setLayout(null);
    JPanel p=new JPanel(); 
    f.add(p); 
    f.setDefaultCloseOperation(3);
    p.setLayout(null);
    p.setSize(500, 500);
    
    Person personObj=new Person(dbl); 
    
    p.add(personObj.nameField); personObj.nameField.setBounds(100, 50, 200, 25);
    p.add(personObj.addressObj.addressField); personObj.addressObj.addressField.setBounds(100, 100, 200, 25);
    JButton ok=new JButton("Submit"); 
    p.add(ok); 
    ok.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
        System.out.println("Sumitted------------- "+personObj.submitUserInput()); 
    }});
    ok.setBounds(100, 150, 100, 25);
    
    p.updateUI();
    f.getRootPane().updateUI();
    
    
    
    
    
    }
    
    
    public void reportError(){
        try{"a".charAt(5); }catch(Exception e){
            new QI_Complain().reportError("Product", "Sale", "second", e);
        }
    
    }
    
    public static void main(String[] args){
    new QI_tools().reportError();
      //  new QI_tools().personAndAddress(); 
    //    new QI_tools().units(); 
//     QI_Cal qc=new QI_Cal(); 
      //  System.out.println(qc.dated("dd-mm-yyyy"));
    //    System.out.println(qc.dated());
        //System.out.println(qc.time("HH:mm"));
       // System.out.println(qc.getDaysBetween("17/07/2016", "17/08/2016"));
        //System.out.println(qc.nextDate(3));
        
        
        
        
    }
}
