package pl.mclojek.fishy.controller;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.dto.fish.CreateFishRequest;
import pl.mclojek.fishy.dto.fish.GetFishResponse;
import pl.mclojek.fishy.dto.fish.GetFishesResponse;
import pl.mclojek.fishy.dto.fish.UpdateFishRequest;
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
    private LakeService lakeService;
    @PathParam("lake_id")
    private Long lakeId;

    @Inject
    public void setService(FishService service) {
        this.service = service;
    }

    @Inject
    public void setLakeService(LakeService lakeService) {
        this.lakeService = lakeService;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postFish(CreateFishRequest request) {

        Optional<Lake> lake = lakeService.find(lakeId);

        if(lake.isPresent()) {
            Fish fish = CreateFishRequest.dtoToEntityMapper(() -> lakeService.find(lakeId).orElseThrow()).apply(request);
            service.create(fish);
            return Response.created(UriBuilder.fromMethod(FishController.class, "getFish").build(fish.getId())).build();
        } else {
            return Response.status(400).build();
        }



    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putFish(@PathParam("id") Long id, UpdateFishRequest request) {
        Optional<Fish> fish = service.find(id);

        if(fish.isPresent()) {
            UpdateFishRequest.dtoToEntityUpdater().apply(fish.get(), request);
            service.update(fish.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteLake(@PathParam("id") Long id) {
        Optional<Fish> fish = service.find(id);

        if(fish.isPresent()) {
            service.delete(fish.get().getId());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
