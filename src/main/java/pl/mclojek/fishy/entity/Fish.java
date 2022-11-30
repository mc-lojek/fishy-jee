package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mclojek.fishy.enums.FishSpecies;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private FishSpecies species;
    private LocalDateTime catchDate;
    private int length;
    private float weight;
    @ManyToOne
    private Lake lake;
    @Transient
    private User hunter;
}
