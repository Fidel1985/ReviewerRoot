package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class DataProvidersHealthCheck extends HealthCheck {
    private final String healthCheckUrl;

    @Inject
    public DataProvidersHealthCheck(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    @Override
    protected Result check() throws Exception {
        URL url = new URL(healthCheckUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(250);
        try {
            if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299)
            {
                return Result.healthy("Service is healthy");
            }
        } catch (SocketTimeoutException | ConnectException e) {
            return Result.unhealthy("Service unavailable at the moment");
        } finally {
            connection.disconnect();
        }
        return Result.unhealthy("Service is unhealthy");
    }

}
