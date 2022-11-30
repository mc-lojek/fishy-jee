package pl.mclojek.fishy.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String email;
    @ToString.Exclude
    private String password;
    private LocalDateTime createdAt;
    private int experience;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Fish> fishList;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;
}
