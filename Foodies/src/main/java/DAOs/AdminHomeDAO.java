/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdminHomeDAO {

    public ResultSet getUserTotal() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select top(10) us.username, us.email, us.phone, sum(so.order_total) as [order_total] from shop_order so\n"
                    + "join user_site us on us.id = so.user_id\n"
                    + "group by us.username, us.email, us.phone\n"
                    + "order by order_total desc");
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getOrderTotal() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select top(10) us.username, us.email, so.order_date, so.order_total, os.status from shop_order so\n"
                    + "join user_site us on us.id = so.user_id\n"
                    + "join order_status os on os.id = so.order_status_id\n"
                    + "order by so.order_date desc");
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
