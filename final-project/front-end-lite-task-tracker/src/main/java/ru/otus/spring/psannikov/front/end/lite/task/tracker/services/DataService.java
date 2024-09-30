package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskRepoDto;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskShortDto;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.mapper.TaskRepoDtoMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService {
    private List<Map<String, Object>> getRawData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Map<String, Object>>> response = restTemplate
                .exchange("http://localhost:8080/api/v1/rawrepo"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<List<Map<String, Object>>>() {
                        }
                );
        List<Map<String, Object>> jsonObjects = response.getBody();
        return jsonObjects;
    }

    private List<TaskRepoDto> prepareData(List<Map<String, Object>> raw) {
        return TaskRepoDtoMapper.mapToTaskRepoDto(raw);
    }

    public List<TaskRepoDto> getAllData() {
        var raw = getRawData();
        return prepareData(raw);
    }

    public List<TaskShortDto> getTasksInWork() {
        var data = getAllData();
        return data
                .stream()
                .filter(s -> s.getStatus().equals("DONE"))
                .map(TaskShortDto::fromTaskRepoDto)
                .collect(Collectors.toList());
    }

    public Optional<TaskRepoDto> getTasksById(long id) {
        var data = getAllData();
        return data
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }
}
