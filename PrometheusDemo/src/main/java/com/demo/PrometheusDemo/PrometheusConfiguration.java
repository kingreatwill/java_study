package com.demo.PrometheusDemo;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.hotspot.DefaultExports;

import javax.annotation.PostConstruct;

//@Configuration
//@EnablePrometheusEndpoint
public class PrometheusConfiguration {
//    private MetricRegistry dropwizardMetricRegistry;
//
//    @Autowired
//    public PrometheusConfiguration(MetricRegistry dropwizardMetricRegistry) {
//        this.dropwizardMetricRegistry = dropwizardMetricRegistry;
//    }

    @PostConstruct
    public void registerPrometheusCollectors() {
        CollectorRegistry.defaultRegistry.clear();
//        new StandardExports().register();
//        new MemoryPoolsExports().register();
//        new DropwizardExports(dropwizardMetricRegistry).register();
        DefaultExports.initialize();
    }
}
