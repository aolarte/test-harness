package com.andresolarte.harness.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TestEnum {
    HOURLY("Hourly"),
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private String label;

    TestEnum(String label) {
        this.label = label;
    }
}
