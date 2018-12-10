package com.andresolarte.harness.springboot.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TestModel {

    @Max(100)
    @Min(1)
    private long percent;

    public long getPercent() {
        return percent;
    }

    public void setPercent(long percent) {
        this.percent = percent;
    }
}
