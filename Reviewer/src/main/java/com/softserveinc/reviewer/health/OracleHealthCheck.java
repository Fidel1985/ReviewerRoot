package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.OracleHealthCheckUrl;
import org.glassfish.jersey.client.JerseyClient;

public class OracleHealthCheck  extends DataProvidersHealthCheck {
    @Inject
    public OracleHealthCheck(JerseyClient client, @OracleHealthCheckUrl String oracleHealthCheckUrl) {
        super(client, oracleHealthCheckUrl);
    }
}
