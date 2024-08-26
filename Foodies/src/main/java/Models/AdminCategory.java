/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class AdminCategory {

    private int catID;
    private String catName;

    public AdminCategory(int catID, String catName) {
        this.catID = catID;
        this.catName = catName;
    }

    public AdminCategory() {
    }

    public AdminCategory(String catName) {
        this.catName = catName;
    }

    public int getCatID() {
        return catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
