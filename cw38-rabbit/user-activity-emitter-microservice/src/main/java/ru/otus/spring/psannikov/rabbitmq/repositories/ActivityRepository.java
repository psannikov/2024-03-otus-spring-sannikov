package ru.otus.spring.psannikov.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.psannikov.useractivitymodels.UserActivity;

@Transactional
public interface ActivityRepository extends JpaRepository<UserActivity, Long> {
}
