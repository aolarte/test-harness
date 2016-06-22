package com.andresolarte.harness.axon;

import com.andresolarte.harness.axon.model.ToDoItemCompletedEvent;
import com.andresolarte.harness.axon.model.ToDoItemCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;

public class ToDoEventHandler {

    @EventHandler
    public void handle(ToDoItemCreatedEvent event) {
        System.out.println("We've got something to do: " + event.getDescription() + " (" + event.getTodoId() + ")");
    }

    @EventHandler
    public void handle(ToDoItemCompletedEvent event) {
        System.out.println("We've completed a task: " + event.getTodoId());
    }
}
