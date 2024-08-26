/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.AdminAccount;
import Models.UserAccount;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdminDAO {
    public String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(AdminAccount acc) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from admin where username=? and [password]=?");
            pst.setString(1, acc.getUsername());
            pst.setString(2, getMd5(acc.getPassword()));

//DEBUG
//            System.out.println(getMd5(acc.getPassword()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public ResultSet getAdmin(String name) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select * from admin where username=?");
                pst.setString(1, name);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    
    public ResultSet getAdminIMG(String name) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select avatar_img from admin where name=?");
                pst.setString(1, name);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    
    public boolean checkIfAdminExist(String fullname){
        Connection conn = DBConnection.getConnection();
        if(conn != null){
            try {
                PreparedStatement pst = conn.prepareStatement("select COUNT(*) from [admin] where fullname=?");
                pst.setString(1, fullname);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
