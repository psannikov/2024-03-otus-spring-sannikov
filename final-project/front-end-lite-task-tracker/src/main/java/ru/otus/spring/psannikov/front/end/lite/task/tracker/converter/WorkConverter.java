package ru.otus.spring.psannikov.front.end.lite.task.tracker.converter;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.WorkDto;

@Component
public class WorkConverter {
    public String workToString(WorkDto workDto) {
        return "<b>Description:</b> %s\n,<b>Date:</b> %s".formatted(
                workDto.getDescription(),
                workDto.getDate());
    }
}
