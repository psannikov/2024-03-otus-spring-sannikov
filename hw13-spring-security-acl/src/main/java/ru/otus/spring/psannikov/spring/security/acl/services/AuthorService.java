package ru.otus.spring.psannikov.spring.security.acl.services;

import ru.otus.spring.psannikov.spring.security.acl.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
