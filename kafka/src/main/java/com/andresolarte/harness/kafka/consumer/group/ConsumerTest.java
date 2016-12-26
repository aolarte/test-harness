package com.andresolarte.harness.kafka.consumer.group;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class ConsumerTest implements Runnable {
    private KafkaStream stream;
    private int threadNumber;

    public ConsumerTest(KafkaStream stream, int threadNumber) {
        this.threadNumber = threadNumber;
        this.stream = stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            byte[] message = it.next().message();
            System.out.println("Thread " + threadNumber + " received: " + new String(message));
        }
        System.out.println("Shutting down Thread: " + threadNumber);
    }
}