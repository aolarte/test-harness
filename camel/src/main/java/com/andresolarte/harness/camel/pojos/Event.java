package com.andresolarte.harness.camel.pojos;

public class Event {
    public Event(String data, Priority priority) {
        this.data = data;
        this.priority = priority;
    }
    private final String data;
    private final Priority priority;

    public String getData() {
        return data;
    }

    public Priority getPriority() {
        return priority;
    }
}
