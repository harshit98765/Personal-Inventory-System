/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pis.Dao;

import com.pis.Bean.ExpensesCategoryBean;
import com.pis.Bean.IncomeCategoryBean;
import static com.pis.Dao.ExpensesCategoryDao.con;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AR WorkStation
 */
public class IncomeCategoryDao {
    static Connection con;
       
    //method to insert the data
    public int addIncomeCategory(IncomeCategoryBean cb){
        int result=0;
        con=ConnectionPool.connectDB();
      String sql = "insert into  income_category(inc_catname, inc_catdetails, userid) VALUES ('" + cb.getInc_catname() + "','" + cb.getInc_catdetails() + "','" + cb.getUserid() + "')"; 

        try {
            Statement stmt=con.createStatement();
            result =stmt.executeUpdate(sql);
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method for the data update 
     public int UpdateIncomeCategory(IncomeCategoryBean cb) {
        int result = 0;

        con = ConnectionPool.connectDB();
      String sql = "update income_category set inc_catname='" + cb.getInc_catname() + "', inc_catdetails='" + cb.getInc_catdetails() + "', userid='" + cb.getUserid() + "' WHERE inc_catid='" + cb.getInc_catid()+ "'";


        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     // method to delete the data in the database 
     public int DeleteIncomeCategory(int incid){
          int result = 0;
          con=ConnectionPool.connectDB();
         String sql="delete from income_category where inc_catid='"+incid+"'";
     try {
         Statement stmt=con.createStatement();
         result =stmt.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
    return result ; 
     }
     
     
     
      // method to show the data of the table 
     public void FindAllIncomeCategory(){
         con= ConnectionPool.connectDB();
         String sql="select * from income_category" ;
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
            System.out.println("Inc Cat_id :"+rs.getInt("inc_catid")+"\t Income category Name :"+rs.getString("inc_catname")+"\t Income Cateogory Details" +rs.getString("inc_catdetails")+" User ID :"+rs.getString("userid"));
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      
     }
     
     // method to find all data inthe form of arraylist
      public ArrayList<IncomeCategoryBean> FindAllIncomeCatArray(){
         con= ConnectionPool.connectDB();
         String sql="select * from income_category" ;
         ArrayList<IncomeCategoryBean> al=new ArrayList<IncomeCategoryBean>();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                IncomeCategoryBean ib=new IncomeCategoryBean();
              
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
//              
              al.add(ib);
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      return al;
     }
      
      // method to find the data by accound id
      public IncomeCategoryBean FindbyId(int incid){
         con= ConnectionPool.connectDB();
         String sql="select * from income_category where inc_catid='"+incid+"'" ;
          IncomeCategoryBean ib=new IncomeCategoryBean();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){  
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
//                    
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     return ib;
     }
     
     // method to show data of only selected employee by thier empno
      public void FindByIncid(int incid){
          con=ConnectionPool.connectDB();
          String sql="select * from income_category where inc_catid='"+incid+"'";
     try {
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next()){
               System.out.println("Inc Cat_id :"+rs.getInt("inc_catid")+"\t Income category Name :"+rs.getString("inc_catname")+"\t Income Cateogory Details" +rs.getString("inc_catdetails")+" User ID :"+rs.getString("userid"));
            
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(IncomeCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      }

        
       // method to find all date by userid       
    public IncomeCategoryBean FindallByUserid( int userid) {
        con = ConnectionPool.connectDB();
        String sql = "select  * from income_category  where userid = '" + userid + "'";
        IncomeCategoryBean ib = new IncomeCategoryBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
              ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));   
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    
    // method to find all data inthe form of arraylist
      public ArrayList<IncomeCategoryBean> FindAllCategoryName(int userid){
         con= ConnectionPool.connectDB();
         String sql = "select  * from income_category  where userid = '" + userid + "'";
        
         ArrayList<IncomeCategoryBean> al=new ArrayList<IncomeCategoryBean>();
     try {
         Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                IncomeCategoryBean ib=new IncomeCategoryBean();
              
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
//              
              al.add(ib);
            }
            con.close();
     } catch (SQLException ex) {
         Logger.getLogger(ExpensesCategoryDao.class.getName()).log(Level.SEVERE, null, ex);
     }
      return al;
     }
     
     public static void main(String[] args) {
        IncomeCategoryDao id=new IncomeCategoryDao();
        IncomeCategoryBean ib=new IncomeCategoryBean();
        
      ib.setInc_catdetails("Direct");
      ib.setInc_catname("Salary");
      ib.setUserid(1);
      ib.setInc_catid(1);
       id.addIncomeCategory(ib);
     //  id.UpdateIncomeCategory(ib);
    // id.DeleteIncomeCategory(2);
   // id.FindAllExpenses();
   //id.FindAllExpensesArray();
   //id.FindoneData(1);
   
//        ArrayList<IncomeCategoryBean> x=id.FindAllCategoryName(2);
//         for(IncomeCategoryBean el:x){
//             System.out.print(el.getInc_catid());
//             System.out.print(el.getInc_catname());
//              System.out.println("");
//             
//         }
        
     
      
    }
     
}
