package pl.mclojek.fishy.model;

import lombok.*;
import pl.mclojek.fishy.common.util.DatetimeUtility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.enums.FishSpecies;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FishModel {

    private long id;
    private String species;
    private String catchDate;
    private int length;
    private float weight;

    public static Map<FishSpecies, String> speciesMap;

    static {
        Map<FishSpecies, String> map = new HashMap<>();
        map.put(FishSpecies.CATFISH, "Sum");
        map.put(FishSpecies.COMMON_CARP, "Karp pełnołuski");
        map.put(FishSpecies.MIRROR_CARP, "Karp królewski");
        map.put(FishSpecies.GRASS_CARP, "Amur");
        map.put(FishSpecies.STURGEON, "Jesiotr");
        map.put(FishSpecies.OTHER, "Inny");
        speciesMap = map;
    }

    public static Function<Fish, FishModel> entityToModelMapper() {
        return fish -> FishModel.builder()
                .id(fish.getId())
                .species(speciesMap.get(fish.getSpecies()))
                .catchDate(fish.getCatchDate().format(DatetimeUtility.viewFormatter))
                .length(fish.getLength())
                .weight(fish.getWeight())
                .build();
    }

}
