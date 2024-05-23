package ru.otus.spring.psannikov.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.springdata.domain.Email;

import java.util.List;

public interface EmailRepository {

    //@Override
    List<Email> findAll();
}
