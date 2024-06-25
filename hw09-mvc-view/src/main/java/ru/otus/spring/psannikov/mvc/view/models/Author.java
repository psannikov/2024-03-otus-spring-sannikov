package ru.otus.spring.psannikov.mvc.view.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"id"})
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

}
