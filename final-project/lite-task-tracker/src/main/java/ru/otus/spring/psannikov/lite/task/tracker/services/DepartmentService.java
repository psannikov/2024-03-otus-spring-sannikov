package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findById(long id);

    List<Department> findAll();

    Department insert(String name);

    Department update(long id, String name);

    void deleteById(long id);
}
