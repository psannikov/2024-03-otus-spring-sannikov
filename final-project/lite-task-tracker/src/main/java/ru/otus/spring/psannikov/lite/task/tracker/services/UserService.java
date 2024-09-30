package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.models.Department;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);

    List<User> findAll();

    User insert(String name, String email, String login, String password, Department department);

    User update(long id, String name, String email, String login, String password, Department department);

    void deleteById(long id);

}
