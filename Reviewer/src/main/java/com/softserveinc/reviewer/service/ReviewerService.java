package com.softserveinc.reviewer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.softserveinc.reviewer.jpa.Person;
import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.model.Review;
import com.softserveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.model.ReviewResult;

public class ReviewerService {

    private final SyndicationService syndicationService;
    private final OracleService oracleService;
    private final ElasticSearchService elasticSearchService;
    private final JpaService jpaService;

    @Inject
    public ReviewerService(SyndicationService syndicationService, OracleService oracleService,
            ElasticSearchService elasticSearchService, JpaService jpaService) {
        this.syndicationService = syndicationService;
        this.oracleService = oracleService;
        this.elasticSearchService = elasticSearchService;
        this.jpaService = jpaService;
    }

    public ReviewResult getSyndicationMatches(String client, String externalId) {

        List<Syndication> syndications = syndicationService.getSources(client);
        List<String> sourceClients = syndications.stream().map(Syndication::getSourceClient).collect(Collectors.toList());

        List<Product> products = oracleService.getSourceMatches(client, externalId);
        List<Product> syndicatedProducts = products.stream().filter(x -> sourceClients.contains(x.getClient())).collect(Collectors.toList());

        List<Review> reviews = elasticSearchService.getReviews(client, externalId);
        List<Review> syndicatedReviews = new ArrayList<>();
        syndicatedProducts.forEach(x -> syndicatedReviews.addAll(elasticSearchService.getReviews(x.getClient(), x.getExternalId())));
        //MyInitializer initializer = new MyInitializer();
        Person person = jpaService.createNewPerson();

        return new ReviewResult(client, externalId, reviews.size(), syndicatedReviews.size());
    }
}
