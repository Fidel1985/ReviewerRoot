package com.softserveinc.reviewer.health;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.response.OracleResponse;
import org.glassfish.jersey.client.JerseyClient;

public class OracleHealthCheck extends HealthCheck {
    private final JerseyClient client;

    @Inject
    public OracleHealthCheck(JerseyClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target("http://localhost:8084/product/besttable/roundPlastic/sources");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        OracleResponse oracleResponse = response.readEntity(OracleResponse.class);

        if (oracleResponse != null && oracleResponse.getProducts() != null) {
            return Result.healthy();
        }
        return Result.unhealthy("Oracle API Failed.");
    }
}