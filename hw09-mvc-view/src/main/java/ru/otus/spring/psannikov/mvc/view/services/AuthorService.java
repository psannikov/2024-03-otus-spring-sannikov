package ru.otus.spring.psannikov.mvc.view.services;

import ru.otus.spring.psannikov.mvc.view.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
