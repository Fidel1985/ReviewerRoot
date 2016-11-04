package com.softserveinc.reviewer.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import lombok.Getter;

@Getter
public class ReviewerConfiguration extends Configuration {
    @NotNull
    @Valid
    private MongoConfiguration mongo;
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

}
