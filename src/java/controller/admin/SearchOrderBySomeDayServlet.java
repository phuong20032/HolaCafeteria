/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.DAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.OrderDateDetail;
import model.Product;

/**
 *
 * @author ngoba
 */
@WebServlet(name = "SearchOrderBySomeDayServlet", urlPatterns = {"/searchorderinday"})
public class SearchOrderBySomeDayServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchOrderBySomeDayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchOrderBySomeDayServlet at " + request.getContextPath() + "</h1>");
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
        List<Product> list = ProductDAO.INSTANCE.getAllProducts();
        List<Category> ls = CategoryDAO.INSTANCE.getAll();
        List<OrderDateDetail> listod = new ArrayList<>();
        String searchbyday = request.getParameter("searchbyday");
        String xDays = null;
        if ("1".equals(searchbyday)) {
            listod = OrderDAO.INSTANCE.OrderDateDetailInXDays(0);
            xDays = "Today";
        }
        if ("2".equals(searchbyday)) {
            listod = OrderDAO.INSTANCE.OrderDateDetailInXDays(2);
            xDays = "Last 3 Days";
        }
        if ("3".equals(searchbyday)) {
            listod = OrderDAO.INSTANCE.OrderDateDetailInXDays(6);
            xDays = "Last 7 Days";
        }
        if ("4".equals(searchbyday)) {
            listod = OrderDAO.INSTANCE.OrderDateDetailInAllDays();
            xDays = "All Days";
        }
        request.setAttribute("alldate", OrderDAO.INSTANCE.AllDate());
        request.setAttribute("ord", listod);
        request.setAttribute("searchbyday", xDays);
        request.getRequestDispatcher("orderstatistic.jsp").forward(request, response);

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
