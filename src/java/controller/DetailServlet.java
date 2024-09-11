package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Product;

/**
 *
 * @author ngoba
 */
public class DetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String cid = request.getParameter("cid");
        int id = Integer.parseInt(id_raw);
        Product p;
        List<Product> list;
        if ((id >= 4 && id <= 15) || (id >= 24 && id <= 1032)) {
            cid = "1";
        }
        if ((id >= 16 && id <= 17) || (id >= 1033 && id <= 1035)) {
            cid = "2";
        }
        if ((id >= 18 && id <= 23) || (id == 1041)) {
            cid = "3";
        }
        if ((id >= 1036 && id <= 1040) || (id >= 1049 && id <= 1050)) {
            cid = "4";
        }
        if (id >= 1043 && id <= 1048) {
            cid = "6";
        }
        if (id >= 1051 && id <= 1058) {
            cid = "10";
        }
        try {

            p = ProductDAO.INSTANCE.getProductById(id_raw);
            request.setAttribute("prod", p);
            list = ProductDAO.INSTANCE.randomRelative(id_raw, cid);
            request.setAttribute("relativeproducts", list);

        } catch (SQLException ex) {

        }

        request.getRequestDispatcher("detail.jsp").forward(request, response);
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
        String id_raw = request.getParameter("ID");
        Product p;
        try {
            p = ProductDAO.INSTANCE.getProductById(id_raw);
            request.setAttribute("prod", p);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("detail.jsp").forward(request, response);
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
