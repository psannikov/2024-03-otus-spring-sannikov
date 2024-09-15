package ru.otus.spring.psannikov.batch.models.postgres;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_comment")
    private String fullComment;
}
