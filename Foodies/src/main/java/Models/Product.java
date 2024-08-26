/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author phuct
 */
public class Product {

    private int id;
    private String category_name;
    private String name;
    private String description;
    private String product_image;
    private int qty_in_stock;
    private int price;

    public Product() {
    }

    public Product(int id, String category_name, String name, String description, String product_image, int qty_in_stock, int price) {
        this.id = id;
        this.category_name = category_name;
        this.name = name;
        this.description = description;
        this.product_image = product_image;
        this.qty_in_stock = qty_in_stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getQty_in_stock() {
        return qty_in_stock;
    }

    public void setQty_in_stock(int qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", category_name=" + category_name + ", name=" + name + ", description=" + description + ", product_image=" + product_image + ", qty_in_stock=" + qty_in_stock + ", price=" + price + '}';
    }

}
