package com.softserveinc.reviewer.resources;

import com.softserveinc.reviewer.response.ElasticSearchResponse;
import com.softserveinc.reviewer.model.Review;
import com.softserveinc.reviewer.service.ElasticSearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/explore")
@Produces(MediaType.APPLICATION_JSON)
public class ElasticSearchResource {
    private static final ElasticSearchResponse ELASTIC_SEARCH_RESPONSE = new ElasticSearchResponse();

    private final ElasticSearchService elasticSearchService;

    public ElasticSearchResource(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @GET
    public Response getOracle(@QueryParam("type") String type, @QueryParam("client") String destinationClientId,
                              @QueryParam("subjectProduct.externalId") String productId) {

        List<Review> reviews = elasticSearchService.getReviews(type, destinationClientId, productId);
        if(reviews.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ELASTIC_SEARCH_RESPONSE.setReviews(reviews);
        return Response.ok(ELASTIC_SEARCH_RESPONSE).build();
    }
}
