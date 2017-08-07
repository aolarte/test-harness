package com.andresolarte.harness.cloudstream.kafka;
//
//import org.apache.zookeeper.server.NIOServerCnxnFactory;
//import org.apache.zookeeper.server.ServerCnxnFactory;
//import org.apache.zookeeper.server.ZooKeeperServer;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.file.Files;
//import java.nio.file.Path;

public class EmbeddedZookeeper {
//    private int port = -1;
//    private int tickTime = 500;
//
//    private ServerCnxnFactory factory;
//    private File snapshotDir;
//    private File logDir;
//
//
//    public EmbeddedZookeeper(int port) {
//        this(port, 500);
//    }
//
//    public EmbeddedZookeeper(int port, int tickTime) {
//        this.port = resolvePort(port);
//        this.tickTime = tickTime;
//    }
//
//    public static void main(String... args) throws IOException, InterruptedException {
//        EmbeddedZookeeper embeddedZookeeper = new EmbeddedZookeeper(9981);
//        embeddedZookeeper.startup();
//    }
//
//    public static File constructTempDir(String dirPrefix) {
//
//        try {
//            Path tempDir = Files.createTempDirectory(dirPrefix);
//            return tempDir.toFile();
//        } catch (IOException e) {
//            throw new RuntimeException("could not create temp directory.", e);
//        }
//    }
//
//    private int resolvePort(int port) {
//
//        return port;
//    }
//
//    public void startup() throws IOException {
//
//        this.factory = NIOServerCnxnFactory.createFactory(new InetSocketAddress("localhost", port), 1024);
//        this.snapshotDir = constructTempDir("embeeded-zk_snapshot");
//        this.logDir = constructTempDir("embeeded-zk_log");
//
//        try {
//            factory.startup(new ZooKeeperServer(snapshotDir, logDir, tickTime));
//        } catch (InterruptedException e) {
//            throw new IOException(e);
//        }
//    }
//
//    public void shutdown() {
//        factory.shutdown();
//
//    }
//
//    public String getConnection() {
//        return "localhost:" + port;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public int getTickTime() {
//        return tickTime;
//    }
//
//    public void setTickTime(int tickTime) {
//        this.tickTime = tickTime;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("EmbeddedZookeeper{");
//        sb.append("connection=").append(getConnection());
//        sb.append('}');
//        return sb.toString();
//    }
}