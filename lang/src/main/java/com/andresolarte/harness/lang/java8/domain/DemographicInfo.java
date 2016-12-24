package com.andresolarte.harness.lang.java8.domain;


public class DemographicInfo {
    private Gender gender;
    private int age;

    public DemographicInfo(Gender gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DemographicInfo that = (DemographicInfo) o;

        if (age != that.age) return false;
        if (gender != that.gender) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gender.hashCode();
        result = 31 * result + age;
        return result;
    }
}
