package pl.mclojek.fishy.model;

import lombok.*;
import pl.mclojek.fishy.common.util.DatetimeUtility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FishCreateModel {

    private String species;
    private String catchDate;
    private int length;
    private float weight;
    private LakeModel lake;

    public static Function<FishCreateModel, Fish> modelToEntityMapper(
            Function<Long, Lake> lakeFunction) {
        return model -> Fish.builder()
                .species(FishModel.speciesMap.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), model.species)).findFirst().map(Map.Entry::getKey).orElseThrow())
                .catchDate(LocalDateTime.parse(model.catchDate, DatetimeUtility.viewFormatter))
                .length(model.length)
                .weight(model.weight)
                .lake(lakeFunction.apply(model.lake.getId()))
                .build();
    }

}
