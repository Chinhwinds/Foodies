/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class AdminProduct {

    private int proID;
    private int catID;
    private String proName;
    private String proDes;
    private String proPic;
    private int itemQuan;
    private double itemPrice;
    private String itemPic;
    private String catName;

    public AdminProduct(String proName, String proDes, String proPic, int catID, int itemQuan, double itemPrice, String itemPic) {
        this.proName = proName;
        this.proDes = proDes;
        this.proPic = proPic;
        this.catID = catID;
        this.itemQuan = itemQuan;
        this.itemPrice = itemPrice;
        this.itemPic = itemPic;
    }

    public AdminProduct(int proID, int catID, String proName, String proDes, String proPic, int itemQuan, double itemPrice, String itemPic) {
        this.proID = proID;
        this.catID = catID;
        this.proName = proName;
        this.proDes = proDes;
        this.proPic = proPic;
        this.itemQuan = itemQuan;
        this.itemPrice = itemPrice;
        this.itemPic = itemPic;
    }

    public AdminProduct() {
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getProID() {
        return proID;
    }

    public String getProName() {
        return proName;
    }

    public String getProDes() {
        return proDes;
    }

    public String getProPic() {
        return proPic;
    }

    public int getItemQuan() {
        return itemQuan;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setProDes(String proDes) {
        this.proDes = proDes;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public void setItemQuan(int itemQuan) {
        this.itemQuan = itemQuan;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
