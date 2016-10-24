package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import org.glassfish.jersey.client.JerseyClient;

public class ReviewerHealthCheck extends HealthCheck {
    private final JerseyClient client;

    @Inject
    public ReviewerHealthCheck(JerseyClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}