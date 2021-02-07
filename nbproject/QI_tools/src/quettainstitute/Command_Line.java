/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Java
 */
public class Command_Line {
    Connection connection; 
    public Command_Line(Connection conn ){
    connection=conn;  
    }
    JLabel responseLabel=new JLabel();     
   public void showQueryUI(){
     
        
    
    JPanel topPanel=new JPanel(); 
    JFrame f=new JFrame("QI SQL Command Shell"); 
    f.setBounds(300,100, 500, 500);
    f.setLayout(new BorderLayout());
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setVisible(true);
    JTextArea jta=new JTextArea(); 
    JScrollPane jsp=new JScrollPane(jta); 
    
    JPanel background=new JPanel(); 
    
    background.setLayout(new BorderLayout());
    f.add(background,BorderLayout.CENTER);  
    
    background.add(topPanel,BorderLayout.NORTH); 
    background.add(jsp,BorderLayout.CENTER); 
    background.add(responseLabel,BorderLayout.SOUTH); 
    
    JButton b=new JButton("Run"); 
    topPanel.setLayout(null); topPanel.setPreferredSize(new Dimension(600,40));
     responseLabel.setPreferredSize(new Dimension(600,40));
    topPanel.add(b); b.setBounds(100, 10, 150, 25);
    b.addActionListener(
            new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
    exicuteCommand(jta.getText());
                }});
    }
    
    void exicuteCommand(String q){
        if(q.isEmpty())return; 
        try{
        Statement statement=connection.createStatement(); 
        statement.execute(q); 
        responseLabel.setText("Exicuted Successfully");
        responseLabel.setForeground(Color.black);
        }catch(Exception e){
        responseLabel.setText("Error : "+e.getMessage());
        responseLabel.setForeground(Color.red);
        }
    
    
    }
    
    
    
    
}
