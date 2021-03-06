package com.andresolarte.harness.lang.java8.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String email;
    private String phone;
    private String address;

    public static Person createFakePerson() {
        System.out.println("Creating fake person record");
        return new Builder().givenName("Fake").surName("Person").build();
    }

    public static Person createJohnDoe() {
        return new Builder()
                .givenName("John")
                .surName("Doe")
                .age(25)
                .gender(Gender.MALE)
                .email("john.doe@example.com")
                .phoneNumber("202-123-4678")
                .address("33 3rd St, Smallville, KS 12333")
                .build();
    }


    public static class Builder {

        private String givenName = "";
        private String surName = "";
        private int age = 0;
        private Gender gender = Gender.FEMALE;
        private String eMail = "";
        private String phone = "";
        private String address = "";

        public Builder givenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder surName(String surName) {
            this.surName = surName;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder gender(Gender val) {
            gender = val;
            return this;
        }

        public Builder email(String val) {
            eMail = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phone = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public Person() {
        super();
    }

    private Person(Builder builder) {
        firstName = builder.givenName;
        lastName = builder.surName;
        age = builder.age;
        gender = builder.gender;
        email = builder.eMail;
        phone = builder.phone;
        address = builder.address;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Person createDefaultPerson() {
        return new Builder().givenName("Default").surName("Person").build();
    }

    public static List<Person> createShortList() {
        List<Person> people = new ArrayList<>();

        people.add(
                new Builder()
                        .givenName("Bob")
                        .surName("Baker")
                        .age(21)
                        .gender(Gender.MALE)
                        .email("bob.baker@example.com")
                        .phoneNumber("201-121-4678")
                        .address("44 4th St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Jane")
                        .surName("Doe")
                        .age(25)
                        .gender(Gender.FEMALE)
                        .email("jane.doe@example.com")
                        .phoneNumber("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("John")
                        .surName("Doe")
                        .age(25)
                        .gender(Gender.MALE)
                        .email("john.doe@example.com")
                        .phoneNumber("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("James")
                        .surName("Johnson")
                        .age(45)
                        .gender(Gender.MALE)
                        .email("james.johnson@example.com")
                        .phoneNumber("333-456-1233")
                        .address("201 2nd St, New York, NY 12111")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Joe")
                        .surName("Bailey")
                        .age(67)
                        .gender(Gender.MALE)
                        .email("joebob.bailey@example.com")
                        .phoneNumber("112-111-1111")
                        .address("111 1st St, Town, CA 11111")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Phil")
                        .surName("Smith")
                        .age(55)
                        .gender(Gender.MALE)
                        .email("phil.smith@examp;e.com")
                        .phoneNumber("222-33-1234")
                        .address("22 2nd St, New Park, CO 222333")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Betty")
                        .surName("Jones")
                        .age(85)
                        .gender(Gender.FEMALE)
                        .email("betty.jones@example.com")
                        .phoneNumber("211-33-1234")
                        .address("22 4th St, New Park, CO 222333")
                        .build()
        );


        return people;
    }


    public static List<Person> createLongList() {
        List<Person> people = new ArrayList<>();
        people.addAll(createShortList());
        people.add(
                new Builder()
                        .givenName("Laura")
                        .surName("Smith")
                        .age(21)
                        .gender(Gender.FEMALE)
                        .email("laura.s@example.com")
                        .phoneNumber("201-121-9923")
                        .address("55 6th St, Smallville, KS 12233")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("John")
                        .surName("Johnson")
                        .age(25)
                        .gender(Gender.MALE)
                        .email("j.j@example.com")
                        .phoneNumber("202-312-1241")
                        .address("312 3rd Ave, Smallville, KS 12342")
                        .build()
        );

        people.add(
                new Builder()
                        .givenName("Michael")
                        .surName("Michaels")
                        .age(25)
                        .gender(Gender.MALE)
                        .email("mike@example.com")
                        .phoneNumber("202-142-3124")
                        .address("123 102rd St, Smallville, KS 12383")
                        .build()
        );
        return people;
    }

    public static List<Optional<Person>> createOptionalList(List<Person> list) {
        return list.stream().map(Optional::of).collect(Collectors.toList());
    }

    public String printCustom(Function<Person, String> f) {
        return f.apply(this);
    }

    public void consume(Consumer<Person> consumer) {
        consumer.accept(this);
    }

    public void consumeEmail(Supplier<String> supplier) {
        this.setEmail(supplier.get());
    }

    public String formatName() {
        return firstName + " " + lastName;
    }
}
