package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.MongoDBConfig;
import org.bson.Document;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int menu;
        System.out.println("Hello world!");
        MongoClient mongoClient = MongoClients.create("mongodb+srv://biaft03:OMZ3qk36VmMXQrVW@cluster0.celdgqs.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db = mongoClient.getDatabase("e-commerceDB");
        MongoCollection products = db.getCollection("products");
//        Document sampleProduct = new Document("_id", "1").append("name", "Pinho Sol");
//
//        col.insertOne(sampleProduct);

        do {
            Scanner input = new Scanner(System.in);
            MenuEmployee menuEmp = new MenuEmployee();
            System.out.println("--------------| WELCOME TO THE SHOPPING CART |-------------");
            System.out.println("Pick an option");
            System.out.println("1) I'm a client");
            System.out.println("2) I'm a employee");
            System.out.println("0) SAIR.");

            menu = input.nextInt();
            while(menu > 4 || menu <0) {
                System.out.println("Informe uma Opção válida!");
                menu = input.nextInt();
            }

            switch(menu) {
                case 1:

                    break;
                case 2:
                    menuEmp.init();
                    break;
            }
        } while(menu != 0);
    }
}