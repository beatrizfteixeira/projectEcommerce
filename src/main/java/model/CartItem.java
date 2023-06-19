package model;

public class CartItem {
    private final Product product;
    private int quantity;
    private int id;
    private double finalPrice;

    public CartItem(Product p, int quantity) {
        this.product = p;
        this.quantity = quantity;
        this.id = p.getId();
        this.finalPrice = quantity * p.getPrice();
    }
    public String getName() {
        return product.getName();
    }
    public Double getFinalPrice() {
        return finalPrice;
    }
    public int getAddedQuantity() {
        return quantity;
    }
    public int getQuantAvailable() {
        return product.getQuantity();
    }
    public Product getProduct() {
        return product;
    }
    public void setAddedQuantity(int quantity) {
        this.quantity = quantity;
        this.finalPrice = this.quantity * product.getPrice();
    }
}
