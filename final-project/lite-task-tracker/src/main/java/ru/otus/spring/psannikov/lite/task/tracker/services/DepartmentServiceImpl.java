package ru.otus.spring.psannikov.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.lite.task.tracker.models.Department;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public Optional<Department> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

    @Override
    public Department insert(String name) {
        return null;
    }

    @Override
    public Department update(long id, String name) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
