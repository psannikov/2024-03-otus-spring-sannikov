package ru.otus.spring.psannikov.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.AuthorDto;
import ru.otus.spring.psannikov.webflux.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public Flux<AuthorDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<AuthorDto> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<AuthorDto> save(@RequestBody Mono<AuthorDto> authorDtoMono) {
        return service.save(authorDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<AuthorDto> update(@RequestBody Mono<AuthorDto> authorDtoMono, @PathVariable String id) {
        return service.update(authorDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
