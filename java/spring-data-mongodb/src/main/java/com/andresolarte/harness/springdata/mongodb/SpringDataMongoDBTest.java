package com.andresolarte.harness.springdata.mongodb;

import com.andresolarte.harness.springdata.mongodb.dao.CustomerDAO;
import com.andresolarte.harness.springdata.mongodb.model.Customer;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SpringDataMongoDBTest {

    @EnableMongoRepositories
    @ComponentScan(basePackageClasses = SpringDataMongoDBTest.class)
    @Configuration
    public class SpringDataMongoDBConfig extends AbstractMongoConfiguration {

        private String name = "test";
        private String host = "localhost";
        private String username;
        private String password;

        @Override
        public String getDatabaseName() {
            return name;
        }

        @Override
        @Bean
        public Mongo mongo() throws Exception {

            ServerAddress serverAddress = new ServerAddress(host);
            MongoClientOptions options = new MongoClientOptions.Builder()
                    .build();
            return new MongoClient(serverAddress, options);

            ///To authenticate

//            List<MongoCredential> credentials = new ArrayList<>();
//            credentials.add(MongoCredential.createScramSha1Credential(
//                    username,
//                    name,
//                    password.toCharArray()
//            ));
//            return new MongoClient(serverAddress, credentials, options);
        }
    }

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringDataMongoDBConfig.class);

        SpringDataMongoDBTest test = context.getBean(SpringDataMongoDBTest.class);
        test.run();
    }

    @Autowired
    private CustomerDAO customerDAO;

    public void run() {
        System.out.println("Running SpringDataMongoDBTest");
        List<Customer> customerList = customerDAO.findAll();
        System.out.println("Customer List: " + customerList.size());

        Customer customer = new Customer();
        customerDAO.save(customer);

        customerList = customerDAO.findAll();
        System.out.println("Customer List: " + customerList.size());

    }
}
