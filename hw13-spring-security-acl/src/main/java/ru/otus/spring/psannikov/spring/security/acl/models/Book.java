package ru.otus.spring.psannikov.spring.security.acl.models;

import jakarta.persistence.*;
import lombok.*;
import ru.otus.spring.psannikov.spring.security.acl.models.Comment;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-authors-entity-graph",
        attributeNodes = {@NamedAttributeNode("author")})
@NamedEntityGraph(name = "books-genres-entity-graph",
        attributeNodes = {@NamedAttributeNode("genre")})
@NamedEntityGraph(name = "books-comment-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments")})
@EqualsAndHashCode(of = {"id"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

}
