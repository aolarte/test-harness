package com.andresolarte.harness.guice.modules;

import com.andresolarte.harness.guice.interceptor.HTMLPrettify;
import com.andresolarte.harness.guice.interceptor.HTMLPrettifyInterceptor;
import com.andresolarte.harness.guice.service.CustomProvider;
import com.andresolarte.harness.guice.service.IProvider;
import com.andresolarte.harness.guice.service.ISecurity;
import com.andresolarte.harness.guice.service.SecurityProvider;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(HTMLPrettify.class),
                new HTMLPrettifyInterceptor());
        bind(IProvider.class).to(CustomProvider.class);
        bind(ISecurity.class).to(SecurityProvider.class);
    }
}
