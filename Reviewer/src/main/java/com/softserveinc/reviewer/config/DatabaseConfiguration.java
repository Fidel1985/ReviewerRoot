package com.softserveinc.reviewer.config;

import javax.validation.constraints.NotNull;

public class DatabaseConfiguration {

    @NotNull
    private String driverClass;
    private String user;
    private String password;
    @NotNull
    private String url;
    private String poolSize;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPoolSize() {
        return poolSize;
    }
}
