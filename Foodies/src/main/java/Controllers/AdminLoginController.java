/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AdminDAO;
import Models.AdminAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AdminLoginController extends HttpServlet {

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
            out.println("<title>Servlet AdminLoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLoginController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String path = request.getRequestURI();
        AdminDAO dao = new AdminDAO();
        if (path.equals("/adminlogin")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("adCookie")) {
                        if (dao.checkIfAdminExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("adname", aCookie.getValue());

                            String fn = session.getAttribute("adname").toString();
                            try {
                                ResultSet rso = dao.getAdmin(fn);
                                rso.next();
//                            System.out.println(rso.getString("fullname"));
                                session.setAttribute("adfullname", rso.getString("fullname"));

                                ResultSet rsIMG = dao.getAdminIMG(rso.getString("fullname"));
                                rsIMG.next();
                                session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                        request.getRequestDispatcher("/admin.jsp").forward(request, response);
                            response.sendRedirect("/admin");
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    request.getRequestDispatcher("/siteAdmin/adminLogin.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/siteAdmin/adminLogin.jsp").forward(request, response);
            }
        } else {
            if (path.equals("/adminlogout")) {
                if (cookies != null) {
                    for (Cookie aCookie : cookies) {
                        if (aCookie.getName().equals("adCookie")) {
                            aCookie.setValue("");
                            aCookie.setPath("/");
                            aCookie.setMaxAge(0);
                            response.addCookie(aCookie);
                        }
                    }
                    session.invalidate();
                    response.sendRedirect("/adminlogin");
                }
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
        if (request.getParameter("btnAdLogin") != null) {
            String user = request.getParameter("txtAdName");
            String pass = request.getParameter("txtAdPass");
            AdminAccount acc = new AdminAccount(user, pass);
            AdminDAO dao = new AdminDAO();
            boolean check = dao.login(acc);

            if (dao.login(acc)) {
                Cookie userCookie = new Cookie("adCookie", user);
                userCookie.setMaxAge(60 * 60 * 12);
                response.addCookie(userCookie);

                HttpSession session = request.getSession();

                try {
                    ResultSet rs = dao.getAdmin(user);
                    rs.next();
                    session.setAttribute("adname", user);
                    session.setAttribute("adfullname", rs.getString("name"));
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("/admin");

            } else {
                HttpSession session = request.getSession();
                session.setAttribute("adError", "Wrong username or password! Please type again.");
                request.getRequestDispatcher("/siteAdmin/adminLogin.jsp").forward(request, response);
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
