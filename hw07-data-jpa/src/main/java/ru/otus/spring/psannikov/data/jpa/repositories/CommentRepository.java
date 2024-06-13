package ru.otus.spring.psannikov.data.jpa.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.psannikov.data.jpa.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @EntityGraph(attributePaths = {"book", "book.author", "book.genre"})
    Optional<Comment> findById(long id);

    @Modifying
    Comment save(Comment comment);

    @Modifying
    void deleteById(long id);

//    @EntityGraph(attributePaths = {"book", "book.author", "book.genre"})
    @Query("select b.comments from Book b where b.id = :id")
    List<Comment> findAllByBookId(long id);
}
