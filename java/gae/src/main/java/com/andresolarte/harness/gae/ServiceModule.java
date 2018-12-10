package com.andresolarte.harness.gae;

import com.andresolarte.harness.gae.services.CacheService;
import com.andresolarte.harness.gae.services.DataService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class ServiceModule  extends AbstractModule {
        @Override
        protected void configure() {
            bind(DataService.class).in(Scopes.SINGLETON);
            bind(CacheService.class).in(Scopes.SINGLETON);

        }

    }
