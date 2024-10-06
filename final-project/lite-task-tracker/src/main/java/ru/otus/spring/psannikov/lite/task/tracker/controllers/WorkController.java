package ru.otus.spring.psannikov.lite.task.tracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.WorkDto;
import ru.otus.spring.psannikov.lite.task.tracker.services.WorkService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @GetMapping("/api/v1/work")
    public List<WorkDto> findAll() {
        return workService.findAll()
                .stream()
                .map(WorkDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/work/{id}")
    public Optional<WorkDto> findById(@PathVariable Long id) {
        return workService.findById(id).map(WorkDto::toDto);
    }

    @PostMapping("/api/v1/work/{id}")
    public WorkDto update(@PathVariable Long id, @RequestBody WorkDto workDto) {
        var work = workService.update(id, workDto.getDescription(), workDto.getDate(), workDto.getWorkerId());
        return WorkDto.toDto(work);
    }

    @PostMapping("/api/v1/work")
    public WorkDto insert(@RequestBody WorkDto workDto) {
        var work = workService.insert(workDto.getDescription(), workDto.getDate(), workDto.getWorkerId());
        return WorkDto.toDto(work);
    }

    @DeleteMapping("/api/v1/work/{id}")
    public void delete(@PathVariable Long id) {
        workService.deleteById(id);
    }
}
