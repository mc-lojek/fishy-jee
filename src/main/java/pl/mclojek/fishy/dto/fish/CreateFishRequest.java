package pl.mclojek.fishy.dto.fish;

import lombok.*;
import pl.mclojek.fishy.common.util.DatetimeUtility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.enums.FishSpecies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFishRequest {

    private FishSpecies species;

    private String catchDate;

    private int length;

    private float weight;

    public static Function<CreateFishRequest, Fish> dtoToEntityMapper(Supplier<Lake> lakeSupplier) {

        return request -> Fish.builder()
                .species(request.getSpecies())
                .catchDate(LocalDateTime.parse(request.getCatchDate(), DatetimeUtility.viewFormatter))
                .length(request.getLength())
                .weight(request.getWeight())
                .lake(lakeSupplier.get())
                .build();
    }

}
