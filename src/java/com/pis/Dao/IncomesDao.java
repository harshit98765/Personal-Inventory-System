package com.pis.Dao;

import com.pis.Bean.IncomesBean;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncomesDao {

    static Connection con;

    //method to insert the data
    public int addIncome(IncomesBean ib) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "insert into  incomes(inc_ac,userid ,inc_catid,amount, transaction_date,receivby,remark) VALUES ('" + ib.getInc_ac() + "','" + ib.getUserid() + "','" + ib.getInc_catid() + "','" + ib.getAmount() + "','" + ib.getTransaction_date() + "','" + ib.getReceiveby() + "','" + ib.getRemark() + "')";

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method for the data update 
    public int updateIncome(IncomesBean ib) {
        int result = 0;

        con = ConnectionPool.connectDB();
        String sql = "update incomes set inc_ac='" + ib.getInc_ac() + "',userid='" + ib.getUserid() + "',inc_catid='" + ib.getInc_catid() + "',amount='" + ib.getAmount() + "',transaction_date='" + ib.getTransaction_date() + "',receiveby='" + ib.getReceiveby() + "',remark='" + ib.getRemark() + "' where inc_id='" + ib.getInc_id() + "'";

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method to delete the data in the database 

    public int deleteIncome(int incid) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "delete from incomes where inc_id='" + incid + "'";
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method to show the data of the table 
    public void FindAllIncomes() {
        con = ConnectionPool.connectDB();
        String sql = "select * from incomes";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Income id:" + rs.getInt("inc_id") + "\t income Accout :" + rs.getString("inc_ac") + "\t UserId :" + rs.getInt("userid") + "\t Income Category Id : " + rs.getInt("inc_catid") + "\t Amount :" + rs.getDouble("amount") + "\t Transaction Date :" + rs.getString("transaction_date") + "\t Receive BY :" + rs.getString("receiveby") + "\t Remark :" + rs.getString("remark"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<IncomesBean> FindAllIncomesArray() {
        con = ConnectionPool.connectDB();
        String sql = "select * from incomes";
        ArrayList<IncomesBean> al = new ArrayList<IncomesBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomesBean ib = new IncomesBean();

                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("receiveby"));
                ib.setRemark(rs.getString("remark"));
//                    
//              
                al.add(ib);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    // method to find the data by accound id
    public IncomesBean FindbyID(int incid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from incomes where inc_id='" + incid + "'";
        IncomesBean ib = new IncomesBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("receiveby"));
                ib.setRemark(rs.getString("remark"));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }

    // method to show data of only selected employee by thier empno
    public void FindoneData(int incid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from incomes where inc_id='" + incid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Income id:" + rs.getInt("inc_id") + "\t income Accout :" + rs.getString("inc_ac") + "\t UserId :" + rs.getInt("userid") + "\t Income Category Id : " + rs.getInt("inc_catid") + "\t Amount :" + rs.getDouble("amount") + "\t Transaction Date :" + rs.getString("transaction_date") + "\t Receive BY :" + rs.getString("receiveby") + "\t Remark :" + rs.getString("remark"));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // method to find all date by date (start date to end date)
     public ArrayList<IncomesBean> FindIncomesDatewise(String sdate,String edate ,int userid) {
        con = ConnectionPool.connectDB();
        String sql= "select  * from incomes  where transaction_date between '"+sdate+"' and '"+edate+"' and userid = '"+userid+"'";

        ArrayList<IncomesBean> al = new ArrayList<IncomesBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomesBean ib = new IncomesBean();
                 ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("receiveby"));
                ib.setRemark(rs.getString("remark"));
              al.add(ib);   
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    

    
    public static void main(String[] args) {
        IncomesBean ib = new IncomesBean();
        ib.setInc_ac("4587");
        ib.setUserid(12645);
        ib.setInc_catid(223);
        ib.setAmount(25451);
        ib.setTransaction_date("2023-12-17");
        ib.setReceiveby("phonpay");
        ib.setRemark("done");
        ib.setInc_id(2);
        IncomesDao id = new IncomesDao();

        id.addIncome(ib);
        //  id.updateIncome(ib);
        // id.deleteIncome(2);
        //id.FindAllIncomes();
        // id.FindAllIncomesArray();
        // id.FindbyID(1);
        // id.FindoneData(1);
        // id.FindallDatewaise("2021-06-13", "2022-07-16", 1834);
//        ArrayList<IncomesBean>  x= id.FindIncomesDatewise("2023-3-26", "2024-01-01", 2);
//        for(IncomesBean cbb:x){
//        System.out.println(cbb.getUserid());
//        System.out.println(cbb.getAmount());
//       }
//   
    }

}
