package pl.mclojek.fishy.dto.lake;

import lombok.*;

import java.util.function.Function;

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

//    private GetFishResponse fishes;


    public static Function<pl.mclojek.fishy.entity.Lake, GetLakeResponse> entityToDtoMapper() {
        return lake -> {
            GetLakeResponse.GetLakeResponseBuilder response = GetLakeResponse.builder();
            return response.id(lake.getId())
                    .name(lake.getName())
                    .latitude(lake.getLatitude())
                    .longitude(lake.getLongitude())
                    .area(lake.getArea())
                    .isPublic(lake.isPublic())
                    .build();
        };

    }

}
