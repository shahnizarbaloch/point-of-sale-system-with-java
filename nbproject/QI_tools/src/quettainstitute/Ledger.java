/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

import javax.swing.JOptionPane;

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
            //if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=BALANCE+"+amount+" WHERE id="+ref)){
        return true;     
            
            //}
        
        }
    return false; 
    
    }
    
  
      public boolean doTransction(int section, int ref, double amount,String dated,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("INSERT INTO TRANSACTIONS (SECTION,REF,AMOUNT,ADMIN_ID,DATED,TIMED,DESCRIPTION) VALUES ("+section+","+ref+","+amount+","+admin+",'"+dated+"',CURRENT_TIME,'"+description+"' )")){
            /*if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=BALANCE+"+amount+" WHERE id="+ref)){
            */
            
        return true;     
            
            //}
        
        }
    return false; 
    
    }
  
    
    
    public boolean doTransction(int section, int ref, double amount,int admin,String description,int billNo){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("INSERT INTO TRANSACTIONS (SECTION,REF,AMOUNT,ADMIN_ID,DATED,TIMED,DESCRIPTION) VALUES ("+section+","+ref+","+amount+","+admin+",CURRENT_DATE,CURRENT_TIME,'"+description+"')")){
            /*
            if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=BALANCE+"+amount+" WHERE id="+ref)){
            */
        return true;     
            
            //}
        
        }
    return false; 
    
    }
    
    
    /**
     * adds some bill to ledger
     * @param billSN bill serial no or invoice no
     * @param ledgerID id of the ledger
     * @return the transaction no. 
     */
    public int addBillToLedger(int section,int billID,int ledgerID,double amount,int admin,String description){
    
     if(dbl.exicuteQury("INSERT INTO TRANSACTIONS (SECTION,REF,AMOUNT,ADMIN_ID,DATED,TIMED,DESCRIPTION,FROM_LEDGER) VALUES ("+section+","+ledgerID+",-"+amount+","+admin+",(SELECT DATED FROM BILL WHERE ID="+billID+" ),(SELECT TIMED FROM BILL WHERE ID="+billID+"),'"+description+"',FALSE)")){
            //if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE=BALANCE-"+amount+" WHERE id="+ledgerID)){
                try{
 int obj=(int)dbl.get("SELECT MAX(ID) FROM TRANSACTIONS WHERE SECTION="+section+" AND REF = "+ledgerID+" AND AMOUNT = -"+amount);                     
 if(dbl.exicuteQury("UPDATE BILL SET LEDGER_ID = "+ledgerID+" , TRANSACTION_NO = "+obj+" WHERE ID="+billID)){
 return obj; 
 }
                return(int)obj; 
                 }catch(Exception e){
                 e.printStackTrace();
                 
                 //}
        //return true;     
            
            }
            
        
        }
            
    
    return 0; 
    }
    
    
    
    
    
    
    public boolean updateTransction(Object id,int section,int ref, double amount,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("UPDATE TRANSACTIONS SET AMOUNT="+amount+",ADMIN_ID="+admin+",DATED=CURRENT_DATE,TIMED=CURRENT_TIME,DESCRIPTION='"+description+"' WHERE id="+id)){
            /*
            Object total=dbl.get("SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE SECTION="+section+" AND REF="+ref); 
            
            if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE="+total+"  WHERE id="+ref)){
            */
        return true;     
            //}
        
        }
    return false; 
    
    }
    
    
      public boolean updateTransction(Object id,int section,int ref, double amount,String dated,int admin,String description){
    //    if(description.isEmpty()){description="-"; }
        if(dbl.exicuteQury("UPDATE TRANSACTIONS SET AMOUNT="+amount+",ADMIN_ID="+admin+",DATED='"+dated+"',TIMED=CURRENT_TIME,DESCRIPTION='"+description+"' WHERE id="+id)){
            
            /*Object total=dbl.get("SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE SECTION="+section+" AND REF="+ref); 
            
            if(dbl.exicuteQury("UPDATE LEDGER SET BALANCE="+total+"  WHERE id="+ref)){
                    */
        return true;     
            //}
                    
        
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
    
    
    public boolean recolculateAndFixLadegerIndexAmount(int ledgerID){
        if(true)return true; 
    double exactAmount=0; 
    double indexAmount=0; 
    
        try{
    exactAmount=(double)dbl.get("SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE REF="+ledgerID+" AND SECTION = "+SectionNo.LEDGER); 
    indexAmount=(double)dbl.get("SELECT BALANCE FROM LEDGER WHERE  ID="+ledgerID); 
        }catch(Exception e){}
        
    if(indexAmount!=exactAmount){
    return dbl.exicuteQury("UPDATE LEDGER SET BALANCE="+exactAmount+" WHERE ID="+ledgerID); 
    }else{
    return false; 
    }
    
    }

    public boolean removeBillFromLedger(Object billID){
    
    try{
    int ledgerID=(int)dbl.get("SELECT LEDGER_ID FROM BILL WHERE ID="+billID); 
    int transactionID=(int)dbl.get("SELECT TRANSACTION_No FROM BILL WHERE ID="+billID); 
    if(ledgerID>0 && transactionID>0){
    dbl.exicuteQury("DELETE FROM TRANSACTIONS WHERE ID="+transactionID); 
    dbl.exicuteQury("UPDATE BILL SET LEDGER_ID=-1 , TRANSACTION_NO=-1 WHERE ID="+billID); 
    recolculateAndFixLadegerIndexAmount(ledgerID); 
    }
    }catch(Exception e){
    return false; 
    }
    return true; 
    }
    
}
