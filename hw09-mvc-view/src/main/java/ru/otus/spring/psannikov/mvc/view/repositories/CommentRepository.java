package ru.otus.spring.psannikov.mvc.view.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.mvc.view.models.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(long id);

    Comment save(Comment comment);

    void deleteById(long id);

}
