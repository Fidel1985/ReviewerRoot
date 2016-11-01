package com.softserveinc.reviewer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ReviewerConfiguration extends Configuration {
    @NotEmpty
    private String syndicationHealthCheckUrl;
    @NotEmpty
    private String syndicationBaseUrl;
    @NotEmpty
    private String syndicationUri;
    @NotEmpty
    private String oracleHealthCheckUrl;
    @NotEmpty
    private String oracleBaseUrl;
    @NotEmpty
    private String oracleUri;
    @NotEmpty
    private String elasticSearchHealthCheckUrl;
    @NotEmpty
    private String elasticSearchBaseUrl;
    @NotEmpty
    private String elasticSearchUri;

    @JsonProperty
    public String getSyndicationHealthCheckUrl() {
        return syndicationHealthCheckUrl;
    }

    @JsonProperty
    public String getSyndicationBaseUrl() {
        return syndicationBaseUrl;
    }

    @JsonProperty
    public String getSyndicationUri() {
        return syndicationUri;
    }

    @JsonProperty
    public String getOracleHealthCheckUrl() {
        return oracleHealthCheckUrl;
    }

    @JsonProperty
    public String getOracleBaseUrl() {
        return oracleBaseUrl;
    }

    @JsonProperty
    public String getOracleUri() {
        return oracleUri;
    }

    @JsonProperty
    public String getElasticSearchHealthCheckUrl() {
        return elasticSearchHealthCheckUrl;
    }

    @JsonProperty
    public String getElasticSearchBaseUrl() {
        return elasticSearchBaseUrl;
    }

    @JsonProperty
    public String getElasticSearchUri() {
        return elasticSearchUri;
    }

}
