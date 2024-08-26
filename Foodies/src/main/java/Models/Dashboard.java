/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class Dashboard {

    double revenue;
    double profit;
    int sales_product;
    String proname;
    int number_of_pro;
    int bought_in_month;
    double totalincome;
    double cost;

    public Dashboard() {
    }

    public Dashboard(double revenue, double profit, int sales_product, double cost) {
        this.revenue = revenue;
        this.profit = profit;
        this.sales_product = sales_product;
        this.cost = cost;
    }

    public Dashboard(double revenue, double profit, int sales_product, int bought_in_month, double totalincome, double cost) {
        this.revenue = revenue;
        this.profit = profit;
        this.sales_product = sales_product;
        this.bought_in_month = bought_in_month;
        this.totalincome = totalincome;
        this.cost = cost;
    }

    public Dashboard(String proname, int number_of_pro) {
        this.proname = proname;
        this.number_of_pro = number_of_pro;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getSales_product() {
        return sales_product;
    }

    public void setSales_product(int sales_product) {
        this.sales_product = sales_product;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public int getNumber_of_pro() {
        return number_of_pro;
    }

    public void setNumber_of_pro(int number_of_pro) {
        this.number_of_pro = number_of_pro;
    }

    public int getBought_in_month() {
        return bought_in_month;
    }

    public void setBought_in_month(int bought_in_month) {
        this.bought_in_month = bought_in_month;
    }

    public double getTotalincome() {
        return totalincome;
    }

    public void setTotalincome(double totalincome) {
        this.totalincome = totalincome;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
