/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pis.Bean.ExpensesBean;
import com.pis.Bean.IncomesBean;
import com.pis.Dao.ExpensesDao;
import com.pis.Dao.IncomesDao;
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
public class DayBook extends HttpServlet {

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
            out.println(" <link rel='stylesheet' href='piscashbook.css'>");

            out.println("</head>");
            out.println("<body>");
            HttpSession hs = request.getSession();
            if (hs.getAttribute("user") == null) {
                response.sendRedirect("Login.html");
            }
            String sdate = request.getParameter("sdate");
            String edate = request.getParameter("edate");
            int userid = (Integer) hs.getAttribute("user");

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

            out.println("    <div class='item' id='item-3'>");
            out.println("   <div class='container'>");
            out.println("    <h1>DayBook</h1>");
            out.println("<form action='DayBook'>");
            out.println(" <table class='datatable table1'>");
            out.println("   <tr>");
            out.println("  <th></th>");
            out.println("   <th colspan='2'>Date From </th>");
            out.println("  <th colspan='2'>TO</th>");
            out.println("  <th></th>");
            out.println("  </tr>");

            out.println("   <tr>");
            out.println("  <td id='heading'>DayBook</td>");
            out.println("   <td colspan='2'><input type='date' name='sdate'></td>");
            out.println("   <td colspan='2'><input type='date' name='edate'></td>");
            out.println(" <td><input type='submit' class='registerbtn' ></td>");

            out.println("          </tr>");
            out.println("  </table>");

            out.println(" </form>");
            out.println(" <table class='datatable table2'><tr>");
            out.println("   <th>Sr no</th>");
            out.println("  <th>Account </th>");
            out.println(" <th>Date </th>");
            out.println("  <th>Amount</th>");
            out.println("  <th>Pay/Receive</th>");
            out.println(" <th>Remark</th>");
            out.println("  </tr>");

            out.println(" <tr>");
            out.println("  <td colspan='5' class='minheading'>Expenses</td>");
            out.println("  </tr>");
            IncomesBean ib = new IncomesBean();
            IncomesDao id = new IncomesDao();
            ExpensesBean eb = new ExpensesBean();
            ExpensesDao ed = new ExpensesDao();
            int c1 = 1;
            int c2 = 1;
            ArrayList<ExpensesBean> a = ed.FindExpensesDatewise(sdate, edate, userid);
            for (ExpensesBean exp : a) {
                out.println(" <tr>");
                out.println("  <td>" + c2 + "</td>");
                out.println("  <td>" + exp.getExp_ac() + "</td>");
                out.println(" <td>" + exp.getTransaction_date() + "</td>");
                out.println(" <td>" + exp.getAmount() + "</td>");
                out.println(" <td>" + exp.getPayby() + "</td>");
                out.println("  <td>" + exp.getRemark() + "</td>");
                out.println(" </tr>");
                c2++;
            }

            out.println("  <tr>");
            out.println("  <td colspan='5' class='minheading'>Incomes</td>  ");
            out.println("  </tr>");
            ArrayList<IncomesBean> x = id.FindIncomesDatewise(sdate, edate, userid);
            for (IncomesBean cbb : x) {
                out.println("  <tr>");
                out.println(" <td>" + c1 + "</td>");
                out.println(" <td>" + cbb.getInc_ac() + "</td>");
                out.println(" <td>" + cbb.getTransaction_date() + "</td>");
                out.println(" <td>" + cbb.getAmount() + "</td>");
                out.println(" <td>" + cbb.getReceiveby() + "</td>");
                out.println("  <td>" + cbb.getRemark() + "</td>");
                out.println("  </tr>");
                c1++;
            }
            out.println("  </table>");
            out.println("    </div>");
            out.println("  </div>");
            out.println(" </div>");
            out.println(" </body>");

            out.println(" </html>");
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
