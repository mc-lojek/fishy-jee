package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mclojek.fishy.enums.FishSpecies;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Fish implements Serializable {
    private long id;
    private FishSpecies species;
    private LocalDateTime catchDate;
    private int length;
    private float weight;
    private Lake lake;
    private User hunter;
}
