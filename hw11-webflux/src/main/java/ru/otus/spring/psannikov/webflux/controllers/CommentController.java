package ru.otus.spring.psannikov.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.psannikov.webflux.dtos.CommentDto;
import ru.otus.spring.psannikov.webflux.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public Flux<CommentDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CommentDto> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<CommentDto> save(@RequestBody Mono<CommentDto> commentDtoMono) {
        return service.save(commentDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<CommentDto> update(@RequestBody Mono<CommentDto> commentDtoMono, @PathVariable String id) {
        return service.update(commentDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
