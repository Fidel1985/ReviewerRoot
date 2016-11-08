package com.softserveinc.reviewer;

import java.util.concurrent.TimeUnit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.softserveinc.reviewer.config.ReviewerConfiguration;
import com.softserveinc.reviewer.health.MongoDBHealthCheck;
import com.softserveinc.reviewer.health.SimpleURLHealthCheck;
import com.softserveinc.reviewer.health.TimeLimitedHealthCheck;
import com.softserveinc.reviewer.injector.GuiceModule;
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
        Injector injector = Guice.createInjector(new GuiceModule(configuration));
        environment.healthChecks().register("3rd-party services", new TimeLimitedHealthCheck(500, TimeUnit.MILLISECONDS,
                new MongoDBHealthCheck(configuration.getMongo()),
                new SimpleURLHealthCheck(configuration.getSwitchBoardHealthCheckUrl()),
                new SimpleURLHealthCheck(configuration.getOracleHealthCheckUrl()),
                new SimpleURLHealthCheck(configuration.getElasticSearchHealthCheckUrl())));
        environment.jersey().register(injector.getInstance(ReviewerResource.class));
    }

}
