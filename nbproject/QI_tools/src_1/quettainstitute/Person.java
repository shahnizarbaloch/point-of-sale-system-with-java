/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Person {
    public Database dbl;
    public Address addressObj; 
    
    public int selectedPerson=-1; 
    
    public Person(Database db){
    dbl=db; 
    addressObj=new Address(dbl); 
    initializeComponents();
    }
    
    
    
    
    void initializeComponents(){
    nameField.setLayout(null);
   new QI_JTextField().validateThis(nameField,3);
    nameField.add(nameDropDown); 
    nameDropDown.setBounds(0, 25, 230, 0); nameDropDown.setEnabled(false);
    
    nameField.addFocusListener(
    new FocusListener(){
    public void focusGained(FocusEvent fe){
    nameField.selectAll();
    }
    public void focusLost(FocusEvent fe){
    nameDropDown.hidePopup();
    
    }
    }
    
    );
    
    
    
    
    //nameDropDown.setBounds(selectedPerson, selectedPerson, selectedPerson, selectedPerson);
    nameField.addKeyListener(new KeyListener(){
        int dropDownSelection=0; 
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){
    int kc=ke.getKeyCode(); 
            
                
            //     case 16: case 17: case 36: case 37: case 39: case 127:  break; 
            
                     
            
                     
            
                     
            
            
            
            
                //System.out.println("Key is "+kc);
            
            
            switch(kc){
                
                case 10: 
                
                          try{
                nameField.setText(nameDropDown.getSelectedItem().toString());
                selectedNameID=(int)nameDropDownIDs.get(nameDropDown.getSelectedIndex()); 
                    }catch(Exception e){}
                nameDropDown.hidePopup();
                    
                    
                    break; 
                
                 case 16: case 17: case 36: case 37: case 39: case 127:  break; 
                    
                case 38: //up arrow to select up
                    enableDropDown=true; 
                    dropDownSelection--; 
                    int all=nameDropDown.getItemCount(); 
                    if(dropDownSelection<0){dropDownSelection=all-1;}
                    
                    //System.out.println("Drop "+all+" = "+dropDownSelection);
                        nameDropDown.setSelectedIndex(dropDownSelection);
                   
                    
                  break; 
                case 40: 
                    enableDropDown=true; 
                    dropDownSelection++; 
                    int allE=nameDropDown.getItemCount(); 
                    if(dropDownSelection==allE){dropDownSelection=0;}
                        nameDropDown.setSelectedIndex(dropDownSelection);
                    
                    break;     
                
                default: 
                    
        //            ActionListener al=labelDropDown.getActionListeners();
                    //dropDownSelection=-1;  
                   // nameDropDown.removeActionListener(al);
                   // autoComplete(); 
                            if(enableAutoComplete){
                            //    autoCompleteName();
                                autoCompletePerson(); 
                            }
                            
                   // labelDropDown.addActionListener(al);
                    break; 
            
            }
            
            
            
            
            
    
    }
    public void keyTyped(KeyEvent ke){}
    });
    
    nameDropDown.addActionListener(al);

    
    
            
    CaretListener cl=new CaretListener(){
        String nameFieldOldValue=""; 
        String addressFieldOldValue=""; 
    
    public void caretUpdate(CaretEvent ce){
        if(ce.getSource()==nameField){
        if(!nameField.getText().equalsIgnoreCase(nameFieldOldValue)){
    selectedNameID=-1; 
        selectedPerson=-1; 
        
        }
    nameFieldOldValue=nameField.getText(); 
    }else{
    
        if(ce.getSource()==addressObj.addressField){

        if(!addressObj.addressField.getText().equalsIgnoreCase(addressFieldOldValue)){
    
        selectedPerson=-1; 
        
        }
    addressFieldOldValue=nameField.getText(); 
        
        }}
    }
    
    
    };
    
    
    nameField.addCaretListener(cl); 
    addressObj.addressField.addCaretListener(cl);
    
    
    }
    
    
    
    public boolean enableAutoComplete=true; 
    
    
    
    
    
 public JTextField nameField=new JTextField();
 
 JTextField addressField=new JTextField(); 
 JTextField contactField=new JTextField(); 
    /**
     * to submit the values
     * @return >0 successful -1 invalid name, -2 invalid address value 
     */
   public int submitUserInput(){
      String name=nameField.getText().trim(); 
   if(!name.isEmpty()){ 
    int nameID=createName(name); //create name
    
    if(nameID>0){   
    int addressID=addressObj.submitUserInput();   
   if(addressID>0){
   selectedPerson=registerPerson(nameID, addressObj.selectedAddress); 
   if(selectedPerson>0){
   return selectedPerson;  
   }
   }else{addressObj.addressField.requestFocus(); return -2; }
   }else{
    nameField.requestFocus(); return -1; 
   }
       
   }else{
       nameField.requestFocus();
   return -1;  
   }
   return selectedPerson; 
   } 
    
   
   public int registerPerson(int nameID, int addressID){
       
       if(nameID==-1 || addressID==-1)return -1;  
   
   
 
   //check if the person already exists. 
   int tr=getPersonID(nameID, addressID); 
   
   if(tr<0){
   /*
   String columnNames="name"; 
   String values=String.valueOf(nameID); 
   //if(addressID>0){
   columnNames+=",address"; 
   values+=","+String.valueOf(addressID); 
  // }
       */
   String query="INSERT INTO PERSON (NAME,ADDRESS) VALUES  ("+nameID+","+addressID+")"; 
   
  if(dbl.exicuteQury(query)){ 
   tr=getPersonID(nameID, addressID); 
  }else{
  return -1; 
  }
   }
   
   
   return tr; 
   }
   
   
   int registerPerson(String name, String address){
       if(name.isEmpty()){return -1; }
   int nameID=createName(name); 
   int addressID=addressObj.registerAddress(address); 
 
   //check if the person already exists. 
   int tr=getPersonID(nameID, addressID); 
   
   if(tr<0){
   
   String columnNames="name"; 
   String values=String.valueOf(nameID); 
   if(addressID>0){
   columnNames+=",address"; 
   values+=","+String.valueOf(addressID); 
   }
   String query="INSERT INTO PERSON ("+columnNames+") values ("+values+")"; 
   
  dbl.exicuteQury(query); 
   tr=getPersonID(name, address); 
  
   }
   
   
   return tr; 
   }
   /**
    * Gets the ID of a person
    * @param nameID   name id
    * @param addressID  address ID
    * @return 
    */
   
   
   int getPersonID(int nameID, int addressID){
   
   int tr=-1; 
   String condition="WHERE  NAME = "+nameID;
   if(addressID>0){
   condition+=" AND  ADDRESS = "+addressID;
   }else{
   condition+=" AND  ADDRESS IS NULL ";
   }
   String query="SELECT ID FROM PERSON "+condition; 
       System.out.println(query);
      try{
      tr=Integer.parseInt(dbl.get(query).toString()); 
      }catch(Exception e){}
       
    return tr;    
   } 
   
   
   
   
   
   /**
    * Gets the ID of a person
    * @param nameID   name id
    * @param addressID  address ID
    * @return 
    */
   
   
   int getPersonID(String name, String address){
    return getPersonID(getNameID(name),addressObj.getID(address));     
   } 
   
   
   
   
    
    /**
     * To register a new address
     * @param v the text of that address
     * @return returns the newly created address id or the id of that existing address id -1 if failed. 
     */
public  int createName(String v){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        String qyery ="INSERT INTO PERSON_NAME (NAME)   "
                + " (SELECT '"+v+"'    FROM PERSON_NAME    WHERE UPPER(NAME) like UPPER('"+v+"')     HAVING count(*)=0)"; 
                dbl.exicuteQury(qyery); 
    int id=getNameID(v); //check if already exists. 
    
  return id; 
    }
    






  public   int getNameID(String v){
    
     v=v.trim(); 
        
    try{
       
        return (int)dbl.get("SELECT ID FROM PERSON_NAME WHERE UPPER(NAME) LIKE UPPER('"+v+"') OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY"); 
    }catch(java.lang.ClassCastException e){
   return -1; 
    }
    
    
}
    
  
 public  String getAddress(int addressID){
  String tr=""; 
  tr=dbl.get("SELECT ADDRESS FROM ADDRESS WHERE ID="+addressID).toString(); 
  return tr; 
  }
  
   
   JComboBox<String> nameDropDown=new JComboBox<String>(); 
    Vector<Object> nameDropDownIDs=new Vector<Object>(); 
   JComboBox<String> personDropDown=new JComboBox<String>(); 
    Vector<Object> personDropDownIDs=new Vector<Object>(); 
    
    
    
    void autoCompletePerson(){
        enableDropDown=false; 
        String iv=nameField.getText(); 
        nameDropDown.removeAllItems();
        nameDropDown.hidePopup();
        if(iv.isEmpty()){return; }
        String query="SELECT PERSON_NAME.ID,ADDRESS.ID,PERSON_NAME.NAME,ADDRESS.ADDRESS "
                + "FROM PERSON_NAME"
                + " INNER JOIN PERSON ON PERSON.NAME=PERSON_NAME.ID"
                + " INNER JOIN ADDRESS ON PERSON.ADDRESS=ADDRESS.ID"
                + " WHERE UPPER(PERSON_NAME.NAME) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; 
        
        Object[][] data=dbl.getTable(query); 
            int l=data.length; 
            
            for (int i = 0; i < l; i++) {
                String v=data[i][2].toString().trim()+" "+data[i][3].toString(); 
                if(!nameField.getText().trim().equals(v)){
                nameDropDown.addItem(v);
                nameDropDownIDs.add(data[i][0]); 
                }
                
            }
    nameDropDown.showPopup();
    
    
    enableDropDown=true; 
    }
    
    /*
    
    void autoCompleteName(){
        enableDropDown=false; 
        String iv=nameField.getText(); 
        nameDropDown.removeAllItems();
        nameDropDown.hidePopup();
        if(iv.isEmpty()){return; }
        String query="SELECT PERSON_NAME.ID,  PERSON_NAME.NAME FROM PERSON_NAME WHERE UPPER(NAME) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; 
        
        Object[][] data=dbl.getTable(query); 
            int l=data.length; 
            
            for (int i = 0; i < l; i++) {
                String v=data[i][1].toString().trim(); 
                if(!nameField.getText().trim().equals(v)){
                nameDropDown.addItem(v);
                nameDropDownIDs.add(data[i][0]); 
                }
                
            }
    nameDropDown.showPopup();
    
    
    enableDropDown=true; 
    }
    
    
    */
    
    
    boolean enableDropDown=true; 
    public int selectedNameID=-1; 
        ActionListener al=new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            
                if(ae.getSource()==nameDropDown){
                    
                
                    if(enableDropDown){
                    try{
                nameField.setText(nameDropDown.getSelectedItem().toString());
                selectedNameID=(int)nameDropDownIDs.get(nameDropDown.getSelectedIndex()); 
                    }catch(Exception e){}
                    }
//                    System.out.println(focusedField+"] = ["+labelFieldID[focusedField]); 
                }}

       
        }; 
    
    
        
    
    
}
