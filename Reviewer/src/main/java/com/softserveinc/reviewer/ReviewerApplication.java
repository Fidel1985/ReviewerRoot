package com.softserveinc.reviewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.softserveinc.reviewer.health.ReviewerHealthCheck;
import com.softserveinc.reviewer.resources.ReviewerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
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
    public void initialize(final Bootstrap<ReviewerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ReviewerConfiguration configuration, final Environment environment) {
        Injector injector = createInjector(configuration);
        environment.healthChecks().register("ReviewerHealthCheck", injector.getInstance(ReviewerHealthCheck.class));
        environment.jersey().register(injector.getInstance(ReviewerResource.class));
    }

    private Injector createInjector(final ReviewerConfiguration configuration) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ReviewerConfiguration.class).toInstance(configuration);
                //bind(ObjectMapper.class).to(ObjectMapper.class);
            }
        });
    }
}
