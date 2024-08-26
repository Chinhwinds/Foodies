/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Category;
import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuct
 */
public class CategoryDAO {
    public List<Category> getAllCategoryInfo() {
        Connection conn = DB.DBConnection.getConnection();
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT * FROM product_category";
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()) {
                    Category c = new Category(rs.getInt(1), rs.getString(2));
                    list.add(c);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return list;
    }
}
