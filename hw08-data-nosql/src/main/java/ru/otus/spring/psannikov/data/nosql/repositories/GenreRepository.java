package ru.otus.spring.psannikov.data.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.psannikov.data.nosql.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findById(String id);

    List<Genre> findAll();
}
