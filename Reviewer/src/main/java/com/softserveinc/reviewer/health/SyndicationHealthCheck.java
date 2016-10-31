package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.SyndicationHealthCheckUrl;
import org.glassfish.jersey.client.JerseyClient;

public class SyndicationHealthCheck extends DataProvidersHealthCheck {
    @Inject
    public SyndicationHealthCheck(JerseyClient client, @SyndicationHealthCheckUrl String syndicationHealthCheckUrl) {
        super(client, syndicationHealthCheckUrl);
    }
}
