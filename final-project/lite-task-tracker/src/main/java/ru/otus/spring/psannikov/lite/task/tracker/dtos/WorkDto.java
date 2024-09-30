package ru.otus.spring.psannikov.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.psannikov.lite.task.tracker.models.Work;

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

    public static WorkDto toDto(Work work) {
        return WorkDto.builder()
                .id(work.getId())
                .description(work.getDescription())
                .date(work.getDate())
                .workerId(work.getWorker().getId())
                .build();
    }

    public static Work toDomain(WorkDto workDto) {
        return Work.builder().build();
    }
}
