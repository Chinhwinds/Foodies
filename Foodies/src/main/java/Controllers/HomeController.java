/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BannerDAO;
import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import Models.Banners;
import Models.Category;
import Models.Product;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuct
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class HomeController extends HttpServlet {

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
        BannerDAO bDAO = new BannerDAO();

        String CategoryId = request.getParameter("CategoryID");
        String txtSearch;
        String checkSearch = request.getParameter("SearchName");
        if (checkSearch == null) {
            txtSearch = request.getParameter("txtSearch");
        } else {
            txtSearch = checkSearch;
        }

        if (CategoryId != null) {
            String pageNumber = request.getParameter("page");
            if (pageNumber == null) {
                pageNumber = "1";
            }
            int page = Integer.parseInt(pageNumber);
            int countProduct = pDAO.getProductCountByCate(Integer.parseInt(CategoryId));
            int endPage = countProduct / 9;
            if (countProduct % 9 != 0) {
                ++endPage;
            }
            List<Product> listProduct = pDAO.getProductInfoByCate(Integer.parseInt(CategoryId), page);

            request.setAttribute("CategoryId", CategoryId);
            request.setAttribute("endPage", endPage);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("tag", page);
        } else if (txtSearch != null) {
            String pageNumber = request.getParameter("page");
            if (pageNumber == null) {
                pageNumber = "1";
            }
            int page = Integer.parseInt(pageNumber);
            int countProduct = pDAO.getProductCountBySearch(txtSearch);
            int endPage = countProduct / 9;
            if (countProduct % 9 != 0) {
                ++endPage;
            }
            List<Product> listProduct = pDAO.searchProductByName(txtSearch, page);

            request.setAttribute("Search", txtSearch);
            request.setAttribute("endPage", endPage);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("tag", page);
        } else {
            String pageNumber = request.getParameter("page");
            if (pageNumber == null) {
                pageNumber = "1";
            }
            int page = Integer.parseInt(pageNumber);
            int countProduct = pDAO.getProductCount();
            int endPage = countProduct / 9;
            if (countProduct % 9 != 0) {
                ++endPage;
            }
            List<Product> listProduct = pDAO.getAllProductInfo(page);

            request.setAttribute("endPage", endPage);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("tag", page);
        }

        List<Category> listCate = cDAO.getAllCategoryInfo();
        List<Product> list3MostOrdered = pDAO.get3MostOrdered();
        List<Banners> listBanner = bDAO.getAllBanners();

        request.setAttribute("listCate", listCate);
        request.setAttribute("list3MostOrdered", list3MostOrdered);
        request.setAttribute("listBanners", listBanner);

        request.getRequestDispatcher("/siteUser/home.jsp").forward(request, response);
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

        if (path.equals("/") || path.equals("/Home")) {
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
//                            System.out.println(aCookie.getValue() + "-----");

                            String fn = session.getAttribute("usermail").toString();
                            try {
                                ResultSet rso = dao.getUser(fn);
                                rso.next();
                                session.setAttribute("fullname", rso.getString("username"));
                                session.setAttribute("userID", rso.getInt("id"));
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
        //processRequest(request, response);
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
