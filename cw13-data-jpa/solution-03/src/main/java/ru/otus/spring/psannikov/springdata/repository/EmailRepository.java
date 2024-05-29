package ru.otus.spring.psannikov.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.springdata.domain.Email;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
