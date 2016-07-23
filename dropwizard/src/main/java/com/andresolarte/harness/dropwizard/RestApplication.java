package com.andresolarte.harness.dropwizard;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApplication extends Application<RestApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new RestApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RestApplicationConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new Jdk8Module());
    }

    @Override
    public void run(RestApplicationConfiguration configuration,
                    Environment environment) {
        environment.healthChecks().register("test", new TestHealthCheck());
        environment.jersey().register(new TestResource());
    }

}
