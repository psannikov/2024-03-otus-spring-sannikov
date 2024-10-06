package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.models.Work;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkService {

    Optional<Work> findById(long id);

    List<Work> findAll();

    Work insert(String description, Date date, long workerId);

    Work update(long id, String description, Date date, long workerId);

    void deleteById(long id);
}
