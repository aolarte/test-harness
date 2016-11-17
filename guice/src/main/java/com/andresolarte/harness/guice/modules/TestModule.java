package com.andresolarte.harness.guice.modules;

import com.andresolarte.harness.guice.interceptor.HTMLPrettify;
import com.andresolarte.harness.guice.interceptor.HTMLPrettifyInterceptor;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(HTMLPrettify.class),
                new HTMLPrettifyInterceptor());
    }
}
