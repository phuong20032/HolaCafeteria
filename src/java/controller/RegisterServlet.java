/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author ngoba
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");
        String fullname = request.getParameter("fullname");
        String phonenum = request.getParameter("phonenum");
        String email = request.getParameter("email");

        // check
        // username ton tai
        String ms = "";
        boolean hasError = false;

        if (UserDAO.INSTANCE.existedUs(user)) {
            ms += "Username existed!\n";
            hasError = true;
        }

        if (UserDAO.INSTANCE.existedEmail(email)) {
            ms += "Email existed!\n";
            hasError = true;
        }

        if (UserDAO.INSTANCE.existedPhoneNum(phonenum)) {
            ms += "Phone number existed!\n";
            hasError = true;
        }

        if (!re_pass.equals(pass)) {
            ms += "Passwords do not match!\n";
            hasError = true;
        }

        if (!common.InOutUtils.isValidEmail(email)) {
            ms += "Invalid email format!\n";
            hasError = true;
        }

        if (!common.InOutUtils.isValidPhoneNumber(phonenum)) {
            ms += "Invalid phone number!\n";
            hasError = true;
        }

        if (!common.InOutUtils.isValidUsername(user)) {
            ms += "Invalid username!\n";
            hasError = true;
        }

        if (!common.InOutUtils.isValidPassword(pass)) {
            ms += "Password too short!\n";
            hasError = true;
        }

        if (!common.InOutUtils.isValidFullName(fullname)) {
            ms += "Invalid full name!\n";
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("error", ms);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            User ac = new User(user, pass, fullname, email, phonenum);
            UserDAO.INSTANCE.register(ac);
            response.sendRedirect("login.jsp");
        }
        // chua co luu vao
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
