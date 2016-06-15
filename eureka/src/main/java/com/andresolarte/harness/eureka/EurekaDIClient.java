package com.andresolarte.harness.eureka;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.guice.EurekaModule;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EurekaDIClient {

    private static final Logger logger = LoggerFactory.getLogger(EurekaDIClient.class);

    static class EurekaTest {

        @Inject
        public EurekaClient client;

        public void run() {
            client.registerHealthCheck(new HealthCheckHandler(){
                int count=3;
                @Override
                public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus currentStatus) {
                    if (count>0) {
                        count=count-1;
                        return InstanceInfo.InstanceStatus.UP;
                    } else {
                        count=5;
                        return InstanceInfo.InstanceStatus.DOWN;
                    }

                }
            });
            while (true) {
                Applications applications=client.getApplicationsForARegion("default");
                if (applications!=null) {
                    for (Application app:applications.getRegisteredApplications()) {
                        logger.info("Registered App: " + app.getName());
                    }

                }
                Application application=client.getApplication("SAMPLE_APP");
                if (application!=null) {
                    logger.info("SAMPLE_APP Live instances: " + application.getInstances().size());
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    static class ExampleServiceModule extends AbstractModule {


        @Override
        protected void configure() {
            bind(EurekaTest.class).asEagerSingleton();
        }
    }

    private static Injector init() throws Exception {
        logger.info("Creating injector for Example Service");
        Module customEurekaModule=Modules.override(new EurekaModule()).with(new AbstractModule() {
            @Override
            protected void configure() {
                DynamicPropertyFactory configInstance = com.netflix.config.DynamicPropertyFactory.getInstance();
                bind(DynamicPropertyFactory.class).toInstance(configInstance);
                // the default impl of EurekaInstanceConfig is CloudInstanceConfig, which we only want in an AWS
                // environment. Here we override that by binding MyDataCenterInstanceConfig to EurekaInstanceConfig.
                bind(EurekaInstanceConfig.class).to(MyDataCenterInstanceConfig.class);

                // (DiscoveryClient optional bindings) bind the optional event bus
                // bind(EventBus.class).to(EventBusImpl.class).in(Scopes.SINGLETON);
            }
        });

        Injector injector = Guice.createInjector(customEurekaModule, new ExampleServiceModule());



        logger.info("Done creating the injector");
        return injector;
    }

    public static void main(String[] args) throws Exception {
        Injector injector = null;
        try {
            injector = init();
            EurekaTest test=injector.getInstance(EurekaTest.class);
            test.run();
            //injector.awaitTermination();
        } catch (Exception e) {
            logger.error("Error starting the sample service: " + e);
            e.printStackTrace();
        } finally {
            if (injector != null) {
                //Close??
            }
        }
    }

}
