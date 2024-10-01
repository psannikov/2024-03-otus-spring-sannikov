package ru.otus.spring.psannikov.lite.task.tracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.TaskDto;
import ru.otus.spring.psannikov.lite.task.tracker.services.TaskService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/api/v1/task")
    public List<TaskDto> findAll() {
        return taskService.findAll()
                .stream()
                .map(TaskDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/task/{id}")
    public Optional<TaskDto> findById(@PathVariable Long id) {
        return taskService.findById(id).map(TaskDto::toDto);
    }

    @PostMapping("/api/v1/task/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        if (taskDto.getId() != id) {
            new RuntimeException("Нельзя изменять ID задачи");
        }
        taskDto.setId(id);
        var task = taskService.update(taskDto);
        return TaskDto.toDto(task);
    }

    @PostMapping("/api/v1/task")
    public TaskDto insert(@RequestBody TaskDto taskDto) {
        var task = taskService.insert(taskDto);
        return TaskDto.toDto(task);
    }

    @DeleteMapping("/api/v1/task/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }


    @GetMapping("/api/v1/rawrepo")
    public List<Map<String, Object>> getRawRepo() {
        return taskService.getRawRepo();
    }
}
