package com.andresolarte.harness.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Neo4JTest {

    private static final Label USER_LABEL = Label.label("user");
    private static final String USERNAME_PROPERTY = "username";
    private static final String MESSAGE_PROPERTY = "message";

    private enum RelTypes implements RelationshipType {
        KNOWS
    }

    public static void main(String... args) throws Exception {

        //GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        GraphDatabaseService graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();

        new Neo4JTest().test(graphDb);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    private void test(GraphDatabaseService graphDb) {

        insert(graphDb);
        setupDb(graphDb);
        findUser(graphDb, 45);
        runCypher(graphDb);
    }

    private void runCypher(GraphDatabaseService graphDb) {
        try (Transaction tx = graphDb.beginTx()) {
            String s = "CREATE (n:" + USER_LABEL.name() + " { " + USERNAME_PROPERTY + ": 'user999' } )";
            graphDb.execute(s);
            tx.success();
        }

        findUser(graphDb, 999);
    }

    private void findUser(GraphDatabaseService graphDb, int idToFind) {
        String nameToFind = "user" + idToFind;
        try (Transaction tx = graphDb.beginTx()) {
            try (ResourceIterator<Node> users =
                         graphDb.findNodes(USER_LABEL, USERNAME_PROPERTY, nameToFind)) {
                users.stream().forEach(node -> {
                    System.out.println("Found node: " + node.getId());
                    node.getRelationships().forEach(relationship -> System.out.println("Found relationship: " + relationship));
                });
            }
        }
    }

    private void setupDb(GraphDatabaseService graphDb) {
        IndexDefinition indexDefinition;
        try (Transaction tx = graphDb.beginTx()) {
            Schema schema = graphDb.schema();
            indexDefinition = schema.indexFor(USER_LABEL)
                    .on(USERNAME_PROPERTY)
                    .create();
            tx.success();
        }

        IntStream.range(0, 100).forEach(id -> {
            try (Transaction tx = graphDb.beginTx()) {
                Schema schema = graphDb.schema();
                System.out.println(String.format("Percent complete: %1.0f%%",
                        schema.getIndexPopulationProgress(indexDefinition).getCompletedPercentage()));
            }
        });


        try (Transaction tx = graphDb.beginTx()) {
            Schema schema = graphDb.schema();
            schema.awaitIndexOnline(indexDefinition, 10, TimeUnit.SECONDS);
            System.out.println("Index complete");
        }
    }

    private void insert(GraphDatabaseService graphDb) {
        Node rootNode;
        try (Transaction tx = graphDb.beginTx()) {
            rootNode = graphDb.createNode();
            rootNode.setProperty(MESSAGE_PROPERTY, "This is the user root");

            IntStream.range(0, 100).forEach(id -> {
                        Node node = graphDb.createNode(USER_LABEL);
                        node.setProperty(USERNAME_PROPERTY, "user" + id);
                        node.setProperty(MESSAGE_PROPERTY, "user created at " + new Date());
                        Relationship relationship = rootNode.createRelationshipTo(node, RelTypes.KNOWS);
                        relationship.setProperty(MESSAGE_PROPERTY, "user ref");
                    }

            );
            System.out.println("Users created");
            tx.success();
        }


    }
}
