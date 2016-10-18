package com.softserveinc.reviewer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.api.FriendRelation;
import com.softserveinc.reviewer.api.SwitchBoard;
import com.softserveinc.reviewer.service.SwitchBoardService;

@Path("/edges/to")
@Produces(MediaType.APPLICATION_JSON)
public class SwitchBoardResource {

    private static final SwitchBoard SWITCH_BOARD = new SwitchBoard();

    private final SwitchBoardService switchBoardService;

    @Inject
    public SwitchBoardResource(SwitchBoardService switchBoardService) {
        this.switchBoardService = switchBoardService;
    }

    @GET
    @Path("/{destinationClientID}")
    public Response getSwitchBoard(@PathParam("destinationClientID") String destinationClientId) {

        List<FriendRelation> friendRelations = switchBoardService.getSwitchBoardByDestinationClientId(destinationClientId);
        if(friendRelations.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SWITCH_BOARD.setData(friendRelations);
        return Response.ok(SWITCH_BOARD).build();
    }
}
