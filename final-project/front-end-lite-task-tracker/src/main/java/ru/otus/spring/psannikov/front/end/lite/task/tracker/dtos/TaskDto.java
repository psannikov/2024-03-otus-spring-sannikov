package ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
