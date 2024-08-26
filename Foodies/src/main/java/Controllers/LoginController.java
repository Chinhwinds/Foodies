/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import Models.UserAccount;
import DAOs.CartDAO;
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
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        Cookie[] cookies = request.getCookies();
        AccountDAO dao = new AccountDAO();
        if (path.equals("/login")) {
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

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = dao.getUser(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteUser/home.jsp").forward(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    request.getRequestDispatcher("/siteLogin/login.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/siteLogin/login.jsp").forward(request, response);
            }
        } else {
//            if (path.equals("/")) {
//                request.getRequestDispatcher("/siteUser/home.jsp").forward(request, response);
//            } else
            if (path.equals("/register")) {
                request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
            } else if (path.equals("/logout")) {

                if (cookies != null) {
                    for (Cookie aCookie : cookies) {
                        if (aCookie.getName().equals("userCookie")) {
                            aCookie.setValue("");
                            aCookie.setPath("/");
                            aCookie.setMaxAge(0);
                            response.addCookie(aCookie);
                        }
                    }
                    System.out.println("1");
                    session.invalidate();
                    System.out.println("2");
                    response.sendRedirect("/");
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
        if (request.getParameter("btnLogin") != null) {
            String email = request.getParameter("txtUser");
            String pass = request.getParameter("txtPass");
            UserAccount acc = new UserAccount(email, pass);
            AccountDAO dao = new AccountDAO();
            boolean check = dao.login(acc);

            if (dao.login(acc)) {
                Cookie userCookie = new Cookie("userCookie", email);
                userCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(userCookie);

                HttpSession session = request.getSession();
//                session.setAttribute("usermail", email);
//                String fn = session.getAttribute("username").toString();
                try {
                    ResultSet rs = dao.getUser(email);
                    rs.next();
                    session.setAttribute("usermail", email);
                    session.setAttribute("fullname", rs.getString("username"));
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("/");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("error", "Wrong username or password! Please type again.");
                request.getRequestDispatcher("/siteLogin/login.jsp").forward(request, response);
            }
        }
        if (request.getParameter("btnRegister") != null) {
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUser");
            String gender = request.getParameter("gender");
            String userEmail = request.getParameter("txtMail");
            String phone = request.getParameter("txtPhone");
            String pass = request.getParameter("txtPass");

            AccountDAO dao = new AccountDAO();
            if (dao.checkIfUserExist(username) && dao.checkIfEmailsExist(userEmail)) {
                session.setAttribute("regisError", "Email and Username already taken! Please choose other email and username.");
                request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
            } else {
                if (dao.checkIfEmailsExist(userEmail)) {
                    session.setAttribute("regisError", "Email already taken! Please choose other email.");
                    request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
                } else if (dao.checkIfUserExist(username)) {
                    session.setAttribute("regisError", "Username already taken! Please choose other username.");
                    request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
                } else {
                    if (gender.equals("male")) {
                        String img = "male.jpg";
                        UserAccount newinfo = new UserAccount(username, userEmail, phone, gender, img, pass);
                        int count = dao.addNewUser(newinfo);
                        if (count > 0) {
                            Cookie userCookie = new Cookie("userCookie", userEmail);
                            userCookie.setMaxAge(60 * 60 * 24 * 3);
                            response.addCookie(userCookie);

                            try {
                                ResultSet rs = dao.getUser(userEmail);
                                rs.next();
                                session.setAttribute("usermail", userEmail);
                                session.setAttribute("fullname", rs.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            response.sendRedirect("/");
                        } else {
                            session.setAttribute("regisError", "Add new user has been failed! Please check if any field are missing.");
                            request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
                        }
                    } else if (gender.equals("female")) {
                        String img = "female.jpg";
                        UserAccount newinfo = new UserAccount(username, userEmail, phone, gender, img, pass);
                        int count = dao.addNewUser(newinfo);
                        if (count > 0) {
                            Cookie userCookie = new Cookie("userCookie", userEmail);
                            userCookie.setMaxAge(60 * 60 * 24 * 3);
                            response.addCookie(userCookie);

                            try {
                                ResultSet rs = dao.getUser(userEmail);
                                rs.next();
                                session.setAttribute("usermail", userEmail);
                                session.setAttribute("fullname", rs.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            response.sendRedirect("/");
                        } else {
                            session.setAttribute("regisError", "Add new user has been failed! Please check if any field are missing.");
                            request.getRequestDispatcher("/siteLogin/register.jsp").forward(request, response);
                        }
                    }
                }
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
