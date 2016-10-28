package com.softserveinc.reviewer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ReviewerConfiguration extends Configuration {
    @NotEmpty
    private String syndicationAdminBaseUrl;
    @NotEmpty
    private String syndicationBaseUrl;
    @NotEmpty
    private String syndicationUri;
    @NotEmpty
    private String oracleAdminBaseUrl;
    @NotEmpty
    private String oracleBaseUrl;
    @NotEmpty
    private String oracleUri;
    @NotEmpty
    private String elasticSearchAdminBaseUrl;
    @NotEmpty
    private String elasticSearchBaseUrl;
    @NotEmpty
    private String elasticSearchUri;
    @NotEmpty
    private String reviewerBaseUrl;
    @NotEmpty
    private String reviewerUri;
    @NotEmpty
    private String dataProvidersAdminUrl;

    @JsonProperty
    public String getSyndicationAdminBaseUrl() {
        return syndicationAdminBaseUrl;
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
    public String getOracleAdminBaseUrl() {
        return oracleAdminBaseUrl;
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
    public String getElasticSearchAdminBaseUrl() {
        return elasticSearchAdminBaseUrl;
    }

    @JsonProperty
    public String getElasticSearchBaseUrl() {
        return elasticSearchBaseUrl;
    }

    @JsonProperty
    public String getElasticSearchUri() {
        return elasticSearchUri;
    }

    @JsonProperty
    public String getReviewerBaseUrl() {
        return reviewerBaseUrl;
    }

    @JsonProperty
    public String getReviewerUri() {
        return reviewerUri;
    }

    @JsonProperty
    public String getDataProvidersAdminUrl() {
        return dataProvidersAdminUrl;
    }
}
