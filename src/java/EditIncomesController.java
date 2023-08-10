/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pis.Bean.IncomeCategoryBean;
import com.pis.Dao.IncomeCategoryDao;
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
public class EditIncomesController extends HttpServlet {

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
            out.println("<title>Servlet EditIncomesController</title>");
            out.println("</head>");
            out.println("<body>");
            HttpSession hs = request.getSession();
            if (hs.getAttribute("user") == null) {
                response.sendRedirect("Login.html");
            }
           
             int userid = (Integer) hs.getAttribute("user");
              String catname = request.getParameter("catname");
            String catdetails = request.getParameter("catdetails");
            IncomeCategoryBean ib = new IncomeCategoryBean();
            IncomeCategoryDao id = new IncomeCategoryDao();
            int catid = Integer.parseInt(request.getParameter("catid"));
            ib.setInc_catid(catid);
            ib.setInc_catname(catname);
            ib.setInc_catdetails(catdetails);
            ib.setUserid(userid);
            int x=id.UpdateIncomeCategory(ib);
            if (x > 0) {
                response.sendRedirect("ViewIncomeCategory");
            } else {
                out.println("Failed");
                response.sendRedirect("ViewIncomeCategory");
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
