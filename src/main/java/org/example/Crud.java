package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Crud {
    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection products;

    public Crud() {
        mongoClient = MongoClients.create("mongodb+srv://biaft03:OMZ3qk36VmMXQrVW@cluster0.celdgqs.mongodb.net/?retryWrites=true&w=majority");
        db = mongoClient.getDatabase("e-commerceDB");
        products = db.getCollection("products");
    }
    public void insertProduct(int id, String name, double price, int quantity) {
        Document novoProduto = new Document("_id", id).append("name", name)
                .append("price", price).append("quantity", quantity);
        products.insertOne(novoProduto);
    }
    public void removeProductById(int id) {
        Document product = new Document("_id", id);
        products.deleteOne(product);
    }
    public void updateProduct(int id, String name, double price, int quantity) {
        Document product = new Document("_id", id);
        Document updatedProduct = new Document("$_id", id).append("name", name)
                .append("price", price).append("quantity", quantity);
        products.updateOne(product, updatedProduct);
        //products.updateOne()
    }
}
