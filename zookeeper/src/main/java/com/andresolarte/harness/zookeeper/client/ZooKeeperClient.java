package com.andresolarte.harness.zookeeper.client;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ZooKeeperClient {
    protected static final Logger LOG = LoggerFactory.getLogger(ZooKeeperClient.class);
    final CountDownLatch connectedSignal = new CountDownLatch(1);
    private ZooKeeper zoo;

    public static void main(String... args) throws IOException, InterruptedException, KeeperException {


        ZooKeeperClient client = new ZooKeeperClient();
        client.connect("127.0.0.1:9981");


        String path = "/node";
        byte[] data = "My Test Data".getBytes();

        if (client.nodeExists(path) == null) {
            LOG.info("Create Node");
            client.create(path, data);
        }

        LOG.info("Data: " + client.getData(path));

        client.updateData(path, "new Data".getBytes());

        client.deleteNode(path);

        client.close();
    }

    public void create(String path, byte[] data) throws
            KeeperException, InterruptedException {
        zoo.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    public Stat updateData(String path, byte[] data) throws KeeperException, InterruptedException {
        return zoo.setData(path, data, -1); //match any version
    }

    public Stat nodeExists(String path) throws
            KeeperException, InterruptedException {

        return zoo.exists(path, true);
    }

    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zoo.delete(path, -1); // Match any version
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] b = zoo.getData(path, new Watcher() {
            //Watcher will only catch one event!!
            public void process(WatchedEvent we) {

                if (we.getType() == Event.EventType.None) {
                    switch (we.getState()) {
                        case Expired:
                            LOG.info("Watch Expired");
                            break;
                    }

                }
                if (we.getType() == Event.EventType.NodeDeleted) {
                    LOG.info("Node Deleted");
                }
                if (we.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        byte[] bn = zoo.getData(path,
                                false, null);
                        String data = new String(bn,
                                "UTF-8");
                        LOG.info("New Data: " + data);

                    } catch (Exception ex) {
                        LOG.error(ex.getMessage(), ex);
                    }
                } else {
                    LOG.info("Other event: " + we.getType());

                }
            }
        }, null);

        return new String(b);
    }

    // Method to connect zookeeper ensemble.
    public ZooKeeper connect(String host) throws IOException, InterruptedException {

        zoo = new ZooKeeper(host, 5000, new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }
}