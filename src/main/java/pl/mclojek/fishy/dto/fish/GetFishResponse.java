package pl.mclojek.fishy.dto.fish;

import lombok.*;
import pl.mclojek.fishy.enums.FishSpecies;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFishResponse {

    private long id;
    private FishSpecies species;
    private String catchDate;
    private int length;
    private float weight;


    public static Function<pl.mclojek.fishy.entity.Fish, GetFishResponse> entityToDtoMapper() {
        return fish -> {
            GetFishResponse.GetFishResponseBuilder response = GetFishResponse.builder();
            return response.id(fish.getId())
                    .id(fish.getId())
                    .species(fish.getSpecies())
                    .catchDate(fish.getCatchDate().format(DateTimeFormatter.ISO_DATE_TIME))
                    .length(fish.getLength())
                    .weight(fish.getWeight())
                    .build();
        };
    }
}
