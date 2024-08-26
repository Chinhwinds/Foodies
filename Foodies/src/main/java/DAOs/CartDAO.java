/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Product;

/**
 *
 * @author Admin
 */
public class CartDAO {
    public Product getProduct(String id) {
        Connection conn = DBConnection.getConnection();
        Product obj = null;

        try {
            String sql = "SELECT p.id, p.category_id, p.name, p.description, p.product_image, pi.price\n"
                    + "FROM product p\n"
                    + "FULL JOIN product_item pi ON p.id = pi.id\n"
                    + "WHERE p.id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new Product();
                obj.setId(rs.getInt("id"));
                System.out.println(obj.getId());
                obj.setCategoryId(rs.getInt("category_id"));
                obj.setName(rs.getString("name"));
                obj.setDescription(rs.getString("description"));
                obj.setProductImage(rs.getString("product_image"));
                obj.setPrice(rs.getInt("price"));
            } else {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return obj;
    }

    public int getUserIDbyName(String Username) {
        Connection conn = DBConnection.getConnection();
        int id = 0;
        try {
            String sql = "SELECT id FROM user_site WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, Username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Select UserIDbyName success");
                id = rs.getInt("id");
                return id;
            } else {
                System.out.println("Select UserIDbyName failure");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public int getUserCartID(int UserID) {
        Connection conn = DBConnection.getConnection();
        int id = 0;
        try {
            String sql = "SELECT id FROM shopping_cart WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, UserID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Select userCartID success");
                id = rs.getInt("id");
                return id;
            } else {
                System.out.println("Select userCartID failure");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public long getTotal(int UserID) { // chua tao cho tung cart rieng
        Connection conn = DBConnection.getConnection();
        long total = 0;

        try {
            String sql = "SELECT SUM(pi.price * pc.qty) AS total_price\n"
                    + "FROM user_site us \n"
                    + "JOIN shopping_cart c ON us.id = c.user_id\n"
                    + "JOIN shopping_cart_item pc ON c.id = pc.cart_id\n"
                    + "JOIN product p ON p.id = pc.product_item_id\n"
                    + "JOIN product_item pi ON p.id = pi.id\n"
                    + "WHERE c.user_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, UserID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getLong("total_price");
            } else {
                total = 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public void setCartUser(int userID) { //chi 1 cart nen khong tao nhieu cart cho 1 user
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "INSERT INTO shopping_cart VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setInt(2, userID);
            int rs = pst.executeUpdate();

            if (rs == 0) {
                System.out.println("Error: insert to shopping_cart fail.");
            }
        } catch (SQLException e) {
            System.out.println(e + "setCartUser");
        }
    }

    public void setCart(int ProId, int Quan, int CartID) {
        Connection conn = DBConnection.getConnection();
        try {
            if (!isCartExisted(CartID)) {
                String sql = "INSERT INTO shopping_cart VALUES (?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, CartID);
                pst.setInt(2, CartID);
                int rs = pst.executeUpdate();
                if (rs != 0) {
                    sql = "INSERT INTO shopping_cart_item VALUES (?, ?, ?, ?)";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, getLastCartId() + 1);
                    System.out.println(getLastCartId() + 1 + "setCartID");
                    pst.setInt(2, CartID);
                    pst.setInt(3, ProId);
                    pst.setInt(4, Quan);
                    rs = pst.executeUpdate();

                    if (rs == 0) {
                        System.out.println("Error: Product with ID " + ProId + " not found in cart to update.");
                    }
                }
            } else {
                String sql = "INSERT INTO shopping_cart_item VALUES (?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, getLastCartId() + 1);
                System.out.println(getLastCartId() + 1 + "setCartID");
                pst.setInt(2, CartID);
                pst.setInt(3, ProId);
                pst.setInt(4, Quan);
                int rs = pst.executeUpdate();

                if (rs == 0) {
                    System.out.println("Error: Product with ID " + ProId + " not found in cart to update.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e + "setCart");
        }
    }

    public void updateCart(int ProId, int Quan) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "UPDATE shopping_cart_item SET qty = ? WHERE product_item_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, Quan);
            pst.setInt(2, ProId);

            int rs = pst.executeUpdate();

            if (rs == 0) {
                System.out.println("Error: Product with ID " + ProId + " not found in cart to update.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating cart: " + e.getMessage());
        }
    }
    
    public int getProductQuantity(int UserID) {
        Connection conn = DBConnection.getConnection();
        int quantity = 0;
        try {
            String sql = "SELECT qty_in_stock FROM product_item WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, UserID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("qty_in_stock");
            } 

        } catch (SQLException e) {
            System.out.println("Error Select quantity: " + e.getMessage() + " updateProductItem");
        }

        return quantity;
    }

    public void updateProductItem(int UserID) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT product_item_id, qty FROM shopping_cart_item WHERE cart_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, UserID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                sql = "UPDATE product_item SET qty_in_stock = ? WHERE id = ?";
                pst = conn.prepareStatement(sql);

                pst.setInt(1, getProductQuantity(rs.getInt("product_item_id")) - rs.getInt("qty"));
                pst.setInt(2, rs.getInt("product_item_id"));

                int rsu = pst.executeUpdate();

                if (rsu == 0) {
                    System.out.println("Error: Set new Quantity for Product fail. updateProductItem");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error Select cart: " + e.getMessage() + " updateProductItem");
        }
    }

    public int getCartQuan(int id) {
        Connection conn = DBConnection.getConnection();
        int quantity = 0;
        try {
            String sql = "SELECT qty FROM shopping_cart_item WHERE product_item_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("qty");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return quantity;
    }

    public int getLastCartId() {
        Connection conn = DBConnection.getConnection();
        int lastId = 0;

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT MAX(id) FROM shopping_cart_item";
            try ( ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    lastId = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.err.println("Error fetching cart ID: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database connection or query error: " + e.getMessage());
        }

        return lastId;
    }

    public int getLastCartUserId(int userID) {
        Connection conn = DBConnection.getConnection();
        int lastId = 0;
        try {
            String sql = "SELECT MAX(id) FROM shopping_cart WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userID);
            try ( ResultSet rs = pst.executeQuery(sql)) {
                if (rs.next()) {
                    lastId = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.err.println("Error fetching cart ID: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database connection or query error: " + e.getMessage());
        }

        return lastId;
    }

    public void deleteCart(int UserID) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM shopping_cart_item WHERE cart_id = ?");
            pst.setInt(1, UserID);
            int rs = pst.executeUpdate();

            if (rs == 0) {
                System.err.println("Error: Cart Delete not effected.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting cart item: " + e.getMessage());
        }
    }

    public void deleteCartItem(int cartItemId) {
        Connection conn = DBConnection.getConnection();
        try ( PreparedStatement pst = conn.prepareStatement("DELETE FROM shopping_cart_item WHERE id = ?")) {

            pst.setInt(1, cartItemId);

            int rs = pst.executeUpdate();

            if (rs == 0) {
                System.err.println("Error: Cart item with ID " + cartItemId + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting cart item: " + e.getMessage());
        }
    }

    public boolean isCartExisted(int CartID) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT COUNT(*) FROM shopping_cart WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, CartID);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Product exists if count is greater than 0
            }
        } catch (SQLException e) {
        }

        return false;
    }

    public boolean isProductInCart(int productId) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT COUNT(*) FROM shopping_cart_item WHERE product_item_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Product exists if count is greater than 0
            }
        } catch (SQLException e) {
        }

        return false;
    }
}
