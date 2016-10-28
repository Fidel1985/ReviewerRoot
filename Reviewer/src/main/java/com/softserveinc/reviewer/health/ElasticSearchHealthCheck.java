package com.softserveinc.reviewer.health;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.ElasticSearchAdminBaseUrl;
import com.softserveinc.reviewer.model.ElasticSearchHealth;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchHealthCheck extends HealthCheck {
    private final JerseyClient client;
    private final String elasticSearchAdminBaseUrl;

    @Inject
    public ElasticSearchHealthCheck(JerseyClient client, @ElasticSearchAdminBaseUrl String elasticSearchAdminBaseUrl) {
        this.client = client;
        this.elasticSearchAdminBaseUrl = elasticSearchAdminBaseUrl;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target(elasticSearchAdminBaseUrl).path("healthcheck");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String result;
        try {
            Response response = invocationBuilder.get();
            ElasticSearchHealth elasticSearchHealth = response.readEntity(ElasticSearchHealth.class);
            result = elasticSearchHealth.getElasticSearchResponse().getMessage();
            if(!elasticSearchHealth.getElasticSearchResponse().isHealthy()) {
                return Result.unhealthy(result);
            }
        } catch (ProcessingException e) {
            result = "ElasticSearch Service unavailable at the moment.";
            return Result.unhealthy(result);
        }
        return Result.healthy(result);
    }
}
