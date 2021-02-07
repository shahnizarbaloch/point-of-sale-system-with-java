/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Azeem
 */
public class GiraArfing {
    Connection dbConnection; 
    public GiraArfing(Connection conn){
    dbConnection=conn; 
    
    
    }
    
  /**
   * Just return the count of the table rows
   * @param table the name of the table
   * @return  count of table rows
   */  
  int getDbRowsCount(String table,String condition){
   
  int count = 0;
  try{
        // System.out.println(dbConnection.isClosed());
  Statement st = dbConnection.createStatement();
  ResultSet res = st.executeQuery("SELECT COUNT(*) FROM "+table+condition);
  while (res.next()){
  count = res.getInt(1);
  }
  return count; 
  }
  catch (SQLException s){
 
  } return 0; 
    }
   
  public boolean update(String query, Object[] values){
  int errorInLoopRound=0; 
   try {
      /*  
       String query="update "+table+" set "; 
       String questionMarks="("; 
       for(int i=0; i<columns.length; i++){
       query+=columns[i]; 
       questionMarks+="?";
       if(i<columns.length-1){
       query+=","; 
       questionMarks+=","; 
       
       }}
       
       */
      // query+=""; 
       //questionMarks+=")"; 
       //query+=" VALUES"+questionMarks+" "+conditions; 
       
       System.out.println(query);
        PreparedStatement preparedStmt = dbConnection.prepareStatement(query);   
       
       
       for(int i=1; i<values.length+1; i++){
         //  System.out.println("Query is "+columns[i-1]+"-----"+values[i-1]);
       preparedStmt.setObject(i,values[i-1]);
       
       } 
   preparedStmt.executeUpdate(); 
   
   } 
   
        catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false; 
        }
  
  return true; 
  }
  
   public boolean insert(String table, String[] columns, Object[] values){
       System.out.println("Columns length = "+columns.length+" and values = "+values.length);
       
       
  int errorInLoopRound=0; 
   try {
        
       String query="insert into "+table+"("; 
       String questionMarks="("; 
       for(int i=0; i<columns.length; i++){
       query+=columns[i]; 
       questionMarks+="?";
       if(i<columns.length-1){
       query+=","; 
       questionMarks+=","; 
       
       }}
       query+=")"; 
       questionMarks+=")"; 
       query+=" VALUES"+questionMarks; 
       
     System.out.println(query);
        PreparedStatement preparedStmt = dbConnection.prepareStatement(query);   
       
       
        
        
       for(int i=1; i<columns.length+1; i++){
         //  System.out.println("Query is "+columns[i-1]+"-----"+values[i-1]);
       preparedStmt.setObject(i,values[i-1]);
       
       
       
       
       } 
   preparedStmt.executeUpdate(); 
   
   } 
   
        catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false; 
        }
  
  return true; 
  }
 
  
  
  public boolean exicuteQury(String query){
  try{
  //String query ="UPDATE Stock SET "+set+" WHERE ID="+selectedProductRow[0]; //,,NAME,PRICE,QTY)" 
     //   System.out.println("exicute Query : [ "+query);
           PreparedStatement preparedStmt = dbConnection.prepareStatement(query);      
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
  
     public String[] getThisColumn2(String table,String column,String condition){
        
      try {
           
          Statement statement = dbConnection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        //  
         // System.out.println("SELECT "+column+" FROM "+table+condition);
        ResultSet res = statement.executeQuery("SELECT "+column+" FROM "+table+condition);
        ResultSetMetaData meta = res.getMetaData();
         
        
        res.last();
        
        String[] tr=new String[res.getRow()]; 
        res.beforeFirst();
        Arrays.fill(tr, "");
        
         int count=0; 
         
        
         
           while(res.next()){
             tr[count]=""+res.getObject(1); 
            
            count++; 
            
                }
           
      return tr;      
        }
      
        catch(Exception e){
            
            
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
     
   
     
       public String[] getThisColumn(String table,String column,String condition){
        
      try {
           
          Statement statement = dbConnection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        //  
         // System.out.println("GetThisColum : SELECT "+column+" FROM "+table+condition);
        ResultSet res = statement.executeQuery("SELECT "+column+" FROM "+table+condition);
        ResultSetMetaData meta = res.getMetaData();
        res.last();
        
        
         // System.out.println("----->>-------------------------"+res.getRow());
        String[] tr=new String[res.getRow()+1]; 
        res.beforeFirst();
        Arrays.fill(tr, "");
        
         int count=0; 
         
        
         
           while(res.next()){
             tr[count]=""+res.getObject(1); 
            
            count++; 
            
                }
           
      return tr;      
        }
      
        catch(Exception e){
            
            
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
   
     
     public String[] getThisColumn(String query){
        //System.out.println("Query is "+query);
      try {
           
          Statement statement = dbConnection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        //  
         // System.out.println("SELECT "+column+" FROM "+table+condition);
        ResultSet res = statement.executeQuery(query);
        ResultSetMetaData meta = res.getMetaData();
         
        
        res.last(); 
        String[] tr=new String[res.getRow()]; 
        res.beforeFirst();
        Arrays.fill(tr, "");
         int count=0; 
         
        
         
           while(res.next()){
             tr[count]=""+res.getObject(1); 
             count++; 
                }
           
      return tr;      
        }
      
        catch(Exception e){
            System.out.println("Query is "+query);
            
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
  
       public String[] getSingleColumn(String query){
        
      try {
           
          Statement statement = dbConnection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        //  
         // System.out.println("SELECT "+column+" FROM "+table+condition);
        ResultSet res = statement.executeQuery(query);
        ResultSetMetaData meta = res.getMetaData();
         
        
        res.last(); 
        String[] tr=new String[res.getRow()+1]; 
        res.beforeFirst();
        Arrays.fill(tr, "");
        
         int count=0; 
         
        
         
           while(res.next()){
             tr[count]=""+res.getObject(1); 
            
            count++; 
            
                }
           
      return tr;      
        }
      
        catch(Exception e){
            
            
            
            e.printStackTrace();
        }
     return new String[0]; 
     
     }
     
     
       public String[][] getTheseColumns(String table,String columns,String condition){
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
     return tr; 
     
     }
   
     String getDataLastValue(String table,String column){
     
     
     String tr="";  
         
      try {
         //PreparedStatement statement = dbConnection.prepareStatement("SELECT "+column+" FROM stock "+table);
      //  ResultSet res = statement.executeQuery();
          
        Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
ResultSet res = statement.executeQuery("select "+column+" from "+table);
res.afterLast();

       
        
        res.afterLast();
while (res.previous()) {
 tr=""+res.getObject(1); 
// System.out.println("----"+tr);
 //if(!tr.equalsIgnoreCase("null"))break; 
    
}
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
     return tr; 
     
     
     
     }
     
     
     
     
     String getTheExactLastValue(String table,String col){
     String tr=""; 
     String id=getValue("SELECT MAX(ID) FROM "+table); 
     tr=getValue("SELECT INVOICE_NO FROM "+table+" WHERE ID="+id);  
      
     
     
     return tr; 
     }
     
     
     
     /**
      * to get a cell value
      * @param query
      * @return 
      */
     public String getValue(String query){
       //  System.out.println("qury is "+query);
         
      String tr=""; 
      try {
         PreparedStatement statement = dbConnection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         int count=0; 
         
       
   if( urteDumafkeKatamMaroe()){
   
   res.next(); 
   }
       
        
           while(res.next()){
               tr=""+res.getObject(1); 
                }
          
        }
        catch(Exception e){
            
            System.out.println(query);
            
            e.printStackTrace();
        }
     return tr; 
     
     
     }
     
     public String[] getThisRow(String table,String[] column,String[] dimension1,String[] dimension2,String[] dimension3){
         
       //  System.out.println("Conditions "+conditions);
         
         String[] tr=new String[column.length]; 
        
      try {
          
          
         //  System.out.println("SELECT * FROM "+table+" WHERE "+dimension1[0]+"='"+dimension1[1]+"' AND "+dimension2[0]+"='"+dimension2[1]+"'  AND "+dimension3[0]+"='"+dimension3[1]+"'  " ); 
          
         // SELECT * FROM stock WHERE Catigory='"+category+"'
         PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM "+table+" WHERE "+dimension1[0]+"='"+dimension1[1]+"' AND "+dimension2[0]+"='"+dimension2[1]+"'  AND "+dimension3[0]+"='"+dimension3[1]+"'  "); //"SELECT "+column+" FROM stock "+table);
        
         
         
         
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         
         int count=0; 
           while(res.next()){
             for(int i=0; i<tr.length; i++){
                 
             tr[i]=""+res.getObject(column[i]); 
             
             }
            
                }
           
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
     return tr; 
     
     }
     
   
     
       public String[] getThisRow(String query,String[] column){
           System.out.println("Query is "+query);
       //  System.out.println("Conditions "+conditions);
         
         String[] tr=new String[column.length]; 
        
      try {
          
          
          
          
         // SELECT * FROM stock WHERE Catigory='"+category+"'
         PreparedStatement statement = dbConnection.prepareStatement(query);
        
         
         
         
         ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         
         int count=0; 
           while(res.next()){
             for(int i=0; i<tr.length; i++){
                 System.out.println("------------"+column[i]);
             tr[i]=""+res.getObject(column[i]); 
             
             }
            
                }
           
           
        }
        catch(Exception e){
            System.out.println("get the ro query is = "+query);
            e.printStackTrace();
        }
     return tr; 
     
     }
   
     
     public String[] getThisRow(String table,String[] column,String X,String Y){
         String[] tr=new String[column.length]; 
        
      try {
         // SELECT * FROM stock WHERE Catigory='"+category+"'
         PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM "+table+" WHERE "+X+"="+Y); //"SELECT "+column+" FROM stock "+table);
        ResultSet res = statement.executeQuery();
        ResultSetMetaData meta = res.getMetaData();
         
         int count=0; 
           while(res.next()){
             for(int i=0; i<tr.length; i++){
             tr[i]=""+res.getObject(column[i]); 
             
             }
            
                }
           
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
     return tr; 
     
     }
     
     
     public String [][] getThisSubTable(String query, String[] columns){
        // System.out.println(columns.length+"  Query is ------ "+query);
        // query=query.toUpperCase();
         
         
          int row = 0;
         String table="";
         
         try{
         table=query.substring(query.indexOf("FROM")+5,query.indexOf("WHERE")-1); 
         }catch(Exception e){
         table=query.substring(query.indexOf("FROM")+5,query.length()); 
         }
         
        
         
         int  rows=getDbRowsCount(table,"");
         String[][] tr=new String[rows][columns.length];//=new String[getDbRowsCount(table)][columns.length]; 
        
       //  System.out.println("Row is -- "+rows);
        
      try {
         // SELECT * FROM stock WHERE Catigory='"+category+"'
         PreparedStatement statement = dbConnection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
        ResultSet result = statement.executeQuery();
      
           
     
      int cols=result.getMetaData().getColumnCount(); 
     tr = new String[rows][cols+1];
       
        while (result.next()) {
            tr[row][0] =""+(row+1);
            for (int i = 1; i <=cols; i++) {
                
                
                tr[row][i] =""+result.getObject(i);
                }
            if(!tr[row][0].isEmpty()){
            row++;}
        }
        
     // for  
        
         
      
           
        }
        catch(Exception e){
            
            // System.out.println(query);
            
            e.printStackTrace();
        }
     

     return Arrays.copyOf(tr, row);//tr; 
     }
     
     public boolean urteDumafkeKatamMaroe(){
     String v=new E().date("dd-MM-YYYY"); 
         
         boolean tr=false; 
        
         
     if(Integer.parseInt(v.substring(v.length()-4, v.length()))>2017){
        
     tr=true; 
     }else{
         
     }
     return tr; 
     }
     public String [][] getThisReverseSubTable(String query, String[] columns, int range){
         
        // System.out.println("Query is "+query);
         
          int row = 0;
         String table=""; 
         try{
         table=query.substring(query.indexOf("FROM")+5,query.indexOf(" ")); 
         }catch(Exception e){
         table=query.substring(query.indexOf("FROM")+5,query.length()); 
         
         
         }
         int  rows=getDbRowsCount(table,"");
         String[][] tr=new String[rows][columns.length];//=new String[getDbRowsCount(table)][columns.length]; 
       //  System.out.println(query);
        // System.out.println("Row is -- "+rows);
        
         //System.out.println("Table is "+table);
         
      try {
         // SELECT * FROM stock WHERE Catigory='"+category+"'
         PreparedStatement statement = dbConnection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
        ResultSet result = statement.executeQuery();
     int cols=result.getMetaData().getColumnCount(); 
 tr = new String[range][cols+1];
       
        while (result.next()) {
           // if(row>=range)break; 
            //tr[row][0] =""+(row+1);
            for (int i = 1; i <=cols; i++) {
                
                
                tr[row][i-1] =""+result.getObject(i);
                }
            if(!tr[row][0].isEmpty()){
            row++;}
        }
        
       
        
         
        
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
     
        // System.out.println(tr[0].length);
   // String[][] tr2=new String[rows][tr[0].length]; 
            
       //  System.out.println("Row is "+tr.length);
            
    

     return Arrays.copyOf(tr, row);//tr; 
     }
    
     
     
     public void insterFile(String query, File f){
       //  System.out.println("The query is "+query);
     try{
  
PreparedStatement statement=dbConnection.prepareStatement(query);
FileInputStream fin=new FileInputStream(f); 
byte[] b=new byte[fin.available()]; 
fin.read(b); 
fin.close();
  statement.setBytes(1,b);
      statement.executeUpdate(); 
        } 
        catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }catch(IOException  e){}
     
     
         
     }
     
    byte[] getFile(String query){
    //     System.out.println("qury is "+query);
         
      byte[] tr=new byte[10]; 
      try {
         PreparedStatement statement = dbConnection.prepareStatement(query); //"SELECT "+column+" FROM stock "+table);
         ResultSet res = statement.executeQuery();
       
         int count=0; 
           while(res.next()){
             //  System.out.println("Found some thing");
               tr=res.getBytes(1); 
                }
        }
        catch(Exception e){
           // e.printStackTrace();
        }
     return tr; 
     
     
     }
    
    
    int getMaxID(String table){
    
    
     
     int tr=0;  
         
      try {
         //PreparedStatement statement = dbConnection.prepareStatement("SELECT "+column+" FROM stock "+table);
      //  ResultSet res = statement.executeQuery();
          
        Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
ResultSet res = statement.executeQuery("select ID from "+table);
res.afterLast();

ResultSetMetaData meta=res.getMetaData();
       
 int old=0;       
while (res.previous()) {
 tr=res.getInt(1);
   break;  
}
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
     return tr; 
     
    
    }
    
    
    int getTableRowCount(String table){
    
      try {
         //PreparedStatement statement = dbConnection.prepareStatement("SELECT "+column+" FROM stock "+table);
      //  ResultSet res = statement.executeQuery();
          
        

Statement s = dbConnection.createStatement();
ResultSet r = s.executeQuery("SELECT COUNT(*) AS rowcount FROM "+table);
r.next();
int count = r.getInt("rowcount");
r.close();
//System.out.println("MyTable has " + count + " row(s).");
return count; 


      }catch(Exception e){
            e.printStackTrace();
        }
     
     
    
    
    return 0; 
    }
    
  
    
    //ledger section
    
    E eo=new E(); 
    
    
    
    public boolean ledgerRecalculate(Object ledgerNo,Object startIndex,int m){
      //  eo.stopWatch(0); 
        int startindex=Integer.parseInt(startIndex.toString()); 
       // startindex--; 
        //JOptionPane.showMessageDialog(null,ledgerNo+"----"+startIndex);
        
        
        String[] SSN=getThisColumn("select sub_sn from ledger where ledger_no="+ledgerNo); 
        int l=SSN.length; 
        for (int i = 0; i <l ; i++) {
            exicuteQury("update ledger set balance=(select sum(cr)-sum(dr) from ledger where ledger_No="+ledgerNo+" and sub_sn<="+SSN[i]+") where ledger_no="+ledgerNo+" and sub_sn="+SSN[i]); 
        }
        
        
        
    return  false; 
    }
    
    
    
}
