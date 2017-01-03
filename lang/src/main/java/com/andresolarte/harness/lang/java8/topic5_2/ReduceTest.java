package com.andresolarte.harness.lang.java8.topic5_2;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;


public class ReduceTest implements Runnable {

    public static class AverageHolder {
        private int total;
        private int count;

        public AverageHolder() {
            total = 0;
            count = 0;

        }

        public AverageHolder(int total, int count) {
            this.total = total;
            this.count = count;
        }

        public AverageHolder combine(AverageHolder holder) {
            System.out.println("Combine: " + count + " items with " + holder.getCount() + " items");
            return new AverageHolder(total + holder.getTotal(), count + holder.getCount());
        }

        public int getTotal() {
            return total;
        }

        public int getCount() {
            return count;
        }

        public double calculateAverage() {
            return (total / count);
        }
    }


    public void run() {
        List<Person> persons = Person.createLongList();
        AverageHolder averageHolder = persons.parallelStream().map(Person::getAge).map(a -> new AverageHolder(a, 1)).reduce(new AverageHolder(), (a, b) -> b.combine(a));
        System.out.println("Average age: " + averageHolder.calculateAverage());

        averageHolder = persons.stream().map(Person::getAge).map(a -> new AverageHolder(a, 1)).reduce(new AverageHolder(), (a, b) -> b.combine(a));
        System.out.println("Average age: " + averageHolder.calculateAverage());
    }
}
