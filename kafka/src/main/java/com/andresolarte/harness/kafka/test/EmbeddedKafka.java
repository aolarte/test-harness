package com.andresolarte.harness.kafka.test;


import com.andresolarte.harness.zookeeper.server.ZooKeeperLocal;

public class EmbeddedKafka {


    public static void main(String... args) {

        try {
            ZooKeeperLocal zookeeper = ZooKeeperLocal.builder().build();
            KafkaLocal kafka = KafkaLocal.builder().build();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }


    }


}