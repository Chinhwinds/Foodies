/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Chinhwind
 */
public class Product {

    private int id;
    private int categoryId;
    private int quantity;
    private String name;
    private String category_name;
    private String description;
    private String productImage; // Store image path or URL
    private String product_image;

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public void setQty_in_stock(int qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public int getQty_in_stock() {
        return qty_in_stock;
    }
    private int qty_in_stock;
    private long price;

    // Constructors
    public Product() {
        // Default constructor (may be needed for some frameworks)
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

    public Product(int id, int categoryId, int quantity, String name, String description, String productImage, long price) {
        this.id = id;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.productImage = productImage;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product(int id, int categoryId, String name, String description, String productImage, long price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.productImage = productImage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProductImage() {
        return productImage;
    }

    public long getPrice() {
        return price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
