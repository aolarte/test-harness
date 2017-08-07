package com.andresolarte.harness.spring4.jdbc;

public class Constants {

    public static final String H2_CLASS = "org.h2.Driver";
    public static final String H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String CREATE_TABLE_DDL = ";INIT=CREATE TABLE IF NOT EXISTS TEST(ID INT auto_increment, NAME VARCHAR)\\;";
}
