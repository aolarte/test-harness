package com.andresolarte.harness.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class CDI2Test {

    public static void main(String... args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            App app = container.select(App.class).get();
            app.run();
        }
    }
}
