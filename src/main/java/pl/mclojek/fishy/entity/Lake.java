package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lakes")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Lake implements Serializable {
    @Id
    private long id;
    private String name;
    private double latitude;
    private double longitude;
    private float area;
    private boolean isPublic;

    @ToString.Exclude
    @Transient
    @Builder.Default
    private List<Fish> fishList = new ArrayList<Fish>();
}
