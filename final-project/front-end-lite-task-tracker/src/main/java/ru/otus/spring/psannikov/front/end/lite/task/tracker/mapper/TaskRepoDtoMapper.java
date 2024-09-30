package ru.otus.spring.psannikov.front.end.lite.task.tracker.mapper;

import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskRepoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskRepoDtoMapper {
    public static List<TaskRepoDto> mapToTaskRepoDto(List<Map<String, Object>> rawData) {
        List<TaskRepoDto> taskRepoDtos = new ArrayList<>();

        for (Map<String, Object> row : rawData) {
            TaskRepoDto taskRepoDto = new TaskRepoDto();
            taskRepoDto.setId((Integer) row.get("id"));
            taskRepoDto.setTitle((String) row.get("title"));
            taskRepoDto.setTask_description((String) row.get("task_description"));
            taskRepoDto.setPriority((String) row.get("priority"));
            taskRepoDto.setStatus((String) row.get("status"));
            taskRepoDto.setParent_task((String) row.get("parent_task"));
            taskRepoDto.setStart_date((String) row.get("start_date"));
            taskRepoDto.setEnd_date((String) row.get("end_date"));
            taskRepoDto.setOwner((String) row.get("owner"));
            taskRepoDto.setLast_work((String) row.get("last_work"));
            taskRepoDtos.add(taskRepoDto);
        }

        return taskRepoDtos;
    }
}
