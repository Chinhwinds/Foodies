/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Banners;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BannerDAO {

    public List<Banners> getAllBanners() {
        Connection conn = DBConnection.getConnection();
        List<Banners> list = new ArrayList<>();
        ResultSet rs = null;
        if (conn != null) {
            try {
                String sql = "select title, image, sortOrder from banners\n"
                        + "order by sortOrder asc";
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Banners ban = new Banners(rs.getString(1), rs.getString(2), rs.getInt(3));
                    list.add(ban);
                }
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return list;
    }
}
