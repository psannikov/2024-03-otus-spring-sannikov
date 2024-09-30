package ru.otus.spring.psannikov.lite.task.tracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.WorkDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.PriorityRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.StatusRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.TaskRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
    public Task insert(String title, String description, long priorityId, long statusId, List<WorkDto> works, long parentId, Date startDate, Date endDate, long ownerId) {
        return save(0, title, description, priorityId, statusId, works, parentId, startDate, endDate, ownerId);
    }

    @Override
    public Task update(long id, String title, String description, long priorityId, long statusId, List<WorkDto> works, long parentId, Date startDate, Date endDate, long ownerId) {
        return save(id, title, description, priorityId, statusId, works, parentId, startDate, endDate, ownerId);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getRawRepo() {
        return taskRepository.findAllRepo();
    }

    private Task save(long id, String title, String description, long priorityId, long statusId, List<WorkDto> works, long parentId, Date startDate, Date endDate, long ownerId) {
        var priority = priorityRepository
                .findById(priorityId)
                .orElseThrow(() -> new EntityNotFoundException("Priority with id %d not found".formatted(priorityId)));
        var status = statusRepository
                .findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException("Status with id %d not found".formatted(priorityId)));
        var parentTask = taskRepository
                .findById(parentId)
                .orElse(null);
        var owner = userRepository.findById(ownerId).get();
        var task = Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .priority(priority)
                .status(status)
                .works(works.stream().map(WorkDto::toDomain).toList())
                .parent(parentTask)
                .startDate(startDate)
                .endDate(endDate)
                .owner(owner)
                .build();
        return taskRepository.save(task);
    }
}
