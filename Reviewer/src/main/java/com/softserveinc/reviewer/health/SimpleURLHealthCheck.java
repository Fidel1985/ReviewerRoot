package com.softserveinc.reviewer.health;

import com.google.inject.Inject;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.Callable;

public class SimpleURLHealthCheck implements Callable<Boolean> {
    private final String healthCheckUrl;

    @Inject
    public SimpleURLHealthCheck(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    @Override
    public Boolean call() throws Exception {
        URL url = new URL(healthCheckUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(250);
        try {
            if (connection.getResponseCode() == 200) {
                return true;
            }
        } catch (SocketTimeoutException | ConnectException e) {
            return false;
        } finally {
            connection.disconnect();
        }
        return false;
    }

}
