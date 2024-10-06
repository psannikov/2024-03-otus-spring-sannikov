package ru.otus.spring.psannikov.hystrix.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.hystrix.models.Author;
import ru.otus.spring.psannikov.hystrix.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Retry(name = "findByIdRetry")
    @Override
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    @CircuitBreaker(name = "findAllCircuitBreaker", fallbackMethod = "findAllRecoverMethod")
    @Override
    public List<Author> findAll() {
        ToolsService.sleepRandomly();
        return authorRepository.findAll();
    }

    //Имитирую запрос в другой источник обновляя исходные данные того же источника
    public List<Author> findAllRecoverMethod(Exception ex) {
        var authors = authorRepository.findAll();
        authors.forEach(author -> author.setFullName(author.getFullName().toUpperCase()));
        return authors;
    }

}
