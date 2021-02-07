package quettainstitute;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class Database {
    public boolean connected=false; 
    public Connection connection; 
    public String dbLocation=""; 
    public boolean disabled=true; 
    
    
    public Database(){
    //connectToRemoteDatabase("localhost",1527); 
    
    }
    public  Database(Connection conn){
    connection=conn; 
    
    }
    
    //connect as server mode
    
   
    
    
    
    //jus for prectice
    public Database(String path){
    connectToLocalDatabase(path); 
    
    }
    
    public void connectToLocalDatabase(String path){
     String dbPath=path+"\\ABM_Database";  
        System.out.println("Path is = "+dbPath);
     String USER="azeem"; 
     String PASS="azeem"; //"0064372";// generateUserPassword(locadScInfo(path+"\\derby.sc"))[1];
     System.out.println("User = "+USER);
     System.out.println("PASS = "+PASS);
       final String JDBC_DRIVER ="com.mysql.jdbc.Driver";  
      final String DB_URL ="jdbc:derby:"+dbPath; 
      
     
      Statement stmt = null;
    try{
        
       
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
          // Open a connection
      System.out.println("Connecting to database...");
      connection = DriverManager.getConnection(DB_URL,USER,PASS);
      //tafoak=!con.isClosed(); 
        System.out.println("Connected");
  connected=true; 
     }catch(SQLException se){
  connected=false;        
         
         se.printStackTrace();
         
         try{
         if(se.getNextException().getMessage().contains("already booted")){
          JOptionPane.showMessageDialog(null, "Programe is already running");
          System.exit(0);
         }
         }catch(NullPointerException e){}
   
   
    }catch(Exception e){
        e.printStackTrace();
   
    }
 }    
     public void connectToAnyDatabase(String path,String user, String pass){
     //String dbPath=path+"\\ABM_Database";  
       // System.out.println("Path is = "+dbPath);
     String USER=user; 
     String PASS=pass; //"0064372";// generateUserPassword(locadScInfo(path+"\\derby.sc"))[1];
     
      // final String JDBC_DRIVER ="com.mysql.jdbc.Driver";  
      final String DB_URL ="jdbc:derby:"+path; 
      
         //System.out.println(DB_URL);
      Statement stmt = null;
    try{
        
       
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
          // Open a connection
     // System.out.println("Connecting to database...");
      connection = DriverManager.getConnection(DB_URL,USER,PASS);
      //tafoak=!con.isClosed(); 
        System.out.println("Connected");
  connected=true; 
     }catch(SQLException se){
  connected=false;        
         
         se.printStackTrace();
         
         try{
         if(se.getNextException().getMessage().contains("already booted")){
          JOptionPane.showMessageDialog(null, "Programe is already running");
          System.exit(0);
         }
         }catch(NullPointerException e){}
   
   
    }catch(Exception e){
        e.printStackTrace();
   
    }
 }    
    
    
    public boolean showQuery=true; 
  public   boolean exicuteQury(String query){
      //Thread.dumpStack();
      System.out.println(query);
  try{
  //String query ="UPDATE Stock SET "+set+" WHERE ID="+selectedProductRow[0]; //,,NAME,PRICE,QTY)" 
      if(showQuery){System.out.println("exicute Query : [ "+query);}
           PreparedStatement preparedStmt = connection.prepareStatement(query);      
          preparedStmt.executeUpdate(); 
          preparedStmt.close();
        
        } 
        catch ( SQLException e) {
            
            // TODO Auto-generated catch block
            System.out.println(query);
            e.printStackTrace();
            return false; 
        }
  
  return true; 
  }
    
    
/**
 * Connect to database. 
 * @param ip
 * @param port
 * @return 
 */    
     public boolean connectToRemoteDatabase(String ip, int port){
        // System.out.println("ip is "+ip);
       //  System.out.println("port is "+ip);
 //       System.out.println("Database path is "+settings.getProperty("DBLocation"));
         //String path=databaseLocation; 
       //final String JDBC_DRIVER ="com.mysql.jdbc.Driver";  
     final String DB_URL ="jdbc:derby://"+ip+":"+port+"//ABM_Database";
        //final String DB_URL ="jdbc:derby:D://Database//ABM_Database";
         //System.out.println("DB is "+DB_URL);
   //     final String DB_URL ="jdbc:derby://localhost:1527//LibraryDatabase";
     
      Statement stmt = null;
    try{
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
          // Open a connection
     // System.out.println("Connecting to database...");
      connection = DriverManager.getConnection(DB_URL,"azeem","azeem");
      
      connected=true; 
      //  System.out.println("Connected db");
     return true; 
     }catch(SQLException se){
         se.printStackTrace();
         connected=false; 
   return false;
    }catch(Exception e){
        e.printStackTrace();
        return false; 
    }
    }
      
    
     
      public boolean connectToRemoteDatabase(String ip, int port,String db){
      //   System.out.println("ip is "+ip);
      //   System.out.println("port is "+ip);
 //       System.out.println("Database path is "+settings.getProperty("DBLocation"));
         //String path=databaseLocation; 
       //final String JDBC_DRIVER ="com.mysql.jdbc.Driver";  
     final String DB_URL ="jdbc:derby://"+ip+":"+port+"//"+db;
        //final String DB_URL ="jdbc:derby:D://Database//ABM_Database";
         //System.out.println("DB is "+DB_URL);
   //     final String DB_URL ="jdbc:derby://localhost:1527//LibraryDatabase";
     
      Statement stmt = null;
    try{
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
          // Open a connection
     // System.out.println("Connecting to database...");
      connection = DriverManager.getConnection(DB_URL,"azeem","azeem");
      connected=true; 
      //  System.out.println("Connected db");
     return true; 
     }catch(SQLException se){
         se.printStackTrace();
         connected=false; 
   return false;
    }catch(Exception e){
        e.printStackTrace();
        return false; 
    }
    }
    
     
      
      
      
      // INSERT -------------------------------------------------------------------------
      /**
       * to insert some value
       * @param query
       * @param v
       * @return 
       */
       public boolean update(String query,Object v[]){
  try{
  //String query ="UPDATE Stock SET "+set+" WHERE ID="+selectedProductRow[0]; //,,NAME,PRICE,QTY)" 
       // System.out.println("exicute Query : [ "+query);
           PreparedStatement preparedStmt = connection.prepareStatement(query); 
           int l=v.length; 
           for (int i = 0; i < l; i++) {
               preparedStmt.setObject(i+1, v[i]);
            }
        
          preparedStmt.executeUpdate(); 
        
        } 
        catch ( SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(query);
            e.printStackTrace();
            return false; 
        }
  
  return true; 
  }
       
       
           public boolean insert(String query,Object v[]){
  try{
  //String query ="UPDATE Stock SET "+set+" WHERE ID="+selectedProductRow[0]; //,,NAME,PRICE,QTY)" 
       // System.out.println("exicute Query : [ "+query);
           PreparedStatement preparedStmt = connection.prepareStatement(query); 
           int l=v.length; 
           for (int i = 0; i < l; i++) {
               preparedStmt.setObject(i+1, v[i]);
            }
           
          preparedStmt.executeUpdate(); 
        
        } 
        catch ( SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(query);
            e.printStackTrace();
            return false; 
        }
  
  return true; 
  }

      
      // SELECT OR GET----------------------------------
        /**
         * get some value out from database. 
         * @param query query to be executed. 
         * @return  the result value
         */
        public Object get(String query){
        if(showQuery) System.out.println("qury is "+query);
        
        
      //String tr=""; 
      try {
         PreparedStatement statement = connection.prepareStatement(query); 
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
        
        if(res.next()){
        Object tr=res.getObject(1); 
        statement.close();
        res.close();
        return tr; 
        }
         
        
      }
        catch(Exception e){
           
            System.out.println(query);
            e.printStackTrace();
        }
     return ""; 
     
     
     }
        
        
        
        
        
        
         /**
      * to get and entire row from a table. 
      * @param query   query
      * @param column   and array which contains the titles of columns. 
      * @return 
      */
      public ArrayList<Object> getRow(String query){
         if(showQuery){ System.out.println("========== [ "+query);}
       
          ArrayList<Object> tr=new ArrayList<Object>(); 
      try {
    
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         //int count=0; 
         int cols=meta.getColumnCount(); 
         
           while(res.next()){
             for(int i=1; i<=cols; i++){
                 tr.add(res.getObject(i)); 
             }
      }
      statement.close();
      res.close();
      
      
      }
      
      
      
                 catch(Exception e){
            
            e.printStackTrace();
        }
     return tr; 
     
     }
    
        
        public Object[][]  getTable(String query){
            if(showQuery){System.out.println("Query is = "+query);}

      try {
         PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //prepareStatement(query);
       //System.out.println("Query is = [ "+query);
        ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
        int colCount=meta.getColumnCount(); 
         //int count=0; 
         res.last();
         int rowCount=res.getRow(); 
         res.beforeFirst(); 
         //res.previous(); 
         Object[][] td=new Object[rowCount][colCount]; 
         int count=0; //count of the row to be filled. 
         
           while(res.next()){
               for(int i=1; i<=colCount; i++){
                   td[count][i-1]=res.getObject(i);
                   
               }
                count++;
           }
           
           statement.close();
           res.close();
      return td; 
      }
        catch(Exception e){
         e.printStackTrace();
        }
      return new Object[0][0]; 
     }  
      
     /**
      * Go get a single column from the database. 
      * @param query SQL query.
      * @return the Object array of result set. 
      */   
       public  Object[] getColumn(String query){
             if(showQuery){System.out.println("Query ==== "+query);}
      try {
           
          Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
     
        ResultSet res = statement.executeQuery(query);
        ResultSetMetaData meta = res.getMetaData();
         
        int cc=meta.getColumnCount();
        
        
        
        
        Object[] tr=new Object[cc]; 
         
        
        res.beforeFirst();
         int count=0; 
         
           while(res.next()){
               for (int i = 0; i < cc; i++) {
                   tr[i]=res.getObject(i+1); 
               }
             
//             count++; 
                }
           statement.close();
           res.close();
      return tr;      
        }
      
        catch(Exception e){
            System.out.println("Query is "+query);
          //  JOptionPane.showMessageDialog(null,query);
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
        public  Object[] getColumnTemp(String query){
             if(showQuery){System.out.println("Query ==== "+query);}
      try {
           
          Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
     
        ResultSet res = statement.executeQuery(query);
        ResultSetMetaData meta = res.getMetaData();
         
        
        res.last(); 
        Object[] tr=new Object[res.getRow()]; 
          System.out.println("tr ===== length is = "+tr.length);
        
        res.beforeFirst();
         int count=0; 
         
           while(res.next()){
             tr[count]=res.getObject(1); 
             count++; 
                }
           statement.close();
           res.close();
      return tr;      
        }
      
        catch(Exception e){
            System.out.println("Query is "+query);
          //  JOptionPane.showMessageDialog(null,query);
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
       /**
      * to get a cell value
      * @param query
      * @return 
      */
     public Object getValue(String query){
         if(showQuery){System.out.println("qury is "+query);}
      Object tr=new Object(); 
      try {
          
         PreparedStatement statement = connection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         int count=0; 
        
      while(res.next()){
               tr=res.getObject(1); 
                }
      statement.close();
      res.close();
      }
        catch(Exception e){
           
            //System.out.println(query);
            //e.printStackTrace();
        }
     return tr; 
     
     
     }
   public byte[] getFile(String query){
    if(showQuery){     System.out.println("qury is "+query);}
         
      byte[] tr=new byte[10]; 
      try {
         PreparedStatement statement = connection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
         ResultSet res = statement.executeQuery();
         int count=0; 
           while(res.next()){
             //  System.out.println("Found some thing");
               tr=res.getBytes(1); 
                }
           statement.close();
           res.close();
        }
        catch(Exception e){
           // e.printStackTrace();
        }
     return tr; 
     }
        
       
   
   /**
    * 
    * @param query
    * @return 
    */
   
   
    public Object[] getFileData(String query){
             if(showQuery){System.out.println("Query ==== "+query);}
        
      try {
        Blob blob = connection.createBlob();   
          Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
     
        ResultSet res = statement.executeQuery(query);
        ResultSetMetaData meta = res.getMetaData();
         res.last(); 
         int rc=res.getRow(); 
        res.beforeFirst();
       
        Object[] tr=new Object[rc]; 
        res.beforeFirst();
        int count=0; 
           while(res.next()){
               tr[count]=res.getBytes("DATA"); 
               count++; 
           }
            statement.close();
           res.close();
      return tr;      
        }
      
        catch(Exception e){
            System.out.println("Query is "+query);
          //  JOptionPane.showMessageDialog(null,query);
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
   
    
     String[][] getTheseColumns(String table,String columns,String condition){
    
         String[][] tr=new String[1][1]; 
         
         /*
         int l=columns.split(",").length; 
         String[][] tr=new String[getDbRowsCount(table,condition)][l]; 
         
        // System.out.println("The count is "+tr.length);
      try {
          
        
          
          //System.out.println("Length is "+l);
        //  System.out.println("Statement is "+"SELECT "+columns+" FROM "+table+condition);
          //System.out.println("SELECT "+column+" FROM stock "+table); 
         PreparedStatement statement = dbConnection.prepareStatement("SELECT "+columns+" FROM "+table+condition);
          
         
         
        ResultSet res = statement.executeQuery();
          //System.out.println("fetch size is "+res.getFetchSize()); 
        ResultSetMetaData meta = res.getMetaData();
        
         int count=0; 
         
             
        
         
      
         
           while(res.next()){
               for(int i=1; i<=l; i++){
             tr[count][i-1]=""+res.getObject(i); 
               }
            count++; 
            
               
            
            
                }
         
           
           
        }
        catch(Exception e){
            
            
            
            e.printStackTrace();
        }
                 */
     return tr; 
     
     }
   public String getValueAndCheckConnection(String query){
      String tr=""; 
      try {
         PreparedStatement statement = connection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
      while(res.next()){
               tr=""+res.getObject(1); 
                }
      statement.close();
      res.close();
      }
        catch(Exception e){
            connected=false; 
        }
     return tr; 
     }
     
    
   
}
