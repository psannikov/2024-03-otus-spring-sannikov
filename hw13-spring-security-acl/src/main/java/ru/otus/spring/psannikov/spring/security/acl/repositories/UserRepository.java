package ru.otus.spring.psannikov.spring.security.acl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.spring.security.acl.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);

    List<User> findAll();

    Optional<User> findByLogin(String login);
}