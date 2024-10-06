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

    private String statusDescription;

    private List<WorkDto> works;

    private long parentId;

    private String parentName;

    private Date startDate;

    private Date endDate;

    private long ownerId;

    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priorityId(task.getPriority().getId())
                .statusId(task.getStatus().getId())
                .statusDescription(task.getStatus().getDescription())
                .works(task.getWorks().stream().map(WorkDto::toDto).toList())
                .parentId(task.getParent() != null ? task.getParent().getId() : 0L)
                .parentName(task.getParent() != null ? task.getParent().getTitle() : "")
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .ownerId(task.getOwner().getId())
                .build();
    }
}
