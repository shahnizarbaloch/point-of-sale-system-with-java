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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class Customer {
    
    private Connection conn;
    
    public Customer(){
    
        try{
    Class.forName("org.sqlite.JDBC");
    conn=DriverManager.getConnection("jdbc:sqlite:ShopDatabase.db");
    }
    catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,
            "There is Problem",
            "Problem!",
            JOptionPane.ERROR_MESSAGE);
    }
    }
    
    JTextField searchBar;
    /**
     * This method is for making customer screen when user click the customer tab
     * @param panel
     */
    public void makeCustomerPanelDesign(JPanel panel){
        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setBounds(30, 5, 180, 35);
        btnAddCustomer.setFont(new Font("Arial", Font.PLAIN, 14));
        
//        BufferedImage img;
//        try {
//        img = ImageIO.read(new File("src\\images\\add_new_customer.png"));
//        Image dimg = img.getScaledInstance(btnAddCustomer.getWidth(), btnAddCustomer.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//
//        btnAddCustomer.setIcon(imageIcon);
//        } catch (IOException e) {
//        }
        
        
        btnAddCustomer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeAddCustomerFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddCustomer.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddCustomer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        panel.add(btnAddCustomer);
        
        JLabel searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchLabel.setBounds(220,12,55,30);
        panel.add(searchLabel);
        
        searchBar = new JTextField();
        searchBar.setBounds(280,12,150,30);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(searchBar);
        searchBar.addActionListener((ActionEvent e) -> {
            try {
                searchCustomer(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                searchCustomer(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               try {
                searchCustomer(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
    });
    
    }
    
    
    /**
     * For Searching any customer from database
     * @param customerName 
     */
    private void searchCustomer(String customerName) throws ClassNotFoundException{
        int count = 0;
        
        try {
            String query="SELECT * FROM customer WHERE customer_name like '"+customerName+"%' ";
           
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
           
            while(rs.next()){
                dtm.setRowCount(count);
                count++;
                Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(3),
                rs.getObject(4)
                };
                
                dtm.addRow(r);

                }
        }
        catch(SQLException e){
        }    
        
    }
    
    DefaultTableModel dtm;
    JTable table;
    int selectedRowIndex;
    /**
     * Method for making table of products 
     * @param panel panel of main screen
     */
    public void makeCustomerTable(JPanel panel){
        String[][] td=new String[0][4];
        String[] Title ={"ID","Name","Number","Address"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm=new DefaultTableModel(td,Title){

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
        }
        };
        table = new JTable(dtm);
        
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
        jsp.setBounds(5, 50, 500, 550);
        
        table.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        selectedRowIndex = table.getSelectedRow();
        
        if(selectedRowIndex!=-1){
        dtm2.setRowCount(0);
        loadCustomerData(selectedRowIndex);
        }
        
        }   
    });
        loadCustomers();
    }
    
    
    DefaultTableModel dtm2;
    JLabel totalAmount;
    JTable ledgerTable;
    JLabel customerVoucherLabel;
    /**
     * for making the ledger table right side of the customer table
     * @param panelMainScreen panelMainScreen
     */
    public void makeRightTable(JPanel panelMainScreen){
        customerVoucherLabel = new JLabel("Customer Ledger");
        customerVoucherLabel.setBounds(580, 10, 200, 35);
        customerVoucherLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panelMainScreen.add(customerVoucherLabel);
        
        //Table of Customer Order
        String[][] td=new String[0][4];
        String[] Title ={"ID","Date","Borrowed","Details"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm2=new DefaultTableModel(td,Title);
        ledgerTable = new JTable(dtm2);
        
        TableColumnModel tcm = ledgerTable.getColumnModel();
        tcm.removeColumn( tcm.getColumn(0) );
       
        
        //table.getTableHeader().setBackground(Color.WHITE);
        ledgerTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        //This is for hiding the lines
        ledgerTable.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        //Hiding the lined between cells
        ledgerTable.setShowGrid(false);
        //background white
        ledgerTable.setFillsViewportHeight(true);
        //table.setShowHorizontalLines(false);
        //table.setShowVerticalLines(false);
        
        ledgerTable.setRowHeight(20);
        ledgerTable.setFont(new Font("Arial",Font.PLAIN,10));
        
        //table.getTableHeader().setFont(new Font("Times New Roman",1,15));
        JScrollPane jsp = new JScrollPane(ledgerTable);
        panelMainScreen.add(jsp); 
        jsp.setBounds(515, 50, 315, 430);
        
        ledgerTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        
        int selectedRow = ledgerTable.getSelectedRow();
        
        if(selectedRow!=-1){
        deleteItem(selectedRow);
        }
        
        }   
        });
        
        
        
        //Total Label
        JLabel amountLable = new JLabel("Total Amount : ");
        amountLable.setBounds(550, 490, 150, 25);
        amountLable.setFont(new Font("Areal",Font.PLAIN,18));
        panelMainScreen.add(amountLable);
        
        //Total Price Label will be shown here
        totalAmount = new JLabel("Rs. 0");
        totalAmount.setBounds(690, 490, 150, 25);
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        totalAmount.setForeground(Color.BLUE);
        panelMainScreen.add(totalAmount);
        
        JButton btnAddBorrowed = new JButton("Add Money");
        btnAddBorrowed.setBounds(520, 530, 150, 40);
        btnAddBorrowed.setFont(new Font("Arial", Font.PLAIN, 12));
        
//        BufferedImage img ;
//        try {
//        img = ImageIO.read(new File("src\\images\\add_money.png"));
//        Image dimg = img.getScaledInstance(btnAddBorrowed.getWidth(), btnAddBorrowed.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//
//        btnAddBorrowed.setIcon(imageIcon);
//        } catch (IOException e) {
//        }
        
        
        btnAddBorrowed.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Here, new Frame should be open and Shopkeeper can add more money to the ledger of the customer
                addMoneyInCustomerLedger(selectedRowIndex);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddBorrowed.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddBorrowed.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        panelMainScreen.add(btnAddBorrowed);
        
        
        JButton btnReturn = new JButton("Return Money");
        btnReturn.setBounds(680, 530, 150, 40);
        btnReturn.setFont(new Font("Arial", Font.PLAIN, 12));
        
        btnReturn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Here, new Frame should be open and Shopkeeper can add more money to the ledger of the customer
                returnMoneyInCustomerLedger(selectedRowIndex);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReturn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        panelMainScreen.add(btnReturn);
        
    }
    
    
    /**
     * method for options of deleting the item and it will remove it from table and call method of deleting it in database
     * @param selectedRow selected row
     */
    private void deleteItem(int selectedRow){
        int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete ?","Delete Option!",JOptionPane.OK_CANCEL_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
        try{
            Object ObjectId = dtm2.getValueAt(selectedRow, 0);
            int id = Integer.parseInt(ObjectId.toString());
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ledger WHERE ledger_id = "+id);
            ps.execute();
            dtm2.removeRow(selectedRow);
            sumTheTotal();
        }
        catch(SQLException e ){
            System.err.println(e.getSQLState());
        }
        }
    }
    
    
    JTextField TvCustomerId,TvBorrowed,TvSomeDetails;
    /**
     * This method is for adding money in customer ledger
     * @param selectedRow selected row 
     */
    private void addMoneyInCustomerLedger(int selectedRow){
        JFrame frame = new JFrame("Add In Customer Ledger");
//        ImageIcon frameicon = new ImageIcon("src\\images\\frame_Icon.png");
//        frame.setIconImage(frameicon.getImage());
        frame.setBounds(480, 60, 350, 220);
        frame.setVisible(true);
        frame.setResizable(true);
        
        JPanel panelMainScreen = new JPanel();
        panelMainScreen.setBounds(0, 0, 350, 450);
        panelMainScreen.setBackground(Color.white);
        panelMainScreen.setLayout(null);
        frame.add(panelMainScreen);
        
        JLabel customerIdLabel = new JLabel("Customer Id");
        customerIdLabel.setBounds(20, 10, 120, 25);
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(customerIdLabel);
        
        TvCustomerId = new JTextField();
        TvCustomerId.setBounds(145, 10, 130, 25);
        TvCustomerId.setFont(new Font("Arial", Font.PLAIN, 14));
        TvCustomerId.setBackground(Color.WHITE);
        TvCustomerId.setEditable(false);
        TvCustomerId.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvCustomerId);
        
        Object ObjectId = dtm.getValueAt(selectedRow, 0);
        int id = Integer.parseInt(ObjectId.toString());
        TvCustomerId.setText(String.valueOf(id));
        
        JLabel borrowedLabel = new JLabel("Add Amount");
        borrowedLabel.setBounds(20, 50, 120, 25);
        borrowedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(borrowedLabel);
        
        TvBorrowed = new JTextField();
        TvBorrowed.setBounds(145, 50, 130, 25);
        TvBorrowed.setFont(new Font("Arial", Font.PLAIN, 14));
        TvBorrowed.setBackground(Color.WHITE);
        TvBorrowed.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvBorrowed);
        new quettainstitute.QI_JTextField().validateThis(TvBorrowed, QI_JTextField.INT_VALIDATION);
        
        
        JLabel someDetailsLabel = new JLabel("Details");
        someDetailsLabel.setBounds(20, 90, 120, 25);
        someDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(someDetailsLabel);
        
        TvSomeDetails = new JTextField();
        TvSomeDetails.setBounds(145, 90, 130, 25);
        TvSomeDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        TvSomeDetails.setBackground(Color.WHITE);
        TvSomeDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvSomeDetails);
        
        JButton btnAdd = new JButton("Add in Database");
        btnAdd.setBounds(85, 135, 160, 40);
        
        btnAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    addAmountInDatabase(TvCustomerId.getText(),
                            TvBorrowed.getText(),
                            TvSomeDetails.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        
        panelMainScreen.add(btnAdd);
    }
    
    
    
    
    /**
     * This method is for adding money in customer ledger
     * @param selectedRow selected row 
     */
    private void returnMoneyInCustomerLedger(int selectedRow){
        JFrame frame = new JFrame("Return In Customer Ledger");
        
        frame.setBounds(480, 60, 350, 220);
        frame.setVisible(true);
        frame.setResizable(true);
        
        JPanel panelMainScreen = new JPanel();
        panelMainScreen.setBounds(0, 0, 350, 450);
        panelMainScreen.setBackground(Color.white);
        panelMainScreen.setLayout(null);
        frame.add(panelMainScreen);
        
        JLabel customerIdLabel = new JLabel("Customer Id");
        customerIdLabel.setBounds(20, 10, 120, 25);
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(customerIdLabel);
        
        TvCustomerId = new JTextField();
        TvCustomerId.setBounds(145, 10, 130, 25);
        TvCustomerId.setFont(new Font("Arial", Font.PLAIN, 14));
        TvCustomerId.setBackground(Color.WHITE);
        TvCustomerId.setEditable(false);
        TvCustomerId.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvCustomerId);
        
        Object ObjectId = dtm.getValueAt(selectedRow, 0);
        int id = Integer.parseInt(ObjectId.toString());
        TvCustomerId.setText(String.valueOf(id));
        
        JLabel borrowedLabel = new JLabel("Add Amount");
        borrowedLabel.setBounds(20, 50, 120, 25);
        borrowedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(borrowedLabel);
        
        TvBorrowed = new JTextField();
        TvBorrowed.setBounds(145, 50, 130, 25);
        TvBorrowed.setFont(new Font("Arial", Font.PLAIN, 14));
        TvBorrowed.setBackground(Color.WHITE);
        new quettainstitute.QI_JTextField().validateThis(TvBorrowed, QI_JTextField.INT_VALIDATION);
        
        TvBorrowed.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvBorrowed);
        
        
        JLabel someDetailsLabel = new JLabel("Details");
        someDetailsLabel.setBounds(20, 90, 120, 25);
        someDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(someDetailsLabel);
        
        TvSomeDetails = new JTextField();
        TvSomeDetails.setBounds(145, 90, 130, 25);
        TvSomeDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        TvSomeDetails.setBackground(Color.WHITE);
        TvSomeDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(TvSomeDetails);
        
        JButton btnAdd = new JButton("Add in Database");
        btnAdd.setBounds(85, 135, 160, 40);
        
        btnAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    
                    int returnMoney = Integer.parseInt(TvBorrowed.getText());
                    int newMoney = -returnMoney;
                    
                    addAmountInDatabase(TvCustomerId.getText(),
                            String.valueOf(newMoney),
                            TvSomeDetails.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        
        panelMainScreen.add(btnAdd);
    }
    
    
    /**
     * This method is for loading the data of products table
     */
    private void loadCustomers() {
        //Count variable is for table rows which is initially zero
        int count=0;
        
        try{
        // The first thing that is done in this software is to call the database connectivity of SQLite.
        //making the object of Connectivity.
        Connectivity obj = new Connectivity();
        //Calling the displayProducts through the object of Connectivity
        ResultSet rs = obj.displayCustomers();
        //it will run untill the last data in the products.
        while(rs.next()){
        dtm.setRowCount(count);
        count++;
        
        Object[] r={   
                rs.getObject(1),
                rs.getObject(2),
                rs.getObject(3),
                rs.getObject(4)
        
        };
        dtm.addRow(r);
        }
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        
    }
    
    /**
     * This will load the selected customer data into the right side table
     * @param selectedRow selected id of the customer
     */
    private void loadCustomerData(int selectedRow){
        
        try{
        int count = 0;    
        Object ObjectId = dtm.getValueAt(selectedRow, 0);
        int id = Integer.parseInt(ObjectId.toString());
        
        customerVoucherLabel.setText(dtm.getValueAt(selectedRow, 1)+"'s Ledger");
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM ledger WHERE customer_id = "+id);
        //it will run untill the last data in the products.
        while(rs.next()){
        dtm2.setRowCount(count);
        count++;
        
        Object[] r={
                rs.getObject(1),
                rs.getObject(5),
                rs.getObject(3),
                rs.getObject(4)
        
        };
        dtm2.addRow(r);
        }
        sumTheTotal();
        }
        catch(NumberFormatException | SQLException e){
            System.err.println("Here is error : "+e.getMessage());
        }
        
       
    }
    
    
    
    
    
    
    JTextField customerName,customerNumber,addressTextField;
    /**
     * This method is for making form of adding any item
     */
    private void makeAddCustomerFrame(){
        JFrame frame = new JFrame("Add New Customer");
        frame.setBounds(480, 60, 350, 220);
        frame.setVisible(true);
        frame.setResizable(true);
        
        JPanel panelMainScreen = new JPanel();
        panelMainScreen.setBounds(0, 0, 350, 450);
        panelMainScreen.setBackground(Color.white);
        panelMainScreen.setLayout(null);
        frame.add(panelMainScreen);
        
        JLabel customerNameLabel = new JLabel("Name");
        customerNameLabel.setBounds(20, 10, 120, 25);
        customerNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(customerNameLabel);
        
        customerName = new JTextField();
        customerName.setBounds(145, 10, 130, 25);
        customerName.setFont(new Font("Arial", Font.PLAIN, 14));
        customerName.setBackground(Color.WHITE);
        customerName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(customerName);
        
        JLabel customerNumberLabel = new JLabel("Number");
        customerNumberLabel.setBounds(20, 50, 120, 25);
        customerNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(customerNumberLabel);
        
        customerNumber = new JTextField();
        customerNumber.setBounds(145, 50, 130, 25);
        customerNumber.setFont(new Font("Arial", Font.PLAIN, 14));
        customerNumber.setBackground(Color.WHITE);
        customerNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                
        panelMainScreen.add(customerNumber);
        
        
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(20, 90, 120, 25);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(addressLabel);
        
        addressTextField = new JTextField();
        addressTextField.setBounds(145, 90, 130, 25);
        addressTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        addressTextField.setBackground(Color.WHITE);
        addressTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(addressTextField);
        
        JButton btnAdd = new JButton("Add in Database");
        btnAdd.setBounds(85, 135, 160, 40);
        
        btnAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    addCustomer(customerName.getText(),
                            customerNumber.getText(),
                            addressTextField.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        
        panelMainScreen.add(btnAdd);
        
    }
    
    /**
     * This is for adding new customer
     */
    private void addCustomer(String Name,String Number,String Address) throws SQLException{
        if(Name.isEmpty() || Number.isEmpty()|| Address.isEmpty()){
            JOptionPane.showMessageDialog(null,
        "Please Fill The Inputs Correctly!",
        "Input Problem",
        JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO customer (customer_name,customer_number,address) VALUES(?,?,?)");

            ps.setString(1, Name);
            ps.setString(2, Number);
            ps.setString(3, Address);
            
            ps.execute();
//            
//            JOptionPane.showMessageDialog(null,
//            Name+ " is Successfully Added!",
//            "Successfull",
//            JOptionPane.INFORMATION_MESSAGE);
            
            customerName.setText("");
            customerNumber.setText("");
            addressTextField.setText("");
            loadCustomers();
        }
    }
    
    
    /**
     * This is for adding new customer
     */
    private void addAmountInDatabase(String Id,String Borrowed,String Details) throws SQLException{
        if(Id.isEmpty() || Borrowed.isEmpty()|| Details.isEmpty()){
            JOptionPane.showMessageDialog(null,
        "Please Fill The Inputs Correctly!",
        "Input Problem",
        JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            java.sql.Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
            
            Date date=new Date(timestamp.getTime());
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ledger (customer_id,borrowed,some_details,date) VALUES(?,?,?,?)");

            ps.setString(1, Id);
            ps.setString(2, Borrowed);
            ps.setString(3, Details);
            ps.setString(4, date.toString());
            
            ps.execute();
            
//            JOptionPane.showMessageDialog(null,
//            Borrowed+ " is Successfully Added!",
//            "Successfull",
//            JOptionPane.INFORMATION_MESSAGE);
            
            TvBorrowed.setText("");
            TvSomeDetails.setText("");
            
            loadCustomerData(selectedRowIndex);
        }
    }
    
    long total = 0;
    /**
     * This method is for adding the values into the voucher table
     */
    private void sumTheTotal(){
        
        total = 0;
        for (int i = 0; i < dtm2.getRowCount(); i++){
        long amount = Long.parseLong(dtm2.getValueAt(i, 2).toString());
        total += amount;
        }
        //System.out.println(total);
        totalAmount.setText("Rs. "+total);
        }
    
}
