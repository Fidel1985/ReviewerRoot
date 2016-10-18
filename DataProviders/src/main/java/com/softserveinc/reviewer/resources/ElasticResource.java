package com.softserveinc.reviewer.resources;

import com.softserveinc.reviewer.api.Elastic;
import com.softserveinc.reviewer.api.Review;
import com.softserveinc.reviewer.service.ElasticService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/explore")
@Produces(MediaType.APPLICATION_JSON)
public class ElasticResource {
    private static final Elastic ELASTIC = new Elastic();

    private final ElasticService elasticService;

    public ElasticResource(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @GET
    public Response getOracle(@QueryParam("type") String type, @QueryParam("client") String destinationClientId,
                              @QueryParam("subjectProduct.externalId") String productId) {

        List<Review> reviews = elasticService.getReviews(type, destinationClientId, productId);
        if(reviews.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ELASTIC.setReviews(reviews);
        return Response.ok(ELASTIC).build();
    }
}
