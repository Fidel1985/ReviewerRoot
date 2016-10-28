package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;

public class ElasticSearchHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("ElasticSearch Service is healthy!");
    }
}
