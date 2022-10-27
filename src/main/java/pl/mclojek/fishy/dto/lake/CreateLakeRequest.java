package pl.mclojek.fishy.dto.lake;

import lombok.*;
import pl.mclojek.fishy.entity.Lake;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateLakeRequest {

    private String name;

    private double latitude;

    private double longitude;

    private float area;

    private boolean isPublic;

    public static Function<CreateLakeRequest, pl.mclojek.fishy.entity.Lake> dtoToEntityMapper() {
        return request -> Lake.builder()
                .name(request.getName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .area(request.getArea())
                .isPublic(request.isPublic())
                .build();
    }

}
