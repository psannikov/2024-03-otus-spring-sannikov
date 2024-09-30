package ru.otus.spring.psannikov.webflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.webflux.models.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
