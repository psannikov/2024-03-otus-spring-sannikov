package ru.otus.spring.psannikov.spring.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.spring.security.auth.models.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(long id);

    Comment save(Comment comment);

    void deleteById(long id);

}
