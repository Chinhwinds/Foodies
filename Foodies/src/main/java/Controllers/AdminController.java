/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.AdminDAO;
import DAOs.AdminHomeDAO;
import DAOs.AdminUserDAO;
import DAOs.DashboardDAO;
import Models.AdminHome;
import Models.AdminUser;
import Models.Dashboard;
import Models.DashboardDetail;
import Models.UserAccount;
import Models.UserOrder;
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
import java.util.ArrayList;
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
public class AdminController extends HttpServlet {

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
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
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

        Cookie[] cookies = request.getCookies();
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        AdminDAO dao = new AdminDAO();
        if (path.equals("/admin")) {
            if (cookies != null) {
                boolean adminLoginCookieFound = false;
                for (Cookie aCookie : cookies) {
                    //check if existed cookie name adCookie
                    if (aCookie.getName().equals("adCookie")) {
                        //check if value of adCookie is fake or not
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
                                session.setAttribute("adfullname", rso.getString("name"));

                                ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                rsIMG.next();
                                session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                DashboardDAO daoD = new DashboardDAO();
                                ResultSet rsRev = daoD.getRevenue();
                                ResultSet rsPro = daoD.getProfit();
                                ResultSet rsSales = daoD.getAmountSales();
                                ResultSet rsCost = daoD.getCost();

                                List<Dashboard> listHome = new ArrayList<>();
                                while (rsRev.next() && rsPro.next() && rsSales.next() && rsCost.next()) {
                                    double rev = rsRev.getDouble("revenue");
                                    double pro = rsPro.getDouble("profit");
                                    int sales = rsSales.getInt("sales_product");
                                    double cost = rsCost.getDouble("cost");

                                    Dashboard dasHome = new Dashboard(rev, pro, sales, cost);
                                    listHome.add(dasHome);
                                }
                                rsRev.close();
                                rsPro.close();
                                rsSales.close();
                                rsCost.close();
                                session.setAttribute("dashboardHomes", listHome);

                                AdminHomeDAO daoH = new AdminHomeDAO();
                                ResultSet rsUser = daoH.getUserTotal();
                                List<AdminHome> listUser = new ArrayList<>();
                                while (rsUser.next()) {
                                    String name = rsUser.getString("username");
                                    String email = rsUser.getString("email");
                                    String phone = rsUser.getString("phone");
                                    double orderTotal = rsUser.getDouble("order_total");

                                    AdminHome objUser = new AdminHome(name, email, phone, orderTotal);
                                    listUser.add(objUser);
                                }
                                rsUser.close();
                                session.setAttribute("dataUserTotal", listUser);

                                ResultSet rsOrder = daoH.getOrderTotal();
                                List<AdminHome> listOrder = new ArrayList<>();
                                while (rsOrder.next()) {
                                    String name = rsOrder.getString("username");
                                    String email = rsOrder.getString("email");
                                    String date = rsOrder.getString("order_date");
                                    double orderTotal = rsOrder.getDouble("order_total");
                                    String status = rsOrder.getString("status");

                                    AdminHome objOrder = new AdminHome(name, email, date, orderTotal, status);
                                    listOrder.add(objOrder);
                                }
                                rsUser.close();
                                session.setAttribute("dataOrderTotal", listOrder);
                            } catch (SQLException ex) {
                                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            request.getRequestDispatcher("/siteAdmin/admin.jsp").forward(request, response);
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
        } else {
            if (path.equals("/adminUser")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    AdminUserDAO daoU = new AdminUserDAO();
                                    ResultSet rs = daoU.getAllUser();
                                    List<AdminUser> list = new ArrayList<>();
                                    while (rs.next()) {
                                        int userID = rs.getInt("id");
                                        String username = rs.getString("username");
                                        String gender = rs.getString("gender");
                                        String email = rs.getString("email");
                                        String phone = rs.getString("phone");
                                        double total_pay = rs.getDouble("total_payment");
                                        int total_bill = rs.getInt("total_bill");

                                        AdminUser au = new AdminUser(userID, username, gender, email, phone, total_pay, total_bill);
                                        list.add(au);
                                    }
                                    rs.close();
                                    session.setAttribute("dataUser", list);
                                } catch (SQLException ex) {
                                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.getRequestDispatcher("/siteAdmin/adminUserManage.jsp").forward(request, response);
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.equals("/adminUser/Create")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.startsWith("/adminUser/User/")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if existed cookie name adCookie
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
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                String[] s = path.split("/");
                                String id = s[s.length - 1];

                                AdminUserDAO daoU = new AdminUserDAO();
                                UserAccount obj = daoU.getUser(id);
                                if (obj == null) {
                                    response.sendRedirect("/adminUser");
                                } else {
                                    session.setAttribute("user", obj);
                                    request.getRequestDispatcher("/siteAdmin/adminUserEdit.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.startsWith("/adminUser/Delete/")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    System.out.println(rso.getString("name"));
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                String[] s = path.split("/");
                                String id = s[s.length - 1];

                                AdminUserDAO daoU = new AdminUserDAO();
                                UserAccount obj = daoU.getUser(id);
                                if (obj == null) {
                                    response.sendRedirect("/adminUser");
                                } else {
                                    session.setAttribute("user", obj);

                                    try {
                                        ResultSet rs = daoU.getUserOrder(id);
                                        List<UserOrder> list = new ArrayList<>();
                                        while (rs.next()) {
                                            int orderId = rs.getInt("id");
                                            String username = rs.getString("username");
                                            String orderDate = rs.getString("order_date");
                                            double orderTotal = rs.getDouble("order_total");

                                            UserOrder objO = new UserOrder(orderId, username, orderDate, orderTotal);
                                            list.add(objO);
                                        }
                                        rs.close();
                                        session.setAttribute("dataOrder", list);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.getRequestDispatcher("/siteAdmin/adminUserDelete.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.startsWith("/adminUser/Detail/")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    System.out.println(rso.getString("name"));
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                String[] s = path.split("/");
                                String id = s[s.length - 1];
                                AdminUserDAO daoU = new AdminUserDAO();
                                UserAccount obj = daoU.getUser(id);
                                if (obj == null) {
                                    response.sendRedirect("/adminUser");
                                } else {
                                    try {
                                        ResultSet rsDetail = daoU.getOrder(id);
                                        ResultSet rsTotal = daoU.getOrderTotal(id);
                                        List<UserOrder> listT = new ArrayList<>();
                                        List<UserOrder> listD = new ArrayList<>();

                                        while (rsTotal.next()) {
                                            int orderId = rsTotal.getInt("id");
                                            double total = rsTotal.getDouble("order_total");

                                            UserOrder objT = new UserOrder(orderId, total);
                                            listT.add(objT);
                                        }
                                        rsTotal.close();
                                        session.setAttribute("size", listT.size());
                                        session.setAttribute("dataTotal", listT);

                                        while (rsDetail.next()) {
                                            int orderID = rsDetail.getInt("id");
                                            String proName = rsDetail.getString("name");
                                            int quantity = rsDetail.getInt("qty");
                                            double price = rsDetail.getDouble("price");
                                            double total_price = rsDetail.getDouble("total_price");
                                            String orderDate = rsDetail.getString("order_date");

                                            UserOrder objD = new UserOrder(orderID, proName, quantity, price, total_price, orderDate);
                                            listD.add(objD);

                                        }
                                        rsDetail.close();
                                        session.setAttribute("dataDetail", listD);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.getRequestDispatcher("/siteAdmin/adminUserDetail.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.equals("/adminDashboard")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    System.out.println(rso.getString("name"));
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                DashboardDAO daoM = new DashboardDAO();
                                int monthDefault = daoM.printCurrentMonth();
                                int yearDefault = daoM.printCurrentYear();

                                try {
                                    int latestYear = Integer.parseInt(daoM.getLatestYear());
                                    int earliestYear = Integer.parseInt(daoM.getEarliestYear());
                                    System.out.println(monthDefault);
                                    session.setAttribute("month", monthDefault);
                                    session.setAttribute("year", yearDefault);

                                    session.setAttribute("latestyear", latestYear);
                                    session.setAttribute("earliestyear", earliestYear);
                                } catch (NumberFormatException e) {
                                    int latestYear = 1;
                                    int earliestYear = 1;
                                }

//                          
//                            System.out.println(session.getAttribute("month"));
//                            System.out.println(request.getParameter("salesMonth"));
                                try {
//                                String month = request.getParameter("salesMonth");
//                                System.out.println(request.getParameter("salesMonth"));
//                                int monthTrs = Integer.parseInt(month);
                                    DashboardDAO daoD = new DashboardDAO();
                                    ResultSet rsRev = daoD.getRevenue();
                                    ResultSet rsPro = daoD.getProfit();
                                    ResultSet rsSales = daoD.getAmountSales();
                                    ResultSet rsBought = daoD.getMonthBought();
                                    ResultSet rsIncome = daoD.getMonthIncome();
                                    ResultSet rsCost = daoD.getCost();

                                    List<Dashboard> list = new ArrayList<>();
                                    while (rsRev.next() && rsPro.next() && rsSales.next() && rsBought.next() && rsIncome.next() && rsCost.next()) {
                                        double rev = rsRev.getDouble("revenue");
                                        double pro = rsPro.getDouble("profit");
                                        int sales = rsSales.getInt("sales_product");
                                        int bought = rsBought.getInt("bought_in_month");
                                        double income = rsIncome.getDouble("total_income");
                                        double cost = rsCost.getDouble("cost");

                                        Dashboard das = new Dashboard(rev, pro, sales, bought, income, cost);
                                        list.add(das);
                                    }
                                    rsRev.close();
                                    rsPro.close();
                                    rsSales.close();
                                    rsBought.close();
                                    rsIncome.close();
                                    rsCost.close();
                                    session.setAttribute("dashboard", list);

                                    ResultSet rsProduct = daoD.getMostProduct();
                                    List<Dashboard> listPro = new ArrayList<>();

                                    while (rsProduct.next()) {
                                        String name = rsProduct.getString("name");
                                        int amount = rsProduct.getInt("number_of_sales_product");

                                        Dashboard dasPro = new Dashboard(name, amount);
                                        listPro.add(dasPro);
                                    }
                                    rsProduct.close();
                                    session.setAttribute("dashboardProduct", listPro);

                                } catch (SQLException ex) {
                                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.getRequestDispatcher("/siteAdmin/adminDashboard.jsp").forward(request, response);
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.equals("/adminDashboard/Cost")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    System.out.println(rso.getString("name"));
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                DashboardDAO daoD = new DashboardDAO();

                                try {
                                    ResultSet rsCostInfo = daoD.getCostInfo();
                                    List<DashboardDetail> listCost = new ArrayList<>();
                                    while (rsCostInfo.next()) {
                                        int id = rsCostInfo.getInt("id");
                                        String name = rsCostInfo.getString("name");
                                        double cost = rsCostInfo.getDouble("cost");

                                        DashboardDetail dasCost = new DashboardDetail(id, name, cost);
                                        listCost.add(dasCost);
                                    }
                                    rsCostInfo.close();
                                    session.setAttribute("dashboardCostInfo", listCost);
                                } catch (SQLException ex) {
                                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                request.getRequestDispatcher("/siteAdmin/adminDashboardCost.jsp").forward(request, response);
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            } else if (path.equals("/adminDashboard/SalesProduct")) {
                if (cookies != null) {
                    boolean adminLoginCookieFound = false;
                    for (Cookie aCookie : cookies) {
                        //check if existed cookie name adCookie
                        if (aCookie.getName().equals("adCookie")) {
                            //check if value of adCookie is fake or not
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
                                    System.out.println(rso.getString("name"));
                                    session.setAttribute("adfullname", rso.getString("name"));

                                    ResultSet rsIMG = dao.getAdminIMG(rso.getString("name"));
                                    rsIMG.next();
                                    session.setAttribute("adIMG", rsIMG.getString("avatar_img"));
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                DashboardDAO daoD = new DashboardDAO();

                                try {
                                    ResultSet rsSalesInfo = daoD.getAmountSalesInfo();
                                    List<DashboardDetail> listAmount = new ArrayList<>();
                                    while (rsSalesInfo.next()) {
                                        int id = rsSalesInfo.getInt("product_item_id");
                                        String name = rsSalesInfo.getString("name");
                                        int qty = rsSalesInfo.getInt("order_qty");
                                        double price = rsSalesInfo.getDouble("price");

                                        DashboardDetail dasAmount = new DashboardDetail(id, name, qty, price);
                                        listAmount.add(dasAmount);
                                    }
                                    rsSalesInfo.close();
                                    session.setAttribute("dashboardSalesInfo", listAmount);
                                } catch (SQLException ex) {
                                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                request.getRequestDispatcher("/siteAdmin/adminDashboardSalesProduct.jsp").forward(request, response);
                            }
                        }
                    }
                    if (!adminLoginCookieFound) {
//                        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                        response.sendRedirect("/adminlogin");
                    }
                } else {
//                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                    response.sendRedirect("/adminlogin");
                }
            }
//        processRequest(request, response);
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

        if (request.getParameter("btnUserEdit") != null) {
            HttpSession session = request.getSession();
            String id = request.getParameter("txtID");
            String username = request.getParameter("txtName");
            String gender = request.getParameter("gender");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");

            if (username.trim().isEmpty()) {
                AdminUserDAO daoU = new AdminUserDAO();
                UserAccount obj = daoU.getUser(id);
                if (obj == null) {
                    response.sendRedirect("/adminUser");
                } else {
                    session.setAttribute("user", obj);
                    request.setAttribute("editError", "Username could not be white space!");
                    request.getRequestDispatcher("/siteAdmin/adminUserEdit.jsp").forward(request, response);
                }
            } else {
                UserAccount newin = new UserAccount(username, gender, email, phone);

                AdminUserDAO dao = new AdminUserDAO();

                int count = dao.editUser(id, newin);
                if (count != 0) {
                    response.sendRedirect("/adminUser");
                } else {
                    request.setAttribute("editError", "Edit product has been failed! Please check if any field are missing.");
                    request.getRequestDispatcher("/siteAdmin/adminUserEdit.jsp").forward(request, response);
                }
            }
        }

        if (request.getParameter("btnUserAdd") != null) {
            String username = request.getParameter("txtName");
            String gender = request.getParameter("gender");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String password = request.getParameter("txtPass");

            AccountDAO dao = new AccountDAO();
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                request.setAttribute("addError", "Username or password could not be white space!");
                request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
            } else {
                if (dao.checkIfUserExist(username) && dao.checkIfEmailsExist(email)) {
                    request.setAttribute("addError", "Email and Username already taken! Please choose other email and username.");
                    request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                } else {
                    if (dao.checkIfEmailsExist(email)) {
                        request.setAttribute("addError", "Email already taken! Please choose other email.");
                        request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                    } else if (dao.checkIfUserExist(username)) {
                        request.setAttribute("addError", "Username already taken! Please choose other username.");
                        request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                    } else {
                        if (gender.equals("male")) {
                            String img = "male.jpg";
                            UserAccount newinfo = new UserAccount(username, email, phone, gender, img, password);
                            int count = dao.addNewUser(newinfo);
                            if (count > 0) {
                                response.sendRedirect("/adminUser");
                            } else {
                                request.setAttribute("addError", "Add new user has been failed! Please check if any field are missing.");
                                request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                            }
                        } else if (gender.equals("female")) {
                            String img = "female.jpg";
                            UserAccount newinfo = new UserAccount(username, email, phone, gender, img, password);
                            int count = dao.addNewUser(newinfo);
                            if (count > 0) {
                                response.sendRedirect("/adminUser");
                            } else {
                                request.setAttribute("addError", "Add new user has been failed! Please check if any field are missing.");
                                request.getRequestDispatcher("/siteAdmin/adminUserAdd.jsp").forward(request, response);
                            }
                        }
                    }
                }
            }
        }

        if (request.getParameter("btnUserDelete") != null) {
            String userid = request.getParameter("txtID");
            AdminUserDAO dao = new AdminUserDAO();
            int count = dao.delete(userid);
            if (count != 0) {
                response.sendRedirect("/adminUser");
            } else {
                request.getRequestDispatcher("/siteAdmin/adminUserDelete.jsp").forward(request, response);
            }
        }

        if (request.getParameter("btnCheck") != null) {
            HttpSession session = request.getSession();
            DashboardDAO daoM = new DashboardDAO();
            int monthDefault = Integer.parseInt(request.getParameter("salesMonth"));
            int yearDefault = Integer.parseInt(request.getParameter("salesYear"));

            session.setAttribute("month", monthDefault);
            session.setAttribute("year", yearDefault);

            try {
                int month = Integer.parseInt(request.getParameter("salesMonth"));
                int year = Integer.parseInt(request.getParameter("salesYear"));

//                System.out.println("Received salesMonth: " + month);
//                System.out.println("Received salesYear: " + year);
                int latestYear = Integer.parseInt(daoM.getLatestYear());
                int earliestYear = Integer.parseInt(daoM.getEarliestYear());

                session.setAttribute("latestyear", latestYear);
                session.setAttribute("earliestyear", earliestYear);

                DashboardDAO daoD = new DashboardDAO();
                ResultSet rsRev = daoD.getRevenue();
                ResultSet rsPro = daoD.getProfit();
                ResultSet rsSales = daoD.getAmountSales();
                ResultSet rsBought = daoD.getMonthBought(month, year);
                ResultSet rsIncome = daoD.getMonthIncome(month, year);
                ResultSet rsCost = daoD.getCost();

                List<Dashboard> list = new ArrayList<>();
                while (rsRev.next() && rsPro.next() && rsSales.next() && rsBought.next() && rsIncome.next() && rsCost.next()) {
                    double rev = rsRev.getDouble("revenue");
                    double pro = rsPro.getDouble("profit");
                    int sales = rsSales.getInt("sales_product");
                    int bought = rsBought.getInt("bought_in_month");
                    double income = rsIncome.getDouble("total_income");
                    double cost = rsCost.getDouble("cost");

                    Dashboard das = new Dashboard(rev, pro, sales, bought, income, cost);
                    list.add(das);
                }
                rsRev.close();
                rsPro.close();
                rsSales.close();
                rsBought.close();
                rsIncome.close();
                rsCost.close();
                session.setAttribute("dashboard", list);

                ResultSet rsProduct = daoD.getMostProduct();
                List<Dashboard> listPro = new ArrayList<>();

                while (rsProduct.next()) {
                    String name = rsProduct.getString("name");
                    int amount = rsProduct.getInt("number_of_sales_product");

                    Dashboard dasPro = new Dashboard(name, amount);
                    listPro.add(dasPro);
                }
                rsProduct.close();
                session.setAttribute("dashboardProduct", listPro);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("/siteAdmin/adminDashboard.jsp").forward(request, response);
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
