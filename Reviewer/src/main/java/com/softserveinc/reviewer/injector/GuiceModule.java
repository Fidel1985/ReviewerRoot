package com.softserveinc.reviewer.injector;

import static com.google.inject.matcher.Matchers.*;

import com.google.inject.Inject;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.softserveinc.reviewer.annotation.Collection;
import com.softserveinc.reviewer.annotation.Database;
import com.softserveinc.reviewer.config.ReviewerConfiguration;
import com.softserveinc.reviewer.annotation.ElasticSearchBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchUri;
import com.softserveinc.reviewer.annotation.OracleBaseUrl;
import com.softserveinc.reviewer.annotation.OracleUri;
import com.softserveinc.reviewer.annotation.SyndicationBaseUrl;
import com.softserveinc.reviewer.annotation.SyndicationUri;
import com.softserveinc.reviewer.dao.StatisticDao;
import com.softserveinc.reviewer.dao.StatisticDaoMongoNativeImpl;
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
        TracingInterceptor interceptor = new TracingInterceptor();
        requestInjection(interceptor);
        bindInterceptor(subclassesOf(ReviewerService.class), any(), interceptor);
        bind(StatisticDao.class).to(StatisticDaoMongoNativeImpl.class);
        bindConstant().annotatedWith(ElasticSearchBaseUrl.class).to(configuration.getElasticSearchBaseUrl());
        bindConstant().annotatedWith(ElasticSearchUri.class).to(configuration.getElasticSearchUri());
        bindConstant().annotatedWith(OracleBaseUrl.class).to(configuration.getOracleBaseUrl());
        bindConstant().annotatedWith(OracleUri.class).to(configuration.getOracleUri());
        bindConstant().annotatedWith(SyndicationBaseUrl.class).to(configuration.getSyndicationBaseUrl());
        bindConstant().annotatedWith(SyndicationUri.class).to(configuration.getSyndicationUri());
        bindConstant().annotatedWith(Database.class).to(configuration.getDatabase().getName());
        bindConstant().annotatedWith(Collection.class).to(configuration.getDatabase().getCollection());
    }

    @Provides
    @Singleton
    MongoClient provideMongoClient() {
        return new MongoClient(configuration.getDatabase().getHost(), configuration.getDatabase().getPort());
    }

}
