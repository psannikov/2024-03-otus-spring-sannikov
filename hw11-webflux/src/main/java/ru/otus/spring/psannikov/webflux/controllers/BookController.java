package ru.otus.spring.psannikov.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.BookDto;
import ru.otus.spring.psannikov.webflux.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public Flux<BookDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BookDto> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<BookDto> save(@RequestBody Mono<BookDto> bookDtoMono) {
        return service.save(bookDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<BookDto> update(@RequestBody Mono<BookDto> bookDtoMono, @PathVariable String id) {
        return service.update(bookDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
