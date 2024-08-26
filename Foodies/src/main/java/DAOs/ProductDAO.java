/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuct
 */
public class ProductDAO {

    public List<Product> getAllProductInfo(int page) {
        Connection conn = DB.DBConnection.getConnection();
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT \n"
                        + "    [p].[id],\n"
                        + "    [pc].[category_name],\n"
                        + "    [p].[name],\n"
                        + "    [p].[description],\n"
                        + "    [pi].[product_image],\n"
                        + "    [pi].[qty_in_stock],\n"
                        + "    [pi].[price]\n"
                        + "FROM \n"
                        + "    product [p]\n"
                        + "JOIN \n"
                        + "    product_category [pc] ON [p].[category_id] = [pc].[id]"
                        + "JOIN \n"
                        + "    product_item [pi] ON [p].[id] = [pi].[id]"
                        + "WHERE [pi].[qty_in_stock] > 0\n"
                        + "ORDER BY [p].[id]"
                        + "OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY;";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, (page - 1) * 9);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                    System.out.println(p);
                    list.add(p);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return list;
    }

    public List<Product> get3MostOrdered() {
        Connection conn = DB.DBConnection.getConnection();
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT [p].[id], \n"
                        + "    [pc].[category_name], \n"
                        + "    [p].[name], \n"
                        + "    [p].[description], \n"
                        + "    [pi].[product_image], \n"
                        + "    [pi].[qty_in_stock], \n"
                        + "    [pi].[price]\n"
                        + " FROM (SELECT TOP 3 [ol].[product_item_id], SUM([ol].[qty]) as [most_ordered]\n"
                        + " FROM order_line [ol]\n"
                        + " GROUP BY [ol].[product_item_id] ORDER BY [most_ordered] DESC) AS [most_table]\n"
                        + " JOIN product_item [pi] ON [pi].[id] = [most_table].[product_item_id]\n"
                        + " JOIN product [p] ON [p].[id] = [pi].[id]\n"
                        + " JOIN product_category [pc] ON [pc].[id] = [p].[category_id]";
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                    list.add(p);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return list;
    }

    public List<Product> getProductInfoByCate(int cid, int page) {
        Connection conn = DB.DBConnection.getConnection();
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                int offset = (page - 1) * 9;
                String sql = "SELECT "
                        + "    [p].[id], "
                        + "    [pc].[category_name], "
                        + "    [p].[name], "
                        + "    [p].[description], "
                        + "    [pi].[product_image], "
                        + "    [pi].[qty_in_stock], "
                        + "    [pi].[price] "
                        + "FROM "
                        + "    product [p] "
                        + "JOIN "
                        + "    product_category [pc] ON [p].[category_id] = [pc].[id] "
                        + "JOIN "
                        + "    product_item [pi] ON [p].[id] = [pi].[id] "
                        + "WHERE [pc].[id] = ? AND [pi].[qty_in_stock] > 0"
                        + "ORDER BY [p].[id] "
                        + "OFFSET " + offset + " ROWS FETCH NEXT 9 ROWS ONLY";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, cid);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                    list.add(p);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return list;
    }

    public List<Product> searchProductByName(String name, int page) {
        Connection conn = DB.DBConnection.getConnection();
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                int offset = (page - 1) * 9;
                String sql = "SELECT "
                        + "    [p].[id], "
                        + "    [pc].[category_name], "
                        + "    [p].[name], "
                        + "    [p].[description], "
                        + "    [pi].[product_image], "
                        + "    [pi].[qty_in_stock], "
                        + "    [pi].[price] "
                        + "FROM "
                        + "    product [p] "
                        + "JOIN "
                        + "    product_category [pc] ON [p].[category_id] = [pc].[id] "
                        + "JOIN "
                        + "    product_item [pi] ON [p].[id] = [pi].[id] "
                        + "WHERE [p].[name] LIKE '%" + name + "%' AND [pi].[qty_in_stock] > 0"
                        + "ORDER BY [p].[id] "
                        + "OFFSET " + offset + " ROWS FETCH NEXT 9 ROWS ONLY";
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                    list.add(p);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return list;
    }

    public Product getProductInfoById(int id) {
        Connection conn = DB.DBConnection.getConnection();
        Product p = null;
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT "
                        + "    [p].[id], "
                        + "    [pc].[category_name], "
                        + "    [p].[name], "
                        + "    [p].[description], "
                        + "    [pi].[product_image], "
                        + "    [pi].[qty_in_stock], "
                        + "    [pi].[price] "
                        + "FROM "
                        + "    product [p] "
                        + "JOIN "
                        + "    product_category [pc] ON [p].[category_id] = [pc].[id] "
                        + "JOIN "
                        + "    product_item [pi] ON [p].[id] = [pi].[id] "
                        + "WHERE [p].[id] = ? AND [pi].[qty_in_stock] > 0";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return p;
    }

    public int getProductCount() {
        Connection conn = DB.DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT COUNT(*) FROM product_item WHERE [qty_in_stock] > 0";
                Statement st = conn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return 0;
    }

    public int getProductCountByCate(int cid) {
        Connection conn = DB.DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT COUNT(*) FROM product_item WHERE category_id=? AND [qty_in_stock] > 0";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, cid);
                rs = pst.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return 0;
    }

    public int getProductCountBySearch(String name) {
        Connection conn = DB.DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "SELECT COUNT(*) FROM product WHERE name LIKE '%" + name + "%' AND qty_in_stock > 0";
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                rs = null;
            }
        }
        return 0;
    }
}
