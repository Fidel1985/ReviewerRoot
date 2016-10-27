package com.softserveinc.reviewer.resources;

import com.google.inject.Inject;

import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.response.OracleResponse;
import com.softserveinc.reviewer.service.OracleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class OracleResource {

    private final OracleService oracleService;

    @Inject
    public OracleResource(OracleService oracleService) {
        this.oracleService = oracleService;
    }

    @GET
    @Path("/{clientID}/{productID}/sources")
    public Response getSourceMatches(@PathParam("clientID") String clientId, @PathParam("productID") String productId) {

        List<Product> products = oracleService.getMatches(clientId, productId);
        if(products.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new OracleResponse(products)).build();
    }

}
