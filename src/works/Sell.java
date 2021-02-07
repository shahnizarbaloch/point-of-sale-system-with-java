/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import point.of.sale.system.Connectivity;
import quettainstitute.QI_JTextField;

/**
 *
 * @author Anonymous
 */
public class Sell {
    
    
    
    Connection conn;
    public Sell(){
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
    JTextField searchBar;
    /**
     * This Method is For Creating Table Which is Shown in Home Screen
     * @param panel mainScreenPanel
     */  
    public void makeSellTable(JPanel panel){
        JLabel label = new JLabel("Search Product");
        label.setBounds(60, 5, 150, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(label);
        
        searchBar = new JTextField();
        searchBar.setBounds(220,5,150,30);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(searchBar);
        searchBar.addActionListener((ActionEvent e) -> {
            try {
                searchItem(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                searchItem(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               try {
                searchItem(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
    });
        
        
        String[][] td=new String[0][5];
        String[] Title ={"ID","Product","Category","Code","Price"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm=new DefaultTableModel(td,Title){

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
        }
        };
        table = new JTable(dtm);
        //table.setModel(dtm);
        
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
        jsp.setBounds(0, 40, 500, 560);
        
        table.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        selectedRowIndex = table.getSelectedRow();
        
        if(selectedRowIndex!=-1){
        setItemDetails(selectedRowIndex);
        }
        
        }   
    });
        loadData();
    }
    
    /**
     * For Searching any item from database
     * @param itemName 
     */
    private void searchItem(String itemName) throws ClassNotFoundException{
        int count = 0;
            
        try {
            String query="SELECT * FROM products WHERE product_name like '"+itemName+"%' ";
            
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
           
            while(rs.next()){
                dtm.setRowCount(count);
                count++;
                Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(6),
                rs.getObject(3),
                rs.getObject(5)
                };
                
                dtm.addRow(r);

                }
        }
        catch(SQLException e){
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
        ResultSet rs = obj.displayProducts();
        //it will run untill the last data in the products.
        while(rs.next()){
        dtm.setRowCount(count);
        count++;
        
        Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(6),
                rs.getObject(3),
                rs.getObject(5)
        
        };
        dtm.addRow(r);
        }
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        
    }
    
    
    JTextField productNameOnSide,productPriceOnSide,productQuantityOnSide;
    /**
     * This Method is for making voucher for the customer, it is being called in callSell Method
     * @param panelMainScreen this is the main Panel of Design Class
     */
    public void makeProductToSell(JPanel panelMainScreen){
        JLabel productDetailLabel = new JLabel("Product to Sell");
        productDetailLabel.setBounds(620, 5, 120, 30);
        productDetailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panelMainScreen.add(productDetailLabel);
        
        
        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(540, 50, 120, 25);
        productNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productNameLabel);
        
        productNameOnSide = new JTextField();
        productNameOnSide.setBounds(660, 50, 130, 25);
        productNameOnSide.setFont(new Font("Arial", Font.PLAIN, 14));
        productNameOnSide.setEditable(false);
        productNameOnSide.setBackground(Color.WHITE);
        productNameOnSide.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                
        panelMainScreen.add(productNameOnSide);
        
        
        JLabel productQuantityLabel = new JLabel("Quantity");
        productQuantityLabel.setBounds(540, 100, 120, 25);
        productQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productQuantityLabel);
        
        productQuantityOnSide = new JTextField();
        productQuantityOnSide.setFont(new Font("Arial", Font.PLAIN, 14));
        productQuantityOnSide.setBounds(660, 100, 130, 25);
        new quettainstitute.QI_JTextField().validateThis(productQuantityOnSide, QI_JTextField.INT_VALIDATION);
                
        panelMainScreen.add(productQuantityOnSide);
        
        
        JLabel productPriceLabel = new JLabel("Price");
        productPriceLabel.setBounds(540, 150, 120, 25);
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productPriceLabel);
        
        productPriceOnSide = new JTextField();
        productPriceOnSide.setFont(new Font("Arial", Font.PLAIN, 14));
        productPriceOnSide.setBounds(660, 150, 130, 25);
        new quettainstitute.QI_JTextField().validateThis(productPriceOnSide, QI_JTextField.INT_VALIDATION);
        
                
        panelMainScreen.add(productPriceOnSide);
        
        //This is the code for resizing image into label
        JButton addToCartBtn = new JButton("Add To Cart");
        addToCartBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addToCartBtn.setBounds(600,200,140,35);
        
        addToCartBtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(!productNameOnSide.getText().isEmpty()){
                    addInVoucherTable(productNameOnSide.getText(),productQuantityOnSide.getText(),productPriceOnSide.getText());
                }
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addToCartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addToCartBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        
//        BufferedImage img;
//        try {
//        img = ImageIO.read(getClass().getResource("Point of Sale System\\src\\images\\atc.png"));
//        Image dimg = img.getScaledInstance(addToCartBtn.getWidth(), addToCartBtn.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//
//        addToCartBtn.setIcon(imageIcon);
//        } catch (IOException e) {
//            System.err.println(""+e.getMessage());
//        }
        
        //Above all the code is for fitting the image into the label
        panelMainScreen.add(addToCartBtn);
        //Whenever User Clicks any table row then add to cart btn should be visible
        JPanel horizontalRow = new JPanel();
        horizontalRow.setBounds(510,250,315,1);
        horizontalRow.setBackground(Color.BLACK);
        
        panelMainScreen.add(horizontalRow);
        
        productQuantityOnSide.addActionListener((ActionEvent e) -> {
            try{
                int price ,quantity,originalPrice;
                quantity = Integer.parseInt(productQuantityOnSide.getText());
                originalPrice = Integer.parseInt(productPriceOnSide.getText());
                price = quantity*originalPrice;
                productPriceOnSide.setText(price+"");
            }
            catch(NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null,
                "Please Fill The Inputs Correctly!",
                "Input Problem",
                JOptionPane.ERROR_MESSAGE);
            }
        });
        
        productQuantityOnSide.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                try{
                    if(productQuantityOnSide.getText().length()>0){
                    int price ,quantity,originalPrice;
                quantity = Integer.parseInt(productQuantityOnSide.getText());
                originalPrice = Integer.parseInt(productPriceOnSide.getText());
                price = quantity*originalPrice;
                productPriceOnSide.setText(price+"");
                    }
                
            }
            catch(NumberFormatException numberFormatException){
                
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
              try{
              if(productQuantityOnSide.getText().length()>0){
                int price ,quantity,originalPrice;
                quantity = Integer.parseInt(productQuantityOnSide.getText());
                originalPrice = Integer.parseInt(productPriceOnSide.getText());
                price = quantity*originalPrice;
                productPriceOnSide.setText(price+"");
                }
                
            }
            catch(NumberFormatException numberFormatException){
                
            }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
    });
    }
    
    long total = 0;
    /**
     * This method is for adding the values into the voucher table
     */
    private void addInVoucherTable(String productName,String productQuantity,String productPrice){
        Object[] object = {productName,productQuantity,productPrice};
        if(productName.isEmpty() || productQuantity.isEmpty() || productPrice.isEmpty()){
            JOptionPane.showMessageDialog(null,
                "Empty values are not accepted!",
                "Input Problem",
                JOptionPane.ERROR_MESSAGE);
        }
        else{
        dtm2.addRow(object);
        total = 0;
        for (int i = 0; i < dtm2.getRowCount(); i++){
        long amount = Long.parseLong((String) dtm2.getValueAt(i, 2));
        total += amount;
        }
        //System.out.println(total);
        totalAmount.setText("Rs. "+total);
        }
        
        }
    
    /**
     * Whenever user select any table row, then values of that row will be added in the text fields
     * @param selectedRowIndex selected row index
     */
    private void setItemDetails(int selectedRowIndex) {
        productNameOnSide.setText(dtm.getValueAt(selectedRowIndex, 1).toString());
        productPriceOnSide.setText(dtm.getValueAt(selectedRowIndex, 4).toString());
        productQuantityOnSide.setText("1");
        }
    
    
    DefaultTableModel dtm2;
    JLabel totalAmount;
    JTable voucherTable;
    /**
     * This method is for making Customer voucher table
     * @param panelMainScreen main Panel
     */
    public void makeCustomerVoucher(JPanel panelMainScreen){
        JLabel customerVoucherLabel = new JLabel("Customer Voucher");
        customerVoucherLabel.setBounds(605, 260, 150, 35);
        customerVoucherLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panelMainScreen.add(customerVoucherLabel);
        
        //Table of Customer Order
        String[][] td=new String[0][3];
        String[] Title ={"Product","Quantity","Price"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm2=new DefaultTableModel(td,Title);
        voucherTable = new JTable(dtm2);
       
        
        //table.getTableHeader().setBackground(Color.WHITE);
        voucherTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
        //This is for hiding the lines
        voucherTable.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        //Hiding the lined between cells
        voucherTable.setShowGrid(false);
        //background white
        voucherTable.setFillsViewportHeight(true);
        //table.setShowHorizontalLines(false);
        //table.setShowVerticalLines(false);
        
        voucherTable.setRowHeight(20);
        voucherTable.setFont(new Font(Font.MONOSPACED,Font.PLAIN,14));
        
        //table.getTableHeader().setFont(new Font("Times New Roman",1,15));
        JScrollPane jsp = new JScrollPane(voucherTable);
        panelMainScreen.add(jsp); 
        jsp.setBounds(515, 295, 315, 230);
        
        voucherTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        int selectedItemRow = voucherTable.getSelectedRow();
        
        if(selectedItemRow!=-1){
        optionForItems(selectedItemRow);
        }
        
        }   
    });
        
        
        //Total Label
        JLabel amountLable = new JLabel("Total Amount : ");
        amountLable.setBounds(540, 530, 150, 25);
        amountLable.setFont(new Font("Areal",Font.PLAIN,18));
        panelMainScreen.add(amountLable);
        
        //Total Price Label will be shown here
        totalAmount = new JLabel("Rs. 0");
        totalAmount.setBounds(680, 530, 150, 25);
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        totalAmount.setForeground(Color.BLUE);
        panelMainScreen.add(totalAmount);
        
        
        //Adding Picture (Buttons) of submit and print
        //This is the code for resizing image into label
        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        submitBtn.setBounds(530,560,140,35);
        submitBtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(dtm2.getRowCount()>0){
                    
                insertDataIntoSell(total);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        
        });
        
        
//        BufferedImage img ;
//        try {
//        img = ImageIO.read(getClass().getResource("/src/images/submit_btn.png"));
//        Image dimg = img.getScaledInstance(submitBtn.getWidth(), submitBtn.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//
//        submitBtn.setIcon(imageIcon);
//        } catch (IOException e) {
//            System.err.println(""+e.getMessage());
//        }
        
        //Above all the code is for fitting the image into the label
        panelMainScreen.add(submitBtn);
        
        //print button
        
        //This is the code for resizing image into label
        JButton printBtn = new JButton("Print and Submit");
        printBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        printBtn.setBounds(690,560,140,35);
        printBtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                
                if(dtm2.getRowCount()>0){
                    
                printBill();
                insertDataIntoSell(total);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                printBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                printBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        
        });
        
        
//        BufferedImage img2;
//        try {
//        img2 = ImageIO.read(getClass().getResource("/src/images/print_btn.png"));
//        Image dimg2 = img2.getScaledInstance(printBtn.getWidth(), printBtn.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon2 = new ImageIcon(dimg2);
//
//        printBtn.setIcon(imageIcon2);
//        } catch (IOException e) {
//            System.err.println(""+e.getMessage());
//        }
        //Above all the code is for fitting the image into the label
        panelMainScreen.add(printBtn);
        
    }
    
    
    /**
     * This method is for inserting the selling data into Database
     */
    private void insertDataIntoSell(long total){
       try{
           PreparedStatement ps = conn.prepareStatement("INSERT INTO sell (date,total_price) VALUES(?,?)");
           
           java.sql.Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
           Date date=new Date(timestamp.getTime());
           
           ps.setString(1, String.valueOf(date));
           ps.setLong(2, total);
           
           ps.execute();
           
           total=0;
           totalAmount.setText("Rs. "+total);
           
           JOptionPane.showMessageDialog(null,
            "Successfully Added!",
            "Successfull",
            JOptionPane.INFORMATION_MESSAGE);
           
           dtm2.setRowCount(0);
           productNameOnSide.setText("");
           productPriceOnSide.setText("");
           productQuantityOnSide.setText("");
           
           
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error Occurred!"+e.getMessage(),
            "Error!",
            JOptionPane.ERROR_MESSAGE);
       }
    }
    
    
    /**
     * whenever voucher item is clicked, delete option will e available from table
     * @param row row of the table
     */
    private void optionForItems(int row){
        int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the selected item?","Delete Option!",JOptionPane.OK_CANCEL_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
        // Saving code here
        dtm2.removeRow(row);
        }
        if(dtm2.getRowCount()==0){
            
        totalAmount.setText("Rs. "+0);
        }
    }
    
    String completeBill = "";
    /**
     * This is for making bill of the customer
     */
    private void printBill(){
        
        
        JTextArea hiddenTextArea = new JTextArea();
        
        completeBill = "========================\n" +
                       "*** Contact For Your Shop***\n" +
                       "*** 0321-3622609 ***\n" +
                       "*** Contact For Address ***\n" +
                       "*** It's Trial Version ***\n" +
                       "========================\n";
        
        
        
        for (int i = 0; i < dtm2.getRowCount(); i++) {
            
        completeBill+= dtm2.getDataVector().elementAt(i).toString().replace(",", " ")+"\n";
        
        }
        
        completeBill=completeBill.replace("[", "");
        completeBill=completeBill.replace("]", "");
        
        completeBill+="\n-- Total Amount : "+"Rs. "+total+" --\n\n";
        
        completeBill+="Developed by - Shah Nizar Baloch -\n";
        completeBill+="@shahnizarbaloch@gmail.com";
        
        hiddenTextArea.append(completeBill);
        hiddenTextArea.setFont(new Font(Font.SERIF,Font.PLAIN,10));
        
        System.out.println(hiddenTextArea.getText());
        
        try {
            hiddenTextArea.print();
        } catch (PrinterException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    
}
