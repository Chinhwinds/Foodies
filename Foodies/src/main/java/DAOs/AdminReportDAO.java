/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.AdminReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdminReportDAO {

    public ResultSet getAllReport() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            String sql = "select s.id, u.username, s.order_total, s.order_date, u.email, u.phone, os.status\n"
                    + "from shop_order s\n"
                    + "join order_status os on s.order_status_id = os.id\n"
                    + "join user_site u on s.user_id = u.id\n"
                    + "order by s.user_id";
        } catch (SQLException ex) {
            Logger.getLogger(AdminReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public AdminReport getReport(String id) {
        Connection conn = DBConnection.getConnection();
        AdminReport obj;
        try {
            String sql = "select s.id, u.username, ph.[name], ph.product_image, o.qty, o.price, p.qty_in_stock\n"
                    + "from shop_order s\n"
                    + "join order_line o on s.id = o.order_id\n"
                    + "join user_site u on s.user_id = u.id\n"
                    + "join product_item p on o.product_item_id = p.id\n"
                    + "join product ph on ph.id = p.id\n"
                    + "where s.id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new AdminReport();
                obj.setShopID(rs.getInt("id"));
                obj.setUserName(rs.getString("username"));
                obj.setProName(rs.getString("name"));
                obj.setProPic(rs.getString("product_image"));
                obj.setOrderQuan(rs.getInt("qty"));
                obj.setOrderPrice(rs.getDouble("price"));
                obj.setItemQuan(rs.getInt("qty_in_stock"));
            } else {
                obj = null;
            }
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    public int delete(int id) {
        Connection conn = DBConnection.getConnection();
        int cnt = 0;
        String sql = "delete s\n"
                + "from shop_order s\n"
                + "where s.id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            cnt = pst.executeUpdate();
        } catch (Exception e) {
            cnt = 0;
        }
        return cnt;
    }
}
