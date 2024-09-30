package ru.otus.spring.psannikov.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.models.Department;
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
    public User insert(String name, String email, String login, String password, Department department) {
        return null;
    }

    @Override
    public User update(long id, String name, String email, String login, String password, Department department) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
