package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;

import java.util.List;
import java.util.Map;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();

    @Query(value = "select * from tasks$v", nativeQuery = true)
    List<Map<String, Object>> findAllRepo();
}
