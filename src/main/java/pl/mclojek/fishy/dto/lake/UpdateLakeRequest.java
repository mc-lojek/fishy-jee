package pl.mclojek.fishy.dto.lake;

import lombok.*;
import pl.mclojek.fishy.entity.Lake;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateLakeRequest {

    private String name;

    private double latitude;

    private double longitude;

    private float area;

    private boolean isPublic;

    public static BiFunction<Lake, UpdateLakeRequest, Lake> dtoToEntityUpdater() {
        return (lake, request) -> {
            lake.setName(request.getName());
            lake.setArea(request.getArea());
            lake.setLatitude(request.getLatitude());
            lake.setLongitude(request.getLongitude());
            lake.setPublic(request.isPublic());
            return lake;
        };
    }
}
