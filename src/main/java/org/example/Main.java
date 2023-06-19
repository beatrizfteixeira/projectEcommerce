package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InputMismatchException {
        int menu = 0;
        boolean runMenu = true;
        while(runMenu) {
            try {
                menuInit(menu);
                runMenu = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                runMenu = true;
            }
        }
    }
    public static void menuInit(int menu) {
        do {
            Scanner input = new Scanner(System.in);
            MenuEmployee menuEmp = new MenuEmployee();
            ClientMenu menuClient = new ClientMenu();
            System.out.println("--------------| WELCOME TO THE SHOPPING CART |-------------");
            System.out.println("Pick an option");
            System.out.println("1) I'm a client");
            System.out.println("2) I'm a employee");
            System.out.println("0) SAIR.");

            menu = input.nextInt();
            while (menu > 4 || menu < 0) {
                System.out.println("Invalid option!.");
                menu = input.nextInt();
            }

            switch (menu) {
                case 1:
                    menuClient.init();
                    break;
                case 2:
                    menuEmp.init();
                    break;
            }
        } while (menu != 0);
    }
}