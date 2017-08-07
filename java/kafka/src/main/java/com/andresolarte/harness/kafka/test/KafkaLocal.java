package com.andresolarte.harness.kafka.test;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;

import java.io.IOException;
import java.util.Properties;


public class KafkaLocal {

    public KafkaServerStartable kafka;

    public KafkaLocal(Properties kafkaProperties) throws IOException, InterruptedException {
        System.out.println("Kafka starting...");
        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);

        kafka = new KafkaServerStartable(kafkaConfig);
        kafka.startup();
        System.out.println("Kafka started");
    }

    public static Builder builder() {
        return new Builder();
    }

    public void stop() {
        System.out.println("Kafka stopping...");
        kafka.shutdown();
        System.out.println("Kafka stopped");
    }

    public static class Builder {

        private String zookeeperConnect = "localhost:2181";
        private String brokerId = "1";

        private Properties properties = new Properties();

        public Builder zookeeperConnect(String zookeeperConnect) {
            this.zookeeperConnect = zookeeperConnect;
            return this;
        }

        public Builder brokerId(String brokerId) {
            this.brokerId = brokerId;
            return this;
        }

        public KafkaLocal build() throws IOException, InterruptedException {
            properties.setProperty("zookeeper.connect", zookeeperConnect);
            properties.setProperty("broker.id", brokerId);
            return new KafkaLocal(properties);
        }


    }

}