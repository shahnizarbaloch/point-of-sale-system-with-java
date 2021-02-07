/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

/**
 *
 * @author Java
 */
public class Ledger {
    Database dbl; 
    public Ledger(Database db){
    dbl=db; 
    
    }
    
    public boolean doTransction(int section, int ref, double amount,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("INSERT INTO TRANSACTIONS (SECTION,REF,AMOUNT,ADMIN_ID,DATED,TIMED,DESCRIPTION) VALUES ("+section+","+ref+","+amount+","+admin+",CURRENT_DATE,CURRENT_TIME,'"+description+"' )")){
            if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=BALANCE+"+amount+" WHERE account_no="+ref)){
        return true;     
            
            }
        
        }
    return false; 
    
    }
    
    public boolean updateTransction(Object id,int section,int ref, double amount,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("UPDATE TRANSACTIONS SET AMOUNT="+amount+",ADMIN_ID="+admin+",DATED=CURRENT_DATE,TIMED=CURRENT_TIME,DESCRIPTION='"+description+"' WHERE id="+id)){
            if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=(SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE SECTION="+section+" AND REF="+ref+")  WHERE account_no="+ref)){
        return true;     
            }
        
        }
    return false; 
    
    }
    
    
    
    public boolean deleteTransaction(Object transactionID){
    
    return dbl.exicuteQury("DELETE FROM TRANSACTIONS WHERE ID="+transactionID); 
    
    }
    
    
    
    public boolean updateTransction(Object id, double amount,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
    return dbl.exicuteQury("UPDATE TRANSACTIONS SET AMOUNT="+amount+",ADMIN_ID="+admin+",DATED=CURRENT_DATE,TIMED=CURRENT_TIME,DESCRIPTION='"+description+"'");  
    
    }
    
    
    
    
}
