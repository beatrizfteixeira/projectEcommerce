package org.example;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Crud {
    private static Crud instance;
    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> products;

    private Crud() {
        mongoClient = MongoClients.create("mongodb+srv://biaft03:OMZ3qk36VmMXQrVW@cluster0.celdgqs.mongodb.net/?retryWrites=true&w=majority");
        db = mongoClient.getDatabase("e-commerceDB");
        products = db.getCollection("products");
    }

    public static synchronized Crud getInstance() {
        if (instance == null) {
            instance = new Crud();
        }
        return instance;
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
    public void updateProductName(int id, String name) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set", new BasicDBObject().append("name", name));
        products.updateOne(searchQuery, updateQuery);
    }
    public void updateProductPrice(int id, float price) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set", new BasicDBObject().append("price", price));
        products.updateOne(searchQuery, updateQuery);
    }
    public void updateProductQuantity(int id, int quantity) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set", new BasicDBObject().append("quantity", quantity));
        products.updateOne(searchQuery, updateQuery);
    }
    public Document searchProductById (int id) {
        DBObject searchQuery = new BasicDBObject();
        Document product = products.find(new Document("_id", id)).first();
        return product;
    }
    public boolean checkIfIdExists(int id) {
        if (searchProductById(id) == null) {
            return false;
        }
        return true;
    }

}
