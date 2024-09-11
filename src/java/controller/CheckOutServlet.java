/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;
import model.User;

/**
 *
 * @author ngoba
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        List<Product> prd = ProductDAO.INSTANCE.getAllProducts();
        Cookie[] arr = request.getCookies();
        String selectedCheckboxes = request.getParameter("selectedCheckboxes");
        if (selectedCheckboxes != null && !selectedCheckboxes.equals("")) {
            String[] buyId = selectedCheckboxes.split(",");

            String txt = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                    }
                }
            }
            Cart cart = new Cart(txt, prd);
            Cart buyCart = new Cart();
            if (buyId != null && buyId.length > 0) {
                for (String id : buyId) {
                    Item t = cart.getItemById(id);
                    buyCart.addItem(t);
                    cart.removeItem(id);
                }
            }
            User account = null;
            Object a = session.getAttribute("account");
            int total = buyCart.getTotalMoney();
            session.setAttribute("total", total);
            if (buyId == null || buyId.length <= 0) {
                response.sendRedirect("cart");
            } else {
                if (a != null) {
                    account = (User) a;
                    OrderDAO.INSTANCE.addOrder(account, buyCart);
                    txt = "";
                    List<Item> items = cart.getItems();
                    if (items.size() > 0) {
                        txt = items.get(0).getProduct().getId() + ":" + items.get(0).getQuantity();
                    }
                    for (int i = 1; i < items.size(); i++) {
                        txt += "-" + items.get(i).getProduct().getId() + ":" + items.get(i).getQuantity();
                    }
                    Cookie c = new Cookie("cart", txt);
                    c.setMaxAge(2 * 24 * 60 * 60);
                    response.addCookie(c);
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("login.jsp");

                }
            }
        }else{
            response.sendRedirect("cart");
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
