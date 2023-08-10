
package com.pis.Dao;

import com.pis.Bean.ExpensesCategoryBean;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExpensesCategoryDao {
    static Connection con;
   

    
    //method to insert the data
    public int addExepenseCategory(ExpensesCategoryBean eb){
        int result=0;
        con=ConnectionPool.connectDB();
      //  String sql=" insert into expenses_category(exp_catname , exp_catdetails, user_id) values('"+eb.getExp_catname()+"','"+eb.getExp_catname()+"','"+eb.getUserid()+"')";                                   "
        String sql = "INSERT INTO expenses_category(exp_catname, exp_catdetails, userid) VALUES ('" + eb.getExp_catname() + "','" + eb.getExp_catdetails() + "','" + eb.getUserid() + "')"; 

        try {
            Statement stmt=con.createStatement();
            result =stmt.executeUpdate(sql);
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method for the data update 
     public int UpdateExpenseCategory(ExpensesCategoryBean eb) {
        int result = 0;

        con = ConnectionPool.connectDB();
      //  String sql = " update expenses_category set exp_catname='"+eb.getExp_catname()+"',exp_catdetails='"+eb.getExp_catdetails()+"',userid='"+eb.getUserid()"' where exp_id='"+eb.getExp_catid()+"'";
        String sql = "UPDATE expenses_category SET exp_catname='" + eb.getExp_catname() + "', exp_catdetails='" + eb.getExp_catdetails() + "', userid='" + eb.getUserid() + "' WHERE exp_catid='" + eb.getExp_catid()+ "'";


        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     // method to delete the data in the database 
     public int DeleteExpensesCategory(int expid){
          int result = 0;
          con=ConnectionPool.connectDB();
         String sql="delete from expenses_category where exp_catid='"+expid+"'";
     try {
         Statement stmt=con.createStatement();
         result =stmt.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
    return result ; 
     }
     
     
      // method to show the data of the table 
     public void FindAllExpensesCategory(){
         con= ConnectionPool.connectDB();
         String sql="select * from expenses_category" ;
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
           //    System.out.println("\tAccount id : "+rs.getInt("acid")+"\tAccount : "+rs.getString("account")+"\t Transaction Date  : "+rs.getString("transaction_date")+" \t Amount : "+rs.getDouble("amount")+"\t UserId : "+rs.getInt("userid")+"\t Operation :"+rs.getString("operation"));
                System.out.println("Exp Cat_id :"+rs.getInt("exp_catid")+"\t Expenses Cstegory Name :"+rs.getString("exp_catname")+"\t Expenses Cateogory Details" +rs.getString("exp_catdetails")+" User ID :"+rs.getString("userid"));
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      
     }
     
     // method to find all data inthe form of arraylist
      public ArrayList<ExpensesCategoryBean> FindAllExpensesArray(){
         con= ConnectionPool.connectDB();
         String sql="select * from expenses_category" ;
         ArrayList<ExpensesCategoryBean> al=new ArrayList<ExpensesCategoryBean>();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                ExpensesCategoryBean eb=new ExpensesCategoryBean();
              
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setExp_catname(rs.getString("exp_catname"));
                eb.setExp_catdetails(rs.getString("exp_catdetails"));
                eb.setUserid(rs.getInt("userid"));
//              
              al.add(eb);
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      return al;
     }
      
      // method to find the data by accound id
      public ExpensesCategoryBean FindbyAcid(int expid){
         con= ConnectionPool.connectDB();
         String sql="select * from expenses_category where exp_catid='"+expid+"'" ;
          ExpensesCategoryBean eb=new ExpensesCategoryBean();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){  
                 eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setExp_catname(rs.getString("exp_catname"));
                eb.setExp_catdetails(rs.getString("exp_catdetails"));
                eb.setUserid(rs.getInt("userid"));
//                    
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     return eb;
     }
     
     // method to show data of only selected employee by thier empno
      public void FindoneData(int expid){
          con=ConnectionPool.connectDB();
          String sql="select * from expenses_category where exp_catid='"+expid+"'";
     try {
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next()){
                System.out.println("Exp Cat_id :"+rs.getInt("exp_catid")+"\t Expenses Cstegory Name :"+rs.getString("exp_catname")+"\t Expenses Cateogory Details" +rs.getString("exp_catdetails")+" User ID :"+rs.getString("userid"));
           
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      }

   
      // method to find all date by userid       
    public ExpensesCategoryBean FindallByUserid( int userid) {
        con = ConnectionPool.connectDB();
        String sql = "select  * from expenses_category  where userid = '" + userid + "'";
        ExpensesCategoryBean eb = new ExpensesCategoryBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setExp_catname(rs.getString("exp_catname"));
                eb.setExp_catdetails(rs.getString("exp_catdetails"));
                eb.setUserid(rs.getInt("userid"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eb;
    }
    
    // method to find all data inthe form of arraylist
      public ArrayList<ExpensesCategoryBean> FindAllCategoryName(int userid){
         con= ConnectionPool.connectDB();
         String sql = "select  * from expenses_category  where userid = '" + userid + "'";
        
         ArrayList<ExpensesCategoryBean> al=new ArrayList<ExpensesCategoryBean>();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                ExpensesCategoryBean eb=new ExpensesCategoryBean();
              
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setExp_catname(rs.getString("exp_catname"));
                eb.setExp_catdetails(rs.getString("exp_catdetails"));
                eb.setUserid(rs.getInt("userid"));
//              
              al.add(eb);
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      return al;
     }
      
     public static void main(String[] args) {
        ExpensesCategoryDao ed=new ExpensesCategoryDao();
        ExpensesCategoryBean eb=new ExpensesCategoryBean();
        
        eb.setExp_catdetails("Direct");
        eb.setExp_catname("Salary");
        eb.setUserid(1);
        eb.setExp_catid(1);
      ed.addExepenseCategory(eb);
      //ed.UpdateExpenseCategory(eb);
      //ed.DeleteExpensesCategory(2);
     // ed.FindAllExpenses();
     // ed.FindAllExpensesArray();
      //ed.FindoneData(1);
     // ed.FindbyAcid(1);
//     ed.DeleteExpensesCategory(6);
//        ArrayList<ExpensesCategoryBean> al=ed.FindAllCategoryName(2);
//         for(ExpensesCategoryBean el:al){
//             System.out.println(el.getExp_catid());
//             System.out.println(el.getExp_catname());
//         }
     }
    }
     

