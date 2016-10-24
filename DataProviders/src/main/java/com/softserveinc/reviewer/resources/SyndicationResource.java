package com.softserveinc.reviewer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.softesrveinc.reviewer.response.SyndicationResponse;
import com.softserveinc.reviewer.service.SyndicationService;

@Path("/edges")
@Produces(MediaType.APPLICATION_JSON)
public class SyndicationResource {

    private final SyndicationService syndicationService;

    @Inject
    public SyndicationResource(SyndicationService syndicationService) {
        this.syndicationService = syndicationService;
    }

    @GET
    @Path("/to/{destinationClient}")
    public Response getSources(@PathParam("destinationClient") String destinationClient) {
        SyndicationResponse response = syndicationService.getSources(destinationClient);
        if(response.getData().isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }
}
