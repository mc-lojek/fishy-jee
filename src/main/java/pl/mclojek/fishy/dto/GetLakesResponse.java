package pl.mclojek.fishy.dto;

import lombok.*;
import pl.mclojek.fishy.entity.Lake;

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
public class GetLakesResponse {

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

    public static Function<Collection<pl.mclojek.fishy.entity.Lake>, GetLakesResponse> entityToDtoMapper() {
        return lakes -> {
            GetLakesResponseBuilder response = GetLakesResponse.builder();
            lakes.stream()
                    .map(lake -> Lake.builder()
                            .id(lake.getId())
                            .name(lake.getName())
                            .build())
                    .forEach(response::lake);
            return response.build();
        };

    }

}
