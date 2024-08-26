/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.AdminUser;
import Models.UserAccount;
import Models.UserOrder;
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
public class AdminUserDAO {

    public ResultSet getAllUser() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        try {
            Statement st = conn.createStatement();
            String sql = "SELECT us.id, us.username, us.gender, us.email, us.phone, "
                    + "COALESCE(SUM(so.order_total), 0) AS total_payment, "
                    + "COUNT(so.user_id) AS total_bill "
                    + "FROM shop_order so "
                    + "RIGHT JOIN user_site us ON so.user_id = us.id "
                    + "GROUP BY us.id, us.username, us.gender, us.email, us.phone "
                    + "ORDER BY total_payment DESC";
            rs = st.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Consider throwing a custom exception or returning null with logging here
        }
        return rs;
    }

    public UserAccount getUser(String id) {
        Connection conn = DBConnection.getConnection();
        UserAccount obj;
        try {
            PreparedStatement pst = conn.prepareStatement("select * from user_site where id =?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new UserAccount();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setGender(rs.getString("gender"));
                obj.setImage(rs.getString("image"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setPassword(rs.getString("password"));
            } else {
                obj = null;
            }

        } catch (SQLException ex) {
            obj = null;
        }
        return obj;
    }

    public ResultSet getUserOrder(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pst = conn.prepareStatement("select so.id, us.username, so.order_date, so.order_total\n"
                    + "from shop_order so\n"
                    + "join user_site us \n"
                    + "on so.[user_id] = us.id\n"
                    + "where us.id =?");
            pst.setString(1, id);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int editUser(String id, UserAccount newinfo) {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        String sql = "update user_site set username=?, gender=?, email=?, phone=? where id=?";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, newinfo.getUsername());
            pst.setString(2, newinfo.getGender());
            pst.setString(3, newinfo.getEmail());
            pst.setString(4, newinfo.getPhone());
            pst.setString(5, id);
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            count = 0;
        }
        return count;
    }

    public void resetAfterDelete(String table) {
        Connection conn = DBConnection.getConnection();

        try {
            String sql = "DECLARE @maxId INT; "
                    + "SELECT @maxId = MAX(id) FROM [" + table + "]; "
                    + "IF @maxId IS NULL "
                    + "BEGIN "
                    + "    DBCC CHECKIDENT ('" + table + "', RESEED, 0); "
                    + "END "
                    + "ELSE "
                    + "BEGIN "
                    + "    DBCC CHECKIDENT ('" + table + "', RESEED, @maxId); "
                    + "END";

            PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int delete(String id) {
        Connection conn = DBConnection.getConnection();
        int cnt = 0;
        int sum = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("delete from user_site where id =?");
            pst.setString(1, id);
            cnt = pst.executeUpdate();
            resetAfterDelete("user_site");
            resetAfterDelete("user_site");

            sum = cnt;

        } catch (Exception e) {
            sum = 0;
        }
        return sum;
    }

    public ResultSet getOrder(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pst = conn.prepareStatement("select so.id, p.[name], sum(ol.qty) as [qty], sum(ol.price) as [price], sum(ol.qty*ol.price) as [total_price], so.order_date\n"
                    + "from order_line ol\n"
                    + "join shop_order so on so.id = ol.order_id\n"
                    + "join product_item [pi] on [pi].id = ol.product_item_id\n"
                    + "join product p on p.id = [pi].id\n"
                    + "where so.user_id =?\n"
                    + "group by so.id, p.[name], so.order_date\n"
                    + "order by so.id");
            pst.setString(1, id);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getOrderTotal(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pst = conn.prepareStatement("select id, order_total from shop_order where user_id =? order by id");
            pst.setString(1, id);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}
