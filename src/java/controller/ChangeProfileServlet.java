/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import common.InOutUtils;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.util.logging.*;

/**
 *
 * @author ngoba
 */
public class ChangeProfileServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeProfileServlet at " + request.getContextPath() + "</h1>");
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
    private static final Logger logger = Logger.getLogger(ChangeProfileServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("change_profile.jsp").forward(request, response);
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
       InOutUtils in = new InOutUtils();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phonenum = request.getParameter("phonenum");
        String address = request.getParameter("address");

        User u = UserDAO.INSTANCE.check(user, pass);
        if (u != null) {
            if (!in.isValidFullName(fullname) || fullname.trim().length() == 0) {
                logger.log(Level.WARNING, "Full Name does not valid");
            }
            if (!in.isValidPhoneNumber(phonenum)|| phonenum.trim().length() == 0) {
                logger.log(Level.WARNING, "Phone number does not valid");
            }
            if (!in.isValidEmail(email)|| email.trim().length() == 0) {
                logger.log(Level.WARNING, "Email does not valid");
            }
            if (!in.isValidAddress(address)|| address.trim().length() == 0) {
                logger.log(Level.WARNING, "Address does not valid");
            }

            if (in.isValidFullName(fullname) == true
                    && fullname.trim().length() != 0
                    && email.trim().length() != 0
                    && phonenum.trim().length() != 0
                    && address.trim().length() != 0
                    && in.isValidPhoneNumber(phonenum) == true
                    && in.isValidEmail(email) == true
                    && in.isValidAddress(address) == true) {
                User ac = new User(user, pass, fullname, email, phonenum, address);
                UserDAO.INSTANCE.changePro(ac);

                HttpSession session = request.getSession();
                session.setAttribute("account", ac);
                logger.log(Level.INFO, "Update Profile Successfully");

            }

        }
        response.sendRedirect("profile.jsp");
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
