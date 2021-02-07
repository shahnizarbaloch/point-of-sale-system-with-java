package quettainstitute;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_Cal {
    String defaultDateFormat="dd/mm/yyyy"; 
    String defaultTimeFormat="dd/MM/yy HH:mm:ss"; 
    /**
     * Gets the System Date
     * @param format date format
     * @return  the date with specified format. 
     */
     public String dated(String format){
    DateFormat dateFormat = new SimpleDateFormat(format);
            Date date = new Date();
            return dateFormat.format(date); 
    }
     
    /**
     * Gets the System Date
     * @return  the date with default date format. 
     */
     public String dated(){
    DateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
            Date date = new Date();
            return dateFormat.format(date); 
    }
     
     
      
 /**
  * Converts the format of date from one to another. 
  * @param v    the input value of date
  * @param fromFormat     the existing format of given date
  * @param toFormat    the format to convert the date into.
  * @return 
  */
public String convertDateFormat(String date,String fromFormat, String toFormat){
    try{
     SimpleDateFormat sdfSource = new SimpleDateFormat(fromFormat);
    Date d = sdfSource.parse(date);
    SimpleDateFormat sdfDestination = new SimpleDateFormat(toFormat);
    date=sdfDestination.format(d);
    }catch(ParseException e){
    e.printStackTrace();
    }
 return date; 
}   


/*
public String convertTimeTo12Hour(String time){
    try{
     SimpleDateFormat sdfSource = new SimpleDateFormat(fromFormat);
    Date d = sdfSource.parse(date);
    SimpleDateFormat sdfDestination = new SimpleDateFormat(toFormat);
    date=sdfDestination.format(d);
    }catch(ParseException e){
    e.printStackTrace();
    }
 return date; 
}   
*/
 

/**
 * to get the system time
 * @param format format of tyme (dd/MM/yy HH:mm:ss)
 * @return the current system time with specified format
 */
  public String time(String format){
       DateFormat df = new SimpleDateFormat(format);
       Date dateobj = new Date();
       Calendar calobj = Calendar.getInstance();
       return df.format(calobj.getTime());
       }
        
    
/**
 * to get the system time
 * @return the current system time with default format
 */
  public String time(){
       DateFormat df = new SimpleDateFormat(defaultTimeFormat);
       Date dateobj = new Date();
       Calendar calobj = Calendar.getInstance();
       return df.format(calobj.getTime());
    }
  
  
  
  
  
  /**
   * 
   * @param start
   * @param end
   * @return 
   */
  
    int getDaysBetween(String start, String end){
        /*
        SimpleDateFormat format=new SimpleDateFormat(defaultDateFormat); 
    int tr=0; 
    try{
    Date sd=format.parse(start); 
    Date ed=format.parse(end);
    System.out.println("-- ] "+ed.compareTo(sd)); 
    
    }catch(Exception ee){System.out.println("----"+ee); }
    
    return tr=0; 
        */
        return 0; 
    }
  
  
  /**
   * to get the date after given date.
   * starts from today. 
   * @param afterDays
   * @return 
   */
    
  public String nextDate(int afterDays){
      /*
    SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());      // getting System data. 
        c.add(Calendar.DATE, afterDays); 
        
        System.out.println(c.getTime());
    return sdf.format(c.getTime());
              */
      return ""; 
  }
  
  
   /**
      * To choose ad date
      * @return the chosen dated
      */
    public String datePicker(){
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

  
        
}
