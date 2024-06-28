package ru.otus.spring.psannikov.mvc.ajax.services;

import ru.otus.spring.psannikov.mvc.ajax.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(long id);

    Comment insert(long bookId, String fullComment);

    Comment update(long id, long bookId, String fullComment);

    void deleteById(long id);

    List<Comment> findAllByBookId(long id);
}
