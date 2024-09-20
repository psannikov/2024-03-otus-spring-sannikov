package ru.otus.spring.psannikov.hystrix.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.hystrix.models.Genre;
import ru.otus.spring.psannikov.hystrix.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Retry(name = "findByIdRetry")
    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }

    @CircuitBreaker(name = "findAllCircuitBreaker", fallbackMethod = "findAllRecoverMethod")
    @Override
    public List<Genre> findAll() {
        ToolsService.sleepRandomly();
        return genreRepository.findAll();
    }

    //Имитирую запрос в другой источник обновляя исходные данные того же источника
    public List<Genre> findAllRecoverMethod(Exception ex) {
        var genres = genreRepository.findAll();
        genres.forEach(genre -> genre.setName(genre.getName().toUpperCase()));
        return genres;
    }
}
