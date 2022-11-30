package pl.mclojek.fishy.model;

import lombok.*;

import java.io.Serializable;
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
public class LakesModel implements Serializable {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Lake {
        private Long id;
        private String name;
    }

    @Singular
    private List<Lake> lakes;

    public static Function<Collection<pl.mclojek.fishy.entity.Lake>, LakesModel> entityToModelMapper() {
        return lakes -> {
            LakesModel.LakesModelBuilder model = LakesModel.builder();
            lakes.stream()
                    .map(lake -> LakesModel.Lake.builder()
                            .id(lake.getId())
                            .name(lake.getName())
                            .build())
                    .forEach(model::lake);
            return model.build();
        };
    }

}
