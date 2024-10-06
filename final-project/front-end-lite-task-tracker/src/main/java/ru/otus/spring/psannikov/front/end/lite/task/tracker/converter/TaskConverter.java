package ru.otus.spring.psannikov.front.end.lite.task.tracker.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TaskConverter {

    private final WorkConverter workConverter;

    public String taskToString(TaskDto taskDto) {

        return ("<b>Id:</b> %d\n<b>Title:</b> %s\n<b>Description:</b> %s\n<b>Priority:</b> %d\n<b>Status:</b> %s\n" +
                "<b>Parent id:</b> %d\n<b>Parent title:</b> %s\n<b>Start date:</b> %s\n<b>End date:</b> %s\n" +
                "<b>Works:</b> \n%s")
                .formatted(taskDto.getId(),
                        taskDto.getTitle(),
                        taskDto.getDescription(),
                        taskDto.getPriorityId(),
                        taskDto.getStatusDescription(),
                        taskDto.getParentId(),
                        taskDto.getParentName(),
                        taskDto.getStartDate(),
                        taskDto.getEndDate(),
                        taskDto.getWorks().isEmpty() ? null : taskDto
                                .getWorks()
                                .stream()
                                .map(workConverter::workToString)
                                .collect(Collectors.joining("\n" + System.lineSeparator())));
    }
}
