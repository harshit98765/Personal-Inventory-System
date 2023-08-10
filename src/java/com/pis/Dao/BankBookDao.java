
package com.pis.Dao;

import com.pis.Bean.BankBookBean;
import com.pis.Bean.CashBookBean;
import static com.pis.Dao.CashBookDao.con;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankBookDao {
   
    
 static Connection con;
 // method for insert the data 
  public int InsertData(BankBookBean bb){
      int result =0;
      con =ConnectionPool.connectDB();
   
     String sql=" insert into bank_book(account,transaction_date,amount,userid,operation)values('"+bb.getAccount()+"','"+bb.getTransaction_date()+"','"+bb.getAmount()+"','"+bb.getUserid()+"','"+bb.getOperation()+"')";                                                    
     try {
         Statement stmt = con.createStatement();
         result=stmt.executeUpdate(sql);
         con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
    return result;         
  }
  

  
   // method for the data update 
     public int UpdateEmployee(BankBookBean bb) {
        int result = 0;

        con = ConnectionPool.connectDB();
        String sql = "update bank_book set account='"+bb.getAccount()+"',transaction_date='"+bb.getTransaction_date()+"',amount='"+bb.getAmount()+"',userid='"+bb.getUserid()+"',operation='"+bb.getOperation()+"' where acid='"+bb.getAcid()+"'"; //where acid='"+bb.getAcid()+"'

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     // method to delete the data in the database 
     public int DeleteEmployee(int acid){
          int result = 0;
          con=ConnectionPool.connectDB();
         String sql="delete from bank_book where acid='"+acid+"'";
     try {
         Statement stmt=con.createStatement();
         result =stmt.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
    return result ; 
     }
     
     // method to show the data of the table 
     public void FindAllBankBook(){
         con= ConnectionPool.connectDB();
         String sql="select * from bank_book" ;
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
               System.out.println("\tAccount id : "+rs.getInt("acid")+"\tAccount : "+rs.getString("account")+"\t Transaction Date  : "+rs.getString("transaction_date")+" \t Amount : "+rs.getDouble("amount")+"\t UserId : "+rs.getInt("userid")+"\t Operation :"+rs.getString("operation"));
             
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      
     }
     
     // method to find all data inthe form of arraylist
      public ArrayList<BankBookBean> FindAllBankBookArray(){
         con= ConnectionPool.connectDB();
         String sql="select * from bank_book" ;
         ArrayList<BankBookBean> al=new ArrayList<BankBookBean>();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                BankBookBean bb=new BankBookBean();
                bb.setAcid(rs.getInt("acid"));
                bb.setAccount(rs.getString("account"));
                bb.setTransaction_date(rs.getString("transaction_date"));
                bb.setAmount(rs.getDouble("amount"));
                bb.setUserid(rs.getInt("userid"));
                bb.setOperation(rs.getString("operation"));              
              al.add(bb);
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      return al;
     }
      
      // method to find the data by accound id
      public BankBookBean FindbyAcid(int acid){
         con= ConnectionPool.connectDB();
         String sql="select * from bank_book where acid='"+acid+"'" ;
          BankBookBean bb=new BankBookBean();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){  
                 bb.setAccount(rs.getString("account"));
                bb.setTransaction_date(rs.getString("transaction_date"));
                bb.setAmount(rs.getDouble("amount"));
                bb.setUserid(rs.getInt("userid"));
                bb.setOperation(rs.getString("operation"));           
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     return bb;
     }
     
     // method to show data of only selected employee by thier empno
      public void FindoneData(int acid){
          con=ConnectionPool.connectDB();
          String sql="select * from bank_book where acid='"+acid+"'";
  
    try {
      
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next()){
               System.out.println("\tAccount id : "+rs.getInt("acid")+"\tAccount : "+rs.getString("account")+"\t Transaction Date  : "+rs.getString("transaction_date")+" \t Amount : "+rs.getDouble("amount")+"\t UserId : "+rs.getInt("userid")+"\t Operation :"+rs.getString("operation"));
               
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     
      }

       
   // method to find all date by date (start date to end date)
       public BankBookBean FindallDatewaise(String sdate,String edate ,int userid){
         con= ConnectionPool.connectDB();
       //  String sql="select * from bank_book where acid='"+acid+"'" ;
         String sql= "select  * from bank_book  where transaction_date between '"+sdate+"' and '"+edate+"' and userid = '"+userid+"'";

          BankBookBean bb=new BankBookBean();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){  
                 bb.setAccount(rs.getString("account"));
                bb.setTransaction_date(rs.getString("transaction_date"));
                bb.setAmount(rs.getDouble("amount"));
                bb.setUserid(rs.getInt("userid"));
                bb.setOperation(rs.getString("operation"));           
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     return bb;
     }
     
        public ArrayList<BankBookBean> FindAllBankBookDatewise(String sdate,String edate ,int userid) {
        con = ConnectionPool.connectDB();
        String sql= "select  * from bank_book  where transaction_date between '"+sdate+"' and '"+edate+"' and userid = '"+userid+"'";

        ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BankBookBean bb = new BankBookBean();
                bb.setAcid(rs.getInt("acid"));
                bb.setAccount(rs.getString("account"));
                bb.setTransaction_date(rs.getString("transaction_date"));
                bb.setAmount(rs.getDouble("amount"));
                bb.setUserid(rs.getInt("userid"));
                bb.setOperation(rs.getString("operation"));
//              
                al.add(bb);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
          
        public double ClosingBalance(int userid) {
        double balance = 0;

        con = ConnectionPool.connectDB();
       String sql = "select ((select coalesce(sum(amount), 0) as total_receive from bank_book b where userid = '"+userid+"' and operation = 'receive') - (select coalesce(sum(amount), 0) as total_send from bank_book b where userid = '"+userid+"' and operation = 'pay')) as closing_balance from dual";
 
      try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("closing_balance");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
     
       
    public static void main(String[] args) {
        BankBookBean bb = new BankBookBean();
       bb.setAccount("552628");
       bb.setAmount(25000);
       bb.setOperation("upi");
       bb.setTransaction_date("2022-06-16");
       bb.setUserid(2834);
      bb.setAcid(1);

        BankBookDao bd = new BankBookDao();

        bd.InsertData(bb);
     //   bd.UpdateEmployee(bb);
     //  bd.DeleteEmployee(2);
   //  bd.FindAllBankBook();
   //bd.FindAllBankBookArray();
   // bd.FindbyAcid(1);
  // bd.FindoneData(1);
 //   bd.FindallDatewise("2022-03-16", "2022-06-06", 1834);
 
// ArrayList<BankBookBean>  x= bd.FindAllBankBookDatewise("2022-01-01", "2024-01-01", 2);
//        for(BankBookBean cbb:x){
//        System.out.println(cbb.getUserid());
//        System.out.println(cbb.getAmount());
//       }
//   
}}
