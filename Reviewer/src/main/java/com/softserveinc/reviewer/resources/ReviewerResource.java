package com.softserveinc.reviewer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.softserveinc.reviewer.model.ReviewResult;
import com.softserveinc.reviewer.service.ReviewerService;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewerResource {
    private final ReviewerService reviewerService;

    @Inject
    public ReviewerResource(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    @GET
    @Path("/{clientID}/{productID}")
    public Response getEffectiveSourceMatches(@PathParam("clientID") String clientId, @PathParam("productID") String productId) {

        ReviewResult result = reviewerService.getSyndicationMatches(clientId, productId);
        if(result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(result).build();
    }
}
