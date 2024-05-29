package ru.otus.spring.psannikov.springdata.repository;

import ru.otus.spring.psannikov.springdata.domain.Email;

import java.util.Optional;

public interface EmailRepositoryCustom {
    Optional<Email> findByPersonId(long personId);
}
