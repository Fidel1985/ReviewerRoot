package com.softserveinc.reviewer.injector;

import com.google.inject.Inject;
import com.google.inject.AbstractModule;
import com.softserveinc.reviewer.ReviewerConfiguration;
import com.softserveinc.reviewer.annotation.ElasticSearchAdminBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchUri;
import com.softserveinc.reviewer.annotation.OracleAdminBaseUrl;
import com.softserveinc.reviewer.annotation.OracleBaseUrl;
import com.softserveinc.reviewer.annotation.OracleUri;
import com.softserveinc.reviewer.annotation.SyndicationAdminBaseUrl;
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
        bindConstant().annotatedWith(ElasticSearchAdminBaseUrl.class).to(configuration.getElasticSearchAdminBaseUrl());
        bindConstant().annotatedWith(ElasticSearchBaseUrl.class).to(configuration.getElasticSearchBaseUrl());
        bindConstant().annotatedWith(ElasticSearchUri.class).to(configuration.getElasticSearchUri());
        bindConstant().annotatedWith(OracleAdminBaseUrl.class).to(configuration.getOracleAdminBaseUrl());
        bindConstant().annotatedWith(OracleBaseUrl.class).to(configuration.getOracleBaseUrl());
        bindConstant().annotatedWith(OracleUri.class).to(configuration.getOracleUri());
        bindConstant().annotatedWith(SyndicationAdminBaseUrl.class).to(configuration.getSyndicationAdminBaseUrl());
        bindConstant().annotatedWith(SyndicationBaseUrl.class).to(configuration.getSyndicationBaseUrl());
        bindConstant().annotatedWith(SyndicationUri.class).to(configuration.getSyndicationUri());

    }
}
