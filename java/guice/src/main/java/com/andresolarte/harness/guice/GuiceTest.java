package com.andresolarte.harness.guice;

import com.andresolarte.harness.guice.modules.TestModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceTest {
    public static void main(String... args) {
        Injector injector = Guice.createInjector(new TestModule());

        App app = injector.getInstance(App.class);
        app.run();
    }
}
