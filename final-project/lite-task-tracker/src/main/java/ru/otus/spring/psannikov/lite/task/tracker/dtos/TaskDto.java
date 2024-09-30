package ru.otus.spring.psannikov.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class TaskDto {
    private long id;

    private String title;

    private String description;

    private long priorityId;

    private long statusId;

    private List<WorkDto> works;

    private long parentId;

    private Date startDate;

    private Date endDate;

    private long ownerId;

    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .priorityId(task.getPriority().getId())
                .statusId(task.getStatus().getId())
                .works(task.getWorks().stream().map(WorkDto::toDto).toList())
                .parentId(task.getParent() != null ? task.getParent().getId() : 0L)
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .ownerId(task.getOwner().getId())
                .build();
    }
}
