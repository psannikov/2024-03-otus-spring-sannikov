package ru.otus.spring.psannikov.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.CommentDto;
import ru.otus.spring.psannikov.webflux.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Flux<CommentDto> findAll() {
        return repository.findAll().map(CommentDto::entityToDto);
    }

    public Mono<CommentDto> findById(String id) {
        return repository.findById(id).map(CommentDto::entityToDto);
    }

    public Mono<CommentDto> save(Mono<CommentDto> commentDtoMono) {
        return commentDtoMono.map(CommentDto::dtoToEntity)
                .flatMap(repository::insert)
                .map(CommentDto::entityToDto);
    }

    public Mono<CommentDto> update(Mono<CommentDto> commentDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> commentDtoMono.map(CommentDto::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(CommentDto::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
