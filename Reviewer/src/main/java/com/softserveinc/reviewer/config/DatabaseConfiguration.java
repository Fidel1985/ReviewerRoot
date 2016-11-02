package com.softserveinc.reviewer.config;

import javax.validation.constraints.NotNull;

public class DatabaseConfiguration {

    @NotNull
    private String host;
    @NotNull
    private int port;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
