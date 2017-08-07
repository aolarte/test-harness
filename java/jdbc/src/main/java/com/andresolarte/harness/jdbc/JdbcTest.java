package com.andresolarte.harness.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid parameters. Expected 4: driver url user password");
        }
        System.out.println("JDBC Connection Testing");
        String driver = args[0];
        String url = args[1];
        String user = args[2];
        String password = args[3];
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {

            System.out.println("Driver not found");
            e.printStackTrace();
            return;

        }

        System.out.println("JDBC Driver Registered!");

        try(Connection connection = DriverManager.getConnection( url, user, password)) {
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


    }

}