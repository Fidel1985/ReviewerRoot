package com.softserveinc.reviewer.health;

import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.OracleHealthCheckUrl;

public class OracleHealthCheck  extends DataProvidersHealthCheck {
    @Inject
    public OracleHealthCheck(@OracleHealthCheckUrl String oracleHealthCheckUrl) {
        super(oracleHealthCheckUrl);
    }
}
