
Open the Shell:

    cqlsh 192.168.33.10

Creating a Keyspace:

    CREATE KEYSPACE demo
    WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };
    
    USE demo;
    
    CREATE TABLE users (
    firstname text, 
    lastname text, 
    age int, 
    email text, 
    city text, 
    PRIMARY KEY (lastname));