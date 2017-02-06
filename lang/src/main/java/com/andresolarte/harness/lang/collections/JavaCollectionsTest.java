package com.andresolarte.harness.lang.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JavaCollectionsTest {

    public static void main(String... args){
        Map<String,String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String value = iterator.next();
            System.out.println("Value: " + value);
        }
    }
}
