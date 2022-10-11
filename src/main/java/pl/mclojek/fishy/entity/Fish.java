package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Fish {
    private long id;
    private String species;
    private LocalDateTime catchDate;
    private int length;
    private float weight;
    private Lake lake;
    private User hunter;

}
