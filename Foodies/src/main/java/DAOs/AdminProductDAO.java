/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.AdminProduct;
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
public class AdminProductDAO {

    public ResultSet getAllProduct() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select p.id, p.name, p.description, p.product_image, c.category_name, i.qty_in_stock, i.price, i.product_image as [product_item_image]\n"
                    + "            from product p\n"
                    + "            join product_item i on p.id = i.id\n"
                    + "            join product_category c on p.category_id = c.id");
        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public AdminProduct getProduct(String id) {
        Connection conn = DBConnection.getConnection();
        AdminProduct obj;
        try {
            String sql = "select p.id, p.[name], p.[description], p.product_image, c.id as cat_id, c.category_name, i.qty_in_stock, i.price, i.product_image as [product_item_image]\n"
                    + "from product p\n"
                    + "join product_item i on p.id = i.id\n"
                    + "join product_category c on p.category_id = c.id\n"
                    + "where p.id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new AdminProduct();
                obj.setProID(rs.getInt("id"));
                obj.setProName(rs.getString("name"));
                obj.setProDes(rs.getString("description"));
                obj.setProPic(rs.getString("product_image"));
                obj.setCatID(rs.getInt("cat_id"));
                obj.setCatName(rs.getString("category_name"));
                obj.setItemQuan(rs.getInt("qty_in_stock"));
                obj.setItemPrice(rs.getDouble("price"));
                obj.setItemPic(rs.getString("product_item_image"));
            } else {
                obj = null;
            }
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    public int addProduct(AdminProduct obj) {
        Connection conn = DBConnection.getConnection();
        int cnt1 = 0, cnt2 = 0, sum = 0;
        String sql1 = "insert into product values(?, ?, ?, ?)";
        String sql2 = "insert into product_item values(?, ?, ?)";

        try {
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst1.setInt(1, obj.getCatID());
            pst1.setString(2, obj.getProName());
            pst1.setString(3, obj.getProDes());
            pst1.setString(4, obj.getProPic());
            pst2.setInt(1, obj.getItemQuan());
            pst2.setString(2, obj.getItemPic());
            pst2.setDouble(3, obj.getItemPrice());
            cnt1 = pst1.executeUpdate();
            cnt2 = pst2.executeUpdate();
            sum = cnt1 + cnt2;
        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sum;
    }

    public int edit(int id, AdminProduct newPro) {
        Connection conn = DBConnection.getConnection();
        int cnt1 = 0, cnt2 = 0, sum = 0;
        String sql1 = "update product\n"
                + "set category_id = ?, name=?, description=?, product_image=?\n"
                + "where id=?";
        String sql2 = "update product_item\n"
                + "set qty_in_stock=?, product_image=?, price=?\n"
                + "where id=?";

        try {
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            PreparedStatement pst2 = conn.prepareStatement(sql2);

            pst1.setInt(1, newPro.getCatID());
            pst1.setString(2, newPro.getProName());
            pst1.setString(3, newPro.getProDes());
            pst1.setString(4, newPro.getProPic());
            pst1.setInt(5, id);

            pst2.setInt(1, newPro.getItemQuan());
            pst2.setString(2, newPro.getItemPic());
            pst2.setDouble(3, newPro.getItemPrice());
            pst2.setInt(4, id);

            cnt1 = pst1.executeUpdate();
            cnt2 = pst2.executeUpdate();
            sum = cnt1 + cnt2;

        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sum;
    }

    public AdminProduct getProductById(int proID) {
        Connection conn = DBConnection.getConnection();
        AdminProduct obj = null;
        try {
            String sql = "select p.id, p.[name], p.[description], p.product_image, c.category_name, i.qty_in_stock, i.price, i.product_image as [product_item_image]\n"
                    + "from product p\n"
                    + "join product_item i on p.id = i.id\n"
                    + "join product_category c on p.category_id = c.id\n"
                    + "where p.id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, proID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new AdminProduct();
                obj.setProID(rs.getInt("id"));
                obj.setProName(rs.getString("name"));
                obj.setProDes(rs.getString("description"));
                obj.setProPic(rs.getString("product_image"));
                obj.setCatName(rs.getString("category_name"));
                obj.setItemQuan(rs.getInt("qty_in_stock"));
                obj.setItemPrice(rs.getDouble("price"));
                obj.setItemPic(rs.getString("product_item_image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public int delete(int id) {
        Connection conn = DBConnection.getConnection();
        int cnt1 = 0, cnt2 = 0, sum = 0;
        String sql1 = "delete p\n"
                + "from product p\n"
                + "join product_item i on p.id = i.id\n"
                + "where p.id = ?";
        String sql2 = "delete i\n"
                + "from product_item i\n"
                + "where i.id = ?";
        try {
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst1.setInt(1, id);
            pst2.setInt(1, id);
            cnt1 = pst1.executeUpdate();
            resetAfterDelete("product");
            cnt2 = pst2.executeUpdate();
            resetAfterDelete("product_item");
            sum = cnt1 + cnt2;
        } catch (Exception e) {
            sum = 0;
        }
        return sum;
    }

    public void resetAfterDelete(String table) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            String sql = "select MAX(id) as maxId from " + table;
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            int maxID = 0;
            if (rs.next()) {
                maxID = rs.getInt("maxId");
            }
            String reset = "DBCC CHECKIDENT ('" + table + "', RESEED, ?)";
            pst = conn.prepareStatement(reset);
            pst.setInt(1, maxID);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
