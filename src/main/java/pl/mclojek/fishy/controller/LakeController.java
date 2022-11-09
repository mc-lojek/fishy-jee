package pl.mclojek.fishy.controller;

import pl.mclojek.fishy.dto.fish.GetFishResponse;
import pl.mclojek.fishy.dto.lake.CreateLakeRequest;
import pl.mclojek.fishy.dto.lake.GetLakeResponse;
import pl.mclojek.fishy.dto.lake.GetLakesResponse;
import pl.mclojek.fishy.dto.lake.UpdateLakeRequest;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.service.LakeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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

        if (entity.isPresent()) {
            return Response.ok(GetLakeResponse.entityToDtoMapper(GetFishResponse.entityToDtoMapper()).apply(entity.get()))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postLake(CreateLakeRequest request) {

        Lake lake = CreateLakeRequest.dtoToEntityMapper().apply(request);
        service.create(lake);

        return Response.created(UriBuilder.fromMethod(LakeController.class, "getLake").build(lake.getId())).build();

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putLake(@PathParam("id") Long id, UpdateLakeRequest request) {
        Optional<Lake> lake = service.find(id);

        if(lake.isPresent()) {
            UpdateLakeRequest.dtoToEntityUpdater().apply(lake.get(), request);
            service.update(lake.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteLake(@PathParam("id") Long id) {
        Optional<Lake> lake = service.find(id);

        if(lake.isPresent()) {
            service.delete(lake.get().getId());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
