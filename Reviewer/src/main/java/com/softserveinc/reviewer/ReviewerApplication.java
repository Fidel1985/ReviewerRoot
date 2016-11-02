package com.softserveinc.reviewer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.softserveinc.reviewer.config.ReviewerConfiguration;
import com.softserveinc.reviewer.health.DataProvidersHealthCheck;
import com.softserveinc.reviewer.injector.GuiceModule;
import com.softserveinc.reviewer.injector.MongoClientModule;
import com.softserveinc.reviewer.resources.ReviewerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ReviewerApplication extends Application<ReviewerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ReviewerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Reviewer";
    }

    @Override
    public void run(final ReviewerConfiguration configuration, final Environment environment) {
        Injector injector = Guice.createInjector(new GuiceModule(configuration), new MongoClientModule(configuration.getDatabase()));
        environment.healthChecks().register("switchboard", new DataProvidersHealthCheck(configuration.getSyndicationHealthCheckUrl()));
        environment.healthChecks().register("oracle", new DataProvidersHealthCheck(configuration.getOracleHealthCheckUrl()));
        environment.healthChecks().register("elasticsearch", new DataProvidersHealthCheck(configuration.getElasticSearchHealthCheckUrl()));
        environment.jersey().register(injector.getInstance(ReviewerResource.class));
    }

}
