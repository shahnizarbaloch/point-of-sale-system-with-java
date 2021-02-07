/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.JComboBox;

/**
 *
 * @author Java
 */
public class Units {
    public static String[] BASIC_UNITS=new String[]{"Piece","Set","KG","Packet","Meter","Sheet","Liter","Feet"}; 
    
    Database dbl; 
    public Units(Database db){
    dbl=db; 
    }
    
    public JComboBox<String> productUnitsCombo=new JComboBox<String>(); 
    public Vector<Integer> productUnitsComboIDs=new Vector<Integer>(); 
    
    public void refreshProductUnitsCombo(int product){
        productUnitsCombo.removeAllItems();
        productUnitsComboIDs.setSize(0);
        if(product<0){return;     }
        Object[][] td=dbl.getTable("SELECT UNIT.ID,UNIT_NAME.NAME FROM UNIT_NAME INNER JOIN UNIT ON UNIT.NAME=UNIT_NAME.ID WHERE UNIT.PRODUCT="+product); 
    int l=td.length; 
        for (int i = 0; i < l; i++) {
    productUnitsCombo.addItem(td[i][1].toString());        
    productUnitsComboIDs.add((int)td[i][0]); 
        }
    
    
    }
    
    
    
    
    
   
    public int updateUnit(int product,int parent,double qty,boolean multiply,String name,int unitID){
       if(name.isEmpty()){return -1; }
       //convert the name into id
   int nameID=createName(name); 
   //check if the person already exists. 
   int tr=getUnitID(product,parent,nameID); 
       
   if(tr<0){
   
   
       
       
   //String query="INSERT INTO UNIT (PRODUCT,PARENT,QTY,MULTIPLY,NAME) values ("+product+","+parent+","+qty+","+multiply+","+name+")"; 
   String query="UPDATE UNIT SET PRODUCT = "+product+",PARENT = "+parent+",QTY = "+qty+",MULTIPLY="+multiply+",NAME = "+nameID+" WHERE PRODUCT="+product; 
   
  dbl.exicuteQury(query); 
    tr=getUnitID(product,parent,nameID); 
  
       
   
  
   }
   
   
   return tr; 
   }
   
    
    
    
    
     public int createUnit(int product,int parent,double qty,boolean multiply,int name){
       
       
   
   
 
   //check if the person already exists. 
   int tr=getUnitID(product,parent,name); 
   
   if(tr<0){
   
   String query="INSERT INTO UNIT (PRODUCT,PARENT,QTY,MULTIPLY,NAME) values ("+product+","+parent+","+qty+","+multiply+","+name+")"; 
   
  dbl.exicuteQury(query); 
    tr=getUnitID(product,parent,name); 
  
   }
   
   
   return tr; 
   }
   
   
   public int createUnit(int product,int parent,double qty,boolean multiply,String name){
       if(name.isEmpty()){return -1; }
       //convert the name into id
   int nameID=createName(name); 
   //check if the person already exists. 
   int tr=getUnitID(product,parent,nameID); 
       
   if(tr<0){
   
   
   tr=createUnit(product, parent, qty, multiply, nameID); 
  
   }
   
   
   return tr; 
   }
   /**
    * Gets the ID of a person
    * @param nameID   name id
    * @param addressID  address ID
    * @return 
    */
   
   
   public int getUnitID(int product, int parent,int name){
   int tr=-1; 
    String query="SELECT ID FROM UNIT WHERE PRODUCT="+product+" AND PARENT="+parent+" AND NAME="+name; 
       
      try{
      tr=Integer.parseInt(dbl.get(query).toString()); 
      }catch(Exception e){}
       
    return tr;    
   } 
   
   
  public int getUnitID(int product,int name){
   int tr=-1; 
    String query="SELECT ID FROM UNIT WHERE PRODUCT="+product+" AND NAME="+name; 
       
      try{
      tr=Integer.parseInt(dbl.get(query).toString()); 
      }catch(Exception e){}
       
    return tr;    
   } 
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    public  int createName(String v){
     v=v.trim(); 
        if(v.isEmpty()){return -1; }
        String qyery ="INSERT INTO UNIT_NAME (NAME)   "
                + " (SELECT '"+v+"'    FROM UNIT_NAME    WHERE UPPER(NAME) like UPPER('"+v+"')     HAVING count(*)=0)"; 
                dbl.exicuteQury(qyery); 
    int id=getNameID(v); //check if already exists. 
    
  return id; 
    }
    
    public   int getNameID(String v){
    
     v=v.trim(); 
        
    try{
       
        return (int)dbl.get("SELECT ID FROM UNIT_NAME WHERE UPPER(NAME) LIKE UPPER('"+v+"') OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY"); 
    }catch(java.lang.ClassCastException e){
   return -1; 
    }
    
    
}
    
    
    public String getBasicUnitName(int product){
        System.out.println(">>>>====<<===="+product);
        String tr=dbl.getValue("SELECT UNIT_NAME.NAME FROM UNIT_NAME INNER JOIN UNIT ON UNIT.NAME=UNIT_NAME.ID WHERE UNIT.PRODUCT="+product+" AND UNIT.PARENT=0 ").toString(); 
        if(tr.startsWith("java.")){tr=""; }
        
    return tr; 
    
    }
    
    public String getUnitName(int id){
    return dbl.getValue("SELECT UNIT_NAME.NAME FROM UNIT_NAME INNER JOIN UNIT ON UNIT.NAME=UNIT_NAME.ID WHERE UNIT.ID="+id).toString(); 
    
    }
    
    Object[][] productUnits=new Object[6][4]; 
    public void loadUnits(int product){
    Object[][] td=dbl.getTable("SELECT UNIT.ID,UNIT.PARENT,UNIT_NAME.NAME, UNIT.QTY FROM UNIT INNER JOIN UNIT_NAME ON UNIT.NAME=UNIT_NAME.ID AND UNIT.PRODUCT="+product); 
        for (int i = 0; i < 6; i++) {
            Arrays.fill(productUnits[i], "");
        }
        
    int l=td.length; 
        for (int i = 0; i < l; i++) {
            
            productUnits[i][0]=td[i][0]; 
            productUnits[i][1]=td[i][1]; 
            productUnits[i][2]=td[i][2]; 
            productUnits[i][3]=td[i][3]; 
            
            System.out.println(td[i][0]+"==============="+td[i][1]+"==============="+td[i][2]+"==============="+td[i][3]);
        }
    
    
    }
    
    
    //to convert the package to unit
    public double convertPackageToUnit(String unit){
    double tr=0; 
    int parent=-1; 
    
  //  while(parent!=0){
    for (int i = 0; i<6; i++) {
        if(!productUnits[i][2].toString().isEmpty()){
            if(productUnits[i][2].toString().equalsIgnoreCase(unit)){
                if(tr==0){tr=(double)productUnits[i][3]; }else{tr*=(double)productUnits[i][3]; }
                parent=(int)productUnits[i][1]; 
        System.out.println(">>======"+parent);        
    //        }
        }}}
    
    while(parent!=0){
        for (int i = 0; i < 6; i++) {
            if(!productUnits[i][2].toString().isEmpty()){
            if((int)productUnits[i][0]==parent){
            if(tr==0){tr=(double)productUnits[i][3]; }else{tr*=(double)productUnits[i][3]; }
            parent=(int)productUnits[i][1]; 
            }}
        }}
    
    return tr; 
    }
    
    
    
}
