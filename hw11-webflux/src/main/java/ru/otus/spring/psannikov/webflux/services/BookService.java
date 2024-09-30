package ru.otus.spring.psannikov.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.BookDto;
import ru.otus.spring.psannikov.webflux.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public Flux<BookDto> findAll() {
        return repository.findAll().map(BookDto::entityToDto);
    }

    public Mono<BookDto> findById(String id) {
        return repository.findById(id).map(BookDto::entityToDto);
    }

    public Mono<BookDto> save(Mono<BookDto> bookDtoMono) {
        return bookDtoMono.map(BookDto::dtoToEntity)
                .flatMap(repository::insert)
                .map(BookDto::entityToDto);
    }

    public Mono<BookDto> update(Mono<BookDto> bookDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> bookDtoMono.map(BookDto::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(BookDto::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
