package com.andresolarte.harness.mongodb;


import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.connection.ServerSettings;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBTest {

    public static void main(String... args) throws InterruptedException {

        MongoClient mongo = MongoClients.create("mongodb://localhost:27017/test");
        MongoDatabase database = mongo.getDatabase("test");
        MongoCollection<Document> customers = database.getCollection("customer");

        List<Document> documentList = new ArrayList<>();
        customers.find().into(documentList, (result,  t) -> {
            System.out.println("Operation Finished!");
        });

        Thread.sleep(100000);
    }


}
