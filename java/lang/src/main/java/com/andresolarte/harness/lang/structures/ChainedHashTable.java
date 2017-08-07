package com.andresolarte.harness.lang.structures;

import java.util.stream.IntStream;

public class ChainedHashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry next;
    }

    private static class Bucket<K, V> {
        Entry<K, V> head;
    }

    private static int MAX_BUCKETS = 1024;
    private Bucket[] buckets = new Bucket[MAX_BUCKETS];

    public ChainedHashTable() {
        IntStream.range(0, MAX_BUCKETS).forEach(i->buckets[i]=new Bucket<K,V>());
    }

    public V get(K key) {
        Entry<K, V> entry = findEntry(key);
        return entry == null ? null : entry.value;
    }

    public boolean containsKey(K key) {
        return findEntry(key) != null;
    }

    private Entry<K, V> findEntry(K key) {
        int bucket = findBucket(key);
        Entry<K, V> entry = buckets[bucket].head;
        while (entry != null) {
            if (entry.key == key) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    public V put(K key, V value) {
        Entry<K, V> entry = findEntry(key);
        if (entry != null) {
            V previousValue = entry.value;
            entry.value = value;
            return previousValue;
        } else {
            int bucketIndex = findBucket(key);
            Bucket bucket = buckets[bucketIndex];
            entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            entry.next = bucket.head;
            bucket.head = entry;
            return null;
        }
    }

    private int findBucket(K key) {
        return key.hashCode() % MAX_BUCKETS;
    }

    public static void main(String... args) {
        ChainedHashTable<String, String> hashTable = new ChainedHashTable<>();

        String key1 = "AaAaAa" ;
        String key2 = "AaAaBB" ;
        hashTable.put(key1, "One");
        hashTable.put(key2, "Two");
        System.out.println("Keys share the same hashcode: " + key1.hashCode() + " " + key2.hashCode());
        System.out.println(hashTable.get(key1));
        System.out.println(hashTable.get(key2));
    }


}
