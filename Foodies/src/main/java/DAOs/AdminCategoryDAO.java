/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.DBConnection;
import Models.AdminCategory;

/**
 *
 * @author phuct
 */
public class AdminCategoryDAO {

    public ResultSet getAllCate() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from product_category");
            } catch (SQLException ex) {
                Logger.getLogger(AdminCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }

    public AdminCategory getCate(String id) {
        Connection conn = DBConnection.getConnection();
        AdminCategory obj;
        try {
            String sql = "select * from product_category where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new AdminCategory();
                obj.setCatID(rs.getInt("id"));
                obj.setCatName(rs.getString("category_name"));
            } else {
                obj = null;
            }
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    public int addNew(AdminCategory obj) {
        Connection conn = DBConnection.getConnection();
        int cnt = 0;
        try {
            String sql = "insert into product_category values(?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, obj.getCatID());
            pst.setString(2, obj.getCatName());
            cnt = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnt;
    }

    public int edit(int id, AdminCategory newCate) {
        Connection conn = DBConnection.getConnection();
        int cnt;
        try {
            String sql = "update product_category set category_name=? where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newCate.getCatName());
            pst.setInt(2, id);
            cnt = pst.executeUpdate();
        } catch (Exception e) {
            cnt = 0;
        }
        return cnt;
    }

    //update this code after update DB

    public int delete(int id) {
        Connection conn = DBConnection.getConnection();
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        int sum = 0;
        try {
            String sql1 = "delete i\n"
                    + "from product_item i\n"
                    + "inner join product p on i.id = p.id\n"
                    + "inner join product_category c on p.category_id = c.id\n"
                    + "where c.id = ?";
            String sql2 = "delete p\n"
                    + "from product p\n"
                    + "inner join product_category c on p.category_id = c.id\n"
                    + "where c.id = ?";
            String sql3 = "delete c\n"
                    + "from product_category c\n"
                    + "where c.id = ?";

            PreparedStatement pst1 = conn.prepareStatement(sql1);
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            PreparedStatement pst3 = conn.prepareStatement(sql3);
            pst1.setInt(1, id);
            pst2.setInt(1, id);
            pst3.setInt(1, id);
            cnt1 = pst1.executeUpdate();
            resetAfterDelete("product_item");
            resetAfterDelete("product_item");
            cnt2 = pst2.executeUpdate();
            resetAfterDelete("product");
            resetAfterDelete("product");
            cnt3 = pst3.executeUpdate();
            resetAfterDelete("product_category");
            resetAfterDelete("product_category");
            sum = cnt1 + cnt2 + cnt3;

        } catch (Exception e) {
            sum = 0;
        }
        return sum;
    }

    //maybe delete this function

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
            Logger.getLogger(AdminCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isExistCatID(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                String sql = "select count(*) \n"
                        + "from product_category c \n"
                        + "where c.id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AdminCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
