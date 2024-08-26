/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;


import DAOs.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;


/**
 *
 * @author Admin
 */

public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        CartDAO dao = new CartDAO();

        if (path.startsWith("/Cart/Delete/")) {
            String[] s = path.split("/");//s[0]=CustomerController;s[1]=Delete;s[2]=1
            String id = s[s.length - 1];
            int num_id = Integer.parseInt(id);
            dao.deleteCartItem(num_id);
            response.sendRedirect("/Cart");
        }
        if (path.startsWith("/Cart/Increase/")) {
            String[] s = path.split("/");//s[0]=CustomerController;s[1]=Delete;s[2]=1
            String id = s[s.length - 1];
            int num_id = Integer.parseInt(id);
            int quantity = dao.getCartQuan(num_id);
            int productQuan = dao.getProductQuantity(num_id);
            if (quantity >= productQuan) {
                dao.updateCart(num_id, quantity);
            } else {
                dao.updateCart(num_id, quantity + 1);
            }
            response.sendRedirect("/Cart");
        }
        if (path.startsWith("/Cart/Decrease/")) {
            String[] s = path.split("/");//s[0]=CustomerController;s[1]=Delete;s[2]=1
            String id = s[s.length - 1];
            int num_id = Integer.parseInt(id);
            int quantity = dao.getCartQuan(num_id);
            if (quantity == 1) {
                response.sendRedirect("/Cart");
            } else {
                dao.updateCart(num_id, quantity - 1);
                response.sendRedirect("/Cart");
            }
        }
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
        HttpSession session = request.getSession();
        String productId = request.getParameter("productId");
        String action = request.getParameter("action");
        String order = request.getParameter("order");
        String url = request.getParameter("formUrl");
        String UserID = request.getParameter("userID");

        String referer = request.getHeader("Referer");
        String[] s = referer.split("/");
        String returnPage = s[s.length - 1];
        System.out.println(returnPage);
        if (returnPage.equals("localhost:8080")) {
            returnPage = "Home";
        }
        if (UserID.equals("")) {
            response.sendRedirect("/login");
        } else {
            int userID = Integer.parseInt(UserID);
            CartDAO dao = new CartDAO();
            Product product = dao.getProduct(productId);
            int id = Integer.parseInt(productId);

            if (action != null && action.equals("add")) {
                if (product != null) {
                    if (!dao.isProductInCart(id)) {
                        dao.setCart(id, 1, userID);
                    } else {
                        int quantity = dao.getCartQuan(id);
                        dao.updateCart(id, quantity + 1);
                    }
                } else {
                    System.out.println(product);
                }
                response.sendRedirect("/" + returnPage);
            }

            if (order != null && order.equals("add")) {
                if (product != null) {
                    if (!dao.isProductInCart(id)) {
                        dao.setCart(id, 1, userID);
                    } else {
                        int quantity = dao.getCartQuan(id);
                        dao.updateCart(id, quantity + 1);
                    }
                } else {
                    System.out.println(product);
                }
                response.sendRedirect("/Cart");
            }
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
