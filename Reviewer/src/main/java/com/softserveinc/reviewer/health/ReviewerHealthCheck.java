package com.softserveinc.reviewer.health;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.DataProvidersAdminUrl;
import com.softserveinc.reviewer.model.HealthCheckResult;
import org.glassfish.jersey.client.JerseyClient;

public class ReviewerHealthCheck extends HealthCheck {
    private final JerseyClient client;
    private final String dataProvidersAdminUrl;

    @Inject
    public ReviewerHealthCheck(JerseyClient client, @DataProvidersAdminUrl String dataProvidersAdminUrl) {
        this.client = client;
        this.dataProvidersAdminUrl = dataProvidersAdminUrl;
    }

    @Override
    protected Result check() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WebTarget webTarget = client.target(dataProvidersAdminUrl).path("healthcheck");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String result;
        try {
            Response response = invocationBuilder.get();
            HealthCheckResult healthCheckResult = response.readEntity(HealthCheckResult.class);
            result = objectMapper.writeValueAsString(healthCheckResult).replaceAll("\"", "");
            if (healthCheckResult.getElasticSearchHealthCheck().isHealthy() &&
                    healthCheckResult.getOracleHealthCheck().isHealthy() &&
                    healthCheckResult.getSyndicationHealthCheck().isHealthy()) {
                return Result.healthy(result);
            }
        } catch (ProcessingException e) {
            result = "ProcessingException occurred, DataProviders service unavailable at the moment";
        }
        return Result.unhealthy(result);
    }
}