package com.softserveinc.reviewer;

import com.softserveinc.reviewer.health.TemplateHealthCheck;
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
    public void run(final ReviewerConfiguration configuration,
                    final Environment environment) {
        final ReviewerResource resource = new ReviewerResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
