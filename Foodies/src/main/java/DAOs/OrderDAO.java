/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class OrderDAO {
    public int getLastOrderId() {
        Connection conn = DBConnection.getConnection();
        int lastId = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT MAX(id) as max_id FROM [shop_order]";
            try ( ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    lastId = rs.getInt("max_id");
                }
            } catch (SQLException e) {
                System.err.println("Error fetching cart ID: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database connection or query error: " + e.getMessage());
        }

        return lastId;
    }

    public int getLastOrderItemId() {
        Connection conn = DBConnection.getConnection();
        int lastId = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT MAX(id) as max_id FROM [order_line]";
            try ( ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    lastId = rs.getInt("max_id");
                }
            } catch (SQLException e) {
                System.err.println("Error fetching cart ID: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database connection or query error: " + e.getMessage());
        }

        return lastId;
    }

    public void createOrder(int orderID, long Total, int UserID) {
        Connection conn = DBConnection.getConnection();
        LocalDate todayPst = LocalDate.now();
        Date pstDate = Date.valueOf(todayPst);

        try {
            String sql = "INSERT INTO [shop_order] VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderID);
            pst.setInt(2, UserID); // User ID 
            pst.setDate(3, pstDate);
            pst.setInt(4, 11); // chua dung du lieu
            pst.setLong(5, Total);
            pst.setInt(6, 1); // chua dung du lieu
            int rs = pst.executeUpdate();

            if (rs == 0) {
                System.out.println("Error: Create Order Fail");
            }
        } catch (SQLException e) {
            System.out.println(e + "hello");
        }
    }

    public void getProductFromCartByUser(int userID, int orderID) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT pc.id, c.user_id, pc.product_item_id, pc.qty, pi.price\n"
                        + "FROM user_site us \n"
                        + "JOIN shopping_cart c ON us.id = c.user_id\n"
                        + "JOIN shopping_cart_item pc ON c.id = pc.cart_id\n"
                        + "JOIN product p ON p.id = pc.product_item_id\n"
                        + "JOIN product_item pi ON p.id = pi.id\n"
                        + "WHERE c.user_id = ?";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, userID); // User ID
                rs = pst.executeQuery();
                while (rs.next()) {
                    try {
                        sql = "INSERT INTO order_line VALUES (?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        pst.setInt(1, getLastOrderItemId() + 1);
                        pst.setInt(2, rs.getInt("product_item_id"));
                        pst.setInt(3, orderID);  
                        pst.setInt(4, rs.getInt("qty"));
                        pst.setLong(5, rs.getLong("price"));
                        int rsu = pst.executeUpdate();
                        if (rsu == 0) {
                            System.out.println("Error: Insert Into order_item false");
                        }
                    } catch (SQLException e) {
                        System.out.println(e + " Error:Order_Item");
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex + "Can't find product! OrderDAO");
            }
        }
    }
}
