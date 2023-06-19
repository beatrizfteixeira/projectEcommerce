package model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;

public class Product {
    @BsonId
    private int id;
    private String name;
    private double price;
    private int quantity;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product (Document docProduct) {
        this.id = (int) docProduct.get("_id");
        this.name = (String) docProduct.get("name");
        this.price = (double) docProduct.get("price");
        this.quantity = (int) docProduct.get("quantity");
    }
    public boolean getAvailability(int quantity) {
        int quantityAfterPutInCart = this.quantity - quantity;
        return quantityAfterPutInCart >= 0;
    }
}
