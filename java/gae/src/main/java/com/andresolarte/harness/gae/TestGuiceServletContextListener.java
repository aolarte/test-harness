package com.andresolarte.harness.gae;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.annotation.WebListener;

@WebListener
public class TestGuiceServletContextListener  extends GuiceServletContextListener{


    @Override protected Injector getInjector() {
        return Guice.createInjector(
                new TestServletModule(),
                new ServiceModule());
    }
}
