package com.andresolarte.harness.zookeeper.server;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ZooKeeperLocal {

    ZooKeeperServerMain zooKeeperServer;

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private int clientPort = 2181;
        private String dataDir = null;

        private Properties zkProperties = new Properties();

        public Builder clientPort(int clientPort) {
            this.clientPort = clientPort;
            return this;
        }

        public Builder dataDir(String dataDir) {
            this.dataDir = dataDir;
            return this;
        }

        public ZooKeeperLocal build() {
            if (dataDir==null) {
                dataDir=constructTempDir("embedded-zk_data");
            }

            zkProperties.setProperty("clientPort", clientPort + "");
            zkProperties.setProperty("dataDir", dataDir);

            return new ZooKeeperLocal(zkProperties);
        }

        public String constructTempDir(String dirPrefix) {

            try {
                Path tempDir = Files.createTempDirectory(dirPrefix);
                return tempDir.toFile().getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException("Could not create temp directory.", e);
            }
        }
    }

    public ZooKeeperLocal(Properties zkProperties) {
        System.out.println("Zookeeper starting...");
        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        try {
            quorumConfiguration.parseProperties(zkProperties);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        zooKeeperServer = new ZooKeeperServerMain();
        final ServerConfig configuration = new ServerConfig();
        configuration.readFrom(quorumConfiguration);


        new Thread() {
            public void run() {
                try {
                    zooKeeperServer.runFromConfig(configuration);
                } catch (Exception e) {
                    System.out.println("ZooKeeper Failed");
                    e.printStackTrace(System.err);
                }
            }
        }.start();
        System.out.println("Zookeeper started...");
    }
}