package com.pis.Dao;

import com.pis.Bean.UsersBean;
import static com.pis.Dao.UsersDao.con;
import com.pis.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDao {

    static Connection con;

    //method to insert the data
    public int addUser(UsersBean ub) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "insert into  users(username,password,name ,address,mobile,email ) VALUES ('" + ub.getUsername() + "','" + ub.getPassword() + "','" + ub.getName() + "','" + ub.getAddress() + "','" + ub.getMobile() + "','" + ub.getEmail() + "')";        
        
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // method for the data update 
    public int updateUsers(UsersBean ub) {
        int result = 0;
        
        con = ConnectionPool.connectDB();
        String sql = "update users set username='" + ub.getUsername() + "',password='" + ub.getPassword() + "',name='" + ub.getName() + "',address='" + ub.getAddress() + "',mobile='" + ub.getMobile() + "',email='" + ub.getEmail() + "' WHERE userid='" + ub.getUserid() + "'";
        
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // method to delete the data in the database 

    public int deleteUsers(int userid) {
        int result = 0;
        con = ConnectionPool.connectDB();
        String sql = "delete from users where userid='" + userid + "'";
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;        
    }

    // method to show the data of the table 
    public void FindAllUsers() {
        con = ConnectionPool.connectDB();
        String sql = "select * from users";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("UserId :" + rs.getInt("userid") + "\t UserName :" + rs.getString("username") + "\t Password :" + rs.getString("password") + "\t Name :" + rs.getString("name") + "\t address :" + rs.getString("address") + "\t Mobile : " + rs.getString("mobile") + "\t Email :" + rs.getString("email"));                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // method to find all data inthe form of arraylist
    public ArrayList<UsersBean> FindAllUsersArray() {
        con = ConnectionPool.connectDB();
        String sql = "select * from users";
        ArrayList<UsersBean> al = new ArrayList<UsersBean>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersBean ub = new UsersBean();
                
                ub.setUserid(rs.getInt("userid"));
                ub.setUsername(rs.getString("username"));
                ub.setPassword(rs.getString("password"));
                ub.setName(rs.getString("name"));
                ub.setAddress(rs.getString("address"));
                ub.setPassword(rs.getString("mobile"));
                ub.setEmail(rs.getString("email"));
//              
                al.add(ub);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    // method to find the data by accound id
    public UsersBean FindbyId(int uid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from users where userid='" + uid + "'";
        UsersBean ub = new UsersBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {                
               ub.setUserid(rs.getInt("userid"));
                ub.setUsername(rs.getString("username"));
                ub.setPassword(rs.getString("password"));
                ub.setName(rs.getString("name"));
                ub.setAddress(rs.getString("address"));
                ub.setMobile(rs.getString("mobile"));
                ub.setEmail(rs.getString("email"));
                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ub;
    }

    // method to show data of only selected employee by thier empno
    public void FindByUserIdData(int uid) {
        con = ConnectionPool.connectDB();
        String sql = "select * from users where userid='" + uid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("UserId :" + rs.getInt("userid") + "\t UserName :" + rs.getString("username") + "\t Password :" + rs.getString("password") + "\t Name :" + rs.getString("name") + "\t address :" + rs.getString("address") + "\t Mobile : " + rs.getString("mobile") + "\t Email :" + rs.getString("email"));                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // mthod to check the User If its username in the table
    
     public int CheckAvailability(String username ){
      int x=0;
      con=ConnectionPool.connectDB();
      String sql="Select * from users where username='"+username+"'";
     try {
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(sql);
        if (rs.next()){
             x=rs.getInt("uid");
         }
        
         con.close();
     } catch (SQLException ex) {
         Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     
      return x;
  }
    
    // method to login 
     public int loginUser(String username,String password ){
      int x=0;
      con=ConnectionPool.connectDB();
      String sql="Select * from users where username='"+username+"'and password='"+password+"' ";
     try {
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(sql);
        if (rs.next()){
             x=rs.getInt("userid");
         }
        
         con.close();
     } catch (SQLException ex) {
         Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
     }
     
      return x;
  }
    // method to find the data by username and password
    public UsersBean Authenticate(String username ,String password) {
        con = ConnectionPool.connectDB();
        String sql = "select * from users where username='" + username + "' and password='"+password+"'";
        UsersBean ub = new UsersBean();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {                
                ub.setUserid(rs.getInt("userid"));
                ub.setUsername(rs.getString("username"));
                ub.setPassword(rs.getString("password"));
                ub.setName(rs.getString("name"));
                ub.setAddress(rs.getString("address"));
                ub.setMobile(rs.getString("mobile"));
                ub.setEmail(rs.getString("email"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ub;
    }
   
    
    
    public static void main(String[] args) {
        UsersBean ub = new UsersBean();
         UsersDao ud = new UsersDao();
         
        ub.setUsername("mohit");
        ub.setPassword("123");
        ub.setName("mohitMourya");
        ub.setAddress("khandwa");
        ub.setMobile("9165906894");
        ub.setEmail("mohit@gmail.com");
        int r=ud.addUser(ub);
        if(r>0){
            System.out.println("Data Added Succefull");
        }
        else{
            System.out.println("Data Added Failed");
        }
        
     
    }
    
}
