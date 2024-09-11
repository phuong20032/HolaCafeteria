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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Category;
import model.Product;

/**
 *
 * @author ngoba
 */
public class HomeServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet HomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String choice = request.getParameter("sortbyday");
        String when = null ;
        if(choice == null){
        choice = "today";
        when ="Today";
        }
        
        List<Category> list = DAO.INSTANCE.getAllCategories();
        request.setAttribute("categories", list);

        List<Product> top;
//         feature part
        try {
            ArrayList<Product> listQuantity = null;
            ArrayList<Product> listRevenue = null;
            switch (choice) {
                case "today":
                    listQuantity = (ArrayList<Product>) ProductDAO.INSTANCE.mostSoldInXDay(0);
                    listRevenue = (ArrayList<Product>) ProductDAO.INSTANCE.mostRevenueInXDay(0);
                    when = "Today";
                    break;

                case "3dayslast":
                    listQuantity = (ArrayList<Product>) ProductDAO.INSTANCE.mostSoldInXDay(2);
                    listRevenue = (ArrayList<Product>) ProductDAO.INSTANCE.mostRevenueInXDay(2);
                    when = "Last 3 days";
                    break;
                case "7dayslast":
                    listQuantity = (ArrayList<Product>) ProductDAO.INSTANCE.mostSoldInXDay(6);
                    listRevenue = (ArrayList<Product>) ProductDAO.INSTANCE.mostRevenueInXDay(6);
                    when = "Last 7 days";
                    break;
                case "all":
                    listQuantity = (ArrayList<Product>) ProductDAO.INSTANCE.mostSold();
                    listRevenue = (ArrayList<Product>) ProductDAO.INSTANCE.topFeature();
                    when = "All";
                    break;
            }
            top = ProductDAO.INSTANCE.topNew();
            request.setAttribute("oldChoice", choice);
            request.setAttribute("newp", top);
            request.setAttribute("prodQuantity", listQuantity);
            request.setAttribute("prodRevenue", listRevenue);
        } catch (Exception e) {

        }
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
        request.setAttribute("sortby", when);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
