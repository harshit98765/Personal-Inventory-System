/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pis.Bean.ExpensesCategoryBean;
import com.pis.Bean.UsersBean;
import com.pis.Dao.ExpensesCategoryDao;
import com.pis.Dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AR WorkStation
 */
public class expenses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewProfile</title>");
            out.println("</head>");
            HttpSession hs=request.getSession();
             if(hs.getAttribute("user")==null){
                response.sendRedirect("Login.html");
            }
            out.println("<body>");

            out.println(" <link rel='stylesheet' href='stylepisexpenses.css'>");

            out.println("</head>");
            out.println("<body>");

          int userid=(Integer)hs.getAttribute("user");
        
            UsersDao ud=new UsersDao();
            UsersBean x= ud.FindbyId(userid);
            out.println("   <div class='cnt'>");
            out.println("<div class='item' id='item-1'>");
            out.println("   <div class='nav'>");
            out.println("  <ul>");
            out.println("  <li><a href='pisIncomeCategory'>Incomes Category</a></li>");
            out.println(" <li><a href='expensescategory'>Expenses Cateogory</a></li>");
            out.println(" <li><a href='incomes'>Incomes</a></li>");
            out.println(" <li><a href='expenses'>Expenses</a></li>");
            out.println("  <li><a href='CashBook'>Cash Book</a></li>");
            out.println(" <li><a href='BankData'>Bank Book</a></li>");
            out.println("   <li><a href='DayBook'>Day Book</a></li>");
            out.println("   <li><a href='BalanceSheet'>Balance Sheet</a></li>");
            
            out.println("  </ul>");
            out.println(" </div>");
            out.println(" </div>");
            out.println(" <div class='item' id='item-2'>");
            out.println("  <div class='logo'>");
            out.println("   <div id='line1'>");
            out.println("     <span id='ps'>Personal</span>");
            out.println("  </div>");
            out.println(" <div id='line2'>");
            out.println("  <span id='inv'>Inventory</span>");
            out.println("  <span id='sys'> System</span>");
            out.println("  </div>");
            out.println("  </div>");
            out.println("  <div class='navbar'>");
            out.println("  <ul class='nav-links'>");
          out.println("    <li><a href='Home'>Home  </a></li>");
            out.println("   <li><a href='ViewProfile'> Profile</a></li>");
            out.println("   <li><a href='pisupdateuser'>Update Profile</a></li>");
            out.println("   <li><a href='Login.html'>Logout</a></li>");
            out.println(" </ul>");
            out.println("  </div>");
            out.println(" </div>");
            out.println("<div class='item' id='item-3'>");
            out.println("  <form class='container' action='expensescontroller'>");
            out.println("   <h1>expenses</h1>");
           
            out.println("  <table class='datatable'>");
            out.println("  <tr>");
            out.println(" <td><label for='c_name'>Enter Expenses</label></td>");
            out.println(" <td><input type='text' id='c_name' name='exp_ac' placeholder='Enter Expenses' required></td>");
            out.println(" </tr>");

            out.println(" <tr>");
            out.println("  <td><label for='Cat_detils'>Enter Category </label></td>");
            out.println("  <td>");
            out.println(" <select id='pay_by' name='cat_detail'>");
            ExpensesCategoryDao ed=new ExpensesCategoryDao();
            ArrayList<ExpensesCategoryBean> al = ed.FindAllCategoryName(userid);
            for (ExpensesCategoryBean el : al) {
                out.println(" <option value='"+el.getExp_catid()+"'>"+el.getExp_catname()+"</option>");
            }
            out.println(" </select>");
            out.println("</td>");
            out.println(" </tr>");

            out.println(" <tr>");
            out.println(" <td><label for='amount'>Enter Amount Details</label></td>");
            out.println("  <td><input type='text' id='amount' name='amount' placeholder='Enter Amount Details' required></td>");
            out.println("  </tr>");
            out.println(" <tr>");
            out.println("   <td><label for='pay_by'>Pay By</label></td>");
            out.println(" <td>");
            out.println(" <select id='pay_by' name='payby'>");
            out.println("  <option value='cash'>Cash</option>");
            out.println(" <option value='check'>Check</option>");
            out.println(" </select>");
            out.println(" </td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("  <td><label for='date'>Enter Date</label></td>");
            out.println("  <td><input type='date' id='date' name='date' placeholder='Enter Category Details' required></td>");
            out.println("</tr>");
            out.println("  <tr>");
            out.println("    <td><label for='remark'>Enter Remark</label></td>");
            out.println("  <td><input type='text' id='remark' name='remark' placeholder='Enter Remark' required></td>");
            out.println(" </tr>");
            out.println(" <tr>");
            out.println(" <td colspan='2'>");
            out.println(" <div class='btns'>");
            out.println("    <input type='submit' class='btn' value='Add'>");
            out.println("    <input type='reset' class='btn' value='Cancel'>");
            out.println("   </div>");
            out.println("    </td>");
            out.println(" </tr>");
            out.println("  </table>");
            out.println(" </form>");
            out.println("</div>");
            out.println("  </div>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
