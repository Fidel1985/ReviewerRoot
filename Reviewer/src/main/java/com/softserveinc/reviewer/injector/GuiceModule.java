package com.softserveinc.reviewer.injector;

import com.google.inject.Inject;
import com.google.inject.AbstractModule;
import com.softserveinc.reviewer.ReviewerConfiguration;
import com.softserveinc.reviewer.annotation.ElasticSearchHealthCheckUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchUri;
import com.softserveinc.reviewer.annotation.OracleHealthCheckUrl;
import com.softserveinc.reviewer.annotation.OracleBaseUrl;
import com.softserveinc.reviewer.annotation.OracleUri;
import com.softserveinc.reviewer.annotation.HealthCheckBaseUrl;
import com.softserveinc.reviewer.annotation.SyndicationBaseUrl;
import com.softserveinc.reviewer.annotation.SyndicationUri;

public class GuiceModule extends AbstractModule {

    private final ReviewerConfiguration configuration;

    @Inject
    public GuiceModule(ReviewerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure() {
        bindConstant().annotatedWith(ElasticSearchHealthCheckUrl.class).to(configuration.getElasticSearchHealthCheckUrl());
        bindConstant().annotatedWith(ElasticSearchBaseUrl.class).to(configuration.getElasticSearchBaseUrl());
        bindConstant().annotatedWith(ElasticSearchUri.class).to(configuration.getElasticSearchUri());
        bindConstant().annotatedWith(OracleHealthCheckUrl.class).to(configuration.getOracleHealthCheckUrl());
        bindConstant().annotatedWith(OracleBaseUrl.class).to(configuration.getOracleBaseUrl());
        bindConstant().annotatedWith(OracleUri.class).to(configuration.getOracleUri());
        bindConstant().annotatedWith(HealthCheckBaseUrl.class).to(configuration.getSyndicationHealthCheckUrl());
        bindConstant().annotatedWith(SyndicationBaseUrl.class).to(configuration.getSyndicationBaseUrl());
        bindConstant().annotatedWith(SyndicationUri.class).to(configuration.getSyndicationUri());

    }
}
