/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import point.of.sale.system.Connectivity;

/**
 *
 * @author Anonymous
 */
public class Report {
    
    Connection conn;
    
    public Report(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:ShopDatabase.db");
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,
            "There is Problem"+e.getMessage(),
            "Problem!",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    DefaultTableModel dtm;
    JTable table;
    int selectedRowIndex;
    JLabel totalAmount;
    /**
     * for making design of Report, Whenever user click report tab then this design will be made
     * @param panel 
     */
    public void makeReportDesign(JPanel panel){
        //Calender
        JCalendar calender = new JCalendar();
        calender.setBounds(520, 10, 300, 200);
        panel.add(calender);
        
        //Simple Label Text
        JLabel textAmount = new JLabel("- Total Sales -",SwingConstants.CENTER);
        textAmount.setFont(new Font("Arial",Font.PLAIN,18));
        textAmount.setBounds(520, 250, 300, 30);
        panel.add(textAmount);
        
        //Listener of the calender
        calender.addPropertyChangeListener("calendar", (PropertyChangeEvent e) -> {
            //final Calendar c = (Calendar) e.getNewValue();
            
            String date = String.valueOf(calender.getDate());
            textAmount.setText(date);
            
            String newDate = date.substring(0,11);
            
            getResultFromSelectedDate(newDate);
        });
        
        //Total Amount Label
        totalAmount = new JLabel("",SwingConstants.CENTER);
        totalAmount.setFont(new Font("Arial",Font.BOLD,18));
        totalAmount.setBounds(520, 280, 300, 30);
        totalAmount.setForeground(Color.BLUE);
        panel.add(totalAmount);
        
        
        //Simple Label Text
        JLabel textAmountLastWeek = new JLabel("- Last Week -",SwingConstants.CENTER);
        textAmountLastWeek.setFont(new Font("Arial",Font.PLAIN,18));
        textAmountLastWeek.setBounds(520, 340, 300, 30);
        panel.add(textAmountLastWeek);
        //Total Amount Label
        JLabel lastWeekAmount = new JLabel("",SwingConstants.CENTER);
        lastWeekAmount.setFont(new Font("Arial",Font.BOLD,18));
        lastWeekAmount.setBounds(520, 370, 300, 30);
        lastWeekAmount.setForeground(Color.BLUE);
        panel.add(lastWeekAmount);
        
        
        //Simple Label Text
        JLabel textAmountLastMonth = new JLabel("- Last Month -",SwingConstants.CENTER);
        textAmountLastMonth.setFont(new Font("Arial",Font.PLAIN,18));
        textAmountLastMonth.setBounds(520, 430, 300, 30);
        panel.add(textAmountLastMonth);
        //Total Amount Label
        JLabel lastMonthAmount = new JLabel("",SwingConstants.CENTER);
        lastMonthAmount.setFont(new Font("Arial",Font.BOLD,18));
        lastMonthAmount.setBounds(520, 460, 300, 30);
        lastMonthAmount.setForeground(Color.BLUE);
        panel.add(lastMonthAmount);
        
        //Making Table of Sell
        String[][] td=new String[0][3];
        String[] Title ={"ID","Date","Amount"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm=new DefaultTableModel(td,Title){

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
        }
        };
        table = new JTable(dtm);
        //Here removing the specific column
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn( tcm.getColumn(0) );
        //table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
        //table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black, 1));
        table.setShowGrid(true);
        //This is for background White
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(new Font("Arial",Font.PLAIN,14));
        //table.getTableHeader().setFont(new Font("Times New Roman",1,15));
        JScrollPane jsp = new JScrollPane(table);
        //Background white continue here
        //jsp.getViewport().setBackground(table.getBackground());
        panel.add(jsp); 
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());
        jsp.setBounds(0, 10, 500, 590);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        //Adding listener to the table row selecter
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        selectedRowIndex = table.getSelectedRow();
        //Here checking selected row is -1 or not, if it is -1 then it means not any specific row is selected
        if(selectedRowIndex!=-1){
        optionForItems(selectedRowIndex);
        }
        
        }   
        });
        
        lastWeekAmount.setText("Rs. "+getLastWeekResult());
        lastMonthAmount.setText("Rs. "+getLastMonthRecord());
        
        loadData();
        sumTheTotal();
        
    }
    
    /**
     * whenever voucher item is clicked, delete option will e available from table
     * @param row row of the table
     */
    private void optionForItems(int row){
        int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the selected record?","Delete Option!",JOptionPane.OK_CANCEL_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
        Object ObjectId = dtm.getValueAt(row, 0);
        try {
        int id = Integer.parseInt(ObjectId.toString());
        PreparedStatement ps = conn.prepareStatement("DELETE FROM sell WHERE sell_id = "+id);
        ps.execute();
        dtm.removeRow(row);
        
        sumTheTotal();
        } 
        catch (NumberFormatException | SQLException e) {
        System.err.println(""+e.getMessage());
        }
        
        }
        if(dtm.getRowCount()==0){
        totalAmount.setText("Rs. "+0);
        }
    }

    /**
     * This method is for loading the data of products table
     */
    private void loadData() {
        //Count variable is for table rows which is initially zero
        int count=0;
        
        try{
        // The first thing that is done in this software is to call the database connectivity of SQLite.
        //making the object of Connectivity.
        Connectivity obj = new Connectivity();
        //Calling the displayProducts through the object of Connectivity
        ResultSet rs = obj.displayRecords();
        //it will run untill the last data in the products.
        while(rs.next()){
        dtm.setRowCount(count);
        count++;
        
        Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(3),
        };
        dtm.addRow(r);
        }
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        
    }
    
    
    long total = 0;
    /**
     * This method is for adding the values into the voucher table
     */
    private void sumTheTotal(){
        
        total = 0;
        for (int i = 0; i < dtm.getRowCount(); i++){
        long amount = Long.parseLong(dtm.getValueAt(i, 2).toString());
        total += amount;
        }
        //System.out.println(total);
        totalAmount.setText("Rs. "+total);
        }
        
    
    /**
     * This method will get the selected Date records from database
     */
    private void getResultFromSelectedDate(String date){
        
        int count = 0;
        dtm.setRowCount(count);
        try{
            String query="SELECT * FROM sell WHERE date like '"+date+"%' ";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            
            while(rs.next()){
                count++;
                Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(3)
                };
                
                dtm.addRow(r);
                }
            
            sumTheTotal();
        }
        catch(SQLException e){
            System.err.println(""+e.getMessage());
        }
    }
    
    /**
     * This method is for getting overall last week report.
     * @return total last week sell
     */
    private int getLastWeekResult(){
        
        int totalLastWeekSelling=0;
        String sql = "SELECT SUM(total_price) FROM sell where date >= date('now', '-6 days')";
        try{
            
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                
                totalLastWeekSelling+=rs.getInt(1);
                }
            
        }
        catch(SQLException e){
            System.err.println(""+e.getMessage());
        }
        
        return totalLastWeekSelling;
    }
    
    /**
     * This is for last month total selling 
     * @return 
     */
    private int getLastMonthRecord(){
        int totalLastMonthSelling=0;
        String sql = "SELECT SUM(total_price) FROM sell where date >= date('now', '-30 days')";
        
        try{
            
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                
                totalLastMonthSelling+=rs.getInt(1);
                }
            
        }
        catch(SQLException e){
            System.err.println(""+e.getMessage());
        }
        
        return totalLastMonthSelling;
    }
}
