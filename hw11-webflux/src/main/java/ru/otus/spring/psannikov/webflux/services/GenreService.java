package ru.otus.spring.psannikov.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.GenreDto;
import ru.otus.spring.psannikov.webflux.repositories.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    public Flux<GenreDto> findAll() {
        return repository.findAll().map(GenreDto::entityToDto);
    }

    public Mono<GenreDto> findById(String id) {
        return repository.findById(id).map(GenreDto::entityToDto);
    }

    public Mono<GenreDto> save(Mono<GenreDto> genreDtoMono) {
        return genreDtoMono.map(GenreDto::dtoToEntity)
                .flatMap(repository::insert)
                .map(GenreDto::entityToDto);
    }

    public Mono<GenreDto> update(Mono<GenreDto> genreDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> genreDtoMono.map(GenreDto::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(GenreDto::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
