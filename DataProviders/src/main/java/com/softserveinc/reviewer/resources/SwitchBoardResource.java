package com.softserveinc.reviewer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.response.SwitchBoardResponse;
import com.softserveinc.reviewer.service.SyndicationService;

@Path("/edges")
@Produces(MediaType.APPLICATION_JSON)
public class SwitchBoardResource {

    private static final SwitchBoardResponse SWITCH_BOARD_RESPONSE = new SwitchBoardResponse();

    private final SyndicationService syndicationService;

    @Inject
    public SwitchBoardResource(SyndicationService syndicationService) {
        this.syndicationService = syndicationService;
    }

    @GET
    @Path("/to/{destinationClientID}")
    public Response getSwitchBoard(@PathParam("destinationClientID") String destinationClientId) {
        List<Syndication> syndications = syndicationService.getSources(destinationClientId);
        if(syndications.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SWITCH_BOARD_RESPONSE.setData(syndications);
        return Response.ok(SWITCH_BOARD_RESPONSE).build();
    }
}
