/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class AdminReport {
    //SHOP ORDER
    private int shopID;
    private int userID;
    private Date orderDate;
    private int shipAddress;
    private double total;
    private int statusID;
    
    //ORDER LINE
    private int orderID;
    private int proitemID;
    private int orderQuan;
    private double orderPrice;
    
    //ORDER STATUS
    private String status;
    
    //USER SITE
    private String userName;
    private String email;
    private String phone;
    
    //PRODUCT 
    private String proName;
    private String proPic;
    //PRODUCT ITEM;
    private int itemQuan;

    public AdminReport(int shopID, int userID, Date orderDate, int shipAddress, double total, int statusID) {
        this.shopID = shopID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.shipAddress = shipAddress;
        this.total = total;
        this.statusID = statusID;
    }

    public AdminReport(int orderID, int proitemID, int orderQuan, double orderPrice) {
        this.orderID = orderID;
        this.proitemID = proitemID;
        this.orderQuan = orderQuan;
        this.orderPrice = orderPrice;
    }

    public AdminReport(String userName, String email, String phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public AdminReport(int shopID, int orderQuan, double orderPrice, String userName, String proName, String proPic, int itemQuan) {
        this.shopID = shopID;
        this.orderQuan = orderQuan;
        this.orderPrice = orderPrice;
        this.userName = userName;
        this.proName = proName;
        this.proPic = proPic;
        this.itemQuan = itemQuan;
    }

    public AdminReport() {
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(int shipAddress) {
        this.shipAddress = shipAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProitemID() {
        return proitemID;
    }

    public void setProitemID(int proitemID) {
        this.proitemID = proitemID;
    }

    public int getOrderQuan() {
        return orderQuan;
    }

    public void setOrderQuan(int orderQuan) {
        this.orderQuan = orderQuan;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getItemQuan() {
        return itemQuan;
    }

    public void setItemQuan(int itemQuan) {
        this.itemQuan = itemQuan;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }
}
