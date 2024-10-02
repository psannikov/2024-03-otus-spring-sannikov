package ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskShortDto {

    private long id;

    private String title;

    private String taskDescription;

    public static TaskShortDto fromTaskRepoDto(TaskRepoDto dto) {
        return TaskShortDto.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .taskDescription(dto.getTaskDescription())
                .build();
    }
}
