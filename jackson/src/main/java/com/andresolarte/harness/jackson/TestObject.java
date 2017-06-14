package com.andresolarte.harness.jackson;

public class TestObject {
    private String name;
    private TestEnum value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestEnum getValue() {
        return value;
    }

    public void setValue(TestEnum value) {
        this.value = value;
    }
}
