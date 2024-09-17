package ru.otus.spring.psannikov.spring.security.acl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;
import ru.otus.spring.psannikov.spring.security.acl.repositories.GenreRepository;
import ru.otus.spring.psannikov.spring.security.acl.services.GenreService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
