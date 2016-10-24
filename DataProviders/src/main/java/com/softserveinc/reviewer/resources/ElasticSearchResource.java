package com.softserveinc.reviewer.resources;

import com.google.inject.Inject;
import com.softesrveinc.reviewer.response.ElasticSearchResponse;
import com.softserveinc.reviewer.service.ElasticSearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/explore")
@Produces(MediaType.APPLICATION_JSON)
public class ElasticSearchResource {

    private final ElasticSearchService elasticSearchService;

    @Inject
    public ElasticSearchResource(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @GET
    public Response getReviews(@QueryParam("type") String type, @QueryParam("client") String destinationClientId,
                               @QueryParam("subjectProduct.externalId") String productId) {

        ElasticSearchResponse response = elasticSearchService.getReviews(type, destinationClientId, productId);
        if(response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }
}
