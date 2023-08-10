/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pis.Bean.BankBookBean;
import com.pis.Bean.CashBookBean;
import com.pis.Bean.ExpensesBean;
import com.pis.Bean.IncomesBean;
import com.pis.Dao.BankBookDao;
import com.pis.Dao.CashBookDao;
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
public class BalanceSheet extends HttpServlet {

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
            out.println("<body>");
            out.println(" <link rel='stylesheet' href='pisbalancesheet.css'>");
            out.println("</head>");
            out.println("<body>");
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
            HttpSession hs = request.getSession();
            if (hs.getAttribute("user") == null) {
                response.sendRedirect("Login.html");
            }

            int userid = (Integer) hs.getAttribute("user");
            out.println(" <div class='item' id='item-3'>");
           
            out.println("<div class='container'>");
            out.println(" <h1>BalanceSheet</h1>");
             
            out.println("<table class='datatable table1'>");
            out.println(" <form action='BalanceSheet'");
            out.println(" <tr> ");
            out.println("<th>Date From </th>");
            out.println("<th>TO</th>");
            out.println("<th></th>");
            out.println(" <th></th>");
            out.println(" </tr>");
            out.println("<tr>");
            out.println(" <td ><input type='date' name='sdate' ></td>");
            out.println("<td ><input type='date' name='edate' ></td>");
            out.println(" <td colspan='2'><input type='submit' class='registerbtn' ></td>");
            out.println("</tr>");
            out.println(" </table>");
            out.println(" </form>");

           
            String sdate = request.getParameter("sdate");
            String edate = request.getParameter("edate");
            BankBookBean bb = new BankBookBean();
            BankBookDao bd = new BankBookDao();
            CashBookBean cb = new CashBookBean();
            CashBookDao cd = new CashBookDao();

            out.println("<div class='tables'>");
            out.println("<table id='ictable'>");
            out.println("<tr>");
            out.println("<th>Incomes</th>");
            out.println("<th>Amount </th>");
            out.println("</tr>");
            IncomesDao id = new IncomesDao();
            double inc = 0;
            ArrayList<IncomesBean> x = id.FindIncomesDatewise(sdate, edate, userid);
            for (IncomesBean cbb : x) {
                out.println("<tr>");
                out.println("<td>" + cbb.getInc_ac() + "</td>");
                out.println("<td>" + cbb.getAmount() + "</td>");
                out.println("</tr>");
                inc += cbb.getAmount();
            }
            out.println("</table>");
            out.println("<table id='extable'>");
            out.println("<tr>");
            out.println("<th>Expenses</th>");
            out.println("<th>Amount </th>");
            out.println("</tr>");

            ExpensesDao ed = new ExpensesDao();
            double exp = 0;
            ArrayList<ExpensesBean> a = ed.FindExpensesDatewise(sdate, edate, userid);
            for (ExpensesBean cbb : a) {
                out.println("<tr>");
                out.println("<td>" + cbb.getExp_ac() + "</td>");
                out.println("<td>" + cbb.getAmount() + "</td>");
                out.println("</tr>");
                exp += cbb.getAmount();
            }
            out.println("</table>");
            out.println("</div>");
            out.println("<table class='datatable' id='table3'>");
            out.println("<tr class='showbalancesheet'>");
            out.println("<td class='incomes'>Total</td>");
            out.println("<td class='incomes'>" + inc + "</td>");
            out.println("<td>Total</td>");
            out.println("<td>" + exp + "</td>");
            out.println("</tr>");
            out.println("<tr class='showbalancesheet'>");
            out.println("<td class='nodata'></td>");

            double profit = inc - exp;
            out.println("<td colspan='2'>Gross Profit</td>");
            out.println("<td><span>" + profit + "</span></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
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
