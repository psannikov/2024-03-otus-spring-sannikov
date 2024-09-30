package ru.otus.spring.psannikov.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.AuthorDto;
import ru.otus.spring.psannikov.webflux.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public Flux<AuthorDto> findAll() {
        return repository.findAll().map(AuthorDto::entityToDto);
    }

    public Mono<AuthorDto> findById(String id) {
        return repository.findById(id).map(AuthorDto::entityToDto);
    }

    public Mono<AuthorDto> save(Mono<AuthorDto> authorDtoMono) {
        return authorDtoMono.map(AuthorDto::dtoToEntity)
                .flatMap(repository::insert)
                .map(AuthorDto::entityToDto);
    }

    public Mono<AuthorDto> update(Mono<AuthorDto> authorDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> authorDtoMono.map(AuthorDto::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(AuthorDto::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
