package pl.mclojek.fishy.controller;

import pl.mclojek.fishy.dto.GetLakeResponse;
import pl.mclojek.fishy.dto.GetLakesResponse;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.service.LakeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/lakes")
public class LakeController {

    private LakeService service;

    public LakeController() {

    }

    @Inject
    public void setService(LakeService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLakes() {
        return Response.ok(GetLakesResponse.entityToDtoMapper().apply(service.findAll()))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLake(@PathParam("id") Long id) {

        Optional<Lake> entity = service.find(id);

        if(entity.isPresent()) {
            return Response.ok(GetLakeResponse.entityToDtoMapper().apply(entity.get()))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
