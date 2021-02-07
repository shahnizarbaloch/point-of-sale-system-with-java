package quettainstitute;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_Str {
    /**
     * concatenates an array to form a solid string value
     * @param array input
     * @return concatenated form array. 
     */
    
    public String arrayToStr(String[] array){
    String tr=""; 
    int l=array.length; 
        for (int i = 0; i < l; i++) {
            tr+=array[i]+" "; 
        }
        return tr.trim(); 
    }
    
  /**
   * Converts in upper case the first letter only. 
   * @param v input value
   * @return output. 
   */
    
    public String sentenceCase(String iv){
        if(!iv.isEmpty()){
    iv=(""+iv.charAt(0)).toUpperCase()+iv.substring(1); 
            }
    
    return iv; 
    }
    
    /**
     * Removes the chars like [{()}];\\<>
     * @param iv input value
     * @return output value.
     */
    public String removeIlligalChars(String iv){
    String ch="[{()}];\\/'<>\""; 
    String tr=""; 
    int l=iv.length(); 
    for(int i=0; i<l; i++){
        if(!ch.contains(""+iv.charAt(i))){tr+=iv.charAt(i);  }; 
    }
    return tr; 
    }
    
    public String removeSQL(String iv){
    String tr=""; 
    //incomplete. 
    
    tr=iv; 
    return tr; 
    }
    
    public String limit(String iv,int limit){
    if(iv.length()>limit){
    iv=iv.substring(0, limit); 
    }
   return iv;  
   
    }
    
 public String wordCase(String iv){
     boolean spaceEnd=iv.endsWith(" "); 
     iv=iv.trim().toLowerCase(); 
     
     if(iv.endsWith(" ")); 
     String[] w=iv.split(" "); 
     iv="";
     String space=""; 
     for (int i = 0; i < w.length; i++) {
         iv+=(space+w[i].charAt(0)).toUpperCase()+w[i].substring(1); 
         space=" "; 
     }
     iv+=spaceEnd?" ":""; 
 return iv; 
 }   
    
  
   public String removeSpecialChars(String iv){
    String ch=" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
    String tr=""; 
    int l=iv.length(); 
    for(int i=0; i<l; i++){
        if(ch.contains(""+iv.charAt(i))){tr+=iv.charAt(i);  }; 
    }
    return tr; 
    }
 
 
 
}
