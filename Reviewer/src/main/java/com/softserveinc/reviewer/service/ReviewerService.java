package com.softserveinc.reviewer.service;

import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.model.Review;
import com.softserveinc.reviewer.model.ReviewResult;
import com.softserveinc.reviewer.model.Syndication;

public class ReviewerService {

    private final SyndicationService syndicationService;
    private final OracleService oracleService;
    private final ElasticSearchService elasticSearchService;

    @Inject
    public ReviewerService(SyndicationService syndicationService, OracleService oracleService,
            ElasticSearchService elasticSearchService) {
        this.syndicationService = syndicationService;
        this.oracleService = oracleService;
        this.elasticSearchService = elasticSearchService;
    }

    public ReviewResult getSyndicationMatches(String client, String externalId) {

        List<Syndication> syndications = syndicationService.getSources(client);
        List<Product> products = oracleService.getSourceMatches(client, externalId);
        List<Review> reviews = elasticSearchService.getReviews(client, externalId);


        ReviewResult result = new ReviewResult(client, externalId, 1L, 2L);
        return result;
    }
}
