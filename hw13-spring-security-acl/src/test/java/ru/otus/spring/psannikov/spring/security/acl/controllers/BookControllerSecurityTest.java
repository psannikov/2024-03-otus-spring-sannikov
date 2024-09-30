package ru.otus.spring.psannikov.spring.security.acl.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.psannikov.spring.security.acl.config.SecurityConfiguration;
import ru.otus.spring.psannikov.spring.security.acl.dto.BookDto;
import ru.otus.spring.psannikov.spring.security.acl.models.Author;
import ru.otus.spring.psannikov.spring.security.acl.models.Book;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;
import ru.otus.spring.psannikov.spring.security.acl.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.acl.services.BookService;
import ru.otus.spring.psannikov.spring.security.acl.services.GenreService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.spring.psannikov.spring.security.acl.controllers.BookControllerTest.asJsonString;

@DisplayName("Контроллер для Book: ")
@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
public class BookControllerSecurityTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    private final long id = 1L;

    private static Stream<MethodTestData> provideUrlsAndMethods() {
        return Stream.of(
                new MethodTestData("/api/v1/book", GET),
                //TODO пока убрал их
//                new MethodTestData("/api/v1/book/1", POST),
//                new MethodTestData("/api/v1/book", POST),
                new MethodTestData("/api/v1/book/1", DELETE)
        );
    }

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
    @MethodSource("provideUrlsAndMethods")
    public void parametrizedTestWithOutUser(MethodTestData methodTestData) throws Exception {
        String url = methodTestData.getUrl();
        HttpMethod method = methodTestData.getMethod();

        if (method.equals(GET)) {
            mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().is3xxRedirection());
        } else if (method.equals(POST)) {
            mvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().is3xxRedirection());
        } else if (method.equals(DELETE)) {
            mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(status().is3xxRedirection());
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
    }

    @ParameterizedTest(name = "c авторизацией страница {0} должна вернуть страницу {1}")
    @MethodSource("provideUrlsAndMethods")
    public void parametrizedTestWithUser(MethodTestData methodTestData) throws Exception {
        when(bookService.findById(id)).thenReturn(mockBook);
        BookDto expectedResult = BookDto.toDto(mockBook);

        String url = methodTestData.getUrl();
        HttpMethod method = methodTestData.getMethod();

        if (method.equals(GET)) {
            mvc.perform(MockMvcRequestBuilders.get(url).with(user(userDetails)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } else if (method.equals(POST)) {
            mvc.perform(MockMvcRequestBuilders
                            .post(url)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(asJsonString(expectedResult))
                            .with(user(userDetails)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } else if (method.equals(DELETE)) {
            mvc.perform(MockMvcRequestBuilders.delete(url).with(user(userDetails)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
    }

    @AllArgsConstructor
    @Getter
    @ToString(exclude = "method")
    private static class MethodTestData {
        private final String url;
        private final HttpMethod method;
    }
}
