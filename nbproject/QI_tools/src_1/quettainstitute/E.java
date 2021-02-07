/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

/**
 *
 * @author Azeem
 */
import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author XPPRESP3
 */
public class E {
    
    E(){
         
    
  //  LocalDate date; 
   // System.out.println(); 
    //diSfference("06102014","07/10/2014");
        //System.out.println("--------------------+   "+invalidChars("Pa(ki}stan")); 
    }
    /**
     * removes the white spaces and converts the string into word case it will not disturb the abrivations
     * @param str string to input
     * @return String cleaned from white spaces and word cased
     */
    
    String wordCase(String str){
str=str.trim(); 
        if(str.length()==0)return ""; 
        str+=" "; 
        str=str.replaceAll("\\s+"," ");
        
        String toReturn=""; 
        
       
        
        
        if(str.charAt(0)==' '){str=str.substring(1, str.length()); }
        str=(""+str.charAt(0)).toUpperCase()+str.substring(1, str.length());
    StringBuilder b = new StringBuilder(str);
    //if(str.contains(" ")){
int i = 0;
do {
    
    if(!b.subSequence(i,  b.indexOf(" ", i)).toString().toUpperCase().equals(b.substring(i,  b.indexOf(" ", i)))){
        
  b.replace(i,b.indexOf(" ", i), b.substring(i,b.indexOf(" ", i)).toLowerCase());      
  b.replace(i, i + 1, b.substring(i,i + 1).toUpperCase());
  
    }else{
    
    
    }
    i =  b.indexOf(" ", i) + 1;
} while (i > 0 && i < b.length());


toReturn=b.toString().substring(0,b.length()-1); 
/*
        }else{
            
            
     if(!b.subSequence(0, b.length()).toString().toUpperCase().equals(b.substring(0, b.length()))){
     b.replace(0,b.length(), b.substring(0,b.length()).toLowerCase());
     b.replace(0, 1, b.substring(0,1).toUpperCase());
     
     }   
            
        
        }
    */
        
    return b.toString().substring(0,b.length()-1); 
    }
    
    
    int invalidChars(char arg){
    String ch="[{()}];\\<>"; 
        if(ch.contains(""+arg)){return 1; }; 

        return -1; 
    }
    
    
    int invalidString(String arg){
    String ch="[{()}];\\<>"; 
    for(int i=0; i<arg.length(); i++){
        if(ch.contains(""+arg.charAt(i))){return 1; }; 
    }
        return -1; 
    }
    
    /**
     * Checks if the password is valid. 
     * @param v  the input value
     * @return  true if value is valid otherwise false. 
     */
    boolean isValidPassword(String v){
    v=v.trim();
    if(v.isEmpty() || v.length()<2){return false; }
    if(invalidString(v)!=-1){return false; }
    if(v.contains("'")){return false; }; 
    if(v.length()>20){return false; }; 
    
    return true; 
    }
   
    /**
     * Makes the numbers easy to read. Example 100000000 to 100,000,000
     * @param str   value
     * @return   str with comas
     */
     String priceFormater(String str) {
    int floatPos = str.indexOf(".") > -1 ? str.length() - str.indexOf(".") : 0;
    int nGroups= (str.length()-floatPos-1-(str.indexOf("-")>-1?1:0))/3;
    for(int i=0; i<nGroups; i++){
        int commaPos = str.length() - i * 4 - 3 - floatPos;
    str = str.substring(0,commaPos) + "," + str.substring(commaPos,str.length());
    }
    return str;
}
    
     /**
      * Removes the scientivic methods from given number
      * @param v
      * @return 
      */
      String removeScientific(String v){
  
  
 double tr = 0;
 
 try{tr=Double.parseDouble(v); }catch(NumberFormatException e){}
 
DecimalFormat df = new DecimalFormat("#");
df.setMaximumFractionDigits(0);

  
  return ""+df.format(tr); 
  }
  
     
     
    /**
     * reduces the v according to c degrees
     * @param v   value
     * @param c   decimal degrees
     * @return 
     */
String degrees(Object v, int c)  
{   
    double d=0; 
    try{d=Double.parseDouble(v.toString());}catch(NumberFormatException e){}
    
   int temp = (int)(d * Math.pow(10 , c));  
   return ""+(((double)temp)/Math.pow(10 , c));  
}    

    
    
    
/**
 * it jus verifies that if it is a number
 * @param str the value to be checked
 * @return if value is a number return true otherwise false; 
 */
    boolean isItAnInt(String str){
    
    try{
    
    Integer.parseInt(str); 
    
    
    }catch(NumberFormatException e){ return false; }
    return true; 
    }
    
    /**
     * Examines the input if it is a mathematical value
     * @param str The input value
     * @return true if it was a number otherwise false; 
     */
    boolean isThisNumber(String str){
    
    try{
    Integer.parseInt(str); 
    }catch(NumberFormatException e){ 
   try{
   
   Float.parseFloat(str); 
   
   
   }catch(NumberFormatException se){
   
   return false; 
   }     
        
        
        
        }
    return true; 
    }
    
    
    
    
    
    public String removeDotZero(Object av){
    String v=av.toString(); 
    if(v.endsWith(".0")){
    return v.substring(0,v.length()-2); 
    }
    return v; 
    }
    
    String formatMathValue(Object v){
        
        
    String value=v.toString();
    if(value.endsWith(".0")){
    value=value.substring(0,value.length()-2); 
    }
    
    if(value.contains("E")){
    //value="EEEEEE"; 
    value=new BigDecimal(value).toPlainString(); 
    
    }
    
    return value; 
    }
    
    
    int invalidCharsAndNotInteger(char arg){
    String ch="[{()}];\\<>"; 
        if(ch.contains(""+arg)){return 1; }; 
        try{
        Integer.parseInt(""+arg);
        }catch(NumberFormatException e){
        return 1; 
        }
        return -1; 
    }
    
    int invalidStringAndNotInteger(String arg){
        String ch="[{()}];\\<>"; 
        
        for(int i=0; i<arg.length(); i++){
        if(ch.contains(""+arg)){return 1; }; 
        try{
        Integer.parseInt(""+arg);
        }catch(NumberFormatException e){
        return 1; 
        }
        }
        return -1; 
    }
    
    
    int difference(String s, String e){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM//yy"); 
    int tr=0; 
    try{
    Date sd=format.parse(s); 
    Date ed=format.parse(e);
    System.out.println(""+sd.compareTo(ed)); 
    
    }catch(Exception ee){System.out.println("----"+ee); }
    
    return tr=0; 
    }
    
    
    
    String date(){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   Date date = new Date();
return dateFormat.format(date); 
    }
    
    String date(String format){
    DateFormat dateFormat = new SimpleDateFormat(format);
	   Date date = new Date();
return dateFormat.format(date); 
    }
    
    
    
    
     String increaseDasy(String formate, int increase){
    
    SimpleDateFormat sdf = new SimpleDateFormat(formate);
Calendar c = Calendar.getInstance();
c.setTime(new Date()); // Now use today date.
c.add(Calendar.DATE, increase); // Adding 5 days
return sdf.format(c.getTime());
  }
    String time(){
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
       Date dateobj = new Date();
       
       Calendar calobj = Calendar.getInstance();
       return df.format(calobj.getTime());
        
    }
    
    String onlyTime(){
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
       Date dateobj = new Date();
       
       Calendar calobj = Calendar.getInstance();
       String toreturn=""; 
       
       try{
           toreturn=df.format(calobj.getTime()).split(" ")[1];
           
       }catch(ArrayIndexOutOfBoundsException e){
       toreturn=df.format(calobj.getTime());
       
       }
       
       return toreturn; 
        
    }
    
    
    String time12Hour(){
    
     DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a z");
        Date date = new Date();
        String formattedDateTime = dateFormat.format(date);
     //   System.out.println(formattedDateTime);
    return formattedDateTime; 
    
    }
    
    public  boolean isValidDate(String date,String f){
        System.out.println("Date is : "+date+" and "+f);
        
        String[] d=date.split("/"); 
        int[] id=new int[3]; 
        try {
           try{
            id[0]=Integer.parseInt(d[0]); 
            id[1]=Integer.parseInt(d[1]); 
            id[2]=Integer.parseInt(d[2]); 
            if(id[2]>2030){
           return false; 
            }
             LocalDate.of(id[2], id[0], id[1]); 
           }catch(NumberFormatException e){
             return false; 
           }
            //LocalDate.of(d[0],d[1],d[2]);
            System.out.println("true");
            return true;
        } catch (DateTimeException e) {
           
            return false;
        }
}
    
    public boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			//System.out.println(date);
		
		} catch (ParseException e) {
			
			//e.printStackTrace();
			return false;
		}
		
		return true;
	}
    
    /*
    public boolean isValidDate(String dateString, String f) {
    SimpleDateFormat df = new SimpleDateFormat(f);
    try {
        System.out.println("parsing date ");
        df.parse(dateString);
        return true;
    } catch (Exception e) {
        return false;
    }
}
    */
    
     public boolean isValidDate(String dateString) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    try {
        
        df.parse(dateString);
        return true;
    } catch (Exception e) {
        return false;
    }
}
    
    boolean isInteger(String arg){
    boolean tr=false; 
    try{
    
    Integer.parseInt(arg);
    tr=true; 
    }catch(Exception e){
    return false; 
    }
    
    
    return tr; 
    }
    
   
   String[] removeDeplucate(String[] arg){
       
      // System.out.print("Recieved "+arg.length);
       
       
    int c=1;  
     Arrays.sort(arg);
    for(int i=1; i<arg.length; i++){
    if(!arg[i-1].equalsIgnoreCase(arg[i])){
    arg[c]=arg[i]; 
    c++; 
    }
    }
    String[] tr=new String[c]; 
    for(int i=0; i<tr.length; i++){
    tr[i]=arg[i]; 
    }
    
    
   // System.out.println(" and Output "+tr.length);
    return tr; 
    }
    
   
   String diplucateValue(String[] str){
       for (int i = 0; i < str.length; i++) {
           for (int j = 0; j < str.length; j++) {
               
               if(str[i].equalsIgnoreCase(str[j]) && i!=j){
                   System.out.println(i+"]===================[Diplucate]===============["+str[i]);
                   
               return str[i]; 
               }
               
           }
       }
   
   return ""; 
   }
   
    
    public  void saveScaledImage(String filePath,String outputFile, int w, int h){
    try {

        BufferedImage sourceImage = ImageIO.read(new File(filePath));
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();

        if(width>height){
            float extraSize=    height-100;
            float percentHight = (extraSize/height)*100;
            float percentWidth = width - ((width/100)*percentHight);
            BufferedImage img = new BufferedImage((int)percentWidth, 100, BufferedImage.TYPE_INT_RGB);
            Image scaledImage = sourceImage.getScaledInstance((int)percentWidth, 100, Image.SCALE_SMOOTH);
            img.createGraphics().drawImage(scaledImage, 0, 0, null);
            BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
            img2 = img.getSubimage((int)((percentWidth-100)/2), 0, 100, 100);

            ImageIO.write(img2, "jpg", new File(outputFile));    
        }else{
            float extraSize=    width-100;
            float percentWidth = (extraSize/width)*100;
            float  percentHight = height - ((height/100)*percentWidth);
            BufferedImage img = new BufferedImage(100, (int)percentHight, BufferedImage.TYPE_INT_RGB);
            Image scaledImage = sourceImage.getScaledInstance(100,(int)percentHight, Image.SCALE_SMOOTH);
            img.createGraphics().drawImage(scaledImage, 0, 0, null);
            BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
            img2 = img.getSubimage(0, (int)((percentHight-100)/2), 100, 100);

            ImageIO.write(img2, "jpg", new File(outputFile));
        }

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
    
    int rounds=0; 
    String fromLanguage=""; 
    void switchKeyboard(String languageName){
      //  Locale locale = Locale.getDefault();
        
        //System.out.println(Locale.getDefault());
          
         
         
             
             InputContext is = InputContext.getInstance();
         String currentLanguage=is.getLocale().getDisplayLanguage(); 
       
           
         
         
            
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
        
        if(rounds==4){rounds=0; return; }
        rounds++; 
        switchKeyboard(languageName); 
    } catch (AWTException e1) {
        e1.printStackTrace();
    }
    
    };
  
         
   
    
    }
    
   
 boolean isThisAPositiveNumber(String str){
    
    try{
    if(Integer.parseInt(str)<0){return false; }; 
    
    
    }catch(NumberFormatException e){ 
   try{
   
   if(Float.parseFloat(str)<0){return false; }; 
   
   
   }catch(NumberFormatException se){
   
   return false; 
   }     
        
        
        
        }
    return true; 
    }
 
 
 
 
Rectangle getRect(String str){
 
 //x=56,y=75,width=145,height=30
 str=str.substring(0, str.indexOf("]"));
   // System.out.println("-----------------"+str);
 String[] v=str.split(","); 
 int x=Integer.parseInt(v[0].split("=")[1]); 
 int y=Integer.parseInt(v[1].split("=")[1]); 
 int w=Integer.parseInt(v[2].split("=")[1]); 
 int h=Integer.parseInt(v[3].split("=")[1]); 
 return new Rectangle(x,y,w,h); 
 
 }
 
 
 /**
  * Classic positive number
  * removes negative  remove scientific alpha bets. 
  * removes .0
  * controls the degree on three. 
  * @param n   number to be given
  * @return 
  */
 String cpn(String n){
     
     try{
     
     if(Double.parseDouble(n)<0){
     return "-1"; 
     }
     }catch(NumberFormatException e){
     return "-1"; 
     }
 String tr=formatMathValue(n); 
 
 
 return tr; 
 }
 
 
 /**
  * 
  * @param v input value 
  * @param m mode of filtering or string type if 0=string, 1=Double 2=Integer
  * @return filtered value
  */
 String filterString(String v, int m){
 
 switch(m){
     case 1: try{
     if(Double.parseDouble(v)<0){v=""; return v; }
     }catch(NumberFormatException e){
         v=""; 
     return v; 
     }break; //double
     case 2:
         try{
     if(Integer.parseInt(v)<0){v=""; return v; }
     }catch(NumberFormatException ee){
             v=""; 
     return v; 
     }
         
         break;  //integer   
 
 }
     
     
     
 v=v.trim().toLowerCase(); 
 //v=v.toLowerCase(); 
 String ristrictedChars="/<>{}()\\\'=&%~.,%"+'\"'; 
 /*for(int i=0; i<ristrictedChars.length(); i++){
     
     System.out.println("------"+ristrictedChars.charAt(i));
            for (int j = 0; j < v.length(); j++) {
// v.replaceAll(""+ristrictedChars.charAt(i), ""); 
     }
           
     
 }*/
 String temp="";
     for (int i = 0; i < v.length(); i++) {
         if(!ristrictedChars.contains(""+v.charAt(i))){
         temp+=v.charAt(i); 
         }
     }
 
 v=temp; 
 v=v.replaceFirst("-", "");
 v=v.replaceFirst("_", "");
 
 
  v=wordCase(v);     
     
     
 return v; 
 }
 
 
 String getWhiteSpaces(String v){
 String tr=""; 
     for (int i = 0; i <= v.length(); i++) {
         tr+="  "; 
     }
 return tr; 
 }
 
 long startOfStopWtch=0;
 void stopWatch(int m){
 
 long duration=0; 
  
 long end=0; 
 switch(m){
     case 0:
         startOfStopWtch=duration=System.currentTimeMillis();
        
         break; 
     case 1: 
         end=System.currentTimeMillis();
         duration=end-startOfStopWtch; 
         JOptionPane.showMessageDialog(null,duration);
         
         break;     
 
 
 }
 
 
 
 }
 
 
 
 /**
  * Converts the format of date from one to another. 
  * @param v    the input value of date
  * @param sourceFormat     the existing format of given date (v)
  * @param distinyFormat    the format to convert the v into.
  * @return 
  */
String CDF(String v,String sourceFormat, String distinyFormat){

    
    
    try{
     SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
    Date date = sdfSource.parse(v);
    SimpleDateFormat sdfDestination = new SimpleDateFormat(distinyFormat);
    v=sdfDestination.format(date);
    }catch(ParseException e){}
    
 

return v; 
}   


boolean saveTxt(String fileName,String txt){
try{
FileOutputStream fout=new FileOutputStream(fileName); 
fout.write(txt.getBytes());

}catch(IOException e){}

return true; 
}
String getFileText(String fileName){

    try{
    FileInputStream fin=new FileInputStream(fileName);
    byte[] b=new byte[fin.available()]; 
    fin.read(b); 
    return new String(b); 
    }catch(IOException e){}
    

return ""; 
}

}
