/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputContext;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author QI PC9
 */
public class Edit_Person extends JTextField implements ActionListener,FocusListener{
public static int Editing_Code=1; 
public static int Editing_Name=2; 
public static int Editing_Address=3; 
public static int Editing_City=4; 
public static int Editing_Phone=5; 
public  Thread finishingThread; 
private Database dbl; 

int currentlyEditing=0; 
int currentSection=0; 
private JComboBox<String> dropDown=new JComboBox<String>(); 
private Vector<Integer> dropDownIDs=new Vector<Integer>(); 
int selectedID=-1; 
int currentPersonID=-1; 
public String messageBox=""; 


/**
 * 
 * @param editingType  if 1 then it is code type 2=name 3=address 4=city 5=phone
 * @param db    Database Object for user data
 * @param personID id of that person whom attributes are being edited. 
 */
    public Edit_Person(int editingType,Database db,int personID){
        currentlyEditing=editingType; 
        dbl=db; 
        currentPersonID=personID; 
        //add validation to the field. 
        switch(editingType){
            case 1: new QI_JTextField().validateThis(this, QI_JTextField.INT_VALIDATION); break; 
            case 2: new QI_JTextField().validateThis(this, QI_JTextField.WORD_CASE_VALIDATION); break;     
            case 3: new QI_JTextField().validateThis(this, QI_JTextField.STRING_VALIDATION); break;
            case 4: 
            case 5: new QI_JTextField().validateThis(this, QI_JTextField.WORD_CASE_VALIDATION); break;    
        }
        
        
    this.setLayout(null);
    this.add(dropDown);
    this.addFocusListener(this);
    this.addActionListener(this);
    dropDown.addActionListener(this);
    this.addKeyListener(
            new KeyListener(){
            public void keyPressed(KeyEvent ke){}
            public void keyReleased(KeyEvent ke){
                int kc=ke.getKeyCode(); 
                switch(kc){
                case 10: 
                          try{
                selectedID=(int)dropDownIDs.get(dropDown.getSelectedIndex()); 
                    }catch(Exception e){}
                dropDown.hidePopup();
                    
                    
                    break; 
                
                 case 16: case 17: case 36: case 37: case 39: case 127:  break; 
                    
                case 38: //up arrow to select up
                    enableDropDown=true; 
                    dropDownSelection--; 
                    int all=dropDown.getItemCount(); 
                    if(dropDownSelection<0){dropDownSelection=all-1;}
                        dropDown.setSelectedIndex(dropDownSelection);
                  break; 
                case 40: 
                    enableDropDown=true; 
                    dropDownSelection++; 
                    int allE=dropDown.getItemCount(); 
                    if(dropDownSelection==allE){dropDownSelection=0;}
                        dropDown.setSelectedIndex(dropDownSelection);
                    
                    break;     
                
                default: 
                    
                       if(enableAutoComplete){
                           
                           if(currentlyEditing>1 && currentlyEditing<5){
                               new Thread(new Runnable(){public void run(){
                                   try{
                                autoCompletePerson(); 
                                }catch(Exception e){e.printStackTrace();}
                               }}).start();
                               }
                            }
                    break; 
            
            }
            
            
            
            
            
    
    }
            public void keyTyped(KeyEvent ke){}
            });
    
    }
    public boolean enableAutoComplete=true; 
    private boolean enableDropDown=false; 
    private int dropDownSelection=0;
    private  boolean autoCompleteIsRunning=false;
    private boolean autoCompleteRunAgain=false; 
    private boolean enableDropdownAction=true; 
    void autoCompletePerson(){
        
       enableDropdownAction=false; 
        enableDropDown=false; 
        dropDownSelection=0; 
        String iv=this.getText().trim(); 
        dropDownIDs.removeAllElements();
        dropDown.removeAllItems();
        if(iv.isEmpty()){return; }
        
        String query=""; 
        switch(currentlyEditing){
            case 2: query="SELECT ID,NAME FROM PERSON_NAME WHERE UPPER(PERSON_NAME.NAME) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; break; 
            case 3: query="SELECT ID,ADDRESS FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; break; 
            case 4: query="SELECT ID,CITY FROM CITY WHERE UPPER(CITY) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; break; 
        
        }
        
        /*
        String query="SELECT PERSON_NAME.ID,ADDRESS.ID,PERSON_NAME.NAME,ADDRESS.ADDRESS,PERSON.ID,coalesce(CITY.CITY,''),PERSON.CODE "
                + "FROM PERSON_NAME"
                + " INNER JOIN PERSON ON PERSON.NAME=PERSON_NAME.ID"
                + " INNER JOIN ADDRESS ON PERSON.ADDRESS=ADDRESS.ID"
                + " LEFT JOIN CITY ON PERSON.CITY=CITY.ID"
                + " WHERE UPPER(PERSON_NAME.NAME) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; 
        */
        dropDown.hidePopup();
        
        Object[][] data=dbl.getTable(query); 
        System.out.print("<");
            int l=data.length; 
            
            for (int i = 0; i < l; i++) {
                String v=data[i][1].toString().trim(); 
                if(!this.getText().trim().equals(v)){
                dropDown.addItem(v);
                dropDownIDs.add((int)data[i][0]); 
                }
                
            }
           System.out.print(">");
            if(dropDown.getItemCount()>0){
    dropDown.showPopup();
    enableDropdownAction=true; 
            }
           
       
    
    enableDropDown=true; 
    
    
    autoCompleteIsRunning=false; 
    
    
    
    
        
    
    }

public void setBounds(int x,int y,int width,int height){
super.setBounds(x, y, width, height);
dropDown.setBounds(0, height, width, 0);
} 




public void actionPerformed(ActionEvent ae){
    
    if(ae.getSource()==dropDown){
        if(enableDropdownAction){
this.setText(dropDown.getItemAt(dropDownSelection));
        }
    }
    
    if(ae.getSource()==this){
        
    submit(); 
    finishingThread.start();
    }
    
}; 
@Override
public void focusGained(FocusEvent fe){
if(fe.getSource()==this){
    System.out.println("Key Board is = "+keyBoard);
 switchKeyboard(keyBoard,0);
 
}
}
@Override
public void focusLost(FocusEvent fe){}


boolean submit(){
    
    boolean tr=false; 
    String txt=this.getText().trim(); 
    if(txt.isEmpty()){return false; }
    
   
    switch(currentlyEditing){
        case 1: tr=submit_code(txt); break; 
        case 2: tr=submit_name(txt); break; 
        case 3: tr=submit_address(txt); break; 
        case 4: tr=submit_city(txt); break; 
        case 5: tr=submit_phone(txt); break; 
    
    }

return tr;  
}


boolean submit_code(String v){
    
    //get current code of the person under consideration. 
    String currentCode=""; 
    try{
    currentCode=dbl.get("SELECT CODE FROM PERSON WHERE ID="+currentPersonID).toString(); 
    }catch(Exception e){}
  if(currentCode.equalsIgnoreCase(v)){return true; }
  
  //check if another person is having the same code. 
  
  try{
      if(((int)dbl.get("SELECT COUNT(ID) FROM PERSON WHERE CODE="+v+" AND ID<>"+currentPersonID))>0){
          messageBox="Another man is also having the same code. "; 
          
      return false; 
      } 
  }catch(Exception e){
  e.printStackTrace();
  } 
  
  //now change the code. 
  boolean b=dbl.exicuteQury("UPDATE PERSON SET CODE="+v+" WHERE ID="+currentPersonID); 
  if(b){
  //if has ledger account
     
     try{
     dbl.exicuteQury("UPDATE LEDGER SET ACCOUNT_NO="+v+" WHERE PERSON="+currentPersonID); 
     
     }catch(Exception e){}
  }
return b; 
}
boolean submit_name(String v){
    //get current name of the person under consideration. 
    
    int currentNameID=-1; 
    try{
    currentNameID=(int)dbl.get("SELECT NAME FROM PERSON WHERE ID="+currentPersonID); 
    }catch(Exception e){}
       
    //check new text ID if exists int name table. 
    
    int newNameID=-1; 
    try{
    newNameID=(int)dbl.get("SELECT ID FROM PERSON_NAME WHERE UPPER(PERSON_NAME.NAME) LIKE UPPER('%"+v+"%')"); 
    }catch(Exception e){}
    if(newNameID>=0){
    if(currentNameID==newNameID)return true; 
      }else{
    newNameID=createName(v); 
    }
    if(newNameID>=0){
    return dbl.exicuteQury("UPDATE PERSON SET NAME="+newNameID+" WHERE ID = "+currentPersonID);  
    }
   return false; 
}
boolean submit_address(String v){
    //get current name of the person under consideration. 
    
    int currentAddressID=-1; 
    try{
    currentAddressID=(int)dbl.get("SELECT ADDRESS FROM PERSON WHERE ID="+currentPersonID); 
    }catch(Exception e){}
       
    //check new text ID if exists int name table. 
    
    int newAddressID=-1; 
    try{
    newAddressID=(int)dbl.get("SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('%"+v+"%')"); 
    }catch(Exception e){}
    if(newAddressID>=0){
    if(currentAddressID==newAddressID)return true; 
      }else{
    newAddressID=registerAddress(v); 
    }
    if(newAddressID>=0){
    return dbl.exicuteQury("UPDATE PERSON SET ADDRESS="+newAddressID+" WHERE ID = "+currentPersonID);  
    }
   return false; 
}




boolean submit_city(String v){
    //get current name of the person under consideration. 
    
    int currentCityID=-1; 
    try{
    currentCityID=(int)dbl.get("SELECT CITY FROM PERSON WHERE ID="+currentPersonID); 
    }catch(Exception e){}
       
    //check new text ID if exists int name table. 
    
    int newCityID=-1; 
    try{
    newCityID=(int)dbl.get("SELECT ID FROM CITY WHERE UPPER(CITY) LIKE UPPER('%"+v+"%')"); 
    }catch(Exception e){}
    if(newCityID>=0){
    if(currentCityID==newCityID)return true; 
      }else{
    newCityID=registerCity(v); 
    }
    if(newCityID>=0){
    return dbl.exicuteQury("UPDATE PERSON SET CITY="+newCityID+" WHERE ID = "+currentPersonID);  
    }
   return false; 
}





boolean submit_phone(String v){
    
    try{
    int c=(int)dbl.get("SELECT COUNT(*) FROM PHONE WHERE SECTION="+currentSection+" AND REF="+currentPersonID); 
    if(c>0){
         return dbl.exicuteQury("UPDATE  PHONE SET PHONE='"+v+"'  WHERE SECTION="+currentSection+" AND REF="+currentPersonID); 
    }else{
         return dbl.exicuteQury("INSERT INTO PHONE  (SECTION,REF,PHONE) VALUES ("+currentSection+","+currentPersonID+",'"+v+"')"); 
    }
    }catch(Exception e){
            e.printStackTrace();
    }
   
   
    
   return false; 
}

public void setSection(int section){
currentSection=section; 
}
private String keyBoard="English"; 
public void setKeyBoard(String keyBoardName){
    keyBoard=keyBoardName; 
}

private String fromLanguage=""; 
    public void switchKeyboard(String languageName,int round){
             InputContext is = InputContext.getInstance();
         String currentLanguage=is.getLocale().getDisplayLanguage(); 
       if(fromLanguage.equalsIgnoreCase(currentLanguage) || currentLanguage.equalsIgnoreCase(languageName) ){
       fromLanguage=""; 
       return; 
       }
           if(round==0){
           fromLanguage=currentLanguage; 
           }
            
           if(!languageName.equalsIgnoreCase(currentLanguage)){    
             
    Robot robot;
    try {
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_ALT);

        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_ALT);
        try{
        
        Thread.sleep(100);
        }catch(InterruptedException e){}
        
        switchKeyboard(languageName,round+1); 
    } catch (AWTException e1) {
        e1.printStackTrace();
    }
    
    }
  
         
   
    
    }
    
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
    }}
     
     
     public  int registerAddress(String v){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        String qyery ="INSERT INTO ADDRESS (ADDRESS)   "
                + " (SELECT '"+v+"'    FROM ADDRESS    WHERE UPPER(ADDRESS) like UPPER('"+v+"')     HAVING count(*)=0)"; 
                dbl.exicuteQury(qyery); 
    int id=getAddressID(v); //check if already exists. 
         return id; 
    }

      public   int getAddressID(String v){
    
     v=v.trim(); 
        
    try{
       
        return (int)dbl.get("SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('"+v+"') OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY"); 
    }catch(java.lang.ClassCastException e){
   return -1; 
    }
    
    
}

public  int registerCity(String v){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        String qyery ="INSERT INTO CITY (CITY)   "
                + " (SELECT '"+v+"'    FROM CITY    WHERE UPPER(CITY) like UPPER('"+v+"')     HAVING count(*)=0)"; 
                dbl.exicuteQury(qyery); 
    int id=getID(v); //check if already exists. 
    
  return id; 
    }
    
  public   int getID(String v){
    
     v=v.trim(); 
        
    try{
       
        return (int)dbl.get("SELECT ID FROM CITY WHERE UPPER(CITY) LIKE UPPER('"+v+"') OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY"); 
    }catch(java.lang.ClassCastException e){
   return -1; 
    }
    
    
}
    
  
  

}