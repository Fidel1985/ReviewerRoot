package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.ElasticSearchHealthCheckUrl;
import com.softserveinc.reviewer.annotation.OracleHealthCheckUrl;
import com.softserveinc.reviewer.annotation.HealthCheckBaseUrl;

public class ReviewerHealthCheck extends HealthCheck {
    private final DataProvidersHealthCheck dataProvidersHealthCheck;
    private final String syndicationHealthCheckUrl;
    private final String oracleHealthCheckUrl;
    private final String elasticSearchHealthCheckUrl;

    @Inject
    public ReviewerHealthCheck(DataProvidersHealthCheck dataProvidersHealthCheck, @HealthCheckBaseUrl String syndicationHealthCheckUrl,
                               @OracleHealthCheckUrl String oracleHealthCheckUrl, @ElasticSearchHealthCheckUrl String elasticSearchHealthCheckUrl) {
        this.dataProvidersHealthCheck = dataProvidersHealthCheck;
        this.syndicationHealthCheckUrl = syndicationHealthCheckUrl;
        this.oracleHealthCheckUrl = oracleHealthCheckUrl;
        this.elasticSearchHealthCheckUrl = elasticSearchHealthCheckUrl;
    }

    @Override
    protected Result check() throws Exception {
        dataProvidersHealthCheck.setHealthCheckUrl(syndicationHealthCheckUrl);
        Result syndicationResult = dataProvidersHealthCheck.check();
        dataProvidersHealthCheck.setHealthCheckUrl(oracleHealthCheckUrl);
        Result oracleResult = dataProvidersHealthCheck.check();
        dataProvidersHealthCheck.setHealthCheckUrl(elasticSearchHealthCheckUrl);
        Result elasticSearchResult = dataProvidersHealthCheck.check();
        String message = "{syndication: " + syndicationResult.getMessage() + ", oracle: " + oracleResult.getMessage() +
                ", elastic search: " + elasticSearchResult.getMessage() + "}";
        if(syndicationResult.isHealthy() && oracleResult.isHealthy() && elasticSearchResult.isHealthy()) {
            return Result.healthy(message);
        }
        return Result.unhealthy(message);
    }
}