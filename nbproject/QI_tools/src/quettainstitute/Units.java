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
    public  static int[] BASIC_UNITS_NUMBERS=new int[]{101,102,103,104,105,106,107,108}; 
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
    /**
     * <html>
     * [*][0]=unit ID()<br>
     * [*][1]=parent ID<br>
     * [*][2]=unit name<br>
     * [*][3]=packed quantity<br>
     * </html>
     */
    public Object[][] productUnits=new Object[6][4]; 
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
    
    
    
    /**<html>
     * To get the largest unit of the product <br>
     * Once load method is called all the info of the product is loaded into productUnits array<br>
     * then it returns the package name of largest package along with basic unit difference  in an array
     * 
     * </html>
     * @return [0]= String package name and [1]=integer the number basic unit place in this package. [2]=integer the id of this package.  
     */
   Object[] getLargestPackage(){
   
       
       
   return new Object[]{}; 
   }
   
   
   
   
   public double getUnitsQtyInPackage(int packageID){
   int l=productUnits.length; 
       
       
       
   //find the basic unit. 
   
   double qtyToBeReturned=(double)productUnits[0][3]; 
   int lastFoundID=(int)productUnits[0][0]; 
   
       
   
   if(lastFoundID==packageID){return qtyToBeReturned;}
   
   
   
   
   
   boolean keepGoing=true; 
   int c=0; 
   while(keepGoing){
       
       
       
        for (int i = 1; i < l; i++) {
           
            
            int id=0; 
            int parentID=0; 
            
            
            try{
            id=Integer.parseInt(productUnits[i][0].toString()); 
            parentID=Integer.parseInt(productUnits[i][1].toString()); 
            }catch(Exception e){}
            
            
            if(lastFoundID==parentID){
            try{
                qtyToBeReturned=qtyToBeReturned*(double)productUnits[i][3]; 
                lastFoundID=id;     
                i=l;  
            }catch(Exception e){e.printStackTrace();}
            
            
            }
            
            
    
       }
   
       
       
       
       
       
   if(lastFoundID==packageID){keepGoing=false; }    
   if(c>=l){keepGoing=false; }    
   c++; 
       
   }
   
       
       
    
   return qtyToBeReturned; 
   }
   
   
   
     public double getUnitsQtyInPackage3(int packageID){
   int l=productUnits.length; 
   
   
       
   //find the basic unit. 
   
   double qtyToBeReturned=(double)productUnits[0][3]; 
   
   
   int lastFoundID=(int)productUnits[0][0]; 
   boolean keepGoing=true; 
   int c=0; 
   if(keepGoing){
       
       
       
       
   
        for (int i = 1; i < l; i++) {
            
            if(productUnits[i][0].toString().isEmpty()){
            System.out.println(""+productUnits[i][3]);
            System.out.print("( "+i);
            try{
                
           if(Integer.parseInt(productUnits[i][1].toString())==lastFoundID){
               
            qtyToBeReturned*=(double)productUnits[i][3]; 
            lastFoundID=Integer.parseInt(productUnits[i][0].toString()); 
               System.out.println(" )");
           }
            }catch(Exception e){
                
                System.err.println(i+""+e.getMessage());
            }}
       }
       
       
       
   
   try{if((double)productUnits[c][0]==packageID){keepGoing=false; }    }catch(Exception e){}
   if(c>=l){keepGoing=false; }    
   c++; 
   }
   
   
       System.out.println(">>>-----------------"+qtyToBeReturned);
   
     
   if(true)return qtyToBeReturned; 
       for (int i = 0; i < l; i++) {
          try{
               
           if(!productUnits[i][0].toString().isEmpty() && Integer.parseInt(productUnits[i][0].toString())==packageID){
               
               //System.out.println("----------------------"+productUnits[i][2]+" ------------- "+productUnits[i][3]);
               
                   qtyToBeReturned=qtyToBeReturned*(double)productUnits[i][3]; 
                   
              
               
               
               for (int j = 0; j < l; j++) {
                   if(!productUnits[j][1].toString().isEmpty() && Integer.parseInt(productUnits[j][1].toString())==packageID){
                       
                   qtyToBeReturned*=getUnitsQtyInPackage(Integer.parseInt(productUnits[j][0].toString())); 
                   }
               }
               
           }
           }catch(Exception e){
           e.printStackTrace();
           }
           
       }
   
   
   
   
   
   
   return qtyToBeReturned; 
   }
   
   
   
   public double getUnitsQtyInPackage2(int packageID){
   int l=productUnits.length; 
   double qtyToBeReturned=0; 
       for (int i = 0; i < l; i++) {
          try{
               
           if(!productUnits[i][0].toString().isEmpty() && Integer.parseInt(productUnits[i][0].toString())==packageID){
               
               //System.out.println("----------------------"+productUnits[i][2]+" ------------- "+productUnits[i][3]);
               
                   qtyToBeReturned=qtyToBeReturned*(double)productUnits[i][3]; 
                   
              
               
               
               for (int j = 0; j < l; j++) {
                   if(!productUnits[j][1].toString().isEmpty() && Integer.parseInt(productUnits[j][1].toString())==packageID){
                       
                   qtyToBeReturned*=getUnitsQtyInPackage(Integer.parseInt(productUnits[j][0].toString())); 
                   }
               }
               
           }
           }catch(Exception e){
           e.printStackTrace();
           }
           
       }
   
   
   
   
   
   
   return qtyToBeReturned; 
   }
   
    
}
