package model;

import Exceptions.CartProductNotAvailable;
import Exceptions.InvalidIdException;
import Exceptions.ProductNotAvailableException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.example.Crud;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private final Crud crud;
    private Map<Integer, CartItem> listCart;
    private Double totalValue;
    private boolean notError;

    private Cart() {
        listCart = new HashMap<Integer, CartItem>();
        crud = Crud.getInstance();
        totalValue = 0.00;
    }
    public static Cart getInstance() {
        if (instance == null) {
            synchronized (Cart.class) {
                if (instance == null) {
                    instance = new Cart();
                }
            }
        }
        return instance;
    }
    public void addProductCart(int id, int quantity) throws InvalidIdException, ProductNotAvailableException {
        checkIfIdExists(id); // Checks if ID exists in the catalog
        CartItem existingItem = checkIfExistsInCart(id); // Checks if exists in the cart
        if (existingItem == null){
            Document dbObj = crud.searchProductById(id);
            Product product = new Product(dbObj);
            checkAvailability(product, quantity);
            if (notError) {
                CartItem cartItem = new CartItem(product, quantity);
                listCart.put(id, cartItem);
                totalValue += cartItem.getFinalPrice();
            }
        } else {
            addExistingProduct(existingItem, quantity);
        }
    }
    public void addExistingProduct(CartItem item, int quantity) throws ProductNotAvailableException {
        checkAvailability(item.getProduct(), item.getAddedQuantity() + quantity);
        if (notError) {
            double oldPrice = item.getFinalPrice();
            item.setAddedQuantity(quantity);
            totalValue = totalValue + item.getFinalPrice() - oldPrice;
        }
    }
    public void removeProductCart(int id, int quantityRemoved) throws CartProductNotAvailable, InvalidIdException {
        checkIfIdExists(id);
        CartItem item = checkIfExistsInCart(id);
        if (item == null) {
            notError = false;
            throw new CartProductNotAvailable("Product is not in the cart.");
        }
        if (notError) {
            checkCartQuantity(id, quantityRemoved);
            if (item.getAddedQuantity() - quantityRemoved == 0) {
                removeProductCompletely(id);
            }
            double oldPrice = item.getFinalPrice();
            item.setAddedQuantity(item.getAddedQuantity() - quantityRemoved);
            totalValue = totalValue - (oldPrice - item.getFinalPrice());
        }
    }
    public void removeProductCompletely(int id) {
        listCart.remove(id);
    }
    public void readCartStatus(){
        for (Map.Entry<Integer, CartItem> entry : listCart.entrySet()) {
            CartItem p = entry.getValue();
            Integer value = entry.getKey();
            System.out.println("ID: " + value + ", Name: " + p.getName() +  ", Quantity:" + p.getAddedQuantity() + ", Total: " + p.getFinalPrice());
        }
        System.out.println("Total price = " + totalValue);
    }
    private void checkIfIdExists(int id) throws InvalidIdException {
        Document product = crud.searchProductById(id);
        if (product == null) {
            notError = false;
            throw new InvalidIdException("This ID does not EVEN exist. Check the inventory!");
        }
       notError = true;
    }
    private void checkAvailability(Product product, int quantity) throws ProductNotAvailableException {
        if (!product.getAvailability(quantity)) {
            notError = false;
            throw new ProductNotAvailableException("There's no product available in inventory.");
        }
        notError = true;
    }
    private void checkCartQuantity(int id, int quantity) throws CartProductNotAvailable {
        CartItem item = listCart.get(id);
        if (item.getAddedQuantity() < quantity) {
            notError = false;
            throw  new CartProductNotAvailable("You can not remove more items than the quantity you have in cart.");
        }
        notError = true;
    }
    private CartItem checkIfExistsInCart(int id) {
        CartItem item = listCart.get(id);
        if (item == null) {
            return null;
        }
        return item;
    }
    public Map<Integer, CartItem> getCart() {
        return listCart;
    }
    public void cleanCart() {
        listCart.clear();
        totalValue = 0.00;
    }
    public boolean isCartEmpty(){
        if(listCart.isEmpty()) {
            return true;
        }
        return false;
    }
}
