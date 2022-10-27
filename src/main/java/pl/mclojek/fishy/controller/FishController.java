package pl.mclojek.fishy.controller;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.dto.fish.GetFishResponse;
import pl.mclojek.fishy.dto.fish.GetFishesResponse;
import pl.mclojek.fishy.dto.lake.CreateLakeRequest;
import pl.mclojek.fishy.dto.lake.GetLakeResponse;
import pl.mclojek.fishy.dto.lake.GetLakesResponse;
import pl.mclojek.fishy.dto.lake.UpdateLakeRequest;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.service.FishService;
import pl.mclojek.fishy.service.LakeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;

@Path("/lakes/{lake_id}/fishes")
@NoArgsConstructor
public class FishController {

    private FishService service;
    @PathParam("lake_id")
    private Long lakeId;

    @Inject
    public void setService(FishService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFishes() {
        return Response.ok(GetFishesResponse.entityToDtoMapper().apply(service.findAllForLake(lakeId)))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFish(@PathParam("id") Long id) {

        Optional<Fish> entity = service.findForLake(id, lakeId);

        if (entity.isPresent()) {
            return Response.ok(GetFishResponse.entityToDtoMapper().apply(entity.get()))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postLake(CreateLakeRequest request) {
//
//        Lake lake = CreateLakeRequest.dtoToEntityMapper().apply(request);
//        service.create(lake);
//
//        return Response.created(UriBuilder.fromMethod(FishController.class, "getLake").build(lake.getId())).build();
//
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response putLake(@PathParam("id") Long id, UpdateLakeRequest request) {
//        Optional<Lake> lake = service.find(id);
//
//        if(lake.isPresent()) {
//            UpdateLakeRequest.dtoToEntityUpdater().apply(lake.get(), request);
//            service.update(lake.get());
//            return Response.noContent().build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response deleteLake(@PathParam("id") Long id) {
//        Optional<Lake> lake = service.find(id);
//
//        if(lake.isPresent()) {
//            service.delete(lake.get().getId());
//            return Response.noContent().build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
