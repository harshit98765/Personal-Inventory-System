package com.pis.Dao;

import com.pis.Bean.CashBookBean;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashBookDao {

    static Connection con;
    
    // method for insert the data 
    public int InsertData(CashBookBean cb) {
        int result = 0;
        con = ConnectionPool.connectDB();

        String sql = " insert into cash_book(account,transaction_date,amount,userid,operation)values('" + cb.getAccount() + "','" + cb.getTransaction_date() + "','" + cb.getAmount() + "','" + cb.getUserid() + "','" + cb.getOperation() + "')";
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method for the data update 
    public int UpdateCashBook(CashBookBean cb) {
        int result = 0;

        con = ConnectionPool.connectDB();
        String sql = "update cash_book set account='" + cb.getAccount() + "',transaction_date='" + cb.getTransaction_date() + "',amount='" + cb.getAmount() + "',userid='" + cb.getUserid() + "',operation='" + cb.getOperation() + "' where acid='" + cb.getAcid() + "'";

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method to delete the data in the database 

    public int DeleteCashBook(int acid) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "delete from cash_book where acid='" + acid + "'";
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method to show the data of the table 
    public void FindAllCashBook() {
        con = ConnectionPool.connectDB();
        String sql = "select * from cash_book";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("\tAccount id : " + rs.getInt("acid") + "\tAccount : " + rs.getString("account") + "\t Transaction Date  : " + rs.getString("transaction_date") + " \t Amount : " + rs.getDouble("amount") + "\t UserId : " + rs.getInt("userid") + "\t Operation :" + rs.getString("operation"));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // method to find all data inthe form of arraylist
    public ArrayList<CashBookBean> FindAllCashBookArray() {
        con = ConnectionPool.connectDB();
        String sql = "select * from cash_book";
        ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CashBookBean bb = new CashBookBean();
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
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    // method to find the data by accound id
    public CashBookBean FindbyAcid(int acid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from cash_book where acid='" + acid + "'";
        CashBookBean bb = new CashBookBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bb.setAccount(rs.getString("account"));
                bb.setTransaction_date(rs.getString("transaction_date"));
                bb.setAmount(rs.getDouble("amount"));
                bb.setUserid(rs.getInt("userid"));
                bb.setOperation(rs.getString("operation"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bb;
    }

    // method to show data of only selected employee by thier empno
    public void FindoneData(int acid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from cash_book where acid='" + acid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("\tAccount id : " + rs.getInt("acid") + "\tAccount : " + rs.getString("account") + "\t Transaction Date  : " + rs.getString("transaction_date") + " \t Amount : " + rs.getDouble("amount") + "\t UserId : " + rs.getInt("userid") + "\t Operation :" + rs.getString("operation"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
       
       
        public ArrayList<CashBookBean> FindAllCashBookDatewise(String sdate,String edate ,int userid) {
        con = ConnectionPool.connectDB();
        String sql= "select  * from cash_book  where transaction_date between '"+sdate+"' and '"+edate+"' and userid = '"+userid+"'";

        ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CashBookBean bb = new CashBookBean();
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
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
     
        
        public double ClosingBalance(int userid) {
        double balance = 0;

        con = ConnectionPool.connectDB();
        String sql = "select (select coalesce(sum(amount),0) as total_credit from cash_book b where userid = '" + userid + "' and operation = 'receive') - coalesce((select sum(amount) as total_debit from cash_book b where userid = '" + userid + "' and operation = 'pay'),0) as closing_balance from dual;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("closing_balance");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
        

    public static void main(String[] args) {
        CashBookBean cb = new CashBookBean();
        cb.setAccount("62628");
        cb.setAmount(25000);
        cb.setOperation("upi");
        cb.setTransaction_date("2023-03-16");
        cb.setUserid(1834);
        cb.setAcid(1);

        CashBookDao cd = new CashBookDao();

         cd.InsertData(cb);
        //  cd.UpdateEmployee(cb);
        //cd.DeleteEmployee(2);
        // cd.FindAllCashBook();
        //cd.FindAllCashBookArray();
        // cd.FindoneData(1);
        // cd.FindbyAcid(1);
//        ArrayList<CashBookBean>  x= cd.FindAllCashBookDatewise( "2023-02-13", "2023-07-16" , 2);
//        for(CashBookBean cbb:x){
//        System.out.println(cbb.getUserid());
//        System.out.println(cbb.getAmount());
//       }

//        double x=cd.ClosingBalance(2);
//        System.out.println(""+x);
    }
}
