package pl.mclojek.fishy.dto.fish;

import lombok.*;
import pl.mclojek.fishy.common.util.DatetimeUtility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.enums.FishSpecies;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFishRequest {

    private FishSpecies species;

    private String catchDate;

    private int length;

    private float weight;

    public static BiFunction<Fish, UpdateFishRequest, Fish> dtoToEntityUpdater() {
        return (fish, request) -> {
            fish.setSpecies(request.getSpecies());
            fish.setCatchDate(LocalDateTime.parse(request.getCatchDate(), DatetimeUtility.viewFormatter));
            fish.setLength(request.getLength());
            fish.setWeight(request.getWeight());
            return fish;
        };
    }
}
