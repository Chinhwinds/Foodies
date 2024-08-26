/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class UserOrder {

    int order_ID;
    String user_name;
    String pro_name;
    int quantity;
    double price;
    double total_price;
    String orderDate;
    double orderTotal;

    public UserOrder() {
    }

    public UserOrder(int order_ID, String user_name, String orderDate, double orderTotal) {
        this.order_ID = order_ID;
        this.user_name = user_name;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public UserOrder(int order_ID, String pro_name, int quantity, double price, double total_price, String orderDate) {
        this.order_ID = order_ID;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
        this.orderDate = orderDate;
    }

    public UserOrder(int order_ID, double orderTotal) {
        this.order_ID = order_ID;
        this.orderTotal = orderTotal;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
    
    

    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

}
