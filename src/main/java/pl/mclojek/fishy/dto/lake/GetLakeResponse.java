package pl.mclojek.fishy.dto.lake;

import lombok.*;
import pl.mclojek.fishy.dto.fish.GetFishResponse;
import pl.mclojek.fishy.entity.Fish;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetLakeResponse {

    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private float area;

    private boolean isPublic;

    private List<GetFishResponse> fishes;


    public static Function<pl.mclojek.fishy.entity.Lake, GetLakeResponse> entityToDtoMapper(
            Function<Fish, GetFishResponse> fishMapper
    ) {
        return lake -> {
            GetLakeResponse.GetLakeResponseBuilder response = GetLakeResponse.builder();
            return response.id(lake.getId())
                    .name(lake.getName())
                    .latitude(lake.getLatitude())
                    .longitude(lake.getLongitude())
                    .area(lake.getArea())
                    .isPublic(lake.isPublic())
                    .fishes(lake.getFishList().stream().map(fishMapper).collect(Collectors.toList()))
                    .build();
        };

    }

}
