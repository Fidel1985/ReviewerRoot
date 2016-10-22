package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.softesrveinc.reviwer.model.Review;
import com.softserveinc.reviewer.ReviewerConfiguration;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchService {
    private final ReviewerConfiguration configuration;
    private final JerseyClient client;
    private final ObjectMapper objectMapper;

    @Inject
    public ElasticSearchService(ReviewerConfiguration configuration, JerseyClient client, ObjectMapper objectMapper) {
        this.configuration = configuration;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public List<Review> getReviews(String destinationClientId, String productId) {
        WebTarget webTarget = client.target(configuration.getElasticSearchBaseUrl()).path(configuration.getElasticSearchUri()).
                queryParam("type", "review").queryParam("client", destinationClientId).queryParam("subjectProduct.externalId", productId);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        List<Review> reviews = objectMapper.convertValue(response.readEntity(ArrayList.class), new TypeReference<List<Review>>() {});

        return reviews != null ? reviews : new ArrayList<>();
    }

}
