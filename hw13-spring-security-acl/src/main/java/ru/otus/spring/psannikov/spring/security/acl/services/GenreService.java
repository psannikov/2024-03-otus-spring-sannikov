package ru.otus.spring.psannikov.spring.security.acl.services;

import ru.otus.spring.psannikov.spring.security.acl.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(long id);

    List<Genre> findAll();
}
