package ru.otus.spring.psannikov.spring.security.acl.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.psannikov.spring.security.acl.config.SecurityConfiguration;
import ru.otus.spring.psannikov.spring.security.acl.models.Author;
import ru.otus.spring.psannikov.spring.security.acl.models.Book;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;
import ru.otus.spring.psannikov.spring.security.acl.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.acl.services.BookService;
import ru.otus.spring.psannikov.spring.security.acl.services.GenreService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для BookPage: ")
@WebMvcTest(BookPageController.class)
@Import(SecurityConfiguration.class)
public class BookPageControllerSecurityTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    private final long id = 1L;

    private final UserDetails userDetails = org.springframework.security.core.userdetails.User
            .builder()
            .username("User1")
                .password("Pass1")
                .roles("LIBRARIAN")
                .build();

    private final Book mockBook = new Book(id, "TITLE",
                new Author(id, "AUTHOR"),
                new Genre(id, "GENRE"),
                new ArrayList<>());

    @ParameterizedTest(name = "без авторизации страница {0} должна выполнять редирект")
    @ValueSource(strings = {"/books", "/edit/1", "/create", "/delete/1"})
    public void parametrizedTestWithOutUser(String url) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().is3xxRedirection());
    }

    @ParameterizedTest(name = "c авторизацией страница {0} должна вернуть страницу {1}")
    @CsvSource(value = {
            "/books, books",
            "/create, addBook",
            "/edit/1, editBook",
            "/delete/1, deleteBook",
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void parametrizedTestWithUser(String requestUrl, String responseUrl) throws Exception {
        when(bookService.findById(id)).thenReturn(Optional.ofNullable(mockBook));
        mvc.perform(MockMvcRequestBuilders
                        .get(requestUrl)
                        .with(user(userDetails)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(responseUrl));
    }

}
