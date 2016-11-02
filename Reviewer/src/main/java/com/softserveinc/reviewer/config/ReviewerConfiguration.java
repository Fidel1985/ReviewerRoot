package com.softserveinc.reviewer.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.softserveinc.reviewer.config.DatabaseConfiguration;
import io.dropwizard.Configuration;

public class ReviewerConfiguration extends Configuration {
    @NotNull
    @Valid
    private DatabaseConfiguration database;
    @NotNull
    private String syndicationHealthCheckUrl;
    @NotNull
    private String syndicationBaseUrl;
    @NotNull
    private String syndicationUri;
    @NotNull
    private String oracleHealthCheckUrl;
    @NotNull
    private String oracleBaseUrl;
    @NotNull
    private String oracleUri;
    @NotNull
    private String elasticSearchHealthCheckUrl;
    @NotNull
    private String elasticSearchBaseUrl;
    @NotNull
    private String elasticSearchUri;

    public DatabaseConfiguration getDatabase() {
        return database;
    }

    public String getSyndicationHealthCheckUrl() {
        return syndicationHealthCheckUrl;
    }

    public String getSyndicationBaseUrl() {
        return syndicationBaseUrl;
    }

    public String getSyndicationUri() {
        return syndicationUri;
    }

    public String getOracleHealthCheckUrl() {
        return oracleHealthCheckUrl;
    }

    public String getOracleBaseUrl() {
        return oracleBaseUrl;
    }

    public String getOracleUri() {
        return oracleUri;
    }

    public String getElasticSearchHealthCheckUrl() {
        return elasticSearchHealthCheckUrl;
    }

    public String getElasticSearchBaseUrl() {
        return elasticSearchBaseUrl;
    }

    public String getElasticSearchUri() {
        return elasticSearchUri;
    }

}
