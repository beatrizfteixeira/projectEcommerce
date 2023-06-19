package org.example;
import java.util.Locale;
import java.util.Scanner;

public class MenuEmployee {
    private Scanner input;
    int menu;
    public MenuEmployee() {
    }
    public void init() {
        Crud crud = Crud.getInstance();
        do
        {
            input = new Scanner(System.in).useLocale(Locale.US);
            System.out.println("--------------| CATALOG SECTION |-------------");
            System.out.println("Pick an option");
            System.out.println("1) Insert a product to the catalog");
            System.out.println("2) Update a product");
            System.out.println("3) Remove a product");
            System.out.println("0) QUIT.");

            menu = input.nextInt();
            while (menu > 4 || menu < 0) {
                System.out.println("Input a valid option!");
                menu = input.nextInt();
            }
            System.out.println(menu);
            switch (menu) {
                case 1: // Insert a new product
                    Scanner inputString = new Scanner(System.in);
                    System.out.println("Product's ID :");
                    int id = input.nextInt();
                    if (crud.checkIfIdExists(id)) {
                        System.out.println("ID already exists.");
                        break;
                    }
                    System.out.println("Product's name:");
                    String name = inputString.nextLine();
                    System.out.println("Product's price:");
                    Float price = input.nextFloat();
                    System.out.println("Product's quantity:");
                    int quantity = input.nextInt();
                    crud.insertProduct(id, name, price, quantity);
                    break;
                case 2: // Update a existing product
                    Scanner inputString2 = new Scanner(System.in);
                    System.out.println("Product's ID");
                    id = input.nextInt();
                    if (!crud.checkIfIdExists(id)) {
                        System.out.println("ID does not exist.");
                        break;
                    }
                    int choice;
                    System.out.println("Which field would you like to modify?");
                    System.out.println("Pick an option");
                    System.out.println("1) name");
                    System.out.println("2) price");
                    System.out.println("3) quantity");
                    choice = input.nextInt();
                    switch (choice) { // Choose a field to modify
                        case 1: {
                            System.out.println("Insert a new name:");
                            String updatedName = inputString2.nextLine();
                            System.out.println(updatedName);
                            crud.updateProductName(id, updatedName);
                            break;
                        }
                        case 2: {
                            System.out.println("Insert a new price:");
                            price = input.nextFloat();
                            crud.updateProductPrice(id, price);
                            break;
                        }
                        case 3: {
                            System.out.println("Insert a new quantity:");
                            quantity = input.nextInt();
                            crud.updateProductQuantity(id, quantity);
                            break;
                        }
                        default:{
                            System.out.println("Error!");
                            break;
                        }
                    }
                    break;
                case 3: // Remove a product by id
                    System.out.println("Product's ID :");
                    id = input.nextInt();
                    if (!crud.checkIfIdExists(id)) {
                        System.out.println("ID does not exist.");
                        break;
                    }
                    crud.removeProductById(id);
                    String mensagem = String.format("The product with ID %d has been successfully removed from the catalog.", id);
                    System.out.println(mensagem);
                    break;
            }
        } while(menu !=0);
    }

}
