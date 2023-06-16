package org.example;

import model.Product;

import java.awt.*;
import java.util.Scanner;

public class MenuEmployee {
    int menu;
    public MenuEmployee() {

    }
    public Product getDataScanner() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Id do produto :");
        int id = inputUser.nextInt();
        System.out.println("Nome do produto :");
        String name = inputUser.next();
        System.out.println("Preço do produto :");
        double price = inputUser.nextDouble();
        System.out.println("Quantidade do produto :");
        int quantity = inputUser.nextInt();
        Product newProduct = new Product(id, name, price, quantity);
        return newProduct;
    }
    public void init() {
        Crud crud = new Crud();
        do
        {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------| CATALOG SECTION |-------------");
            System.out.println("Pick an option");
            System.out.println("1) Insert a product to the catalog");
            System.out.println("2) Remove a product");
            System.out.println("3) Update a product");
            System.out.println("0) QUIT.");

            menu = input.nextInt();
            while (menu > 4 || menu < 0) {
                System.out.println("Input a valid option!");
                menu = input.nextInt();
            }
            System.out.println(menu);
            switch (menu) {
                case 1:
                    System.out.println("OLA");
                    System.out.println("Id do produto :");
                    int id = input.nextInt();
                    System.out.println("Nome do produto :");
                    String name = input.next();
                    System.out.println("Preço do produto :");
                    Double price = input.nextDouble();
                    System.out.println("Quantidade do produto :");
                    int quantity = input.nextInt();

                    crud.insertProduct(id, name, price, quantity);

                    break;
                case 2:
                    System.out.println("Id do produto :");
                    id = input.nextInt();
                    crud.removeProductById(id);
                    break;
                case 3:
                    System.out.println("Id do produto :");
                    id = input.nextInt();
                    System.out.println("Novo Nome do produto :");
                    name = input.next();
                    System.out.println("Preço do produto :");
                    price = input.nextDouble();
                    System.out.println("Quantidade do produto :");
                    quantity = input.nextInt();
                    crud.updateProduct(id, name, price, quantity);
                    break;
            }
        } while(menu !=0);
    }

}
