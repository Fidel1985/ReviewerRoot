package com.softserveinc.reviewer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.softserveinc.reviewer.health.ElasticSearchHealthCheck;
import com.softserveinc.reviewer.health.OracleHealthCheck;
import com.softserveinc.reviewer.health.SyndicationHealthCheck;
import com.softserveinc.reviewer.resources.ElasticSearchResource;
import com.softserveinc.reviewer.resources.OracleResource;
import com.softserveinc.reviewer.resources.SyndicationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProvidersApplication extends Application<ProvidersConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProvidersApplication().run(args);
    }

    @Override
    public String getName() {
        return "DataProviders";
    }

    @Override
    public void initialize(final Bootstrap<ProvidersConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ProvidersConfiguration configuration,
                    final Environment environment) {
        Injector injector = Guice.createInjector();
        environment.healthChecks().register("SyndicationHealthCheck", injector.getInstance(SyndicationHealthCheck.class));
        environment.healthChecks().register("OracleHealthCheck", injector.getInstance(OracleHealthCheck.class));
        environment.healthChecks().register("ElasticSearchHealthCheck", injector.getInstance(ElasticSearchHealthCheck.class));
        environment.jersey().register(injector.getInstance(SyndicationResource.class));
        environment.jersey().register(injector.getInstance(OracleResource.class));
        environment.jersey().register(injector.getInstance(ElasticSearchResource.class));
    }

}
