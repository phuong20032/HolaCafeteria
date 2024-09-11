/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author ngoba
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/add_product"})
public class AddProductServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
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
        List<Category> list1 = DAO.INSTANCE.getAllCategories();
        request.setAttribute("catego", list1);
        request.getRequestDispatcher("add_product.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String describe = request.getParameter("describe");
        String image = request.getParameter("image");
        String cid_raw = request.getParameter("cid");
        int price = 0;
        int cid = 0;
        try {
            price = Integer.parseInt(price_raw);
            cid = Integer.parseInt(cid_raw);
        } catch (Exception e) {
            price = 10000000;
        }
        

        try {
            if (name.length() > 25 || price < 0 || price > 10000000 || describe.length() > 1000 || !image.endsWith(".jpg")) {
                if (name.length() > 25) {
                    request.setAttribute("errorName", "Product names are limited to 25 characters.");
                }
                if (price < 0 || price > 10000000) {
                    request.setAttribute("errorPrice", "Invalid price.");
                }
                if (describe.length() > 1000) {
                    request.setAttribute("errorDescribe", "Product describe are limited to 1000 characters.");
                }
                if (!image.endsWith(".jpg")) {
                    request.setAttribute("errorImage", "Product image must have the extension \".jpg\"");
                }
                doGet(request, response);
            } else {
                Product c1 = new Product(name, price, describe, image, cid);
                ProductDAO.INSTANCE.insert(c1);
                response.sendRedirect("manager?cid=" + cid);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

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
