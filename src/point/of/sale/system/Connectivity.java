/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point.of.sale.system;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anonymous
 */
public class Connectivity {
    private static Connection conn;
    private static final Boolean HAS_DATA=false;
    private static final String PRODUCTS_TABLE="products";
    private static final String CUSTOMER_TABLE="customer";
    private static final String LEDGER_TABLE = "ledger"; 
    private static final String SELL_TABLE="sell";
    
    

    /**
     * This will connect the software with SQLite Database
     * and initialize() method will be called in this method.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn=DriverManager.getConnection("jdbc:sqlite:ShopDatabase.db");
        initialize();
    }

    /**
     * This method is for creating all the tables of the software..
     * @throws SQLException 
     */
    private void initialize() throws SQLException {
        
        // SQL statement for creating a new table
        String create_customer_table = "CREATE TABLE IF NOT EXISTS "+CUSTOMER_TABLE+
                        "(customer_id INTEGER NOT NULL,"
                                + "customer_name varchar(60),"
                                + "customer_number varchar(60),"
                                + "address varchar(60),"
                                + "PRIMARY KEY(customer_id));";
        
//        String create_category_table = "CREATE TABLE IF NOT EXISTS category"
//                        + "(product_category_id INTEGER NOT NULL,"
//                        + "product_category varchar (60),"
//                       + "product_date varchar (60),"
//                        + "PRIMARY KEY(product_category_id));";

/**
 * String create_products_table = "CREATE TABLE IF NOT EXISTS "+PRODUCTS_TABLE+
                        "(product_id INTEGER NOT NULL,"
                                +"product_category_id INTEGER NOT NULL,"
                                + "product_name varchar(60),"
                                + "product_code varchar(60),"
                                + "original_price INTEGER,"
                                + "selling_price INTEGER,"
                                + "PRIMARY KEY(id),"
                                + "FOREIGN KEY(product_category_id) REFERENCES category(product_category_id) "
                                + "ON UPDATE CASCADE "
                                + "ON DELETE CASCADE);";
 */
        
        String create_products_table = "CREATE TABLE IF NOT EXISTS "+PRODUCTS_TABLE+
                        "(product_id INTEGER NOT NULL,"
                                + "product_name varchar(60),"
                                + "product_code varchar(60),"
                                + "original_price INTEGER,"
                                + "selling_price INTEGER,"
                                + "category varchar(60),"
                                + "PRIMARY KEY(product_id));";
        
        String create_ledger_table ="CREATE TABLE IF NOT EXISTS "+LEDGER_TABLE+
                        "(ledger_id INTEGER NOT NULL,"                
                                + "customer_id INTEGER NOT NULL,"
                                + "borrowed INTEGER,"
                                + "some_details varchar(60),"
                                + "date text,"
                                + "PRIMARY KEY(ledger_id),"
                                + "FOREIGN KEY(customer_id) REFERENCES customer(customer_id) "
                                + "ON UPDATE CASCADE "
                                + "ON DELETE CASCADE);";
        
        String create_sell_table = "CREATE TABLE IF NOT EXISTS "+SELL_TABLE+
                        "(sell_id INTEGER NOT NULL,"
                                + "date text,"
                                + "total_price INTEGER,"
                                + "PRIMARY KEY(sell_id));";
        
        System.out.println("Creating ....");
        Statement stmt = conn.createStatement();
            // create a new table
        stmt.execute(create_customer_table);
        System.out.println("DONE! 1");
        stmt.execute(create_ledger_table);
        System.out.println("DONE!! 2");
        stmt.execute(create_products_table);
        System.out.println("DONE!! 3");
        stmt.execute(create_sell_table);
        
        System.out.println("DONE!!");
                
//                PreparedStatement ps = conn.prepareStatement("INSERT INTO "+tableName+" (product_name,product_code,original_price,selling_price) values (Mango,S1123,20,2000)");
//                System.out.println("This line also being executed!");
//                ps.execute();
//                
//                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO "+tableName+" (product_name,product_code,original_price,selling_price) values (Mango,S1123,20,2000)");
//                ps2.execute();
            }
//            else{
//                PreparedStatement ps = conn.prepareStatement("INSERT INTO "+tableName+" (product_name,product_code,original_price,selling_price) values (?,?,?,?)");
//                ps.setString(1, "Mango");
//                ps.setString(2, "S2");
//                ps.setInt(3, 120);
//                ps.setInt(4, 140);
//                ps.execute();
//                
//                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO "+tableName+" (product_name,product_code,original_price,selling_price) values (?,?,?,?)");
//                ps2.setString(1, "Orange");
//                ps2.setString(2, "S2");
//                ps2.setInt(3, 220);
//                ps2.setInt(4, 240);
//                ps2.execute();
//            }
    
    /**
     * This method is for showing all the products into the table of main screen
     * @return everything from products table
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public ResultSet displayProducts() throws SQLException, ClassNotFoundException{
        
        if(conn==null){
            getConnection();
        }
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM "+PRODUCTS_TABLE);
    }
    
    
    /**
     * This method is for showing all the products into the table of main screen
     * @return everything from products table
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public ResultSet displayRecords() throws SQLException, ClassNotFoundException{
        
        if(conn==null){
            getConnection();
        }
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM "+SELL_TABLE);
    }
    
    
    /**
     * This method is for showing all the products into the table of main screen
     * @return everything from products table
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public ResultSet displayCustomers() throws SQLException, ClassNotFoundException{
        
        if(conn==null){
            getConnection();
        }
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM "+CUSTOMER_TABLE);
    }
    
}
