package quettainstitute;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_Math {
       
    /**
     * reduces the v according to c degrees
     * @param v   value
     * @param c   decimal degrees
     * @return 
     */
public Object degrees(Object v, int c)  
{   
    
    
    double d=0; 
    try{d=Double.parseDouble(v.toString());}catch(NumberFormatException e){}
        DecimalFormat df = new DecimalFormat("#.##");
        String dx=df.format(d);
        d=Double.valueOf(dx);

   return d; 
}    

      
    /**
     * reduces the v according to c degrees
     * @param v   value
     * @param c   decimal degrees
     * @return 
     */
public double degrees(double v, int c)  
{   
    double d=0; 
    //try{d=Double.parseDouble(v.toString());}catch(NumberFormatException e){}
   int temp = (int)(d * Math.pow(10 , c));  
   return (((double)temp)/Math.pow(10 , c));  
}    

/**
 * to check of some input is an integer. 
 * @param arg input value
 * @return true if input is an Integer
 */
 public boolean isInteger(String arg){
    boolean tr=false; 
    try{
    
    Integer.parseInt(arg);
    tr=true; 
    }catch(Exception e){
    return false; 
    }
       return tr; 
    }
/**
 * to check the given value if it is a double value. 
 * @param arg input value
 * @return true if double false otherwise. 
 */ 

public boolean isDouble(String arg){
    String v=arg.toString(); 
if(v.endsWith(".")){
    v+="0"; 
    }
try{
Double.parseDouble(v); 
return true; 
}catch(NumberFormatException e){
return false; 
}    
}
 
 
   
 /**
  * returns the double values of given stringInput
  * @param arg
  * @return 
  */
 
 
 public String validateInDouble(Object arg){
    String tr=""; 
    String v=arg.toString(); 
    
    if(v.endsWith(".")){
        
        if(!v.substring(0, v.length()-1).contains(".")){
            v+="0"; 
        }
    }
    try{
    Double.parseDouble(v); 
    tr=v;  
    }catch(NumberFormatException e){
        int l=v.length(); 
        for (int i = 0; i < l; i++) {
            try{
            Double.parseDouble(""+v.charAt(i)); 
            tr+=v.charAt(i); 
            }catch(NumberFormatException e2){
            if(v.charAt(i)=='.' && !tr.contains(".")){tr+=v.charAt(i);}
            }
        }}
    
    
    
    return tr;  
    }
 
 /**
  * validates the given value 
  * 1. trim<br>
  * 2. Avoid minus<br>
  * 3. Add 0 if starts with .<br>
  * 4. Make it double<br>
  * @param iv input value
  * @return filtered value. 
  */
 
 public String validatePrice(String iv){
        iv=iv.trim(); 
        if(iv.isEmpty()){return iv; }
        if(iv.startsWith("-")){
        iv=iv.substring(1); 
        }
        if(iv.startsWith(".")){
        iv="0"+iv; 
        }
        if(!isDouble(iv)){
             iv=validateInDouble(iv); 
             
        }
     
        
 return iv; 
 }
 
 
 public Object removeDotZero(Object v){
    String tr=v.toString(); 
    if(tr.endsWith(".0")){
    tr=tr.substring(0,tr.indexOf(".")); 
    }
    return tr; 
    }
    
 /**
  * Removes the scientific method from math value <br> 
  * Controls the degrees to 2 degrees<br>
  * Removes .0 from given value
  * 
  * @param v value to be inserted
  * @return pure value; 
  */
 public Object makeShowAble(Object v){
     Object tr=v.toString(); 
     
     try{
 //LD1===============Remove Scientific method
              tr=degrees(tr,2); 
     
     
 //End LD1     
    
//LD2============Controll Degrees
tr=new BigDecimal(tr.toString()).toPlainString(); 
 //End LD2
    
     //LD3 ========== remove .Zero
     
     tr=removeDotZero(tr); 
     
     
     }catch(Exception e){   }
     
 return tr; 
 }
 
    
}
