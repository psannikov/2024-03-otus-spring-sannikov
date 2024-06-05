package ru.otus.spring.psannikov.data.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.psannikov.data.nosql.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findById(String id);

    Comment save(Comment comment);

    void deleteById(String id);

    List<Comment> findAllByBookId(String id);
}
