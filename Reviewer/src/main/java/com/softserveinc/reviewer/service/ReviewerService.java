package com.softserveinc.reviewer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.softesrveinc.reviewer.model.Product;
import com.softesrveinc.reviewer.model.Review;
import com.softesrveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.model.ReviewResult;

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
        List<String> sourceClients = syndications.stream().map(Syndication::getSourceClient).collect(Collectors.toList());

        List<Product> products = oracleService.getSourceMatches(client, externalId);
        List<Product> syndicatedProducts = products.stream().filter(x -> sourceClients.contains(x.getClient())).collect(Collectors.toList());

        List<Review> reviews = new ArrayList<>();
        List<Review> syndicatedReviews = new ArrayList<>();
        products.forEach(x -> reviews.addAll(elasticSearchService.getReviews(x.getClient(), x.getExternalId())));
        syndicatedProducts.forEach(x -> syndicatedReviews.addAll(elasticSearchService.getReviews(x.getClient(), x.getExternalId())));

        return new ReviewResult(client, externalId, reviews.size(), syndicatedReviews.size());
    }
}
