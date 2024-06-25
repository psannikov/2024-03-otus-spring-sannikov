package ru.otus.spring.psannikov.data.nosql.services;

import ru.otus.spring.psannikov.data.nosql.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(String id);

    Comment insert(String bookId, String fullComment);

    Comment update(String id, String bookId, String fullComment);

    void deleteById(String id);

    List<Comment> findAllByBookId(String id);
}
