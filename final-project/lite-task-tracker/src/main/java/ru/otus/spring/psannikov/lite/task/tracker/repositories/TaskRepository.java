package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();
}
