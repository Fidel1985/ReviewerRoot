package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;

public class OracleHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Oracle Service is healthy!");
    }
}