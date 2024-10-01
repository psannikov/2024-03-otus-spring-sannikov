package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.dtos.UserDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);

    List<User> findAll();

    User insert(UserDto userDto);

    User update(UserDto userDto);

    void deleteById(long id);

}
