package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.dtos.TaskDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> findById(long id);

    List<Task> findAll();

    Task insert(TaskDto taskDto);

    Task update(TaskDto taskDto);

    void deleteById(long id);
}
