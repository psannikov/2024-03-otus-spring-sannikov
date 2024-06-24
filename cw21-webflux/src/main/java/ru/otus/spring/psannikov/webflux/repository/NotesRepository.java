package ru.otus.spring.psannikov.webflux.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.psannikov.webflux.domain.Notes;


public interface NotesRepository extends ReactiveCrudRepository<Notes, Long> {

    Flux<Notes> findByPersonId(@NotNull Long personId);
}
