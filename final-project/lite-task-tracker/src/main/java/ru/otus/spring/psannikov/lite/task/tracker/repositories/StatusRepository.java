package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {
}
