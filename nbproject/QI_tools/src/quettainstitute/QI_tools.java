package quettainstitute;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    Person personObj=new Person(dbl,dbl.connection); 
    
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
    
    
    public void viewPersonPanel(){
    JFrame f=new JFrame(); 
    f.setBounds(200, 100, 600, 400);
    f.setVisible(true);
    f.setLayout(new BorderLayout());
    f.setDefaultCloseOperation(3);
    JPanel jp=new JPanel(); 
    f.add(jp,BorderLayout.CENTER); 
    jp.setBackground(Color.WHITE);
    
    //connect to the Database; 
    Database db=new Database(); 
    Database dblSettings=new Database(); 
    
    db.connectToRemoteDatabase("192.168.1.4", 7000); 
    dblSettings.connectToRemoteDatabase("192.168.1.4", 7001,"SettingsDB"); 
    //dblSettings.connectToAnyDatabase("E:\\Projects\\Java\\ABM\\Data\\Settings\\SettingsDB","azeem","azeem");
    Person personObj=new Person(db,dblSettings.connection); 
    
    
    jp.setLayout(null);
    
    JLabel[] jl={
    new JLabel("Name"), 
    new JLabel("Address"),
    new JLabel("Citry"),
    new JLabel("Code"),
    new JLabel("Phone")
    }; 
    
    jp.add(jl[3]); jl[3].setBounds(100, 50, 100, 25);
    jp.add(personObj.codeField); personObj.codeField.setBounds(200, 50, 200, 25);
  
    jp.add(jl[0]); jl[0].setBounds(100, 100, 100, 25);
    jp.add(personObj.nameField); personObj.nameField.setBounds(200, 100, 200, 25);
    
    jp.add(jl[1]); jl[1].setBounds(100, 140, 100, 25);
    jp.add(personObj.addressObj.addressField); personObj.addressObj.addressField.setBounds(200, 140, 200, 25);
    
    jp.add(jl[2]); jl[2].setBounds(100, 180, 100, 25);
    jp.add(personObj.cityObj.cityField); personObj.cityObj.cityField.setBounds(200, 180, 200, 25);
   
   jp.add(jl[4]); jl[4].setBounds(100, 220, 100, 25);
    jp.add(personObj.phoneField); personObj.phoneField.setBounds(200, 220, 200, 25);
   
    JButton jb=new JButton("Submit"); 
    jp.add(jb); 
    jb.setBounds(200, 255, 120, 25);
    jb.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
    JOptionPane.showMessageDialog(null,personObj.submitUserInput()); 
    }});
    
    
    }
     public void viewEditPanel(){
    JFrame f=new JFrame(); 
    f.setBounds(200, 100, 600, 400);
    f.setVisible(true);
    f.setLayout(new BorderLayout());
    f.setDefaultCloseOperation(3);
    JPanel jp=new JPanel(); 
    f.add(jp,BorderLayout.CENTER); 
    jp.setBackground(Color.WHITE);
    
    //connect to the Database; 
    Database db=new Database(); 
    Database dblSettings=new Database(); 
    
    String url="localhost"; 
    db.connectToRemoteDatabase(url, 7000); 
    dblSettings.connectToRemoteDatabase(url, 7001,"SettingsDB"); 
    //dblSettings.connectToAnyDatabase("E:\\Projects\\Java\\ABM\\Data\\Settings\\SettingsDB","azeem","azeem");
    Person personObj=new Person(db,dblSettings.connection); 
    
    
    jp.setLayout(null);
    
    JLabel[] jl={
    new JLabel("Edit"), 
    }; 
    
    
  Edit_Person field=new Edit_Person(Edit_Person.Editing_Address,db,1987); 
  field.setKeyBoard("English");
    jp.add(jl[0]); jl[0].setBounds(100, 100, 100, 25);
    jp.add(field); field.setBounds(200, 100, 200, 25);
    
    
    JButton jb=new JButton("Submit"); 
    jp.add(jb); 
    jb.setBounds(200, 255, 120, 25);
    jb.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
    JOptionPane.showMessageDialog(null,personObj.submitUserInput()); 
    }});
    
    
    }
    
     public void viewQI_JTextField(){
     JFrame frame=new JFrame(); 
     frame.setVisible(true);
     frame.setLayout(null);
     JTextField field=new JTextField(); 
     frame.add(field); field.setBounds(200, 200, 200, 25);
     frame.setBounds(200, 200, 600, 500);
     frame.setDefaultCloseOperation(3);
     new QI_JTextField().validateThis(field, QI_JTextField.STRING_VALIDATION);
     
     
     }
     
     
    
    public static void main(String[] args){
        //new QI_tools().viewPersonPanel(); 
        //new QI_tools().viewEditPanel();
        new QI_tools().viewQI_JTextField(); 
    //new QI_tools().reportError();
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
