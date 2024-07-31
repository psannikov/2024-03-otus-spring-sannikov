package ru.otus.spring.psannikov.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.domain.Memo;
import ru.otus.spring.psannikov.domain.Status;
import ru.otus.spring.psannikov.domain.Task;
import ru.otus.spring.psannikov.domain.User;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Override
    public Task createTask(Memo memo) {
        log.info("Create new task from memo {}", memo.getTitle());
        delay();
        log.info("Creating task from memo {} done", memo.getTitle());
        return Task
                .builder()
                .title("Task for memo " + memo.getTitle())
                .description("Description for memo " + memo.getFullText())
                .memo(memo)
                .performer(new User("Ivan", "Ivanov"))
                .status(Status.NEW)
                .build();
    }

    @Override
    public Task takeTask(Task task) {
        log.info("Take task {} to work", task.getTitle());
        task.setStatus(Status.IN_PROGRESS);
        log.info("Task {} in work", task.getTitle());
        return task;
    }

    @Override
    public Task finishTask(Task task) {
        log.info("Finish work on task {}", task.getTitle());
        task.setStatus(Status.DONE);
        log.info("Work on task {} is finished", task.getTitle());
        return task;
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
