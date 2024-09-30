package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.Priority;

public interface PriorityRepository extends CrudRepository<Priority, Long> {
}
