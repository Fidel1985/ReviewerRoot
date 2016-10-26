package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.softesrveinc.reviwer.model.Review;
import com.softesrveinc.reviwer.response.ElasticSearchResponse;
import com.softserveinc.reviewer.annotation.ElasticSearchBaseUrl;
import com.softserveinc.reviewer.annotation.ElasticSearchUri;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchService {
    private final JerseyClient client;
    private final String elasticSearchBaseUrl;
    private final String elasticSearchUri;

    @Inject
    public ElasticSearchService(JerseyClient client, @ElasticSearchBaseUrl String elasticSearchBaseUrl,
            @ElasticSearchUri String elasticSearchUri) {
        this.client = client;
        this.elasticSearchBaseUrl = elasticSearchBaseUrl;
        this.elasticSearchUri = elasticSearchUri;
    }

    public List<Review> getReviews(String destinationClientId, String productId) {
        WebTarget webTarget = client.target(elasticSearchBaseUrl).path(elasticSearchUri).
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
