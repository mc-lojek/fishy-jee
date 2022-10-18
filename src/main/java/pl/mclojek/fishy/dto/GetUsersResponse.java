package pl.mclojek.fishy.dto;

import lombok.*;

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
public class GetUsersResponse {


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User {
        private Long id;
        private String username;
    }

    @Singular
    private List<User> users;

    public static Function<Collection<pl.mclojek.fishy.entity.User>, GetUsersResponse> entityToDtoMapper() {
        return users -> {
            GetUsersResponse.GetUsersResponseBuilder response = GetUsersResponse.builder();
            users.stream()
                    .map(user -> User.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .build())
                    .forEach(response::user);
            return response.build();
        };
    }
}
