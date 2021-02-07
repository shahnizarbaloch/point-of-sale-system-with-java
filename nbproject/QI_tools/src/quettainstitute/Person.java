/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    public City cityObj; 
    public int selectedPerson=-1; 
    public JTextField codeField= new JTextField(); 
    public JTextField phoneField=new JTextField();
    
    public boolean justEditing=false; 
    public int current_person_is=-1; 
    public boolean done=false; 
    
   
    
    
    public int nextNewCode=0;
     public void getNextNewCode(){
         
         System.out.println("=======================================");
         
         if(nextNewCode==0){
         
    new Thread(new Runnable(){public void run(){
    try{
     int nextNewCode=(int)dbl.get("SELECT MIN(CODE)+1 FROM PERSON WHERE code>0 and CODE+1 NOT IN (SELECT CODE FROM PERSON) ");  
    codeField.setText(""+nextNewCode); 
    }catch(Exception e){}
}}).start();    
         }else{
    codeField.setText(""+nextNewCode);      
         
         }
    }
    public Language languageObj; 
    public Person(Database db,Connection settingsConn){
    
        languageObj=new Language(settingsConn);  
    

    dbl=db; 
        setLanguage(); 
        
    addressObj=new Address(dbl); 
    cityObj=new City(dbl); 
    initializeComponents();
    enableFields(true);
    resetFields();
    
    
     
    }
    
    
    public void setTextAlign(int align){
    nameField.setHorizontalAlignment(align);
    addressObj.addressField.setHorizontalAlignment(align);
    cityObj.cityField.setHorizontalAlignment(align);
    ((JLabel)nameDropDown.getRenderer()).setHorizontalAlignment(align);
    }
    
    
    private void  setLanguage(){
    String language=dbl.get("SELECT VALUE FROM SETTINGS WHERE SECTION='PERSON_NAME' AND NAME = 'Language'").toString(); 
    if(language.isEmpty()){
    language="English"; 
    }
    
        languageObj.setLanguage(language);
        languageObj.load(language, 0);
        System.out.println("Language is >>>>>>=================>"+language);
    
    }
    
    
    int dropDownSelection=0;
    void initializeComponents(){
        
        
        
        
    nameField.setLayout(null);
    
    nameField.addKeyListener(new AutoCodeFinder());
    addressObj.addressField.addKeyListener(new AutoCodeFinder());
    cityObj.cityField.addKeyListener(new AutoCodeFinder());
    
   new QI_JTextField().validateThis(nameField,3);
   
   if(languageObj.textAlign==JTextField.LEFT){
    nameField.add(nameDropDown); 
    addressObj.addressField.setFont(new Font(languageObj.fontFace,0,16));
    nameField.setFont(new Font(languageObj.fontFace,0,16));
    cityObj.cityField.setFont(new Font(languageObj.fontFace,0,16));
    nameDropDown.setFont(new Font(languageObj.fontFace,0,16));
   
   }else{
   
    nameField.add(nameDropDown); 
    addressObj.addressField.setFont(new Font(languageObj.fontFace,0,18));
    nameField.setFont(new Font(languageObj.fontFace,0,18));
    cityObj.cityField.setFont(new Font(languageObj.fontFace,0,18));
    nameDropDown.setFont(new Font(languageObj.fontFace,0,18));
    
   }
    
    nameField.addFocusListener(fl);
    addressObj.addressField.addFocusListener(fl);
    cityObj.cityField.addFocusListener(fl);
    
    
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
    new QI_JTextField().validateThis(codeField, QI_JTextField.INT_VALIDATION);
    
    
    /*
    codeField.addKeyListener(new KeyListener(){
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){
    fillFieldsFromCode();
    
    }
    public void keyTyped(KeyEvent ke){
    
    }
    });
    */
    codeField.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent ae){
               
                 String cd=codeField.getText(); 
               resetFields();
               codeField.setText(cd);
               
                    fillFieldsFromCode();            
                    
                    if(selectedPerson==-1){
         try{nextNewCode=Integer.parseInt(cd);          System.out.println("=====>>>>>>>>==============="); }catch(Exception e){}
        nameField.setEnabled(true);
        addressObj.addressField.setEnabled(true);
        cityObj.cityField.setEnabled(true);
        
        nameField.requestFocus();
        
                    }
                
                
                }
            });
    
    
    
    
    
    
    
    codeField.addFocusListener(fl);
    
    //nameDropDown.setBounds(selectedPerson, selectedPerson, selectedPerson, selectedPerson);
    nameField.addKeyListener(new KeyListener(){
         
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){
    int kc=ke.getKeyCode(); 
                
            switch(kc){
                
                case 10: 
                
                          try{
                //nameField.setText(nameDropDown.getSelectedItem().toString());
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
                    try{
                        nameDropDown.setSelectedIndex(dropDownSelection);
                    }catch(Exception e){}
                    
                    break;     
                
                default: 
                    
        //            ActionListener al=labelDropDown.getActionListeners();
                    //dropDownSelection=-1;  
                   // nameDropDown.removeActionListener(al);
                   // autoComplete(); 
                            if(enableAutoComplete){
                            //    autoCompleteName();
                                getNextNewCode();
                                autoCompletePerson(); 
                            }
                            
                   // labelDropDown.addActionListener(al);
                    break; 
            
            }
            
            
            
            
            
    
    }
    public void keyTyped(KeyEvent ke){
    
        if(ke.getKeyCode()==10){
    getNextNewCode();
        }
    
    }
    });
    
    nameDropDown.addActionListener(al);

    
    
            
    CaretListener cl=new CaretListener(){
        String nameFieldOldValue=""; 
        String addressFieldOldValue=""; 
    
    public void caretUpdate(CaretEvent ce){
        
        if(beingReseted)return;
        if(codeField.getText().isEmpty()){
        enableFields(true);
        }
        if(ce.getSource()==nameField){
        if(!nameField.getText().equalsIgnoreCase(nameFieldOldValue)){
    selectedNameID=-1; 
        selectedPerson=-1; 
        
        }
    nameFieldOldValue=nameField.getText(); 
    }
    
        if(ce.getSource()==addressObj.addressField){

        if(!addressObj.addressField.getText().equalsIgnoreCase(addressFieldOldValue)){
    
        selectedPerson=-1; 
        
        }
    addressFieldOldValue=nameField.getText(); 
        
        }
        
       // findPersonCode();
        
    }
    
    
    };
    
    
    nameField.addCaretListener(cl); 
    addressObj.addressField.addCaretListener(cl);
    cityObj.cityField.addCaretListener(cl); 
    
    
    
    codeField.addActionListener(al);
    
    
    
    }
    
    
    public void setDropDownSize(int width){
    
    nameDropDown.setBounds(0, 25, width, 0);
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
            String na=nameField.getText(); 
            String ad=addressObj.addressField.getText(); 
            String ci=cityObj.cityField.getText();
            String cd=codeField.getText(); 
            String ph=phoneField.getText(); 
            //if(cd.isEmpty())return -1;  

            
            
            
            if(na.isEmpty() || ad.isEmpty()){return -1; }
            
       //interpret  inputs into code
       int[] inputID= interpretInputsIntoIDs(na,ad,ci); 
       boolean mayBeExist=inputID[0]>=0 && inputID[1]>=0 && inputID[2]>=0; 
       int perID=-1; 
       
       if(!mayBeExist){
          
           inputID[0]=inputID[0]<0?createName(na):inputID[0]; 
           inputID[1]=inputID[1]<0?addressObj.registerAddress(ad):inputID[1]; 
          
          if(!ci.isEmpty()){
          
              inputID[2]=inputID[2]<0?cityObj.registerCity(ci):inputID[2];
          
          //check if exits with city
            perID=getPersonID(inputID[0], inputID[1], inputID[2]); 
          
            
                    if(perID==-1){//now create it. 
                    perID=registerPerson(inputID[0], inputID[1],inputID[2]); 
          
                    }

          } else{
          
              //check if exits with city. 
            perID=getPersonID(inputID[0], inputID[1]); 
          
                if(perID==-1){//now create it. 
                    
                    if(getPersonID(cd)!=-1){
                        getNextNewCode();
                        return -1; 
                    }
                    perID=registerPerson(inputID[0], inputID[1]); 
          
                  }

          }
       }else{//it may not be exist so there for just create it. 
      
       perID=getPersonID(inputID[0], inputID[1],inputID[2]); 
       if(perID==-1){
         perID=registerPerson(inputID[0], inputID[1],inputID[2]); 
       }
      }
       
       
       if(perID>=0){
           selectedPerson=perID; 
       setCodeForPerson(selectedPerson,cd); 
       addPhone(ph,perID); 
       }
       
               
      
      
       
       if(true)return perID; 
       
       if(selectedPerson>-1)return selectedPerson; 
       int ID=getPersonID(nameField.getText(), addressObj.addressField.getText(),cityObj.cityField.getText()); 
       if(ID>0)return ID; 
       String code=codeField.getText(); 
       if(!code.isEmpty()){
           try{
       int c=(int)dbl.get("SELECT count(ID) FROM PERSON WHERE CODE = "+code); 
       if(c>0){
       return -1; 
       }
           }catch(Exception e){}
       }else{
       return -1; 
       }
      String name=nameField.getText().trim(); 
   if(!name.isEmpty()){ 
    int nameID=createName(name); //create name
    
    if(nameID>0){   
    int addressID=addressObj.submitUserInput();   
   if(addressID>0){
        String city=cityObj.cityField.getText().trim(); 
       if(!city.isEmpty()){ 
       int cityID=cityObj.submitUserInput(); 
      selectedPerson=registerPerson(nameID, addressObj.selectedAddress,cityID);    
       }else{     
   selectedPerson=registerPerson(nameID, addressObj.selectedAddress); }
       resetFields();
       
   if(selectedPerson>0){
       setCodeForPerson(selectedPerson,code); 
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
    setCodeForPerson(selectedPerson,code); 
   return selectedPerson; 
   } 
    
   
   
   /*
   
   
SELECT ID FROM PERSON WHERE 
NAME=( SELECT ID FROM PERSON_NAME WHERE UPPER(NAME) LIKE UPPER('kashif')) AND 
ADDRESS=( SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('KOCHLAK')) AND 
CITY = ( SELECT ID FROM CITY WHERE UPPER(CITY) LIKE UPPER('PASHIN'))
;
   
   SELECT 
(SELECT ID FROM PERSON_NAME WHERE UPPER(NAME) LIKE UPPER('kashif')) AS NAME,
( SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('KOCHLAK')) AS ADDRESS,
( SELECT ID FROM CITY WHERE UPPER(CITY) LIKE UPPER('ZIARAT')) AS CITY
 FROM BILL OFFSET 0 ROW FETCH NEXT 1 ROW ONLY ; 
   
   */
  /*
   void getPersonID(String name,String address,String city){
   //sTRING
   
   }*/
   
   int[] interpretInputsIntoIDs(String n,String a,String c){
   int[] tr={-1,-1,-1}; 
   
   Object[][] td=dbl.getTable("SELECT " +
"(SELECT ID FROM PERSON_NAME WHERE UPPER(NAME) LIKE UPPER('"+n+"')) AS NAME, " +
"( SELECT ID FROM ADDRESS WHERE UPPER(ADDRESS) LIKE UPPER('"+a+"')) AS ADDRESS, " +
"( SELECT ID FROM CITY WHERE UPPER(CITY) LIKE UPPER('"+c+"')) AS CITY " +
" FROM BILL OFFSET 0 ROW FETCH NEXT 1 ROW ONLY "); 
   try{
   if(td[0][0]!=null){tr[0]=(int)td[0][0]; }
   if(td[0][1]!=null){tr[1]=(int)td[0][1]; }
   if(td[0][2]!=null){tr[2]=(int)td[0][2]; }
   }catch(Exception  e){}
   
   
   return tr; 
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
   
   
   
   public int registerPerson(int nameID, int addressID,int cityID){
       
       if(nameID==-1 || addressID==-1 || cityID==-1)return -1;  
   
   
 
   //check if the person already exists. 
   int tr=getPersonID(nameID, addressID, cityID); 
   
   if(tr<0){
   
   String query="INSERT INTO PERSON (NAME,ADDRESS,CITY) VALUES  ("+nameID+","+addressID+","+cityID+")"; 
   
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
   
   
   
   
   
   
   
   int getPersonID(int nameID, int addressID,int cityID){
   
   int tr=-1; 
   String condition="WHERE  NAME = "+nameID;
   if(addressID>0){
   condition+=" AND  ADDRESS = "+addressID;
   }else{
   condition+=" AND  ADDRESS IS NULL ";
   }
   
   if(cityID>0){
   condition+=" AND  city = "+cityID;
   }else{
   condition+=" AND  city IS NULL ";
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
   
  public int getPersonID(String name, String address,String city){
    return getPersonID(getNameID(name),addressObj.getID(address),cityObj.getID(city));     
   } 
  /**
   * Filed fields 
   * this method gets the id of person about whom fields are filled. 
   * atleast name and address field should be filled.
   * if city is also filled then it will also compare the city to find the person. 
   * @return 
   */
  public int getPersonID(){
      String n=nameField.getText().trim(); 
      String a=addressObj.addressField.getText().trim(); 
      String c=cityObj.cityField.getText().trim(); 
      if(!n.isEmpty() && !a.isEmpty()){
          if(c.isEmpty()){
    return getPersonID(n,a);     
          }else{
    return getPersonID(n,a,c);           
          }
      }
      return -1; 
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
    
public  int addPhone(String v,int id){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        
        String qyery ="INSERT INTO PHONE  (SECTION,REF,PHONE) VALUES (1,"+id+",'"+v+"')"; 
                dbl.exicuteQury(qyery); 
    
    
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
  
   
   public JComboBox<String> nameDropDown=new JComboBox<String>(); 
    Vector<Object> nameDropDownIDs=new Vector<Object>(); 
    
  
    JComboBox<String> personDropDown=new JComboBox<String>(); 
    Vector<Object> personDropDownIDs=new Vector<Object>(); 
    Vector<String> addressLabel=new Vector<String>(); 
    Vector<String> nameLabel=new Vector<String>(); 
    Vector<String> cityLabel=new Vector<String>(); 
    Vector<String> personCodes=new Vector<String>(); 
    
    
    
    boolean autoCompleteIsRunning=false;
    boolean autoCompleteRunAgain=false; 
    void autoCompletePerson(){
       
       
       
         
        
        enableDropDown=false; 
        dropDownSelection=0; 
        String iv=nameField.getText(); 
        nameDropDown.removeAllItems();
        nameDropDown.hidePopup();
        nameLabel.removeAllElements();
        cityLabel.removeAllElements();
        addressLabel.removeAllElements();
        nameDropDownIDs.removeAllElements();
        personDropDownIDs.removeAllElements();
        personCodes.removeAllElements();
        if(iv.isEmpty()){return; }
        String query="SELECT PERSON_NAME.ID,ADDRESS.ID,PERSON_NAME.NAME,ADDRESS.ADDRESS,PERSON.ID,coalesce(CITY.CITY,''),PERSON.CODE "
                + "FROM PERSON_NAME"
                + " INNER JOIN PERSON ON PERSON.NAME=PERSON_NAME.ID"
                + " INNER JOIN ADDRESS ON PERSON.ADDRESS=ADDRESS.ID"
                + " LEFT JOIN CITY ON PERSON.CITY=CITY.ID"
                + " WHERE DELETED=FALSE AND UPPER(PERSON_NAME.NAME) LIKE UPPER('%"+iv+"%') OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY"; 
        
        Object[][] data=dbl.getTable(query); 
        int l=data.length; 
            
        if(l==0){
        codeField.setText(""+nextNewCode);
        return; 
        }    
            
            for (int i = 0; i < l; i++) {
                
                try{
                if(((int)data[i][6])==-1){data[i][6]=""; }
                }catch(Exception e){}
                
                String v=data[i][2].toString().trim()+" "+data[i][3].toString().trim()+" "+data[i][5].toString().trim()+" "+data[i][6].toString().trim(); 
                if(!nameField.getText().trim().equals(v)){
                    cityLabel.add(data[i][5].toString()); 
                    personCodes.add(data[i][6].toString()); 
                    addressLabel.add(data[i][3].toString()); 
                    personDropDownIDs.add(data[i][4]); 
                    
                    nameLabel.add(data[i][2].toString()); 
                nameDropDown.addItem(v);
                nameDropDownIDs.add(data[i][0]); 
                }
                
            }
          //  try{
            nameDropDown.setBounds(0, nameField.getHeight(), nameField.getWidth(), 0);
    nameDropDown.showPopup();
           // }catch(Exception e){}
       
    
    enableDropDown=true; 
    
    
    autoCompleteIsRunning=false; 
    
    
    
    
        
    
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
                        int index=nameDropDown.getSelectedIndex(); 
                codeField.setText(""+personCodes.get(index).toString()); if(codeField.getText().equalsIgnoreCase("-1")){codeField.setText("");  codeField.setEnabled(true);}
                nameField.setText(nameLabel.get(index).toString());
                addressObj.addressField.setText(addressLabel.get(index).toString());
                selectedNameID=(int)nameDropDownIDs.get(index); 
                cityObj.cityField.setText(cityLabel.get(index).toString());
                selectedPerson=(int)personDropDownIDs.get(index); 
                        System.out.println("===========>>>><<<<====================="+selectedPerson);
                fillPhoneField(selectedPerson); 
                    }catch(Exception e){
                    e.printStackTrace();
                    }
                    }
//                    System.out.println(focusedField+"] = ["+labelFieldID[focusedField]); 
                }
        if(ae.getSource()==codeField){
            if(justEditing){
                String code=codeField.getText();
                 done=false; 
                try{
                if((int)dbl.get("SELECT COUNT(ID) FROM PERSON WHERE CODE = "+code+" AND ID <> "+current_person_is)>0){
                JOptionPane.showMessageDialog(null, "Another exists");
                return; 
                }else{
                   
                done=dbl.exicuteQury("UPDATE PERSON SET CODE="+code+" WHERE ID="+current_person_is);                 } 
                }catch(Exception e){}
                
                
                
                //JOptionPane.showMessageDialog(null, "Editing. "+current_person_is);
            }else{
                fillFieldsFromCode();} 
        
        }
        
        if(ae.getSource()==nameField){
       // JOptionPane.showMessageDialog(null, "xyz");
        }
        
        
        }
        

       
        }; 
    
    
    FocusListener fl=new FocusListener(){
    public void focusGained(FocusEvent fe){
    

        
    languageObj.switchKeyBoard(false);
    ((JTextField)fe.getSource()).selectAll();
    
    if(fe.getSource()==codeField){
        nameField.setEnabled(false);
        addressObj.addressField.setEnabled(false);
        cityObj.cityField.setEnabled(false);
        
    if(codeField.getText().isEmpty()){
        
        getNextNewCode();
        
    
    }
    
    }
    
    
    }
    public void focusLost(FocusEvent fe){
        
    
    }
    };     
    
    
    boolean beingReseted=false; 
    public void resetFields(){
        beingReseted=true; 
    nameField.setText("");
    addressObj.addressField.setText("");
    cityObj.cityField.setText(""); 
    codeField.setText("");
    phoneField.setText("");
    beingReseted=false; 
    }
    
    public void fillFieldsFromCode(){
     if(codeField.getText().isEmpty()){ enableFields(true);  return; }
    if(codeField.isVisible()){
        if(codeField.getText().isEmpty())return; 
        String query="SELECT PERSON.ID,PERSON_NAME.NAME,ADDRESS.ADDRESS,coalesce(CITY.CITY,'') " +
"                FROM PERSON_NAME " +
"                 INNER JOIN PERSON ON PERSON.NAME=PERSON_NAME.ID " +
"                 INNER JOIN ADDRESS ON PERSON.ADDRESS=ADDRESS.ID " +
"                 LEFT JOIN CITY ON PERSON.CITY=CITY.ID " +
" WHERE PERSON.CODE= "+codeField.getText(); 
        Object[][] td=dbl.getTable(query); 
        if(td.length>0){
            beingReseted=true; 
        nameField.setText(td[0][1].toString());
        addressObj.addressField.setText(td[0][2].toString());
        cityObj.cityField.setText(td[0][3].toString());
          //  System.out.println("========>> >>==============="+td[0][0]);
        selectedPerson=Integer.parseInt(td[0][0].toString()); 
        //System.out.println(selectedPerson+"========>> >>==============="+td[0][0]);
        fillPhoneField(selectedPerson); 
          beingReseted=false; 
        enableFields(false);
        }else{
        enableFields(true); 
        }
        
        
     
        
    
    }
    
    }
     boolean disableCaretListener=false; 
    void enableFields(boolean en){
    nameField.setEnabled(en);
    addressObj.addressField.setEnabled(en);
    cityObj.cityField.setEnabled(en);
    codeField.setEnabled(true);
    }
    /**
    to find the person Code while typing in name address or city. 
    * if not found then it presents the recommended value. 
    */
  void findPersonCode(){
      String n=nameField.getText(); 
      String a=addressObj.addressField.getText(); 
      String c=cityObj.cityField.getText();
     
      
      int nID=-1; 
      int aID=-1; 
      int cID=-1; 
      int pID=-1; 
      if(!n.isEmpty()){  //find name
      nID=getNameID(n); 
            if(nID>-1){   

            if(!a.isEmpty()){
                //find address
            aID=addressObj.getID(a); 
         
            if(aID>-1){
                //find city
                
                if(!c.isEmpty()){
                    cID=cityObj.getID(c); 
                if(cID>-1){
                      pID=getPersonID(nID, aID, cID); 
                    
                }
                }
                
                    
            }    }
            
            }
      
      
      }
      
      
      if(pID>-1){
                   // JOptionPane.showMessageDialog(null, pID);
                       selectedPerson=pID;   
                      //  codeField.setEnabled(false);
                        codeField.setText(dbl.get("SELECT CODE FROM PERSON WHERE ID="+pID).toString());
                        if(codeField.getText().equalsIgnoreCase("-1")){codeField.setText(""); codeField.setEnabled(true);}
                    }else{
      
      codeField.setEnabled(true);
      /*if(codeField.getText().isEmpty()){
      try{
      codeField.setText(dbl.get("SELECT MAX(CODE+1) FROM PERSON").toString()); 
      if(codeField.getText().equalsIgnoreCase("0")){codeField.setText("1");  codeField.setEnabled(true);}
      }catch(Exception e){
         e.printStackTrace();
      }
             }*/
      selectedPerson=-1;
      }
  
  
  }
     void setCodeForPerson(int id,String code){
         if(code.isEmpty())return; 
    dbl.exicuteQury("UPDATE PERSON SET CODE="+code+" WHERE ID="+id); 
    }
    
    
  public   void setVisible(boolean visible){
    codeField.setVisible(visible);
    nameField.setVisible(visible);
    addressObj.addressField.setVisible(visible);
    cityObj.cityField.setVisible(visible);
    phoneField.setVisible(visible);
    }
  
  public void  fillPhoneField(int id){
  new Thread(new Runnable(){
      public void run(){
          String ph=""; 
          try{
          ph=dbl.get("SELECT PHONE FROM PHONE WHERE  REF="+id).toString(); 
          }catch(Exception e){}
  phoneField.setText(ph);    
      }
  
  
  }).start();
  
  
  }
  
  public void setTextAlignment(int alignment){
  codeField.setHorizontalAlignment(alignment);
  nameField.setHorizontalAlignment(alignment);
  phoneField.setHorizontalAlignment(alignment);
  addressObj.addressField.setHorizontalAlignment(alignment);
  cityObj.cityField.setHorizontalAlignment(alignment);
  }
  /**
   * To get the id of some person by providing the code. 
   * @param code code of person
   * @return id of that person. 
   */
  public int getPersonID(Object code){
      
      try{
  return (int)dbl.get("SELECT ID FROM PERSON WHERE CODE="+code); 
          }catch(Exception e){
          return -1; 
          }
  
  }
  
  
  
  class AutoCodeFinder implements KeyListener{
  public void keyPressed(KeyEvent ke){}
  public void keyTyped(KeyEvent ke){
      
  }
  public void keyReleased(KeyEvent ke){
      
      String name=nameField.getText(); 
      String add=addressObj.addressField.getText(); 
      String city=cityObj.cityField.getText(); 
      int prsn=-1; 
      
      if(!name.isEmpty() && !add.isEmpty() && !city.isEmpty()){
  prsn=getPersonID(name, add,city);     
      }else
      
      if (!name.isEmpty() && !add.isEmpty()){
        prsn=getPersonID(name, add);         
      }
  
      if(prsn!=-1){
      codeField.setText(dbl.get("SELECT CODE FROM PERSON WHERE ID="+prsn).toString());
      }else{
      codeField.setText(""+nextNewCode);
      }
      
       
      
      
      
      
  }
  
  
  }
  
  
  
}
