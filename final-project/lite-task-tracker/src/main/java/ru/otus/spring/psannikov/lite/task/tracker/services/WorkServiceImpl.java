package ru.otus.spring.psannikov.lite.task.tracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.models.Work;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.UserRepository;
import ru.otus.spring.psannikov.lite.task.tracker.repositories.WorkRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;

    private final UserRepository userRepository;

    @Override
    public Optional<Work> findById(long id) {
        return workRepository.findById(id);
    }

    @Override
    public List<Work> findAll() {
        return workRepository.findAll();
    }

    @Override
    public Work insert(String description, Date date, long workerId) {
        return save(0, description, date, workerId);
    }

    @Override
    public Work update(long id, String description, Date date, long workerId) {
        return save(id, description, date, workerId);
    }

    @Override
    public void deleteById(long id) {

    }

    private Work save(long id, String description, Date date, long workerId) {
        var user = userRepository.findById(workerId)
                .orElseThrow(() -> new EntityNotFoundException("User with id %d not found".formatted(workerId)));
        var work = Work.builder()
                .id(id)
                .description(description)
                .date(date)
                .worker(user)
                .build();
        return workRepository.save(work);
    }
}
