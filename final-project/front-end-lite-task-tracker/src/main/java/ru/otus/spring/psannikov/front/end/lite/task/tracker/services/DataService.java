package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskRepoDto;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskShortDto;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.mapper.TaskRepoDtoMapper;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService {
    private List<Map<String, Object>> getRawData() {
        String username = "user1";
        String password = "password1";
//        String auth = username + ":" + password;
//        String encodedAuth = Base64Utils.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
//        String authHeader = "Basic " + encodedAuth;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password, StandardCharsets.UTF_8);
//        headers.set("Authorization", authHeader);
//
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate
                .exchange("http://localhost:8080/api/v1/rawrepo"
                        , HttpMethod.GET
//                        ,null
                        , entity
                        , new ParameterizedTypeReference<List<Map<String, Object>>>() {
                        }
                );
            // Проверяем статус ответа
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println(response.getStatusCode());
                List<Map<String, Object>> jsonObjects = response.getBody();
                return jsonObjects;  // Возвращаем тело ответа
            } else {
                System.out.println("Ошибка: " + response.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Выводим информацию об ошибке
            System.err.println("HTTP ошибка: " + e.getStatusCode());
            System.err.println("Ответ от сервера: " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            // Общая обработка других ошибок
            e.printStackTrace();
            return null;
        }

//        return jsonObjects;
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
