/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Java
 */
public class Utilities {
    
    
  
    
    /**
     * Shows a confirmation message to choose yes and no. 
     * @param title The title of Option
     * @param message  The message of the dialog  
     * @param font  Font name in string type if language is English the leave it empty. 
     * @return  0 if yes and 1 if no. 
     */
     public int confirm(String title,String message,String font){
    JLabel jl=new JLabel(message); 
    if(!font.isEmpty()){
    jl.setFont(new Font("Jameel Noori Nastaleeq",0,18));
    }
   return JOptionPane.showConfirmDialog(null, jl, title, JOptionPane.YES_NO_OPTION);
   }
     
     /**
      * Shows a confirmation message to choose yes and no. 
      * @param title    The title of Option
      * @param message   The message of the dialog  
      * @param font Font name in string type if language is English the leave it empty. 
      */
     public void alert(String title,String message,String font){
    JLabel jl=new JLabel(message); 
    jl.setPreferredSize(new Dimension(200,50));
    if(!font.isEmpty()){
    jl.setFont(new Font(font,0,22));
    jl.setPreferredSize(new Dimension(message.length()*7,50));
     JOptionPane.showMessageDialog(null, jl, title, JOptionPane.INFORMATION_MESSAGE);
    }else{
   //
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    }
    
     /**
      * to show an alert that user is not authorized so. 
      * @param language 
      */
     public void noPermisstion(String language){
     if(language.equalsIgnoreCase("English")){
     alert("No permission","You don't have permission to do this. ",""); 
     }else{
     alert("اجازت نہیں","آپ کو یہ اختیار حاصل نہیں۔ ","Jameel Noori Nastaleeq"); 
     
     }
     }
     
     /**
      * shows the alert for huge amount
      * @param language 
      */
     
     
     void hugeNumber(String language){
     
     if(language.equalsIgnoreCase("English")){
     alert("Huge amount","Given amount is too large.",""); 
     }else{
     alert("بڑی رقم","یہ رقم بہت بڑی ہے۔ ","Jameel Noori Nastaleeq"); 
     
     }
     
     
     
     }
     
     /**
      * to show an alert that task was not done. 
      * @param language 
      */
     public void notDoneMessage(String language){
     if(language.equalsIgnoreCase("English")){
     alert("Problem","Not Done",""); 
     }else{
     alert("مسئلہ","نہیں ہوا۔ ","Jameel Noori Nastaleeq"); 
     
     }
     }
     
      /**
      * to show an alert that user is not authorized so. 
      * @param language 
      */
     public void doneMessage(String language){
     if(language.equalsIgnoreCase("English")){
     alert("Complete","Done",""); 
     }else{
     alert("مکمل","ہوگیا","Jameel Noori Nastaleeq"); 
     
     }
     }
     /**
      * Confirms that if user wants to delete some Item. 
      * @param language
      * @return 
      */
      public int sureToDelet(String language){
         
     if(language.equalsIgnoreCase("English")){
     return confirm("Confirms","Are you really sure to delete this?  ",""); 
     }else{
     return confirm("تصدیق","کیا واقعی آپ اسےمٹانا چاہتے ہو؟ ","Jameel Noori Nastaleeq"); 
     }
     
     }
     
      
      
      
       /**
      * Confirms that if user wants to delete some Item. 
      * @param language
      * @return 
      */
      public int sureToShutdown(String language){
         
     if(language.equalsIgnoreCase("English")){
     return confirm("Confirms","To continue this programe will shutdown once. Then start it again. ",""); 
     }else{
     return confirm("تصدیق","ایسا کرنے کے لیے پروگرام ایک دفع بند ہوجائے گا۔ اسے دوبارہ سٹارٹ کیجیے گا۔","Jameel Noori Nastaleeq"); 
     }
     
     }
      
      
      
       public int sureToRename(String language){
         
     if(language.equalsIgnoreCase("English")){
     return confirm("Confirms","Are you sure to rename this?  ",""); 
     }else{
     return confirm("تصدیق","کیاواقعی آپ اس کے نام میں تبدیلی لانا چاہتے ہو۔  ","Jameel Noori Nastaleeq"); 
     }
     
     }
      
     /**
      * to show the delete msg. 
      * @param language  current language
      * @param done  true to delete or false to show not deleted. 
      * @return 
      */
      public void deletedMsg(String language,boolean done){
         
     if(language.equalsIgnoreCase("English")){
      if(done){   
     alert("Confirms","Deleted  ",""); 
      }else{
      alert("Confirms","Not Deleted  ",""); 
      }
     }else{
         
      if(done){   
     alert("تصدیق","مٹا دیا گیا ","Jameel Noori Nastaleeq"); 
      }else{
      alert("تصدیق","نہیں مٹا ","Jameel Noori Nastaleeq"); 
      }
         
         
    
     }
     
     }
      
      
     /**
      * To choose ad date
      * @return the chosen dated
      */
    String datePicker(){
    JFrame f=new JFrame("Date Picker"); 
       return new DatePicker(f).setPickedDate(); 
    }
    
    class DatePicker {
  int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
  JLabel l = new JLabel("", JLabel.CENTER);
  String day = "";
  JDialog d;
  JButton[] button = new JButton[49];
 
  public DatePicker(JFrame parent) {
    d = new JDialog();
    d.setModal(true);
    String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 120));
 
    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      button[x].setBackground(Color.white);
      if (x > 6) {
        button[x].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            day = button[selection].getActionCommand();
            
            d.dispose();
          }
        });
      }
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
     
    // Previous month button
    JButton previous = new JButton("<< Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month--;
        displayDate();
      }
    });
    p2.add(previous);
     
    // Current month label between the previous and next buttons
    p2.add(l);
     
    // Next month button
    JButton next = new JButton("Next >>");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month++;
        displayDate();
      }
    });
    p2.add(next);
     
    d.add(p1, BorderLayout.CENTER);
    d.add(p2, BorderLayout.SOUTH);
    d.pack();
    d.setLocationRelativeTo(parent);
    displayDate();
    d.setVisible(true);
    
   //String toBeReturn=day+"/"+(month+1)+"/"+year; 
    //JOptionPane.showMessageDialog(null,toBeReturn);
  }
 
  public void displayDate() {
    for (int x = 7; x < button.length; x++) {
      button[x].setText("");
    }
     
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
 
    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
      button[x].setText("" + day);
    }
   
    l.setText(sdf.format(cal.getTime()));
    d.setTitle("Date Picker");
    
  }
 
  public String setPickedDate() {
    if (day.equals("")) {
      return day;
    }
   
    // Set the return date format
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
         
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    
    return sdf.format(cal.getTime());
    
  }
    
    
}

void animate(JComponent component,int t,int l){

new Thread(new Runnable(){public void run(){
    for (int i = 0; i < l; i++) {
        component.setBackground(Color.red);
     try{
     Thread.sleep(t);
     component.setBackground(Color.white);
     Thread.sleep(t);
     }catch(InterruptedException e){}   
     
     
    }

}}).start();

}


public static void copyFolder(File source, File destination)
{
    if (source.isDirectory())
    {
        if (!destination.exists())
        {
            destination.mkdirs();
        }

        String files[] = source.list();

        for (String file : files)
        {
            File srcFile = new File(source, file);
            File destFile = new File(destination, file);

            copyFolder(srcFile, destFile);
        }
    }
    else
    {
        InputStream in = null;
        OutputStream out = null;

        try
        {
            in = new FileInputStream(source);
            out = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }
        }
        catch (Exception e)
        {
            try
            {
                in.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

            try
            {
                out.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}

 /**
     * to set the right align for JCombo Box. 
     */
    class MyListCellRenderer extends DefaultListCellRenderer {
  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    Component component = super.getListCellRendererComponent(list, value,
        index, isSelected, cellHasFocus);
    component.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    return component;
  }}

    /**
     * to get a font according to language. 
     * @param language  current language
     * @return required font. 
     */
    public Font getFont(String language){
        if(language.equalsIgnoreCase("Urdu")){
         return new Font("Jameel Noori Nastaleeq", 0, 18);
        }else{
        return new Font("Arial", 0, 16);
        }
    
    
    }
    
     public int getHAlign(String language){
    
     if(language.equalsIgnoreCase("Urdu")){
        
         return javax.swing.JTextField.RIGHT; 
        }else{
         return javax.swing.JTextField.LEFT; 
        }
    }
    
    
    
}
 
    