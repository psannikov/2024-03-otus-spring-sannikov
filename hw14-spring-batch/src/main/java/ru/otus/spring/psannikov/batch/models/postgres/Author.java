package ru.otus.spring.psannikov.batch.models.postgres;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
@EqualsAndHashCode(of = {"id"})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
}
