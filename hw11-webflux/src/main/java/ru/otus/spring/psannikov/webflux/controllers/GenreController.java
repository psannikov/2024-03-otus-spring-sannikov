package ru.otus.spring.psannikov.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.GenreDto;
import ru.otus.spring.psannikov.webflux.services.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping
    public Flux<GenreDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<GenreDto> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<GenreDto> save(@RequestBody Mono<GenreDto> genreDtoMono) {
        return service.save(genreDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<GenreDto> update(@RequestBody Mono<GenreDto> genreDtoMono, @PathVariable String id) {
        return service.update(genreDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
