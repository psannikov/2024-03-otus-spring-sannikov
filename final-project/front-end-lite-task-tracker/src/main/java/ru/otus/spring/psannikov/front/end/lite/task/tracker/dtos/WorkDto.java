package ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class WorkDto {

    private long id;

    private String description;

    private Date date;

    private long workerId;
}
