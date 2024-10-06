package ru.otus.spring.psannikov.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.psannikov.useractivitymodels.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}
