package org.example;

import Exceptions.CartProductNotAvailable;
import Exceptions.EmptyCart;
import Exceptions.InvalidIdException;
import Exceptions.ProductNotAvailableException;
import model.Cart;
import model.SaleConfirmation;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ClientMenu {
    private Cart cart;
    private Scanner input;
    private SaleConfirmation saleConfirmation;
    int menu;
    public ClientMenu() {
        cart = Cart.getInstance();
        saleConfirmation = new SaleConfirmation();
    }
    public void init() throws InputMismatchException {
        Crud crud = Crud.getInstance();
        try {
            do {
                input = new Scanner(System.in).useLocale(Locale.US);
                System.out.println("--------------| CLIENT CATALOG SECTION |-------------");
                System.out.println("Pick an option");
                System.out.println("1) Add a product to cart");
                System.out.println("2) Remove a product from your cart");
                System.out.println("3) Cart status");
                System.out.println("4) Buy all items");
                System.out.println("0) QUIT.");

                menu = input.nextInt();
                while (menu > 4 || menu < 0) {
                    System.out.println("Input a valid option!");
                    menu = input.nextInt();
                }

                switch (menu) {
                    case 1: // Add a product to cart by id
                        System.out.println("Insert an product ID");
                        int id = input.nextInt();
                        System.out.println("Insert an quantity to add in the cart");
                        int quantity = input.nextInt();
                        try {
                            cart.addProductCart(id, quantity);
                        } catch (ProductNotAvailableException | InvalidIdException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2: // Remove a product from the cart by id
                        System.out.println("Insert an product ID");
                        id = input.nextInt();
                        System.out.println("How many items do you want to remove?");
                        int quant = input.nextInt();
                        try {
                            cart.removeProductCart(id, quant);
                        } catch (InvalidIdException | CartProductNotAvailable e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3: // Cart status
                        cart.readCartStatus();
                        break;
                    case 4: // Purchase items
                        cart.readCartStatus();
                        System.out.println("Are you gonna buy all those items?");
                        System.out.println("1) Yes");
                        System.out.println("2) No");
                        int resp = input.nextInt();
                        if (resp == 2) {
                            break;
                        } else {
                            try {
                                saleConfirmation.confirmSale(cart);
                            } catch (EmptyCart e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                }
            } while (menu != 0);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            menu = 9;
        }
    }
}
