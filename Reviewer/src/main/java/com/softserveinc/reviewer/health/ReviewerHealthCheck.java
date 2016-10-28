package com.softserveinc.reviewer.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;

public class ReviewerHealthCheck extends HealthCheck {
    private final SyndicationHealthCheck syndicationHealthCheck;
    private final OracleHealthCheck oracleHealthCheck;
    private final ElasticSearchHealthCheck elasticSearchHealthCheck;

    @Inject
    public ReviewerHealthCheck(SyndicationHealthCheck syndicationHealthCheck, OracleHealthCheck oracleHealthCheck,
            ElasticSearchHealthCheck elasticSearchHealthCheck) {
        this.syndicationHealthCheck = syndicationHealthCheck;
        this.oracleHealthCheck = oracleHealthCheck;
        this.elasticSearchHealthCheck = elasticSearchHealthCheck;
    }

    @Override
    protected Result check() throws Exception {
        Result syndicationResult = syndicationHealthCheck.check();
        Result oracleResult = oracleHealthCheck.check();
        Result elasticSearchResult = elasticSearchHealthCheck.check();
        String message = "{syndication: " + syndicationResult.getMessage() + ", oracle: " + oracleResult.getMessage() +
                ", elastic search: " + elasticSearchResult.getMessage() + "}";
        if(syndicationResult.isHealthy() && oracleResult.isHealthy() && elasticSearchResult.isHealthy()) {
            return Result.healthy(message);
        }
        return Result.unhealthy(message);
    }
}