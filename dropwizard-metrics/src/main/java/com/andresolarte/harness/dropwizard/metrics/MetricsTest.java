package com.andresolarte.harness.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class MetricsTest {
    public static void main(String... args) throws InterruptedException{
        MetricRegistry metrics = new MetricRegistry();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        Meter requests = metrics.meter("requests");
        requests.mark();

        Histogram responseSizes = metrics.histogram("request-sizes");
        responseSizes.update(600);

        Thread.sleep(5*1000);

        responseSizes.update(200);

        Thread.sleep(5*1000);
    }
}
