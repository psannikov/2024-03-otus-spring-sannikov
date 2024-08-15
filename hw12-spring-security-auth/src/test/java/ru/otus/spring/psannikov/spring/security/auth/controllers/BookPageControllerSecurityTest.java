package ru.otus.spring.psannikov.spring.security.auth.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.psannikov.spring.security.auth.config.SecurityConfiguration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для BookPage с авторизацией должен")
@WebMvcTest(BookPageController.class)
@Import({SecurityConfiguration.class})
public class BookPageControllerSecurityTest {

    @Autowired
    private MockMvc mvc;

    private final static long ID = 1L;

    @DisplayName("без авторизации страница 'список книг' выполнять редирект")
    @Test
    public void listBooksPageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("без авторизации страница 'редактирования' выполнять редирект")
    @Test
    public void editBookPageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/edit/{id}", ID))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("без авторизации страница 'создания' выполнять редирект")
    @Test
    public void createBookPageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/create"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("без авторизации страница 'удаления' выполнять редирект")
    @Test
    public void deleteBookPageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/delete/{id}", ID))
                .andExpect(status().is3xxRedirection());
    }

}
