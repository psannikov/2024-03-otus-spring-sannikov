package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Value("${application.url.allTask}")
    private String urlAll;

    @Value("${application.url.findByIdTask}")
    private String urlFindByIdTask;

    public List<TaskDto> getTasksInWork() {
        var data = getAllTasks();
        return data
                .stream()
                .filter(s -> !s.getStatusDescription().equals("DONE"))
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> getTasksById(long id) {
        String urlFindByIdTaskWithId = String.format(urlFindByIdTask, id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Optional<TaskDto>> response = restTemplate
                .exchange(urlFindByIdTaskWithId
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<Optional<TaskDto>>() {
                        });
        var res = response.getBody();
        return res;
    }

    public List<TaskDto> getAllTasks() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TaskDto>> response = restTemplate
                .exchange(urlAll
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<List<TaskDto>>() {
                        });
        var res = response.getBody();
        return res;
    }
}
