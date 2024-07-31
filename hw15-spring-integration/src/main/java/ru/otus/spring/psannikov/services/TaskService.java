package ru.otus.spring.psannikov.services;

import ru.otus.spring.psannikov.domain.Memo;
import ru.otus.spring.psannikov.domain.Task;

public interface TaskService {

    Task createTask(Memo memo);

    Task takeTask(Task task);

    Task finishTask(Task task);
}
