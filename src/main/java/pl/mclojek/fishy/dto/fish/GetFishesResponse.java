package pl.mclojek.fishy.dto.fish;

import lombok.*;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.enums.FishSpecies;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFishesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Fish {
        private long id;
        private FishSpecies species;
        private String catchDate;
        private int length;
        private float weight;
        private Lake lake;
    }

    @Singular
    private List<Fish> fishes;

    public static Function<Collection<pl.mclojek.fishy.entity.Fish>, GetFishesResponse> entityToDtoMapper() {
        return fishes -> {
            GetFishesResponseBuilder response = GetFishesResponse.builder();
            fishes.stream()
                    .map(fish -> Fish.builder()
                            .id(fish.getId())
                            .species(fish.getSpecies())
                            .catchDate(fish.getCatchDate().format(DateTimeFormatter.ISO_DATE_TIME))
                            .length(fish.getLength())
                            .weight(fish.getWeight())
                            .lake(fish.getLake())
                            .build()
                    ).forEach(response::fish);
            return response.build();
        };
    }
}
