package ru.otus.spring.psannikov.data.rest.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DayOfWeekHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (LocalDateTime.now().getDayOfWeek().getValue() == 6 || LocalDateTime.now().getDayOfWeek().getValue() == 7) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Ахтунг, сервер отдыхает не мешайте!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Всё чики-пуки!").build();
        }
    }
}
