package com.softserveinc.reviewer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.model.Review;
import com.softserveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.model.ReviewResult;

public class ReviewerService {
    private final SwitchBoardService switchBoardService;
    private final OracleService oracleService;
    private final ElasticSearchService elasticSearchService;

    @Inject
    public ReviewerService(SwitchBoardService switchBoardService, OracleService oracleService, ElasticSearchService elasticSearchService) {
        this.switchBoardService = switchBoardService;
        this.oracleService = oracleService;
        this.elasticSearchService = elasticSearchService;
    }

    public ReviewResult getSyndicationMatches(String client, String externalId) {

        List<Syndication> syndications = switchBoardService.getSources(client);
        List<String> sourceClients = syndications.stream().map(Syndication::getSourceClient).collect(Collectors.toList());

        List<Product> products = oracleService.getSourceMatches(client, externalId);
        List<Product> syndicatedProducts = products.stream().filter(x -> sourceClients.contains(x.getClient())).collect(Collectors.toList());

        List<Review> reviews = elasticSearchService.getReviews(client, externalId);
        List<Review> syndicatedReviews = new ArrayList<>();
        syndicatedProducts.forEach(x -> syndicatedReviews.addAll(elasticSearchService.getReviews(x.getClient(), x.getExternalId())));

        return new ReviewResult(client, externalId, reviews.size(), syndicatedReviews.size());
    }
}
