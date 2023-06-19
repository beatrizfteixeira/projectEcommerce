package model;

import Exceptions.EmptyCart;
import org.example.Crud;

import java.util.Map;
import java.util.Set;

public class SaleConfirmation {
    private Crud crud;

    public SaleConfirmation() {
        crud = Crud.getInstance();
    }
    public void confirmSale(Cart cart) throws EmptyCart {
        if (cart.isCartEmpty()) {
            throw new EmptyCart("The cart is empty! Add products in the cart to buy them.");
        }
        updateDataBase(cart.getCart());
        cart.cleanCart();
    }
    private void updateDataBase(Map<Integer, CartItem> listCart) {
        Set<Integer> ids = listCart.keySet();
        for (Integer id : ids) {
            System.out.println("IDs: " + id);
            CartItem c = listCart.get(id);
            int quantity = c.getAddedQuantity();
            crud.updateProductQuantity(id, c.getQuantAvailable() - quantity);
        }
    }
}
