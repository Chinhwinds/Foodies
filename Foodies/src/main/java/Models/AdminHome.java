/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class AdminHome {

    String user_name;
    String user_email;
    String user_phone;
    double order_Total;
    String order_Date;
    String order_status;

    public AdminHome() {
    }

    public AdminHome(String user_name, String user_email, String order_Date, double order_Total, String order_status) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.order_Total = order_Total;
        this.order_Date = order_Date;
        this.order_status = order_status;
    }

    public AdminHome(String user_name, String user_email, String user_phone, double order_Total) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.order_Total = order_Total;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public double getOrder_Total() {
        return order_Total;
    }

    public void setOrder_Total(double order_Total) {
        this.order_Total = order_Total;
    }

    public String getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Date(String order_Date) {
        this.order_Date = order_Date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

}
