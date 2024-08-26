/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Dashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DashboardDAO {

    public int printCurrentMonth() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Extract the month as an integer
        int month = currentDate.getMonthValue();
        // Print the numeric representation of the current month
        return month;
    }

    public int printCurrentYear() {
        return Year.now().getValue();
    }

    public ResultSet getMostProduct() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select top(5) ol.product_item_id, p.name, sum(ol.qty) as number_of_sales_product \n"
                    + "from order_line ol\n"
                    + "join product p on p.id = ol.product_item_id\n"
                    + "group by product_item_id, p.name\n"
                    + "order by number_of_sales_product desc";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getRevenue() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select sum(order_total) as [revenue] from shop_order";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCost() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select sum(price/2)*20 as [cost] from product_item";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCostInfo() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select [pi].id, p.name, price/2 as [cost] \n"
                    + "from product_item [pi]\n"
                    + "join product p on p.id = [pi].id";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProfit() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select sum(order_total) - (select sum(price/2)*20 as [cost] from product_item) as [profit] from shop_order";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getAmountSales() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select sum(qty) as [sales_product] from order_line";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getAmountSalesInfo() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select ol.product_item_id, p.name, sum(ol.qty) as [order_qty], ol.price\n"
                    + "from order_line ol\n"
                    + "join product p on p.id = ol.product_item_id\n"
                    + "group by ol.product_item_id, p.name, ol.price\n"
                    + "order by order_qty desc";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getMonthBought() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            int month = printCurrentMonth();
            int year = printCurrentYear();

            Statement st = conn.createStatement();
            String sql = "select COUNT(*) as [bought_in_month] from shop_order where MONTH(order_date) =" + month + "and YEAR(order_date) =" + year;
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getMonthBought(int month, int year) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
//            int month = printCurrentMonth();
            Statement st = conn.createStatement();
            String sql = "select COUNT(*) as [bought_in_month] from shop_order where MONTH(order_date) =" + month + "and YEAR(order_date) =" + year;
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getMonthIncome() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            int month = printCurrentMonth();
            int year = printCurrentYear();

            Statement st = conn.createStatement();
            String sql = "select sum(order_total) as [total_income] from shop_order where MONTH(order_date) =" + month + "and YEAR(order_date) =" + year;
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getMonthIncome(int month, int year) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
//            int month = printCurrentMonth();
            Statement st = conn.createStatement();
            String sql = "select sum(order_total) as [total_income] from shop_order where MONTH(order_date) =" + month + "and YEAR(order_date) =" + year;
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String getLatestYear() {
        Connection conn = DBConnection.getConnection();
        String year = null;
        try {
            String sql = "select top(?) year(so.order_date) as [latest_year]\n"
                    + "from shop_order so \n"
                    + "join user_site us on us.id = so.user_id \n"
                    + "join order_status os on os.id = so.order_status_id\n"
                    + "order by so.order_date desc";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                year = rs.getString("latest_year");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }

    public String getEarliestYear() {
        Connection conn = DBConnection.getConnection();
        String year = null;
        try {
            String sql = "select top(?) year(so.order_date) as [earliest_year]\n"
                    + "from shop_order so \n"
                    + "join user_site us on us.id = so.user_id \n"
                    + "join order_status os on os.id = so.order_status_id\n"
                    + "order by so.order_date asc";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                year = rs.getString("earliest_year");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }
}
