package com.andresolarte.harness.kafka.producer;

import com.andresolarte.harness.kafka.KafkaTestConstants;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

public class TestProducer {
    public static void main(String... args) {
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", kafka.serializer.StringEncoder.class.getCanonicalName());
        props.put("partitioner.class", SimplePartitioner.class.getCanonicalName());
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<>(config);

        IntStream.range(0, 10).forEach(i -> {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<>(KafkaTestConstants.TOPIC, ip, msg);
            producer.send(data);
        });
        producer.close();
    }
}