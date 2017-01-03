package com.andresolarte.harness.lang.java8.topic4_1;

import com.andresolarte.harness.lang.java8.domain.DemographicInfo;
import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MapTest implements Runnable {
    @Override
    public void run() {
        List<Person> personList = Person.createLongList();
        System.out.println("===Map Distinct===");

        personList.stream().map(p -> new DemographicInfo(p.getGender(), p.getAge())).distinct().forEach(d -> System.out.println("Gender: " + d.getGender() + " age: " + d.getAge()));
        System.out.println("===Map Group===");

        Map<Integer, List<DemographicInfo>> map = personList.stream().map(p -> new DemographicInfo(p.getGender(), p.getAge()))
                .collect(Collectors.groupingBy(DemographicInfo::getAge));

        map.entrySet().stream().peek(e -> System.out.println("Age: " + e.getKey())).forEach(e -> e.getValue().stream().forEach(d -> System.out.println(d.getGender())));

    }
}
