package com.softserveinc.reviewer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.api.SwitchBoard;
import com.softserveinc.reviewer.service.SwitchBoardService;

@Path("/edges/to")
@Produces(MediaType.APPLICATION_JSON)
public class SwitchBoardResource {

    private final SwitchBoardService switchBoardService;

    @Inject
    public SwitchBoardResource(SwitchBoardService switchBoardService) {
        this.switchBoardService = switchBoardService;
    }

    @GET
    public List<SwitchBoard> getSwitchBoard(@QueryParam("id") long id) {
        return switchBoardService.getSwitchBoardById(id);
    }
}
