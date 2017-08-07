package com.andresolarte.harness.guice.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultProvider.class)
public interface IProvider {

    String provideMessage();
}
