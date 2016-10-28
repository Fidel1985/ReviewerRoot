package com.softserveinc.reviewer.health;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.SyndicationAdminBaseUrl;
import com.softserveinc.reviewer.model.SyndicationHealth;
import org.glassfish.jersey.client.JerseyClient;

public class SyndicationHealthCheck extends HealthCheck {
    private final JerseyClient client;
    private final String syndicationAdminBaseUrl;

    @Inject
    public SyndicationHealthCheck(JerseyClient client, @SyndicationAdminBaseUrl String syndicationAdminBaseUrl) {
        this.client = client;
        this.syndicationAdminBaseUrl = syndicationAdminBaseUrl;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target(syndicationAdminBaseUrl).path("healthcheck");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String result;
        try {
            Response response = invocationBuilder.get();
            SyndicationHealth elasticSearchHealth = response.readEntity(SyndicationHealth.class);
            result = elasticSearchHealth.getSyndicationHealthResponse().getMessage();
            if(!elasticSearchHealth.getSyndicationHealthResponse().isHealthy()) {
                return Result.unhealthy(result);
            }
        } catch (ProcessingException e) {
            result = "Syndication Service unavailable at the moment";
            return Result.unhealthy(result);
        }
        return Result.healthy(result);
    }
}