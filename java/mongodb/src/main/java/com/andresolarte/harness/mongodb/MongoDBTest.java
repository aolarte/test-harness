package com.andresolarte.harness.mongodb;


import com.andresolarte.harness.mongodb.model.Customer;
import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.connection.ServerSettings;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MongoDBTest {

    public static boolean done = false;

    public static MongoCollection<Customer> customers ;

    public static void main(String... args) throws InterruptedException {

        MongoClient mongo = MongoClients.create("mongodb://localhost:27017/test");
        MongoDatabase database = mongo.getDatabase("test");
        customers = database.getCollection("customer", Customer.class);

        Customer customer1 = new Customer();
        customer1.setUuid(UUID.randomUUID());
        customer1.setName("Test Name");
        List<Customer> insertList = Arrays.asList(customer1);
        customers.insertMany(insertList, (result,  t) -> {
            customers.find().forEach( c-> {
                System.out.println("Doc: " + c.getName());
            },  (result2,  t2)->{
                done = true;
            });


        });
//
//        List<Customer> documentList = new ArrayList<>();
//        customers.find().into(documentList, (result,  t) -> {
//            result.forEach(d -> {
//                System.out.println("Doc: " + d);
//            });
//            System.out.println("Operation Finished!");
//            done = true;
//        });

        while (!done) {
            Thread.sleep(100);
        }

    }


}
