package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.ElasticSearchHealthCheckUrl;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchHealthCheck extends DataProvidersHealthCheck {
    @Inject
    public ElasticSearchHealthCheck(JerseyClient client, @ElasticSearchHealthCheckUrl String elasticSearchHealthCheckUrl) {
        super(client, elasticSearchHealthCheckUrl);
    }
}
