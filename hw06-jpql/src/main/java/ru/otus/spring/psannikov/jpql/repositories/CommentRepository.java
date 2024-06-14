package ru.otus.spring.psannikov.jpql.repositories;


import ru.otus.spring.psannikov.jpql.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    Comment save(Comment comment);

    void deleteById(long id);

    List<Comment> findAllByBookId(long id);
}
