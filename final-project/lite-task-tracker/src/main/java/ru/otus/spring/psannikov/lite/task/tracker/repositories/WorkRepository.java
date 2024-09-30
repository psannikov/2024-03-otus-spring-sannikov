package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.Work;

import java.util.List;

public interface WorkRepository extends CrudRepository<Work, Long> {

    List<Work> findAll();
}
