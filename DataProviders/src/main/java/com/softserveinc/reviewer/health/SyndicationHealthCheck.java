package com.softserveinc.reviewer.health;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.response.SyndicationResponse;
import org.glassfish.jersey.client.JerseyClient;

public class SyndicationHealthCheck extends HealthCheck {
    private final JerseyClient client;

    @Inject
    public SyndicationHealthCheck(JerseyClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target("http://localhost:8084/edges/to/table-next");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        SyndicationResponse syndicationResponse = response.readEntity(SyndicationResponse.class);

        if (syndicationResponse != null && syndicationResponse.getData() != null) {
            return Result.healthy();
        }
        return Result.unhealthy("Syndication API Failed.");
    }
}