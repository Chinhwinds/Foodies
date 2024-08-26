/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import Models.UserAccount;
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
public class UserController extends HttpServlet {

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
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        Cookie[] cookies = request.getCookies();
        AccountDAO accDao = new AccountDAO();

        String path = request.getRequestURI();
        if (path.equals("/User")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("userCookie")) {
                        if (accDao.checkIfEmailsExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("usermail", aCookie.getValue());
                            System.out.println(aCookie.getValue() + "-----");

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = accDao.getUser(fn);
                                rso.next();
                                UserAccount profile = new UserAccount(rso.getString("username"),
                                        rso.getString("email"),
                                        rso.getString("phone"),
                                        rso.getString("gender"),
                                        rso.getString("image"));
                                session.setAttribute("profile", profile);
                                session.setAttribute("fullname", rso.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteUser/userProfile.jsp").forward(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    response.sendRedirect("/login");
                }
            } else {
                response.sendRedirect("/login");
            }
        } else if (path.equals("/User/Edit")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("userCookie")) {
                        if (accDao.checkIfEmailsExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("usermail", aCookie.getValue());
                            System.out.println(aCookie.getValue() + "-----");

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = accDao.getUser(fn);
                                rso.next();
                                UserAccount profile = new UserAccount(rso.getString("username"),
                                        rso.getString("email"),
                                        rso.getString("phone"),
                                        rso.getString("gender"),
                                        rso.getString("image"));
                                session.setAttribute("profile", profile);
                                session.setAttribute("fullname", rso.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    response.sendRedirect("/login");
                }
            } else {
                response.sendRedirect("/login");
            }
        } else if (path.equals("/User/ChangePassword")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("userCookie")) {
                        if (accDao.checkIfEmailsExist(aCookie.getValue())) {
                            adminLoginCookieFound = true;
                            if (session == null) {  // Check if the session is null
                                session = request.getSession(true);  // Recreate session if it is null
                            }
                            session.setAttribute("usermail", aCookie.getValue());
                            System.out.println(aCookie.getValue() + "-----");

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = accDao.getUser(fn);
                                rso.next();
                                UserAccount profile = new UserAccount(rso.getString("username"),
                                        rso.getString("email"),
                                        rso.getString("phone"),
                                        rso.getString("gender"),
                                        rso.getString("image"));
                                session.setAttribute("profile", profile);
                                session.setAttribute("fullname", rso.getString("username"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteUser/changePassword.jsp").forward(request, response);
                        }
                    }
                }
                if (!adminLoginCookieFound) {
                    response.sendRedirect("/login");
                }
            } else {
                response.sendRedirect("/login");
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
        System.out.println(request.getParameter("btnEditProfile"));
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        Cookie[] cookies = request.getCookies();
        if (request.getParameter("btnEditProfile") != null) {
//            System.out.println("Edit Profile button clicked");
            String username = request.getParameter("txtUsername");
            String gender = request.getParameter("txtGender");
            String userEmail = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String pass = request.getParameter("txtPassword");
            String checkEmail = request.getParameter("txtCheck");
            String checkUsername = request.getParameter("txtCheckUser");

            System.out.println("Parameters: " + username + ", " + gender + ", " + userEmail + ", " + phone + ", " + pass + ", " + checkEmail);

            AccountDAO dao = new AccountDAO();
            if (dao.checkIfUserExistToEdit(checkUsername, username) && dao.checkIfEmailsExistToEdit(checkEmail, userEmail)) {
                session.setAttribute("regisError", "Email and Username already taken! Please choose other email and username.");
                request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
            } else {
                if (dao.checkIfEmailsExistToEdit(checkEmail, userEmail)) {
                    session.setAttribute("regisError", "Email already taken! Please choose other email.");
                    request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                } else if (dao.checkIfUserExistToEdit(checkUsername, username)) {
                    session.setAttribute("regisError", "Username already taken! Please choose other username.");
                    request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                } else {
                    UserAccount check = new UserAccount(checkEmail, pass);
                    if (dao.login(check)) {
                        System.out.println("Login successful");
                        if (gender.equals("male")) {
                            String img = "male.jpg";
                            UserAccount newinfo = new UserAccount(username, userEmail, phone, gender, img);
                            boolean checkE = dao.editAccount(checkEmail, newinfo);
                            if (checkE) {
                                if (cookies != null) {
                                    boolean adminLoginCookieFound = false;
                                    for (Cookie aCookie : cookies) {
                                        if (aCookie.getName().equals("userCookie")) {
                                            aCookie.setValue("");
                                            aCookie.setPath("/");
                                            aCookie.setMaxAge(0);
                                            response.addCookie(aCookie);

                                            Cookie userCookie = new Cookie("userCookie", userEmail);
                                            userCookie.setMaxAge(60 * 60 * 24 * 3);
                                            response.addCookie(userCookie);

                                            adminLoginCookieFound = true;
                                            if (session == null) {  // Check if the session is null
                                                session = request.getSession(true);  // Recreate session if it is null
                                            }
                                            session.setAttribute("usermail", userCookie.getValue());
                                            System.out.println(userCookie.getValue() + "-----");

                                            String fn = session.getAttribute("usermail").toString();
                                            try {
                                                ResultSet rso = dao.getUser(fn);
                                                rso.next();
                                                UserAccount profile = new UserAccount(rso.getString("username"),
                                                        rso.getString("email"),
                                                        rso.getString("phone"),
                                                        rso.getString("gender"),
                                                        rso.getString("image"));
                                                session.setAttribute("profile", profile);
                                                session.setAttribute("fullname", rso.getString("username"));
                                            } catch (SQLException ex) {
                                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            response.sendRedirect("/User");
                                        }
                                    }
                                    if (!adminLoginCookieFound) {
                                        response.sendRedirect("/login");
                                    }
                                } else {
                                    response.sendRedirect("/login");
                                }
                            } else {
                                session.setAttribute("regisError", "Edit user profile has been failed! Please check if any field are missing.");
                                request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                            }
                        } else if (gender.equals("female")) {
                            String img = "female.jpg";
                            UserAccount newinfo = new UserAccount(username, userEmail, phone, gender, img);
//                            System.out.println(newinfo.toString());
                            boolean checkE = dao.editAccount(checkEmail, newinfo);
                            System.out.println("cc3m2");

                            if (checkE) {
                                if (cookies != null) {
                                    boolean adminLoginCookieFound = false;
                                    for (Cookie aCookie : cookies) {
                                        if (aCookie.getName().equals("userCookie")) {
                                            aCookie.setValue("");
                                            aCookie.setPath("/");
                                            aCookie.setMaxAge(0);
                                            response.addCookie(aCookie);

                                            Cookie userCookie = new Cookie("userCookie", userEmail);
                                            userCookie.setMaxAge(60 * 60 * 24 * 3);
                                            response.addCookie(userCookie);

                                            adminLoginCookieFound = true;
                                            if (session == null) {  // Check if the session is null
                                                session = request.getSession(true);  // Recreate session if it is null
                                            }
                                            session.setAttribute("usermail", userCookie.getValue());
                                            System.out.println(userCookie.getValue() + "-----");

                                            String fn = session.getAttribute("usermail").toString();
                                            try {
                                                ResultSet rso = dao.getUser(fn);
                                                rso.next();
                                                UserAccount profile = new UserAccount(rso.getString("username"),
                                                        rso.getString("email"),
                                                        rso.getString("phone"),
                                                        rso.getString("gender"),
                                                        rso.getString("image"));
                                                session.setAttribute("profile", profile);
                                                session.setAttribute("fullname", rso.getString("username"));
                                            } catch (SQLException ex) {
                                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            response.sendRedirect("/User");
                                        }
                                    }
                                    if (!adminLoginCookieFound) {
                                        response.sendRedirect("/login");
                                    }
                                } else {
                                    response.sendRedirect("/login");
                                }
                            } else {
                                session.setAttribute("regisError", "Edit user profile has been failed! Please check if any field are missing.");
                                request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.setAttribute("regisError", "Wrong password, please try again.");
                        request.getRequestDispatcher("/siteUser/editProfile.jsp").forward(request, response);
                    }
                }
            }
        }

        if (request.getParameter("btnChangePass") != null) {
            String oldPass = request.getParameter("txtOldPass");
            String rptNewPass = request.getParameter("rptNewPass");
            System.out.println(rptNewPass);
            String newPass = request.getParameter("txtNewPass");
            System.out.println(newPass);

            AccountDAO dao = new AccountDAO();
            if (cookies != null) {
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("userCookie")) {

                        if (session == null) {  // Check if the session is null
                            session = request.getSession(true);  // Recreate session if it is null
                        }
                        session.setAttribute("usermail", aCookie.getValue());
                        System.out.println(aCookie.getValue() + "-----");
                    }
                }

                String fn = session.getAttribute("usermail").toString();
                UserAccount changePassAcc = new UserAccount(fn, oldPass);
                if (dao.login(changePassAcc)) {
                    if (newPass.equals(rptNewPass)) {
                        boolean checkCP = dao.changePassword(changePassAcc, newPass);
                        System.out.println(checkCP);
                        if (checkCP) {
                            for (Cookie aCookie : cookies) {
                                if (aCookie.getName().equals("userCookie")) {
                                    aCookie.setValue("");
                                    aCookie.setPath("/");
                                    aCookie.setMaxAge(0);
                                    response.addCookie(aCookie);
                                }
                            }
//                            System.out.println("1");
                            session.invalidate();
//                            System.out.println("2");
                            response.sendRedirect("/");
                        } else {
                            session.setAttribute("regisError", "Change user's password has been failed! Please check if any field are missing.");
                            request.getRequestDispatcher("/siteUser/changePassword.jsp").forward(request, response);
                        }
                    } else {
                        session.setAttribute("regisError", "The new password isn't match with the repeated one, please try again.");
                        request.getRequestDispatcher("/siteUser/changePassword.jsp").forward(request, response);
                    }
                } else {
                    session.setAttribute("regisError", "Wrong password, please try again.");
                    request.getRequestDispatcher("/siteUser/changePassword.jsp").forward(request, response);
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
