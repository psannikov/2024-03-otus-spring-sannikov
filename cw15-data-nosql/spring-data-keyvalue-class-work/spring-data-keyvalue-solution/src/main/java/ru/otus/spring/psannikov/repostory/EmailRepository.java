package ru.otus.spring.psannikov.repostory;

import ru.otus.spring.psannikov.domain.Email;

import java.util.List;

public interface EmailRepository {

    List<Email> findAll();

    Email save(Email email);
}
