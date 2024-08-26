/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AdminCategoryDAO;
import DAOs.AdminDAO;
import DAOs.AdminProductDAO;
import Models.AdminCategory;
import Models.AdminProduct;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuct
 */
public class AdminProductController extends HttpServlet {

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
            out.println("<title>Servlet AdminProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminProductController at " + request.getContextPath() + "</h1>");
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
//Product Controller
        if (path.equals("/admin/adminProduct")) {
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
                            request.getRequestDispatcher("/siteAdmin/adminProduct.jsp").forward(request, response);
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
        } else if (path.equals("/admin/adminProduct/Create")) {
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
                            AdminCategoryDAO dao = new AdminCategoryDAO();
                            ResultSet rs = dao.getAllCate();
                            List<AdminCategory> list = new ArrayList<>();
                            try {
                                while (rs.next()) {
                                    int catID = rs.getInt("id");
                                    String catName = rs.getString("category_name");

                                    AdminCategory cate = new AdminCategory(catID, catName);
                                    list.add(cate);
                                }
//                HttpSession session = request.getSession();
                                rs.close();
                                session.setAttribute("data", list);
                            } catch (SQLException ex) {
                                Logger.getLogger(AdminProductController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteAdmin/adminProCreate.jsp").forward(request, response);
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

        } else if (path.startsWith("/admin/adminProduct/Edit/")) {
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
                            AdminProductDAO dao = new AdminProductDAO();
                            AdminProduct obj = dao.getProduct(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminProduct");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("edit", obj);
                                request.getRequestDispatcher("/siteAdmin/adminProEdit.jsp").forward(request, response);
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

        } else if (path.startsWith("/admin/adminProduct/Delete/")) {
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
                            AdminProductDAO dao = new AdminProductDAO();
                            AdminProduct obj = dao.getProduct(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminProduct");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("delete", obj);
                                request.getRequestDispatcher("/siteAdmin/adminProDelete.jsp").forward(request, response);
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

        } //Category Controller        
        else if (path.equals("/admin/adminProduct/categoryCreate")) {
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
                            request.getRequestDispatcher("/siteAdmin/adminCateCreate.jsp").forward(request, response);
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

        } else if (path.startsWith("/admin/adminProduct/categoryEdit/")) {
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
                            AdminCategoryDAO dao = new AdminCategoryDAO();
                            AdminCategory obj = dao.getCate(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminProduct");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("edit", obj);
                                request.getRequestDispatcher("/siteAdmin/adminCateEdit.jsp").forward(request, response);
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

        } else if (path.startsWith("/admin/adminProduct/categoryDelete/")) {
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
                            AdminCategoryDAO dao = new AdminCategoryDAO();
                            AdminCategory obj = dao.getCate(id);

                            if (obj == null) {
                                response.sendRedirect("/admin/adminProduct");
                            } else {
//                HttpSession session = request.getSession();
                                session.setAttribute("catedelete", obj);
                                request.getRequestDispatcher("/siteAdmin/adminCateDelete.jsp").forward(request, response);
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

        if (request.getParameter("btnProCreate") != null) {
//            int proID = Integer.getInteger(request.getParameter("txtProID"));
            String proName = request.getParameter("txtProName");
            String proDes = request.getParameter("txtProDes");
            String proPic = request.getParameter("txtProPic");
            int catID = Integer.parseInt(request.getParameter("txtCatID"));
            int itemQuan = Integer.parseInt(request.getParameter("txtItemQuan"));
            double itemPrice = Double.parseDouble(request.getParameter("txtItemPrice"));
            String itemPic = request.getParameter("txtItemPic");

            AdminProductDAO dao = new AdminProductDAO();

            if (itemQuan < 0) {
                request.setAttribute("Error", "Enter the quantity that bigger than 0.");
                request.getRequestDispatcher("/siteAdmin/adminProCreate.jsp").forward(request, response);
            } else if (itemPrice < 0) {
                request.setAttribute("Errors", "Enter the price that bigger than 0.");
                request.getRequestDispatcher("/siteAdmin/adminProCreate.jsp").forward(request, response);
            } else if ((proName.trim().isEmpty() || proName.equals("")) && (proDes.trim().isEmpty() || proDes.equals(""))) {
                request.setAttribute("error", "Please fill in the required field.");
                request.getRequestDispatcher("/siteAdmin/adminProCreate.jsp").forward(request, response);
            } else if (!isValidImage(proPic) || !isValidImage(itemPic)) {
                request.setAttribute("errorss", "Please provide a valid image file for the product and item pictures.");
                request.getRequestDispatcher("/siteAdmin/adminProCreate.jsp").forward(request, response);
            } else {
                AdminProduct obj = new AdminProduct(proName, proDes, proPic, catID, itemQuan, itemPrice, itemPic);

                int cnt = dao.addProduct(obj);
                if (cnt > 0) {
                    response.sendRedirect("/admin/adminProduct");
                } else {
                    response.sendRedirect("/admin/adminProduct/Create");
                }
            }

        } else if (request.getParameter("btnProEdit") != null) {
            int proID = Integer.parseInt(request.getParameter("txtID"));
            String name = request.getParameter("txtName");
            String des = request.getParameter("txtDes");
            String proPic = request.getParameter("txtProPic");
            int catID = Integer.parseInt(request.getParameter("txtCatID"));
            int quan = Integer.parseInt(request.getParameter("txtQuan"));
            double price = Double.parseDouble(request.getParameter("txtPrice"));
            String itemPic = request.getParameter("txtItemPic");

            AdminProductDAO dao = new AdminProductDAO();

            if (quan < 0) {
                request.setAttribute("Error", "Enter the quantity that bigger than 0.");
                request.getRequestDispatcher("/siteAdmin/adminProEdit.jsp").forward(request, response);
            } else if (price < 0) {
                request.setAttribute("Errors", "Enter the price that bigger than 0.");
                request.getRequestDispatcher("/siteAdmin/adminProEdit.jsp").forward(request, response);
            } else if ((name.equals("") || name.trim().isEmpty()) || (des.equals("") || des.trim().isEmpty())) {
                request.setAttribute("error", "Please fill in the required field.");
                request.getRequestDispatcher("/siteAdmin/adminProEdit.jsp").forward(request, response);
            } else if ((proPic.equals("") || proPic.isEmpty()) && (itemPic.isEmpty() || itemPic.equals(""))) {
                AdminProduct existPic = dao.getProductById(proID);
                proPic = existPic.getProPic();
                itemPic = existPic.getItemPic();
            } else if (!(proPic.equals("") || proPic.isEmpty()) && (itemPic.isEmpty() || itemPic.equals(""))) {
                AdminProduct existPic = dao.getProductById(proID);
                itemPic = existPic.getItemPic();
            } else if ((proPic.equals("") || proPic.isEmpty()) && !(itemPic.isEmpty() || itemPic.equals(""))) {
                AdminProduct existPic = dao.getProductById(proID);
                proPic = existPic.getProPic();
            } else {
                String newPic = request.getParameter("txtProPic");
                String newItem = request.getParameter("txtItemPic");
            }

            AdminProduct obj = new AdminProduct(name, des, proPic, catID, quan, price, itemPic);

            int cnt = dao.edit(proID, obj);

            if (cnt > 0) {
                response.sendRedirect("/admin/adminProduct");
            } else {
                response.sendRedirect("/admin/adminProduct/Edit" + proID);
            }
        } else if (request.getParameter("btnProDelete") != null) {
            int proID = Integer.parseInt(request.getParameter("txtID"));
            AdminProductDAO dao = new AdminProductDAO();
            int cnt = dao.delete(proID);
            if (cnt != 0) {
                response.sendRedirect("/admin/adminProduct");
            }
        }

//        Category Controller
        if (request.getParameter("btnCatCreate") != null) {
            int catCreateID = Integer.parseInt(request.getParameter("txtCreateID"));
            String catCreateName = request.getParameter("txtCreateName");
            AdminCategoryDAO daos = new AdminCategoryDAO();
            boolean check = daos.isExistCatID(catCreateID);

            if (check) {
                request.setAttribute("error", "This Category ID has been existed.");
                request.getRequestDispatcher("/siteAdmin/adminCateCreate.jsp").forward(request, response);
            } else if (catCreateName.equals("") || catCreateName.trim().isEmpty()) {
                request.setAttribute("errors", "Please fill in the required field.");
                request.getRequestDispatcher("/siteAdmin/adminCateCreate.jsp").forward(request, response);
            } else {
                AdminCategory obj = new AdminCategory(catCreateID, catCreateName);

                int counts = daos.addNew(obj);

                if (counts > 0) {
                    response.sendRedirect("/admin/adminProduct");
                } else {
                    response.sendRedirect("/admin/adminProduct/categoryCreate");
                }
            }

        } else if (request.getParameter("btnCatEdit") != null) {
            int catID = Integer.parseInt(request.getParameter("txtID"));
            String catName = request.getParameter("txtName");
            AdminCategoryDAO dao = new AdminCategoryDAO();

            if (catName.equals("") || catName.trim().isEmpty()) {
                request.setAttribute("errors", "Please fill in the required field.");
                request.getRequestDispatcher("/siteAdmin/adminCateEdit.jsp").forward(request, response);
            }
            AdminCategory obj = new AdminCategory(catID, catName);

            int count = dao.edit(catID, obj);
            if (count > 0) {
                response.sendRedirect("/admin/adminProduct");
            } else {
                response.sendRedirect("/admin/adminProduct/ategoryEdit" + catID);
            }
        } else if (request.getParameter("btnCatDelete") != null) {
            int catDeleteID = Integer.parseInt(request.getParameter("txtCatID"));
            System.out.println(request.getParameter("txtCatID"));
            String catDeleteName = request.getParameter("txtCatName");

            AdminCategoryDAO dao = new AdminCategoryDAO();
            int cnt = dao.delete(catDeleteID);
            if (cnt > 0) {
                response.sendRedirect("/admin/adminProduct");
            } else {
                response.sendRedirect("/admin/adminProduct/categoryDelete" + catDeleteID);
            }
        }
//        processRequest(request, response);
    }

    private boolean isValidImage(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".jpg") || lowerCaseFileName.endsWith(".jpeg")
                || lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".gif")
                || lowerCaseFileName.endsWith(".bmp");
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
