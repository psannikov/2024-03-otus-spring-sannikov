package ru.otus.spring.psannikov.jpql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
@NamedEntityGraph(name = "books-books-entity-graph",
        attributeNodes = {@NamedAttributeNode("book")})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "full_comment")
    private String fullComment;
}
