package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Lake implements Serializable {
    private long id;
    private String name;
    private double latitude;
    private double longitude;
    private float area;
    private boolean isPublic;

    @ToString.Exclude
    private List<Fish> fishList;
}
