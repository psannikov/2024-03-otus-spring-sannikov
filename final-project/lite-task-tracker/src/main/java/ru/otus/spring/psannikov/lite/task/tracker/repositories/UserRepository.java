package ru.otus.spring.psannikov.lite.task.tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
}
