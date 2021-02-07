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
public class Product {

    private Connection conn;
    
    public Product() {
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
    
    
    private JTextField searchBar;
    /**
     * This is for making the product design, whenever user clicks products button
     * @param panel panel of the main Screen
     */
    public void makeProductDesign(JPanel panel){
        
        JButton btnAddItem = new JButton("Add New Item");
        btnAddItem.setBounds(150, 8, 160, 35);
        btnAddItem.setFont(new Font("Arial", Font.PLAIN, 14));
        
        
        btnAddItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeAddItemFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddItem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        panel.add(btnAddItem);
        
        
        JLabel searchLabel = new JLabel("Search Item");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchLabel.setBounds(320,12,100,30);
        panel.add(searchLabel);
        
        searchBar = new JTextField();
        searchBar.setBounds(420,12,150,30);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(searchBar);
        searchBar.addActionListener((ActionEvent e) -> {
            try {
                searchProducts(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                searchProducts(searchBar.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               try {
                searchProducts(searchBar.getText());
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
     * For Searching any item from database
     * @param productName 
     */
    private void searchProducts(String productName) throws ClassNotFoundException{
        int count = 0;
        
        try {
            String query="SELECT * FROM products WHERE product_name like '"+productName+"%' ";
            
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
                rs.getObject(4),
                rs.getObject(5)
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
    public void makeProductTable(JPanel panel){
        String[][] td=new String[0][6];
        String[] Title ={"ID","Product","Category","Code","Original Price","Selling Price"};
        //DefaultTableModel ki Khoobi Ye Hai K Jitne Rows Add Hote Jahenge Adjust Hota Jahega Table
        dtm=new DefaultTableModel(td,Title);
        table = new JTable(dtm){

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
        }
        };
        
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
        jsp.setBounds(10, 50, 820, 550);
        
        table.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        selectedRowIndex = table.getSelectedRow();
        
        if(selectedRowIndex!=-1){
        deleteItem(selectedRowIndex);
        }
        
        }   
    });
        loadData();
    }
    
    
    JTextField productName,productCode,productOriginalPrice,productSellingPrice,productCategory;
    /**
     * This method is for making form of adding any item
     */
    private void makeAddItemFrame() {
        JFrame frame = new JFrame("Add New Item");
//        ImageIcon frameicon = new ImageIcon(ImageIO.read(getClass().getResource(
//                                                    "/images/frame_Icon.png")));
        //frame.setIconImage(frameicon.getImage());
        frame.setBounds(480, 60, 350, 310);
        frame.setVisible(true);
        frame.setResizable(true);
        
        JPanel panelMainScreen = new JPanel();
        panelMainScreen.setBounds(0, 0, 350, 450);
        panelMainScreen.setBackground(Color.white);
        panelMainScreen.setLayout(null);
        frame.add(panelMainScreen);
        
        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(20, 10, 120, 25);
        productNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productNameLabel);
        
        productName = new JTextField();
        productName.setBounds(145, 10, 130, 25);
        productName.setFont(new Font("Arial", Font.PLAIN, 14));
        productName.setBackground(Color.WHITE);
        productName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(productName);
        
        JLabel productCodeLabel = new JLabel("Product Code");
        productCodeLabel.setBounds(20, 50, 120, 25);
        productCodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productCodeLabel);
        
        productCode = new JTextField();
        productCode.setBounds(145, 50, 130, 25);
        productCode.setFont(new Font("Arial", Font.PLAIN, 14));
        productCode.setBackground(Color.WHITE);
        productCode.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(productCode);
        
        
        JLabel productOriginalPriceLabel = new JLabel("Original Price");
        productOriginalPriceLabel.setBounds(20, 90, 120, 25);
        productOriginalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productOriginalPriceLabel);
        
        productOriginalPrice = new JTextField();
        productOriginalPrice.setBounds(145, 90, 130, 25);
        productOriginalPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        productOriginalPrice.setBackground(Color.WHITE);
        productOriginalPrice.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 
        new quettainstitute.QI_JTextField().validateThis(productOriginalPrice, QI_JTextField.INT_VALIDATION);
        panelMainScreen.add(productOriginalPrice);
        
        
        JLabel productSellingPriceLabel = new JLabel("Selling Price");
        productSellingPriceLabel.setBounds(20, 130, 120, 25);
        productSellingPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productSellingPriceLabel);
        
        productSellingPrice = new JTextField();
        productSellingPrice.setBounds(145, 130, 130, 25);
        productSellingPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        productSellingPrice.setBackground(Color.WHITE);
        productSellingPrice.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 
        
        new quettainstitute.QI_JTextField().validateThis(productSellingPrice, QI_JTextField.INT_VALIDATION);
        panelMainScreen.add(productSellingPrice);
        
        
        JLabel productCategoryLabel = new JLabel("Category");
        productCategoryLabel.setBounds(20, 170, 120, 25);
        productCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMainScreen.add(productCategoryLabel);
        
        productCategory = new JTextField();
        productCategory.setBounds(145, 170, 130, 25);
        productCategory.setFont(new Font("Arial", Font.PLAIN, 14));
        productCategory.setBackground(Color.WHITE);
        productCategory.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        
        panelMainScreen.add(productCategory);
        
        
        JButton btnAdd = new JButton("Add in Database");
        btnAdd.setBounds(85, 220, 160, 35);
        
//        BufferedImage img;
//        try {
//        img = ImageIO.read(new File("src\\images\\add_in_database.png"));
//        Image dimg = img.getScaledInstance(btnAdd.getWidth(), btnAdd.getHeight(),
//        Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//
//        btnAdd.setIcon(imageIcon);
//        } catch (IOException e) {
//        }
        
        
        btnAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    addProductInDatabase(productName.getText(),
                            productCode.getText(),
                            productOriginalPrice.getText(),
                            productSellingPrice.getText(),
                            productCategory.getText());
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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
     * This method checks the inputs and add them in the database
     * @param ProductName product name
     * @param ProductCode product code
     * @param OriginalPrice original price
     * @param SellingPrice selling price
     * @param Category category
     */
    private void addProductInDatabase(String ProductName, String ProductCode, 
            String OriginalPrice, String SellingPrice, String Category) throws ClassNotFoundException, SQLException 
    {
        if(ProductName.isEmpty() || ProductCode.isEmpty()|| OriginalPrice.isEmpty()
                || SellingPrice.isEmpty()||Category.isEmpty()){
            JOptionPane.showMessageDialog(null,
        "Please Fill The Inputs Correctly!",
        "Input Problem",
        JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products (product_name,product_code,original_price,selling_price,category) VALUES(?,?,?,?,?)");

            ps.setString(1, ProductName);
            ps.setString(2, ProductCode);
            ps.setString(3, OriginalPrice);
            ps.setString(4, SellingPrice);
            ps.setString(5, Category);
            
            ps.execute();
            
//            JOptionPane.showMessageDialog(null,
//            ProductName+ " is Successfully Added!",
//            "Successfull",
//            JOptionPane.INFORMATION_MESSAGE);
            
            loadData();
            
            productName.setText("");
            productCode.setText("");
            productCategory.setText("");
            productOriginalPrice.setText("");
            productSellingPrice.setText("");
            
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
                rs.getObject(4),
                rs.getObject(5)
        
        };
        dtm.addRow(r);
        }
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        
    }
    
    /**
     * method for options of deleting the item and it will remove it from table and call method of deleting it in database
     * @param selectedRow selected row
     */
    private void deleteItem(int selectedRow){
        int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the selected item?","Delete Option!",JOptionPane.OK_CANCEL_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
        try{
            Object ObjectId = dtm.getValueAt(selectedRow, 0);
            int id = Integer.parseInt(ObjectId.toString());
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE product_id = "+id);
            ps.execute();
            dtm.removeRow(selectedRow);
        }
        catch(SQLException e ){
            System.err.println(e.getSQLState());
        }
        }
    }
    
    
}
