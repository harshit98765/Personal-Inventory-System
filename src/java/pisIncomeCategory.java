/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AR WorkStation
 */
public class pisIncomeCategory extends HttpServlet {

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
            out.println(" <link rel='stylesheet' href='pisincomecategory.css'>");
            out.println("<body>");
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
            out.println("  <div class='item' id='item-3'>");
            out.println("    <form action='incomecategorycontroller'>");
            out.println("<div class='container'>");
            out.println("<h1> Income Category</h1>");
            out.println("<table class='datatable table1'>");
            out.println(" <tr>");
            out.println(" <td>");
            out.println("Category Name");
            out.println("  </td>");
            out.println(" <td colspan='2'>");
            out.println(" <input type='text' placeholder='Category Name' name='inccatname' required>");
            out.println(" </td>");
            out.println(" </tr>");
            out.println(" <tr>");
            out.println(" <td>");
            out.println(" Category Details");
            out.println(" </td>");
            out.println(" <td colspan='2'>");
            out.println("  <input type='text' placeholder='Category Details' name='inccatdetails' required>");
            out.println(" </td>");
            out.println(" </tr>");

            out.println(" <div class='btns'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<input type='submit' class='btn' value='add'>");
            out.println(" </td>");
            out.println("<td>");
            out.println("<input type='reset' class='btn' value='Cancel'>");
            out.println(" </td>");
            out.println(" <td>");
            out.println("<a href='ViewIncomeCategory' class='viewtable'>View ALL</a>");
            out.println(" </td>");
            out.println("  </div>");
            out.println(" </table>");
            out.println("  </div>");
            out.println("  </form>");
            out.println("  </div>");
            out.println(" </div>");
            out.println(" </div>");
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
