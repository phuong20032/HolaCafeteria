/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author ngoba
 */
public class ShopServlet extends HttpServlet {

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
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
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

        List<Product> prd = ProductDAO.INSTANCE.getAllProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, prd);
        int size = cart.getItems().size();
        request.setAttribute("cart", cart);
        request.setAttribute("size", size);
        List<Category> categories = DAO.INSTANCE.getAllCategories();
        String cid_raw = request.getParameter("cid");
        String price_raw = request.getParameter("price");
        String keyword = request.getParameter("keyword");
        String sortby = request.getParameter("sortby");
        String index_raw = request.getParameter("index");
        List<Product> allProduct = ProductDAO.INSTANCE.getAllProducts();
        int cid;
        Integer price;
        int endPage;
        int index = 1;
        try {
            cid = (cid_raw == null) ? 0 : Integer.parseInt(cid_raw);
            price = (price_raw == null || price_raw.equals("")) ? null : Integer.parseInt(price_raw);
            List<Product> list = ProductDAO.INSTANCE.search(cid, price, keyword, sortby);
            index = (index_raw == null || index_raw.equals("")) ? 1 : Integer.parseInt(index_raw);
            endPage = list.size() / 6;
            if (list.size() % 6 != 0) {
                endPage++;
            }
            List<Product> products = ProductDAO.INSTANCE.searchPaging(cid, price, keyword, sortby, index);
            request.setAttribute("index", index);
            request.setAttribute("endP", endPage);
            request.setAttribute("products", products);
            request.setAttribute("s_sortby", sortby);
            request.setAttribute("s_keyword", keyword);
            request.setAttribute("s_cid", cid);
            request.setAttribute("s_price", price);
        } catch (Exception e) {
            System.out.println("Error at search " + e.getMessage());
        }
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
