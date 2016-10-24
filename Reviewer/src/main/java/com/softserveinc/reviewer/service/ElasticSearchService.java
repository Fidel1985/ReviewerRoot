package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.softesrveinc.reviewer.model.Review;
import com.softesrveinc.reviewer.response.ElasticSearchResponse;
import com.softserveinc.reviewer.ReviewerConfiguration;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchService {
    private final ReviewerConfiguration configuration;
    private final JerseyClient client;

    @Inject
    public ElasticSearchService(ReviewerConfiguration configuration, JerseyClient client) {
        this.configuration = configuration;
        this.client = client;
    }

    public List<Review> getReviews(String destinationClientId, String productId) {
        WebTarget webTarget = client.target(configuration.getElasticSearchBaseUrl()).path(configuration.getElasticSearchUri()).
                queryParam("type", "review").queryParam("client", destinationClientId).queryParam("subjectProduct.externalId", productId);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        ElasticSearchResponse elasticSearchResponse = response.readEntity(ElasticSearchResponse.class);
        if(elasticSearchResponse != null && elasticSearchResponse.getHits() != null) {
            return elasticSearchResponse.getHits();
        }
        else {
            return new ArrayList<>();
        }
    }

}
