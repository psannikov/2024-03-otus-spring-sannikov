package ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskRepoDto {
    private Integer id;

    private String title;

    private String taskDescription;

    private String priority;

    private String status;

    private String parentTask;

    private String startDate;

    private String endDate;

    private String owner;

    private String lastWork;
}
