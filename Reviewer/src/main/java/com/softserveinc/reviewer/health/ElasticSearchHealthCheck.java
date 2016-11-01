package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.ElasticSearchHealthCheckUrl;

public class ElasticSearchHealthCheck extends DataProvidersHealthCheck {
    @Inject
    public ElasticSearchHealthCheck(@ElasticSearchHealthCheckUrl String elasticSearchHealthCheckUrl) {
        super(elasticSearchHealthCheckUrl);
    }
}
