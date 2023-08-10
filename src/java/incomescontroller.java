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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AR WorkStation
 */
public class incomescontroller extends HttpServlet {

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
            out.println("<title>Servlet expensescontroller</title>");
            out.println("</head>");
            HttpSession hs = request.getSession();
            if (hs.getAttribute("user") == null) {
                response.sendRedirect("Login.html");
            }

            out.println("<body>");
            int userid = (Integer) hs.getAttribute("user");
            String expac = request.getParameter("inc_ac");
            int expcatid = Integer.parseInt(request.getParameter("cat_detail"));
            String date = request.getParameter("date");
            String remark = request.getParameter("remark");
            String receiveby = request.getParameter("receiveby");
            double amount = Double.parseDouble(request.getParameter("amount"));

            
            String str = "cash";
          IncomesBean ib= new  IncomesBean();
          ib.setInc_ac(expac);
          ib.setUserid(userid);
          ib.setInc_catid(expcatid);
          ib.setAmount(amount);
          ib.setTransaction_date(date);
          ib.setReceiveby(receiveby);
          ib.setRemark(remark);
          
          IncomesDao id=new IncomesDao();
            int a=id.addIncome(ib);

             if(a>0){
                if (receiveby.equals(str)) {
                     CashBookBean cb=new CashBookBean();
                     cb.setAccount(expac);
                     cb.setAmount(amount);
                     cb.setOperation("Receive");
                     cb.setTransaction_date(date);
                     cb.setUserid(userid);
                     CashBookDao cd=new CashBookDao();
                     int x=cd.InsertData(cb);
                     
                 }
                 else{
                     BankBookBean bb=new BankBookBean();
                     bb.setAccount(expac);
                     bb.setAmount(amount);
                     bb.setOperation("Receive");
                     bb.setTransaction_date(date);
                     bb.setUserid(userid);
                     BankBookDao bd=new BankBookDao();
                     int x=bd.InsertData(bb);
                     
                 }
                
            
          
             
                response.sendRedirect("incomes"); 
            }
            else {
                 
                out.println("Failed");
                response.sendRedirect("incomes");
            }
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
