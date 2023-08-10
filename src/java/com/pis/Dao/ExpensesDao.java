package com.pis.Dao;

import com.pis.Bean.ExpensesBean;
import static com.pis.Dao.ExpensesDao.con;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpensesDao {

    static Connection con;

    //method to insert the data
    public int addexpenses(ExpensesBean eb) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "insert into  expenses(exp_ac,userid ,exp_catid,amount, transaction_date,payby,remark) VALUES ('" + eb.getExp_ac() + "','" + eb.getUserid() + "','" + eb.getExp_catid() + "','" + eb.getAmount() + "','" + eb.getTransaction_date() + "','" + eb.getPayby() + "','" + eb.getRemark() + "')";

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method for the data update 
    public int updateExpenses(ExpensesBean eb) {
        int result = 0;

        con = ConnectionPool.connectDB();
        String sql = "update expenses set exp_ac='" + eb.getExp_ac() + "',userid='" + eb.getUserid() + "',exp_catid='" + eb.getExp_catid() + "',amount='" + eb.getAmount() + "',transaction_date='" + eb.getTransaction_date() + "',payby='" + eb.getPayby() + "',remark='" + eb.getRemark() + "' where exp_id='" + eb.getExp_id() + "'";

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method to delete the data in the database 

    public int deleteexpenses(int expid) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "delete from expenses where exp_id='" + expid + "'";
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method to show the data of the table 
    public void FindAllIncomes() {
        con = ConnectionPool.connectDB();
        String sql = "select * from expenses";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Expense id :" + rs.getInt("exp_id") + "\t Expenses Account :" + rs.getString("exp_ac") + "\t UserId :" + rs.getInt("userid") + "\t Expenses  Catid :" + rs.getDouble("amount") + "\t TransactioId :" + rs.getString("transaction_date") + "\t PayBY :" + rs.getString("payby") + "\t Remark :" + rs.getString("remark"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ExpensesBean> FindAllIncomesArray() {
        con = ConnectionPool.connectDB();
        String sql = "select * from expenses";
        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesBean eb = new ExpensesBean();

                eb.setExp_id(rs.getInt("exp_id"));
                eb.setExp_ac(rs.getString("exp_ac"));
                eb.setUserid(rs.getInt("userid"));
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setAmount(rs.getDouble("amount"));
                eb.setTransaction_date(rs.getString("transaction_date"));
                eb.setPayby(rs.getString("payby"));
                eb.setRemark(rs.getString("remark"));
                al.add(eb);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    // method to find the data by accound id
    public ExpensesBean FindbyID(int expid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from expenses where exp_id='" + expid + "'";
        ExpensesBean eb = new ExpensesBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                eb.setExp_id(rs.getInt("exp_id"));
                eb.setExp_ac(rs.getString("exp_ac"));
                eb.setUserid(rs.getInt("userid"));
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setAmount(rs.getDouble("amount"));
                eb.setTransaction_date(rs.getString("transaction_date"));
                eb.setPayby(rs.getString("payby"));
                eb.setRemark(rs.getString("remark"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eb;
    }

    // method to show data of only selected employee by thier empno
    public void FindoneData(int expid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from expenses where exp_id='" + expid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Expense id :" + rs.getInt("exp_id") + "\t Expenses Account :" + rs.getString("exp_ac") + "\t UserId :" + rs.getInt("userid") + "\t Expenses  Catid :" + rs.getDouble("amount") + "\t TransactioId :" + rs.getString("transaction_date") + "\t PayBY :" + rs.getString("payby") + "\t Remark :" + rs.getString("remark"));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public ArrayList<ExpensesBean> FindExpensesDatewise(String sdate,String edate ,int userid) {
        con = ConnectionPool.connectDB();
        String sql= "select  * from expenses  where transaction_date between '"+sdate+"' and '"+edate+"' and userid = '"+userid+"'";

        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesBean eb = new ExpensesBean();
                   eb.setExp_id(rs.getInt("exp_id"));
                eb.setExp_ac(rs.getString("exp_ac"));
                eb.setUserid(rs.getInt("userid"));
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setAmount(rs.getDouble("amount"));
                eb.setTransaction_date(rs.getString("transaction_date"));
                eb.setPayby(rs.getString("payby"));
                eb.setRemark(rs.getString("remark"));
                al.add(eb);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    // method to find all date by userid       
    public ExpensesBean FindallByUserid(int userid) {
        con = ConnectionPool.connectDB();
        String sql = "select  * from expenses  where userid = '" + userid + "'";
        ExpensesBean eb = new ExpensesBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                eb.setExp_id(rs.getInt("exp_id"));
                eb.setExp_ac(rs.getString("exp_ac"));
                eb.setUserid(rs.getInt("userid"));
                eb.setExp_catid(rs.getInt("exp_catid"));
                eb.setAmount(rs.getDouble("amount"));
                eb.setTransaction_date(rs.getString("transaction_date"));
                eb.setPayby(rs.getString("payby"));
                eb.setRemark(rs.getString("remark"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eb;
    }

    public static void main(String[] args) {
        ExpensesBean eb = new ExpensesBean();
        eb.setExp_ac("4578");
        eb.setUserid(456);
        eb.setExp_catid(84545);
        eb.setAmount(25000);
        eb.setTransaction_date("2022-12-06");
        eb.setPayby("upi");
        eb.setRemark("failded");
        eb.setExp_id(1);
        ExpensesDao ed = new ExpensesDao();
         ed.addexpenses(eb);
        // ed.updateExpenses(eb);
        // ed.deleteUsers(2);
        // ed.FindAllIncomes();
        //ed.FindAllIncomesArray();
        //ed.FindbyID(1);
        //ed.FindoneData(1);
        // ed.FindallDatewaise("2021-06-13", "2022-07-16", 1834);
//        ArrayList<ExpensesBean>  x= ed.FindExpensesDatewise("2022-01-01", "2024-01-01", 2);
//        for(ExpensesBean cbb:x){
//        System.out.println(cbb.getUserid());
//        System.out.println(cbb.getAmount());
//       }
   
    }
}
