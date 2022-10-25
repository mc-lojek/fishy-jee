package pl.mclojek.fishy.model;

import lombok.*;
import pl.mclojek.fishy.common.util.DatetimeUtility;
import pl.mclojek.fishy.entity.Fish;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FishEditModel {

    private String species;
    private String catchDate;
    private int length;
    private float weight;

    public static Function<Fish, FishEditModel> entityToModelMapper() {
        return fish -> FishEditModel.builder()
                .species(FishModel.speciesMap.get(fish.getSpecies()))
                .catchDate(fish.getCatchDate().format(DatetimeUtility.viewFormatter))
                .length(fish.getLength())
                .weight(fish.getWeight())
                .build();
    }

    public static BiFunction<Fish, FishEditModel, Fish> modelToEntityUpdater() {
        return (fish, request) -> {
            fish.setSpecies(FishModel.speciesMap.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), request.getSpecies())).findFirst().map(Map.Entry::getKey).orElseThrow());
            fish.setCatchDate(LocalDateTime.parse(request.getCatchDate(), DatetimeUtility.viewFormatter));
            fish.setLength(request.length);
            fish.setWeight(request.weight);
            return fish;
        };
    }

}
