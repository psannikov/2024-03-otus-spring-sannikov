package ru.otus.spring.psannikov.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.UserDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User insert(UserDto userDto) {
        return null;
    }

    @Override
    public User update(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
