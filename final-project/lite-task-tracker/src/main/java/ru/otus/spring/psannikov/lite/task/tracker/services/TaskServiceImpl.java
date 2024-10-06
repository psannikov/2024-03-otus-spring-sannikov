package ru.otus.spring.psannikov.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.TaskDto;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.WorkDto;
import ru.otus.spring.psannikov.lite.task.tracker.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.PriorityRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.StatusRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.TaskRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final PriorityRepository priorityRepository;

    private final StatusRepository statusRepository;

    private final UserRepository userRepository;

    @Override
    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task insert(TaskDto taskDto) {
        return save(taskDto);
    }

    @Override
    public Task update(TaskDto taskDto) {
        return save(taskDto);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    private Task save(TaskDto taskDto) {
        var priority = priorityRepository.findById(taskDto.getPriorityId())
                .orElseThrow(() -> new EntityNotFoundException("Priority with id %d not found"
                        .formatted(taskDto.getPriorityId())));
        var status = statusRepository.findById(taskDto.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status with id %d not found"
                        .formatted(taskDto.getStatusId())));
        var parentTask = taskRepository.findById(taskDto.getParentId()).orElse(null);
        var owner = userRepository.findById(taskDto.getOwnerId()).orElseThrow();
        var task = Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .priority(priority)
                .status(status)
                .works(taskDto.getWorks().stream().map(WorkDto::toDomain).toList())
                .parent(parentTask)
                .startDate(taskDto.getStartDate())
                .endDate(taskDto.getEndDate())
                .owner(owner)
                .build();
        return taskRepository.save(task);
    }
}
