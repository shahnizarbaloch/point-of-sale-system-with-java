/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Java
 */
public class Address {
    public boolean enableAutoComplete=true; 
    /**
     * 0 auto fill <br>
     * 1 drop down <br>
     * 2 single world drop down. 
     */
    int autoCompleteType=0; 
    
    
    
    Database dbl; 
    public JTextField addressField=new JTextField(); 
    public JTextField cityField=new JTextField(); 
    Address(Database db){
   dbl=db; 
    initializeComponents(); 
    
    }
    
    public int selectedaddressID=-1; 
     void initializeComponents(){
    addressField.setLayout(null);
    
    new QI_JTextField().validateThis(addressField,3);
    addressField.add(addressDropDown);  
    addressDropDown.setBounds(0, 25, 50, 0);
    addressDropDown.setEnabled(false);
    addressField.addFocusListener(
    new FocusListener(){
    public void focusGained(FocusEvent fe){
    addressField.selectAll();
    }
    public void focusLost(FocusEvent fe){
    addressDropDown.hidePopup();
    }
    }
    
    );
    
    addressField.addComponentListener(
    new ComponentListener(){
    public void componentHidden(ComponentEvent e) {
     
    }

    public void componentMoved(ComponentEvent e) {
     
    }

    public void componentResized(ComponentEvent e) {
     addressDropDown.setSize(addressField.getWidth(), 0);
    }

    public void componentShown(ComponentEvent e) {
     

    }
    
    }
    
    
    );
    
    
    //addressDropDown.setBounds(selectedPerson, selectedPerson, selectedPerson, selectedPerson);
    addressField.addKeyListener(new KeyListener(){
        int dropDownSelection=0; 
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){
    int kc=ke.getKeyCode(); 
            
                
            //     case 16: case 17: case 36: case 37: case 39: case 127:  break; 
            
                     
            
                     
            
                     
            
            
            
            
                //System.out.println("Key is "+kc);
            
            
            switch(kc){
                
                case 10: 
                
                          try{
                addressField.setText(addressDropDown.getSelectedItem().toString());
                selectedaddressID=(int)addressDropDownIDs.get(addressDropDown.getSelectedIndex()); 
                    }catch(Exception e){}
                addressDropDown.hidePopup();
                    
                    
                    break; 
                
                 case 16: case 17: case 36: case 37: case 39: case 127:  break; 
                    
                case 38: //up arrow to select up
                    enableDropDown=true; 
                    dropDownSelection--; 
                    int all=addressDropDown.getItemCount(); 
                    if(dropDownSelection<0){dropDownSelection=all-1;}
                    
                    //System.out.println("Drop "+all+" = "+dropDownSelection);
                        addressDropDown.setSelectedIndex(dropDownSelection);
                   
                    
                  break; 
                case 40: 
                    enableDropDown=true; 
                    dropDownSelection++; 
                    int allE=addressDropDown.getItemCount(); 
                    if(dropDownSelection==allE){dropDownSelection=0;}
                        addressDropDown.setSelectedIndex(dropDownSelection);
                    
                    break;     
                
                default: 
                    
        //            ActionListener al=labelDropDown.getActionListeners();
                    //dropDownSelection=-1;  
                   // nameDropDown.removeActionListener(al);
                   // autoComplete(); 
                    if(enableAutoComplete){
                             autoCompleteName();}
                   // labelDropDown.addActionListener(al);
                    break; 
            
            }
            
            
            
            
            
    
    }
    public void keyTyped(KeyEvent ke){}
    });
    
    addressDropDown.addActionListener(al);

    
    addressField.addCaretListener(new CaretListener(){
        String fieldOldValue=""; 
    public void caretUpdate(CaretEvent ce){
        if(!addressField.getText().equalsIgnoreCase(fieldOldValue)){
    selectedaddressID=-1; }
    fieldOldValue=addressField.getText(); 
    }
    });
    
    
    }
   
    
    
    int selectedAddress=-1; 
    
    public int submitUserInput(){
    
    String v=addressField.getText().trim(); 
    if(!v.isEmpty()){
    selectedAddress=registerAddress(v); 
    }else{
    return -1; 
    }
    return selectedAddress;  
    }
    
    
    /**
     * To register a new address
     * @param v the text of that address
     * @return returns the newly created address id or the id of that existing address id -1 if failed. 
     */
public  int registerAddress(String v){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        String qyery ="INSERT INTO ADDRESS (ADDRESS)   "
                + " (SELECT '"+v+"'    FROM ADDRESS    WHERE UPPER(ADDRESS) like UPPER('"+v+"')     HAVING count(*)=0)"; 
                dbl.exicuteQury(qyery); 
    int id=getID(v); //check if already exists. 
    
  return id; 
    }
    
  public   int getID(String v){
    
     v=v.trim(); 
        
    try{
       
        return (int)dbl.get("SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('"+v+"') OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY"); 
    }catch(java.lang.ClassCastException e){
   return -1; 
    }
    
    
}
    
  
 public  String getAddress(int addressID){
  String tr=""; 
  tr=dbl.get("SELECT ADDRESS FROM ADDRESS WHERE ID="+addressID).toString(); 
  return tr; 
  }
  
 
 
 JComboBox<String> addressDropDown=new JComboBox<String>(); 
 Vector<Object> addressDropDownIDs=new Vector<Object>(); 
   
 boolean enableDropDown=true; 
 void autoCompleteName(){
     
        enableDropDown=false; 
        String iv=addressField.getText(); 
        System.out.println("--------------------------"+iv);
        addressDropDown.removeAllItems();
        addressDropDown.hidePopup();
        if(iv.isEmpty()){return; }
        String query="SELECT ADDRESS.ID,  ADDRESS.ADDRESS FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; 
        
        Object[][] data=dbl.getTable(query); 
            int l=data.length; 
            
            for (int i = 0; i < l; i++) {
                String v=data[i][1].toString().trim(); 
                if(!addressField.getText().trim().equals(v)){
                addressDropDown.addItem(v);
                addressDropDownIDs.add(data[i][0]); 
                }
                
            }
    addressDropDown.showPopup();
    
    
    enableDropDown=true; 
    }
    
 
 ActionListener al=new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            
                if(ae.getSource()==addressDropDown){
                    
                
                    if(enableDropDown){
                    try{
                addressField.setText(addressDropDown.getSelectedItem().toString());
                selectedaddressID=(int)addressDropDownIDs.get(addressDropDown.getSelectedIndex()); 
                    }catch(Exception e){}
                    }
//                    System.out.println(focusedField+"] = ["+labelFieldID[focusedField]); 
                }}

       
        }; 
   
  
}
