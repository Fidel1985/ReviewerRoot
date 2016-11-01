package com.softserveinc.reviewer.injector;

import static com.google.inject.matcher.Matchers.*;

import com.google.inject.Inject;
import com.google.inject.AbstractModule;
import com.softserveinc.reviewer.ReviewerConfiguration;
import com.softserveinc.reviewer.annotation.ElasticSearchBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchUri;
import com.softserveinc.reviewer.annotation.OracleBaseUrl;
import com.softserveinc.reviewer.annotation.OracleUri;
import com.softserveinc.reviewer.annotation.SyndicationBaseUrl;
import com.softserveinc.reviewer.annotation.SyndicationUri;
import com.softserveinc.reviewer.interceptor.LoggingInterceptor;
import com.softserveinc.reviewer.interceptor.TracingInterceptor;
import com.softserveinc.reviewer.service.ReviewerService;

public class GuiceModule extends AbstractModule {

    private final ReviewerConfiguration configuration;

    @Inject
    public GuiceModule(ReviewerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure() {
        bindInterceptor(subclassesOf(ReviewerService.class), any(), new LoggingInterceptor());
        bindInterceptor(subclassesOf(ReviewerService.class), any(), new TracingInterceptor());
        bindConstant().annotatedWith(ElasticSearchBaseUrl.class).to(configuration.getElasticSearchBaseUrl());
        bindConstant().annotatedWith(ElasticSearchUri.class).to(configuration.getElasticSearchUri());
        bindConstant().annotatedWith(OracleBaseUrl.class).to(configuration.getOracleBaseUrl());
        bindConstant().annotatedWith(OracleUri.class).to(configuration.getOracleUri());
        bindConstant().annotatedWith(SyndicationBaseUrl.class).to(configuration.getSyndicationBaseUrl());
        bindConstant().annotatedWith(SyndicationUri.class).to(configuration.getSyndicationUri());

    }
}
