package com.andresolarte.harness.camel.pojos;

public class Result {
    public Result(String data) {
        this.data = data;
    }

    public Result(String data, String reference) {
        this.data = data;
        this.reference = reference;
    }

    public String data;
    public String reference;
}
