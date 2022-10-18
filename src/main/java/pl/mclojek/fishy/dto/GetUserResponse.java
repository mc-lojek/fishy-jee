package pl.mclojek.fishy.dto;

import lombok.*;
import pl.mclojek.fishy.entity.User;

import java.time.LocalDateTime;
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
public class GetUserResponse {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    private Long id;
    private String username;
    private String email;
    private String createdAt;
    private int experience;

    public static Function<User, GetUserResponse> entityToDtoMapper() {
        return user -> GetUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt().format(formatter))
                .experience(user.getExperience())
                .build();
    }
}
