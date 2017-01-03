package com.andresolarte.harness.zookeeper.server;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class EmbeddedZookeeper {
    public static void main(String... args){

        try {
            ZooKeeperLocal zookeeper = ZooKeeperLocal.builder().build();
            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace(System.out);

        }


    }
}