package com.softserveinc.reviewer.health;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.OracleAdminBaseUrl;
import com.softserveinc.reviewer.model.OracleHealth;
import org.glassfish.jersey.client.JerseyClient;

public class OracleHealthCheck extends HealthCheck {
    private final JerseyClient client;
    private final String oracleAdminBaseUrl;

    @Inject
    public OracleHealthCheck(JerseyClient client, @OracleAdminBaseUrl String oracleAdminBaseUrl) {
        this.client = client;
        this.oracleAdminBaseUrl = oracleAdminBaseUrl;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target(oracleAdminBaseUrl).path("healthcheck");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String result;
        try {
            Response response = invocationBuilder.get();
            OracleHealth oracleHealth = response.readEntity(OracleHealth.class);
            result = oracleHealth.getOracleHealthResponse().getMessage();
            if(!oracleHealth.getOracleHealthResponse().isHealthy()) {
                return Result.unhealthy(result);
            }
        } catch (ProcessingException e) {
            result = "Oracle Service unavailable at the moment.";
            return Result.unhealthy(result);
        }
        return Result.healthy(result);
    }
}