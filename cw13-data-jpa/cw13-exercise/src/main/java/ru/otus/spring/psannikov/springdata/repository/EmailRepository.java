package ru.otus.spring.psannikov.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.springdata.domain.Email;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("select e from Email e where e.address = :address")
    Optional<Email> findByEmailAddress(@Param("address") String address);

    @Transactional
    @Modifying
    @Query("update Email e set e.address = :address where e.id= :id")
    void updateEmailById(@Param("id") Long id, @Param("address") String address);
}
