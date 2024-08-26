/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
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
public class AccountDAO {

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

    public boolean login(UserAccount acc) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from user_site where email=? and [password]=?");
            pst.setString(1, acc.getEmail());
            pst.setString(2, getMd5(acc.getPassword()));

//DEBUG
            System.out.println(getMd5(acc.getPassword()));

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

    public boolean editAccount(String hiddenEmail, UserAccount acc) {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("update [user_site]\n "
                    + "set\n"
                    + "[username] = ?,\n"
                    + "[gender] = ?,\n"
                    + "[image] = ?,\n"
                    + "[email] = ?,\n"
                    + "[phone] = ?\n"
                    + "where [email] = ?;");
            pst.setString(1, acc.getUsername());
            pst.setString(2, acc.getGender());
            pst.setString(3, acc.getImage());
            pst.setString(4, acc.getEmail());
            pst.setString(5, acc.getPhone());
            pst.setString(6, hiddenEmail);
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count != 0;
    }

    public boolean changePassword(UserAccount acc, String newPass) {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("update [user_site]\n "
                    + "set\n"
                    + "[password] = ?\n"
                    + "where [email] = ?;");
            pst.setString(1, getMd5(newPass));
            pst.setString(2, acc.getEmail());
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count != 0;
    }

    public int addNewUser(UserAccount acc) {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("insert into user_site values (?,?,?,?,?,?)");
            pst.setString(1, acc.getUsername());
            pst.setString(2, acc.getGender());
            pst.setString(3, acc.getImage());
            pst.setString(4, acc.getEmail());
            pst.setString(5, acc.getPhone());
            pst.setString(6, getMd5(acc.getPassword()));
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ResultSet getUser(String email) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select * from user_site where email=?");
                pst.setString(1, email);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }

    public boolean checkIfUserExist(String name) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select COUNT(*) from user_site where username=?");
                pst.setString(1, name);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkIfUserExistToEdit(String checkUser, String username) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select COUNT(*) from user_site "
                        + "where"
                        + " username = ? and username not in ('" + checkUser + "')");
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                System.out.println(count);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkIfEmailsExist(String email) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select COUNT(*) from user_site where email=?");
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
//                System.out.println(count);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkIfEmailsExistToEdit(String checkMail, String email) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select COUNT(*) from user_site "
                        + "where"
                        + " email = ? and email not in ('" + checkMail + "')");
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                System.out.println(count);
                return count > 0;
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
