package com.andresolarte.harness.dropwizard.model;


import com.google.common.base.Optional;

public class TestOptionalGuava {
    private String name;
    private Optional<Detail> detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Detail> getDetail() {
        return detail;
    }

    public void setDetail(Optional<Detail> detail) {
        this.detail = detail;
    }
}
