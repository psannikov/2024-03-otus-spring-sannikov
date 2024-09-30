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

    private String task_description;

    private String priority;

    private String status;

    private String parent_task;

    private String start_date;

    private String end_date;

    private String owner;

    private String last_work;
}
