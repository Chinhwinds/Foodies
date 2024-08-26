/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AdminDAO;
import DAOs.AdminReportDAO;
import Models.AdminReport;
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
public class AdminReportController extends HttpServlet {

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
            out.println("<title>Servlet AdminReportController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminReportController at " + request.getContextPath() + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        AdminDAO daoS = new AdminDAO();
        if (path.equals("/admin/adminReport")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("adCookie")) {
                        if (daoS.checkIfAdminExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("adname", aCookie.getValue());

                            String fn = session.getAttribute("adname").toString();
                            try {
                                ResultSet rso = daoS.getAdmin(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("name"));

                                AdminDAO daoIMG = new AdminDAO();
                                ResultSet rsIMG = daoIMG.getAdminIMG(rso.getString("name"));
                                rsIMG.next();
                                session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteAdmin/adminReport.jsp").forward(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else {
//                request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                response.sendRedirect("/adminlogin");
            }

        } else if (path.startsWith("/admin/adminReport/List/")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("adCookie")) {
                        if (daoS.checkIfAdminExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("adname", aCookie.getValue());

                            String fn = session.getAttribute("adname").toString();
                            try {
                                ResultSet rso = daoS.getAdmin(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("name"));

                                AdminDAO daoIMG = new AdminDAO();
                                ResultSet rsIMG = daoIMG.getAdminIMG(rso.getString("name"));
                                rsIMG.next();
                                session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String[] s = path.split("/");
                            String id = s[s.length - 1];
                            AdminReportDAO dao = new AdminReportDAO();
                            AdminReport obj = dao.getReport(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminReport");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("list", obj);
                                request.getRequestDispatcher("/siteAdmin/adminReportList.jsp").forward(request, response);
                            }
                        }
                    }
                }
                if (!adminLoginCookieFound) {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else {
//                request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                response.sendRedirect("/adminlogin");
            }

        } else if (path.startsWith("/admin/adminReport/Delete/")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("adCookie")) {
                        if (daoS.checkIfAdminExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("adname", aCookie.getValue());

                            String fn = session.getAttribute("adname").toString();
                            try {
                                ResultSet rso = daoS.getAdmin(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("name"));

                                AdminDAO daoIMG = new AdminDAO();
                                ResultSet rsIMG = daoIMG.getAdminIMG(rso.getString("name"));
                                rsIMG.next();
                                session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String[] s = path.split("/");
                            String id = s[s.length - 1];
                            AdminReportDAO dao = new AdminReportDAO();
                            AdminReport obj = dao.getReport(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminReport");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("delete", obj);
                                session.setAttribute("getID", id);
                                request.getRequestDispatcher("/siteAdmin/adminReportDelete.jsp").forward(request, response);
                            }
                        }
                    }
                }
                if (!adminLoginCookieFound) {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else {
//                request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                response.sendRedirect("/adminlogin");
            }

        }
//        processRequest(request, response);
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
        if (request.getParameter("btnReDelete") != null) {
            int shopID = Integer.parseInt(request.getParameter("txtID"));
            AdminReportDAO dao = new AdminReportDAO();
            int cnt = dao.delete(shopID);
            if (cnt != 0) {
                response.sendRedirect("/admin/adminReport");
            }
        }
//        processRequest(request, response);
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
