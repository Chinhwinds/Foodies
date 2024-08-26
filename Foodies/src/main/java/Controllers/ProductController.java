/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuct
 */
public class ProductController extends HttpServlet {

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
        ProductDAO pDAO = new ProductDAO();
        CategoryDAO cDAO = new CategoryDAO();
        String productId = request.getParameter("ProductID");

        if (productId != null) {
            Product product = pDAO.getProductInfoById(Integer.parseInt(productId));
            if (product != null) {
                request.setAttribute("product", product);

                request.getRequestDispatcher("/siteUser/productInfo.jsp").forward(request, response);
            }
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
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        Cookie[] cookies = request.getCookies();
        AccountDAO dao = new AccountDAO();

        if (path.startsWith("/Product")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("userCookie")) {
                        if (dao.checkIfEmailsExist(aCookie.getValue())) {

                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("usermail", aCookie.getValue());
                            System.out.println(aCookie.getValue() + "-----");

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = dao.getUser(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("username"));

                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            processRequest(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    processRequest(request, response);
                }
            } else {
                processRequest(request, response);
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
