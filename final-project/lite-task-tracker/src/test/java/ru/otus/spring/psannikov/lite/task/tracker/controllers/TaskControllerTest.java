package ru.otus.spring.psannikov.lite.task.tracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.psannikov.lite.task.tracker.dtos.TaskDto;
import ru.otus.spring.psannikov.lite.task.tracker.models.Priority;
import ru.otus.spring.psannikov.lite.task.tracker.models.Status;
import ru.otus.spring.psannikov.lite.task.tracker.models.Task;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;
import ru.otus.spring.psannikov.lite.task.tracker.models.Work;
import ru.otus.spring.psannikov.lite.task.tracker.services.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для Task должен")
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskController bookController;

    @MockBean
    private TaskService taskService;

    private Task mockTask;
    private List<Task> mockTasks;
    private TaskDto mockTaskDto;
    private List<TaskDto> mockTasksDto;

    private final long ID = 1L;
    private final String TITLE = "Title_1";
    private final String DESCRIPTION = "Description_1";
    private Priority priority;
    private Status status;
    private final List<Work> WORKS = new ArrayList<>();
    private User owner;

    @BeforeEach
    void init() {
        priority = new Priority(ID, DESCRIPTION);
        status = new Status(ID, DESCRIPTION);
        owner = new User(ID, null, null, null, null, null);
        mockTask = Task.builder()
                .id(ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .priority(priority)
                .status(status)
                .works(WORKS)
                .owner(owner)
                .build();
        mockTasks = List.of(mockTask);
        mockTaskDto = TaskDto.toDto(mockTask);
        mockTasksDto = List.of(mockTaskDto);
    }

    @DisplayName("вернуть список задач")
    @Test
    void getAllTasksTest() throws Exception {
        when(taskService.findAll()).thenReturn(mockTasks);
        List<TaskDto> expectedResult = mockTasksDto;
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedResult)));
        verify(taskService, times(1)).findAll();
    }

    @DisplayName("вернуть задачу по ее номеру")
    @Test
    void getTasksByIdTest() throws Exception {
        when(taskService.findById(ID)).thenReturn(Optional.ofNullable(mockTask));
        var expectedResult = mockTaskDto;
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/task/%d".formatted(ID)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedResult)));
        verify(taskService, times(1)).findById(ID);
    }

    @DisplayName("удалять задачу")
    @Test
    void deleteTaskTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/task/{id}", ID))
                .andExpect(status().isOk());
        verify(taskService, times(1)).deleteById(ID);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
