package pl.mclojek.fishy.model;

import lombok.*;
import pl.mclojek.fishy.entity.Lake;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JSF view model class in order to not to use entity classes. Represents single character to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class LakeModel {

    private long id;
    private String name;
    private String coords;
    private float area;
    private String isPublic;
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<FishModel> fishList = new ArrayList<FishModel>();

    public static Function<Lake, LakeModel> entityToModelMapper() {
        return lake -> LakeModel.builder()
                .id(lake.getId())
                .name(lake.getName())
                .coords( "[" + lake.getLatitude() + ";" + lake.getLongitude() + "]")
                .area(lake.getArea())
                .isPublic(lake.isPublic() ? "PZW" : "Prywatne")
                .fishList(lake.getFishList().stream().map(FishModel.entityToModelMapper()).collect(Collectors.toList()))
                .build();
    }
}
