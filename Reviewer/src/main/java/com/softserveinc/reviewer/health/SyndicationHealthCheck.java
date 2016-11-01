package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.SyndicationHealthCheckUrl;

public class SyndicationHealthCheck extends DataProvidersHealthCheck {
    @Inject
    public SyndicationHealthCheck(@SyndicationHealthCheckUrl String syndicationHealthCheckUrl) {
        super(syndicationHealthCheckUrl);
    }
}
