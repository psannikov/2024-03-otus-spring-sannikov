package ru.otus.spring.psannikov.lite.task.tracker.services;

import ru.otus.spring.psannikov.lite.task.tracker.dtos.WorkDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskService {

    Optional<Task> findById(long id);

    List<Task> findAll();

    Task insert(String title, String description, long priorityId, long statusId, List<WorkDto> works, long parentId, Date startDate, Date endDate, long ownerId);

    Task update(long id, String title, String description, long priorityId, long statusId, List<WorkDto> works, long parentId, Date startDate, Date endDate, long ownerId);

    void deleteById(long id);

    List<Map<String, Object>> getRawRepo();
}
