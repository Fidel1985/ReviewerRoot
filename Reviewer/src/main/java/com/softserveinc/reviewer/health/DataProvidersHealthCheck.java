package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import org.glassfish.jersey.client.JerseyClient;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DataProvidersHealthCheck extends HealthCheck {
    private final JerseyClient client;
    private final String healthCheckUrl;

    @Inject
    public DataProvidersHealthCheck(JerseyClient client, String healthCheckUrl) {
        this.client = client;
        this.healthCheckUrl = healthCheckUrl;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target(healthCheckUrl).path("healthcheck");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        try {
            Response response = invocationBuilder.get();
            if(response.getStatus() == 500) {
                return Result.unhealthy("Service is unhealthy");
            }
        } catch (ProcessingException e) {
            return Result.unhealthy("Service unavailable at the moment");
        }
        return Result.healthy("Service is healthy");
    }

}
