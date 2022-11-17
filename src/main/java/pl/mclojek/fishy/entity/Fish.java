package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mclojek.fishy.enums.FishSpecies;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "fishes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Fish implements Serializable {
    @Id
    private long id;
    private FishSpecies species;
    private LocalDateTime catchDate;
    private int length;
    private float weight;
    @Transient
    private Lake lake;
    @Transient
    private User hunter;
}
