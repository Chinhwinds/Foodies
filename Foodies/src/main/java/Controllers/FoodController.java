/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class FoodController extends HttpServlet {
   
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
            out.println("<title>Servlet FoodController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FoodController at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if (path.equals("/Home") || path.equals("/")) {
            request.getRequestDispatcher("/siteUser/home.jsp").forward(request, response);
        }
//        if (path.equals("/Home/Chay")) {
//            request.getRequestDispatcher("/Chay.jsp").forward(request, response);
//        }
//        if (path.equals("/Home/Man")) {
//            request.getRequestDispatcher("/Man.jsp").forward(request, response);
//        }
//        if (path.equals("/Home/Uong")) {
//            request.getRequestDispatcher("/Uong.jsp").forward(request, response);
//        }
//        if (path.equals("/Home/Other")) {
//            request.getRequestDispatcher("/Other.jsp").forward(request, response);
//        }

        if (path.equals("/Cart")) {
            if (session.getAttribute("userID") == null) {
                System.out.println("Null userID");
                response.sendRedirect("/login");
            } else {
                request.getRequestDispatcher("/siteUser/Cart.jsp").forward(request, response);
            }

        }
        if (path.equals("/Order")) {
            if (session.getAttribute("userID") == null) {
                System.out.println("Null userID");
                response.sendRedirect("/login");
            } else {
                request.getRequestDispatcher("/siteUser/Order.jsp").forward(request, response);
            }
        }

        if (path.equals("/index.jsp")) {
            response.sendRedirect("/Home");
        }

        if (path.equals("/home.jsp")) {
            response.sendRedirect("/Home");
        }
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
